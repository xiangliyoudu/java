package cn.control.backend;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tool.Constants;
import tool.PageSupport;
import cn.pojo.AppCategory;
import cn.pojo.AppInfo;
import cn.pojo.AppVersion;
import cn.pojo.BackendUser;
import cn.pojo.DataDictionary;
import cn.service.backend.AppInfoService;
import cn.service.backend.IAppCategoryService;
import cn.service.backend.IAppVersionService;
import cn.service.backend.IDataDictionaryService;

import com.alibaba.fastjson.JSONArray;

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
	public ModelAndView appList(Integer pageIndex, 
								String querySoftwareName,
								Integer queryFlatformId,
								Integer queryCategoryLevel1,
								Integer queryCategoryLevel2,
								Integer queryCategoryLevel3) {
		ModelAndView mav = new ModelAndView();
				
		if (querySoftwareName != null) {
			mav.addObject("querySoftwareName", querySoftwareName);
		}
		if (queryFlatformId != null) {
			List<DataDictionary> flatFormList = 
					IDataDictionaryService.findListByTypeCode("APP_FLATFORM");
			mav.addObject("flatFormList", flatFormList);
			mav.addObject("queryFlatformId", queryFlatformId);
		}
		if (queryCategoryLevel1 != null) {
			List<AppCategory> categoryLevel1List = 
					iAppCategoryService.findLevel1();
			mav.addObject("categoryLevel1List", categoryLevel1List);
			mav.addObject("queryCategoryLevel1", queryCategoryLevel1);
		}
		if (queryCategoryLevel2 != null) {
			List<AppCategory> categoryLevel2List = 
					iAppCategoryService.findLevel23(queryCategoryLevel1);
			mav.addObject("categoryLevel2List", categoryLevel2List);
			mav.addObject("queryCategoryLevel2", queryCategoryLevel2);
		}
		if (queryCategoryLevel3 != null) {
			List<AppCategory> categoryLevel3List = 
					iAppCategoryService.findLevel23(queryCategoryLevel2);
			mav.addObject("categoryLevel3List", categoryLevel3List);
			mav.addObject("queryCategoryLevel3", queryCategoryLevel3);
		}
		
		PageSupport pages = new PageSupport();
		// 设置当前页页码
		if (pageIndex != null) {
			pages.setCurrentPageNo(pageIndex);
		}
		// 设置每页显示的记录数
		pages.setPageSize(ROW);
		
		//将查询参数封装为Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("softwareName", querySoftwareName);
		map.put("flatformId", queryFlatformId);
		map.put("categoryLevel1", queryCategoryLevel1);
		map.put("categoryLevel2", queryCategoryLevel2);
		map.put("categoryLevel3", queryCategoryLevel3);
		// 设置总记录数
		int count = appInfoService.appCount(map);

		pages.setTotalCount(count);
		// pages.setTotalPageCountByRs();
		// 计算查询初始位置
		int start = (pages.getCurrentPageNo() - 1) * pages.getPageSize();
		// 添加分页查询参数
		map.put("start", start);
		map.put("size", pages.getPageSize());
		
		List<AppInfo> appInfoList = appInfoService.findAll(map);
		//查询记录
		mav.addObject("pages", pages);
		mav.addObject("appInfoList", appInfoList);
		
		List<DataDictionary> flatFormList = 
				IDataDictionaryService.findListByTypeCode("APP_FLATFORM");
		mav.addObject("flatFormList", flatFormList);
		
		List<AppCategory> categoryLevel1List = 
				iAppCategoryService.findLevel1();
		mav.addObject("categoryLevel1List", categoryLevel1List);
		
		mav.setViewName("backend/applist");
		return mav;
	}

	// Ajax实现二级，三级分类载入
	@RequestMapping(value = "/categorylevellist.json", 
					method = RequestMethod.GET)
	@ResponseBody
	public Object categorylevellist(Integer pid) {
		
		List<AppCategory> categoryLevelList = 
				iAppCategoryService.findLevel23(pid);
				
		String cjson = JSONArray.toJSONString(categoryLevelList);
		return cjson;
	}
	
	// Ajax查询“所属平台”列表
	/*@RequestMapping(value = "/datadictionarylist.json", 
					method = RequestMethod.GET)
	@ResponseBody
	public Object flatFormlist(String tcode) {
		List<DataDictionary> flatFormList = 
				IDataDictionaryService.findListByTypeCode(tcode);
				
		String cjson = JSONArray.toJSONString(flatFormList);
		return cjson;
	}*/
	
	@RequestMapping(value="/check", method=RequestMethod.GET)
	public ModelAndView checkAppinfo(Integer aid, Integer vid) {
		
		AppInfo appInfo = appInfoService.findById(aid);
		AppVersion appVersion = 
				iAppVersionService.findById(vid);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("appInfo", appInfo);
		mav.addObject("appVersion", appVersion);
	
		mav.setViewName("backend/appcheck");
		return mav;
	}

	@RequestMapping(value="/checksave", method=RequestMethod.POST)
	public String updateApp(Integer id, Integer status, HttpSession session) {		
		BackendUser backendUser = (BackendUser) session
				.getAttribute(Constants.USER_SESSION);
		
		appInfoService.updateStatus(backendUser.getId(),new Date(),id, status);
		return "redirect:list";
	}

}









