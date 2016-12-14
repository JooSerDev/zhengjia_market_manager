package com.joosure.manager.mvc.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.dto.ItemStatisticsDto;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.service.IStatisticsService;

/**
 * 统计所用的controller
 * @author Ted-wuhuhu
 * @Time 2016年12月5日 下午8:45:02
 *
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

	private static final String UserType_All = "all";
	private static final String UserType_New = "new";

	private static final String SysStatistics_Pv = "pv";
	private static final String SysStatistics_Uv = "uv";

	@Autowired
	private IStatisticsService statisticsService;

	@RequestMapping("/user")
	public String toUserStaticsPage(){
		return "statistics/userStatistics";
	}

	@RequestMapping("/item")
	public String toItemStaticsPage(){
		return "statistics/itemStatistics";
	}

	@RequestMapping("/visit")
	public String toVisitStaticsPage(){
		return "statistics/visitStatistics";
	}

	@RequestMapping("/retention")
	public String toRetentionStaticsPage(){
		return "statistics/retentionStatistics";
	}

	/**
	 * 用户统计
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:34:32
	 * @param request
	 * @return
	 */
	@RequestMapping("/userCount")
	@ResponseBody
	public JsonResultBean<Integer> getUserCountStatisticsData(HttpServletRequest request){
		JsonResultBean<Integer> result = new JsonResultBean<Integer>();
		String type = request.getParameter("UserType");
		if(StringUtils.isBlank(type)){
			return result;
		}
		if(UserType_All.equals(type)){
			List<Integer> allUserCount = statisticsService.getAllUserCount();
			result.setResult(allUserCount);
		}
		if(UserType_New.equals(type)){
			List<Integer> newUserCount = statisticsService.getNewUserCount();
			result.setResult(newUserCount);
		}
		result.setRetMsg("获取数据成功");
		return result;
	}

	/**
	 * 宝贝统计
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:34:32
	 * @param request
	 * @return
	 */
	@RequestMapping("/itemStatistics")
	@ResponseBody
	public JsonResultBean<ItemStatisticsDto> getItemStatisticsData(HttpServletRequest request){
		JsonResultBean<ItemStatisticsDto> result = new JsonResultBean<ItemStatisticsDto>();
		int itemType = 0;
		int day = 1;
		String rankType = "pvRate";
		try {
			itemType = Integer.parseInt(request.getParameter("itemType"));
		} catch (Exception e) {
			itemType = 0;
		}
		try {
			day = Integer.parseInt(request.getParameter("day"));
		} catch (Exception e) {
			day = 1;
		}
		rankType = request.getParameter("rankType");
		if(StringUtils.isBlank(rankType)){
			rankType = "pvRate";
		}
		//统计宝贝
		List<ItemStatisticsDto> data = statisticsService.statisItemData(itemType,day,rankType);
		int count = 0;
		if(data != null){
			count = data.size();
		}
		result.setRows(data);
		result.setTotal(count);
		return result;
	}

	/**
	 * 用户统计
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:34:32
	 * @param request
	 * @return
	 */
	@RequestMapping("/visitStatistics")
	@ResponseBody
	public JsonResultBean<Integer> getVisitStatisticsData(HttpServletRequest request){
		JsonResultBean<Integer> result = new JsonResultBean<Integer>();
		String type = request.getParameter("type");
		if(StringUtils.isBlank(type)){
			return result;
		}
		if(SysStatistics_Pv.equals(type)){
			List<Integer> sysPvCount = statisticsService.getSysPvCount();
			result.setResult(sysPvCount);
		}
		if(SysStatistics_Uv.equals(type)){
			List<Integer> sysUvCount = statisticsService.getSysUvCount();
			result.setResult(sysUvCount);
		}
		result.setRetMsg("获取数据成功");
		return result;
	}

	/**
	 * 用户统计
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:34:32
	 * @param request
	 * @return
	 */
	@RequestMapping("/retentionStatistics")
	@ResponseBody
	public JsonResultBean<String> getRetentionStatisticsData(HttpServletRequest request){
		JsonResultBean<String> result = new JsonResultBean<String>();
		int days = 1;
		try{
			days = Integer.parseInt(request.getParameter("days"));
		} catch(NumberFormatException e){
			days = 1;
		}
		List<Integer> newCount = statisticsService.getNewCount(days);
		List<Integer> retentionCount = statisticsService.getRetentionCount(days);
		result.setRetMsg("获取数据成功");
		return result;
	}

}
