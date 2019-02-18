package com.xlyd.springboot.app_platform.control.backend;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/backend/app")
public class AppInfoListController {
    private static int ROW = 5;

    @Resource
    private AppInfoService appInfoService;

    @Resource
    private IDataDictionaryService IDataDictionaryService;

    @Resource
    private IAppCategoryService iAppCategoryService;

    @Resource
    private IAppVersionService iAppVersionService;

    @RequestMapping(value = "/list")
    public String appList(Integer pageIndex,
                          String querySoftwareName,
                          Integer queryFlatformId,
                          Integer queryCategoryLevel1,
                          Integer queryCategoryLevel2,
                          Integer queryCategoryLevel3,
                          Model model) {

        if (querySoftwareName != null) {
            model.addAttribute("querySoftwareName", querySoftwareName);
        }
        if (queryFlatformId != null) {
            List<DataDictionary> flatFormList =
                    IDataDictionaryService.findListByTypeCode("APP_FLATFORM");
            model.addAttribute("flatFormList", flatFormList);
            model.addAttribute("queryFlatformId", queryFlatformId);
        }
        if (queryCategoryLevel1 != null) {
            List<AppCategory> categoryLevel1List = iAppCategoryService.findLevel1();
            model.addAttribute("categoryLevel1List", categoryLevel1List);
            model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        }
        if (queryCategoryLevel2 != null) {
            List<AppCategory> categoryLevel2List =
                    iAppCategoryService.findLevel23(queryCategoryLevel1);
            model.addAttribute("categoryLevel2List", categoryLevel2List);
            model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        }
        if (queryCategoryLevel3 != null) {
            List<AppCategory> categoryLevel3List =
                    iAppCategoryService.findLevel23(queryCategoryLevel2);
            model.addAttribute("categoryLevel3List", categoryLevel3List);
            model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        }

        // 将查询参数封装为Map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 1);
        map.put("softwareName", querySoftwareName);
        map.put("flatformId", queryFlatformId);
        map.put("categoryLevel1", queryCategoryLevel1);
        map.put("categoryLevel2", queryCategoryLevel2);
        map.put("categoryLevel3", queryCategoryLevel3);

        // 设置当前页页码
        if (pageIndex == null) {
            pageIndex = 1;
        }
        // 分页查询
        PageHelper.startPage(pageIndex, Constants.pageSize);
        List<AppInfo> appInfoList = appInfoService.findAll(map);
        PageInfo<AppInfo> pageInfo = new PageInfo<>(appInfoList);

        // 模板展示属性
        model.addAttribute("pageInfo", pageInfo);

        List<DataDictionary> flatFormList =
                IDataDictionaryService.findListByTypeCode("APP_FLATFORM");
        model.addAttribute("flatFormList", flatFormList);

        List<AppCategory> categoryLevel1List =
                iAppCategoryService.findLevel1();
        model.addAttribute("categoryLevel1List", categoryLevel1List);

        return "backend/applist";
    }

    // Ajax实现二级，三级分类载入
    @RequestMapping(value = "/categorylevellist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object categorylevellist(Integer pid) {

        List<AppCategory> categoryLevelList =
                iAppCategoryService.findLevel23(pid);

        String cjson = JSONArray.toJSONString(categoryLevelList);
        return cjson;
    }

    // Ajax查询“所属平台”列表
	/*@RequestMapping(value = "/datadictionarylist.json", method = RequestMethod.GET)
	@ResponseBody
	public Object flatFormlist(String tcode) {
		List<DataDictionary> flatFormList = 
				IDataDictionaryService.findListByTypeCode(tcode);
				
		String cjson = JSONArray.toJSONString(flatFormList);
		return cjson;
	}*/

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String checkAppinfo(Integer aid, Integer vid, Model model) {

        AppInfo appInfo = appInfoService.findById(aid);
        AppVersion appVersion = iAppVersionService.findById(vid);

        model.addAttribute("appInfo", appInfo);
        model.addAttribute("appVersion", appVersion);

        return "backend/appcheck";
    }

    @RequestMapping(value = "/checksave", method = RequestMethod.POST)
    public String updateApp(Integer id, Integer status, HttpSession session) {
        BackendUser backendUser =
                (BackendUser) session.getAttribute(Constants.BACKEND_USER_SESSION);

        appInfoService.updateStatus(backendUser.getId(), new Date(), id, status);
        return "redirect:list";
    }

}









