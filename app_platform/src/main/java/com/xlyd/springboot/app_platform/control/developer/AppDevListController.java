package com.xlyd.springboot.app_platform.control.developer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xlyd.springboot.app_platform.entity.*;
import com.xlyd.springboot.app_platform.service.backend.AppInfoService;
import com.xlyd.springboot.app_platform.service.backend.IAppCategoryService;
import com.xlyd.springboot.app_platform.service.backend.IAppVersionService;
import com.xlyd.springboot.app_platform.service.backend.IDataDictionaryService;
import com.xlyd.springboot.app_platform.tool.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dev/flatform/app")
public class AppDevListController {

    @Resource
    private AppInfoService appInfoService;

    @Resource
    private IDataDictionaryService iDataDictionaryService;

    @Resource
    private IAppCategoryService iAppCategoryService;

    @Resource
    private IAppVersionService iAppVersionService;

    @RequestMapping(value = "/list")
    public String appList(Integer pageIndex,
                          String querySoftwareName,
                          Integer queryStatus,
                          Integer queryFlatformId,
                          Integer queryCategoryLevel1,
                          Integer queryCategoryLevel2,
                          Integer queryCategoryLevel3,
                          Model model) {

        if (querySoftwareName != null) {
            model.addAttribute("querySoftwareName", querySoftwareName);
        }
        if (queryStatus != null) {
            List<DataDictionary> statusList =
                    iDataDictionaryService.findListByTypeCode("APP_STATUS");
            model.addAttribute("statusList", statusList);
            model.addAttribute("queryStatus", queryStatus);
        }
        if (queryFlatformId != null) {
            List<DataDictionary> flatFormList =
                    iDataDictionaryService.findListByTypeCode("APP_FLATFORM");
            model.addAttribute("flatFormList", flatFormList);
            model.addAttribute("queryFlatformId", queryFlatformId);
        }
        if (queryCategoryLevel1 != null) {
            List<AppCategory> categoryLevel1List =
                    iAppCategoryService.findLevel1();
            model.addAttribute("categoryLevel1List", categoryLevel1List);
            model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        }
        if (queryCategoryLevel2 != null) {
            List<AppCategory> categoryLevel2List = iAppCategoryService
                    .findLevel23(queryCategoryLevel1);
            model.addAttribute("categoryLevel2List", categoryLevel2List);
            model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        }
        if (queryCategoryLevel3 != null) {
            List<AppCategory> categoryLevel3List = iAppCategoryService
                    .findLevel23(queryCategoryLevel2);
            model.addAttribute("categoryLevel3List", categoryLevel3List);
            model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        }

        // 将查询参数封装为Map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("softwareName", querySoftwareName);
        map.put("status", queryStatus);
        map.put("flatformId", queryFlatformId);
        map.put("categoryLevel1", queryCategoryLevel1);
        map.put("categoryLevel2", queryCategoryLevel2);
        map.put("categoryLevel3", queryCategoryLevel3);

        // 设置当前页页码
        if (pageIndex == null) {
            pageIndex = 0;
        }

        // 查询记录
        PageHelper.startPage(pageIndex, Constants.PAGE_SIZE);
        List<AppInfo> appInfoList = appInfoService.findAll(map);
        PageInfo<AppInfo> pageInfo = new PageInfo<>(appInfoList);

        model.addAttribute("pageInfo", pageInfo);

        // 查询App状态列表
        List<DataDictionary> statusList =
                iDataDictionaryService.findListByTypeCode("APP_STATUS");
        model.addAttribute("statusList", statusList);

        // 查询平台列表
        List<DataDictionary> flatFormList =
                iDataDictionaryService.findListByTypeCode("APP_FLATFORM");
        model.addAttribute("flatFormList", flatFormList);

        // 查询一级分类列表
        List<AppCategory> categoryLevel1List = iAppCategoryService.findLevel1();
        model.addAttribute("categoryLevel1List", categoryLevel1List);

        return "developer/appinfolist";
    }

    @RequestMapping(value = "/appinfoadd", method = RequestMethod.GET)
    public String newApp() {
        String mav = "developer/appinfoadd";
        return mav;
    }

    // Ajax实现一级，二级，三级分类载入
    @RequestMapping(value = "/categorylevellist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object categorylevellist(Integer pid) {
        List<AppCategory> categoryLevelList = null;

        if (pid == null) {
            categoryLevelList = iAppCategoryService.findLevel1();
        } else {
            categoryLevelList = iAppCategoryService.findLevel23(pid);
        }

        String cjson = JSONArray.toJSONString(categoryLevelList);
        return cjson;
    }

    // Ajax查询“所属平台”列表
    @RequestMapping(value = "/datadictionarylist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object flatFormlist(String tcode) {
        List<DataDictionary>
                flatFormList = iDataDictionaryService.findListByTypeCode(tcode);
        String cjson = JSONArray.toJSONString(flatFormList);
        return cjson;
    }

    // Ajax验证APK名称的有效性
    @RequestMapping(value = "apkexist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object apkNameCheck(String APKName) {
        String result = null;

        if (APKName != null && !APKName.equals("")) {
            AppInfo appInfo = appInfoService.findByAPKName(APKName);

            if (appInfo != null) {
                result = "exist";
            } else {
                result = "noexist";
            }
        } else {
            result = "empty";
        }
        String cjson = JSON.toJSONString(result);
        return cjson;
    }

    // 新增appInfo，及文件上传
    @RequestMapping(value = "/appinfoaddsave", method = RequestMethod.POST)
    public String saveApp(AppInfo appInfo,
                          HttpSession session,
                          HttpServletRequest request,
                          @RequestParam(value = "a_logoPicPath", required = true) MultipartFile attach,
                          Model model) {

        String logoPicPath = request.getContextPath() + "/static/images/";
        String logoLocPath = Constants.FILEUPLOAD_PATH_PIC;

        if (!attach.isEmpty()) {
            // 获取文件名称
            String originalFilename = attach.getOriginalFilename();

            // 获取文件类型
            String contentType = attach.getContentType();

            // 获取原文件后缀
            String surfFix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            // 上传文件最大值
            int fileSize = Constants.FILE_SIZE;
            // 判断文件大小是否满足要求
            if (attach.getSize() > fileSize) {
                model.addAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
                return "developer/appinfoadd";
                // 判断文件格式是否正确
            } else if (Constants.checkPicType(surfFix)) {
                // 指定目标文件路径
                logoLocPath += originalFilename;
                File targetFile = new File(logoLocPath);

                try {
                    // 上传文件
                    attach.transferTo(targetFile);
                    // 完善图片文件的URL路径
                    logoPicPath += originalFilename;
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
                    return "developer/appinfoadd";
                }
            } else {
                model.addAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_3);
                return "developer/appinfoadd";
            }
        } else {
            model.addAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_5);
            return "developer/appinfoadd";
        }

        // 从Session中获取devUser
        DevUser devUser = (DevUser) session.getAttribute(Constants.DEV_USER_SESSION);

        // 设置appInfo的devId
        appInfo.setDevId(devUser.getId());

        // 设置appInfo的createdBy
        appInfo.setCreatedBy(devUser.getId());

        // 设置appInfo的logoPicPath
        appInfo.setLogoPicPath(logoPicPath);

        // 设置appInfo的logoLocPath
        appInfo.setLogoLocPath(logoLocPath);

        // 设置appInfo的creationDate
        appInfo.setCreationDate(new Date());

        // 添加appInfo
        int i = appInfoService.addAppInfo(appInfo);

        if (i != -1) {
            return "redirect:/dev/flatform/app/list";
        } else {
            model.addAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
            return "developer/appinfoadd";
        }
    }

    // 查询appversion列表
    @RequestMapping(value = "/appversionadd")
    public ModelAndView appVersionAdd(Integer id) {

        List<AppVersion> appVersionList = iAppVersionService
                .findByAppInfoId(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("appId", id);
        mav.addObject("appVersionList", appVersionList);
        mav.setViewName("developer/appversionadd");
        return mav;
    }

    // 新增appversion
    @RequestMapping(value = "/addversionsave", method = RequestMethod.POST)
    public ModelAndView addVersionSave(
            AppVersion appVersion,
            HttpSession session,
            HttpServletRequest request,
            @RequestParam(value = "a_downloadLink", required = true) MultipartFile attach) {

        ModelAndView mav = new ModelAndView();

        String downloadLink = "";
        String apkLocPath = "";
        String apkFileName = "";

        if (!attach.isEmpty()) {
            // 定义文件存放路径/appInfo-ssm/statics/uploadfiles
            String absPath = session.getServletContext().getRealPath(
                    "statics" + File.separator + "uploadfiles");
            // 获取绝对路径前缀 D:/t86/
            String prePath = absPath.substring(0, absPath.indexOf(".metadata"));
            // 工程所在路径 /appInfo-ssm
            String relativePath = session.getServletContext().getContextPath();
            String path = relativePath + File.separator + "statics"
                    + File.separator + "uploadfiles";

            // 获取old文件名称
            String oldName = attach.getOriginalFilename();

            // 获取原文件前缀
            String prefix = oldName.substring(0, oldName.lastIndexOf("."));
            // 获取原文件后缀
            String surffix = oldName.substring(oldName.lastIndexOf("."));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            // apkfilename
            apkFileName = prefix + appVersion.getVersionNo()
                    + sdf.format(new Date()) + surffix;

            // 上传文件最大值
            int fileSize = 5000000;
            // 判断文件大小是否满足要求
            if (attach.getSize() > fileSize) {

                mav.addObject("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
                mav.setViewName("developer/appversionadd");
                return mav;
            } else {

                // 设置apkLocPath
                apkLocPath = prePath
                        + "appInfo-ssm/WebContent/statics/uploadfiles/";
                // 指定目标文件路径
                File targetFile = new File(prePath
                        + "appInfo-ssm/WebContent/statics/uploadfiles/"
                        + apkFileName);
                try {
                    // 上传文件
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    mav.addObject("fileUploadError",
                            Constants.FILEUPLOAD_ERROR_2);
                    mav.setViewName("developer/appversionadd");
                    return mav;
                }
                // Mysql中logoPicPath字段
                // /appInfo-ssm/statics/uploadfiles/com.speedsoftware.rootexplorer.jpg
                downloadLink = path + File.separator + apkFileName;

            }
        }
        // 从Session中获取devUser
        DevUser devUser = (DevUser) session
                .getAttribute(Constants.DEV_USER_SESSION);
        Date date = new Date();
        appVersion.setCreatedBy(devUser.getId());
        appVersion.setCreationDate(date);
        appVersion.setDownloadLink(downloadLink);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setApkFileName(apkFileName);

        iAppVersionService.save(appVersion);
        // System.out.println(appVersion.getId());
        appInfoService.updateVersionId(devUser.getId(), date,
                appVersion.getAppId(), appVersion.getId());

        mav.setViewName("redirect:list");
        return mav;
    }

    // 修改appversion
    @RequestMapping("/appversionmodify")
    public ModelAndView updateVersion(Integer vid, Integer aid) {

        ModelAndView mav = new ModelAndView();

        List<AppVersion> appVersionList = iAppVersionService
                .findByAppInfoId(aid);

        AppVersion appVersion = iAppVersionService.findById(vid);

        mav.addObject("appVersion", appVersion);
        mav.addObject("appVersionList", appVersionList);

        mav.setViewName("developer/appversionmodify");
        return mav;
    }

    // 保存修改之后appversion
    @RequestMapping("/appversionmodifysave")
    public ModelAndView updateVersionSave(
            AppVersion appVersion,
            HttpSession session,
            HttpServletRequest request,
            @RequestParam(value = "attach", required = true) MultipartFile attach) {

        ModelAndView mav = new ModelAndView();

        if (!attach.isEmpty()) {

            // 工程所在路径 /appInfo-ssm
            String relativePath = session.getServletContext().getContextPath();
            String path = relativePath + "/statics" + "/uploadfiles";
            // 获取old文件名称
            String oldName = attach.getOriginalFilename();

            // 获取原文件前缀
            String prefix = oldName.substring(0, oldName.lastIndexOf("."));
            // 获取原文件后缀
            String surffix = oldName.substring(oldName.lastIndexOf("."));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            // apkfilename
            String apkFileName = prefix + appVersion.getVersionNo()
                    + sdf.format(new Date()) + surffix;
            appVersion.setApkFileName(apkFileName);

            String downloadLink = path + "/" + apkFileName;
            appVersion.setDownloadLink(downloadLink);

            // 上传文件最大值
            int fileSize = 5000000;
            // 判断文件大小是否满足要求
            if (attach.getSize() > fileSize) {

                mav.addObject("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
                mav.setViewName("developer/appversionadd");
                return mav;
            } else {

                // 设置apkLocPath
                String apkLocPath = appVersion.getApkLocPath();

                // 指定目标文件路径
                File targetFile = new File(apkLocPath + apkFileName);

                try {
                    // 上传文件
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    mav.addObject("fileUploadError",
                            Constants.FILEUPLOAD_ERROR_2);
                    mav.setViewName("developer/appversionadd");
                    return mav;
                }

            }
        }
        // 从Session中获取devUser
        DevUser devUser = (DevUser) session
                .getAttribute(Constants.DEV_USER_SESSION);
        appVersion.setModifyBy(devUser.getId());
        appVersion.setModifyDate(new Date());

        iAppVersionService.update(appVersion);

        mav.setViewName("redirect:list");
        return mav;
    }

    // Ajax 删除apk文件
    @RequestMapping(value = "/delfile.json", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteAPKFileAndLogo(Integer id, String flag, HttpSession session) {
        Boolean isEmpty = false;
        String result = "";
        File oldFile = null;

        DevUser devUser = (DevUser) session
                .getAttribute(Constants.DEV_USER_SESSION);

        if (flag.equals("apk")) {
            AppVersion appVersion = iAppVersionService.findById(id);
            oldFile = new File(appVersion.getApkLocPath()
                    + appVersion.getApkFileName());
            oldFile.delete();
            isEmpty = iAppVersionService.emptyAPKFileName(devUser.getId(),
                    new Date(), id);
        }

        if (flag.equals("logo")) {
            AppInfo appInfo = appInfoService.findById(id);
            oldFile = new File(appInfo.getLogoLocPath()
                    + appInfo.getLogoPicPath().substring(
                    appInfo.getLogoPicPath().lastIndexOf("/") + 1));

            oldFile.delete();
            isEmpty = appInfoService.emptyLogoPicPath(devUser.getId(),
                    new Date(), id);
        }

        if (isEmpty) {
            result = "success";
        } else {
            result = "failed";
        }
        String json = JSON.toJSONString(result);

        return json;
    }

    @RequestMapping("/appinfomodify")
    public ModelAndView modifyAppinfo(Integer id) {
        ModelAndView mav = new ModelAndView();
        AppInfo appInfo = appInfoService.findById(id);
        mav.addObject("appInfo", appInfo);
        mav.setViewName("developer/appinfomodify");
        return mav;
    }

    // 保存修改之后appversion
    @RequestMapping("/appinfomodifysave")
    public ModelAndView modifyAppInfo(
            AppInfo appInfo,
            HttpSession session,
            @RequestParam(value = "attach", required = true) MultipartFile attach) {

        ModelAndView mav = new ModelAndView();

        if (!attach.isEmpty()) {

            // 工程所在路径 /appInfo-ssm
            String relativePath = session.getServletContext().getContextPath();
            String path = relativePath + "/statics" + "/uploadfiles";
            // 获取old文件名称
            String oldName = attach.getOriginalFilename();

            // 获取原文件前缀
            String prefix = oldName.substring(0, oldName.lastIndexOf("."));
            // 获取原文件后缀
            String surffix = oldName.substring(oldName.lastIndexOf("."));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            // new filename
            String fileName = prefix + sdf.format(new Date()) + surffix;
            appInfo.setLogoPicPath(path + "/" + fileName);

            // 上传文件最大值
            int fileSize = 5 * 1024000;
            // 判断文件大小是否满足要求
            if (attach.getSize() > fileSize) {
                mav.addObject("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
                mav.setViewName("developer/appversionadd");
                return mav;
            } else {

                // 设置logoLocPath
                String logoLocPath = appInfo.getLogoLocPath();

                // 指定目标文件路径
                File targetFile = new File(logoLocPath + fileName);

                try {
                    // 上传文件
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    mav.addObject("fileUploadError",
                            Constants.FILEUPLOAD_ERROR_2);
                    mav.setViewName("developer/appversionadd");
                    return mav;
                }

            }
        }
        // 从Session中获取devUser
        DevUser devUser = (DevUser) session
                .getAttribute(Constants.DEV_USER_SESSION);
        appInfo.setModifyBy(devUser.getId());
        appInfo.setModifyDate(new Date());

        appInfoService.update(appInfo);

        mav.setViewName("redirect:list");
        return mav;
    }

    // 查看appin信息
    @RequestMapping("/appview/{aid}")
    public ModelAndView showAppInfo(@PathVariable Integer aid) {
        ModelAndView mav = new ModelAndView();
        // find the appInfo
        AppInfo appInfo = appInfoService.findById(aid);

        // get the appVersion list
        List<AppVersion> appVersionList = iAppVersionService
                .findByAppInfoId(aid);

        mav.addObject("appInfo", appInfo);
        mav.addObject("appVersionList", appVersionList);
        mav.setViewName("developer/appinfoview");
        return mav;
    }

    // Ajax实现上架、下架
    @RequestMapping(value = "/{appId}/sale.json/{saleSwitch}", method = RequestMethod.PUT)
    @ResponseBody
    public Object changeSaleStatus(@PathVariable Integer appId,
                                   @PathVariable String saleSwitch,
                                   HttpSession session) {

        String result = null;
        if (saleSwitch != null) {
            Integer status = 0;
            if (saleSwitch.equals("open")) {
                status = 4;
            } else if (saleSwitch.equals("close")) {
                status = 5;
            }

            DevUser devUser = (DevUser) session.getAttribute(Constants.DEV_USER_SESSION);

            int i = appInfoService.updateStatus(devUser.getId(), new Date(),
                    appId, status);
            if (i > 0) {
                result = "success";
            } else {
                result = "failed";
            }
        } else {
            result = "failed";
        }

        String json = JSON.toJSONString(result);
        return json;
    }

    // 删除appinfo及其相关的appversion记录
    @RequestMapping(value = "/delapp.json", method = RequestMethod.GET)
    @ResponseBody
    public Object delAppInfo(Integer id) {

        String result = "";

        if (id != null) {
            // 查找appversion列表
            int i = 0;
            List<AppVersion> appVersionList = iAppVersionService
                    .findByAppInfoId(id);
            for (int n = 0; n < appVersionList.size(); n++) {
                // 移除apk
                File oldFile = new File(appVersionList.get(n).getApkLocPath()
                        + appVersionList.get(n).getApkFileName());

                if (oldFile.exists()) {
                    oldFile.delete();
                }
                // 删除appversion记录
                i = iAppVersionService
                        .deleteById(appVersionList.get(n).getId());
            }

            // 移除appInfo的logo在文件夹中的图片
            AppInfo appInfo = appInfoService.findById(id);
            File oldFile = new File(appInfo.getLogoLocPath()
                    + appInfo.getLogoPicPath().substring(
                    appInfo.getLogoPicPath().lastIndexOf("/") + 1));

            if (oldFile.exists()) {
                oldFile.delete();
            }
            // delete appInfo
            int j = appInfoService.deleteById(id);

            if (i > 0 && j > 0) {
                result = "true";
            } else {
                result = "false";
            }
        } else {
            result = "notexist";
        }

        String json = JSON.toJSONString(result);
        return json;
    }

}
