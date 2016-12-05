package com.joosure.manager.mvc.wechat.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.constant.StorageConstant;
import com.joosure.server.mvc.wechat.entity.pojo.Activity;
import com.joosure.server.mvc.wechat.entity.pojo.ActivityEnter;
import com.joosure.server.mvc.wechat.service.itf.IActivityService;

/**
 * 活动的控制器
 * @author Ted-wuhuhu
 * @Time 2016年12月2日 上午11:35:43
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	public volatile static int approvalLimitCount = 0;//审核限制名额
	public volatile static int alreadyApprovedCount = 0;//已经通过名额
	public static String Acty_Status = Activity.STATUS_normal;//当前报名活动状态

	private static Logger log = Logger.getLogger(ActivityController.class);

	@Autowired
	private IActivityService activityService;

	/**
	 * 活动管理主页
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午4:19:07
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/actyManage")
	public String toActivityManagePage(HttpServletRequest request,Model model){
		return "activity/activityManage";
	}

	/**
	 * 报名管理主页
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午4:32:54
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/enterManage")
	public String toEnterManagePage(HttpServletRequest request,Model model){
		return "activity/enterManage";
	}

	/**
	 * 活动详情页
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午4:33:12
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/actyDetail")
	public String toActivityDetailPage(HttpServletRequest request,Model model){
		Integer id = 0;
		try {
			id = Integer.parseInt(request.getParameter("actyId"));
		} catch (Exception e) {
			id = 1;
			e.printStackTrace();
		}
		model.addAttribute("actyId",id);
		return "activity/activityDetail";
	}

	/**
	 * 报名详情页
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午4:33:23
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/enterDetail")
	public String toEnterDetailPage(HttpServletRequest request,Model model){
		Integer id = 0;
		try {
			id = Integer.parseInt(request.getParameter("enterId"));
		} catch (Exception e) {
			id = 1;
			e.printStackTrace();
		}
		model.addAttribute("enterId",id);
		return "activity/enterDetail";
	}

	/**
	 * 分页加载活动列表
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午4:40:10
	 * @param request
	 * @param cond
	 * @return
	 */
	@RequestMapping("/getActyDetail")
	@ResponseBody
	public JsonResultBean<Activity> getActyDetail(HttpServletRequest request,Activity cond){
		JsonResultBean<Activity> ret = new JsonResultBean<Activity>();
		List<Activity> acties = activityService.getAllAvailableActy(cond);
		int total = 1;
		ret.setRows(acties);
		ret.setTotal(total);
		return ret;
	}

	/**
	 * 分页加载活动列表
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午4:40:10
	 * @param request
	 * @param cond
	 * @return
	 */
	@RequestMapping("/getEnterDetail")
	@ResponseBody
	public JsonResultBean<ActivityEnter> getEnterDetail(HttpServletRequest request,ActivityEnter cond){
		JsonResultBean<ActivityEnter> ret = new JsonResultBean<ActivityEnter>();
		List<ActivityEnter> acties = activityService.getActyEnterList(cond);
		int total = 1;
		ret.setRows(acties);
		ret.setTotal(total);
		return ret;
	}

	/**
	 * 
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午9:05:47
	 * @param request
	 * @param cond
	 * @return
	 */
	@RequestMapping("/getActyListByPage")
	@ResponseBody
	public JsonResultBean<Activity> getActivityListByPage(HttpServletRequest request,Activity cond){
		JsonResultBean<Activity> ret = new JsonResultBean<Activity>();
		List<Activity> acties = activityService.getAllAvailableActy(cond);
		int total = activityService.getAllAvailableActyCount(cond);
		ret.setRows(acties);
		ret.setTotal(total);
		return ret;
	}

	/**
	 * 分页加载活动报名列表
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午4:40:10
	 * @param request
	 * @param response
	 * @param cond
	 * @return
	 */
	@RequestMapping("/getActyEnterListByPage")
	@ResponseBody
	public JsonResultBean<ActivityEnter> getActyEnterListByPage(HttpServletRequest request,ActivityEnter cond){
		JsonResultBean<ActivityEnter> ret = new JsonResultBean<ActivityEnter>();
		List<ActivityEnter> acties = activityService.getActyEnterList(cond);
		int total = activityService.getActyEnterCount(cond);
		ret.setRows(acties);
		ret.setTotal(total);
		return ret;
	}

	/**
	 * 改变报名活动的状态信息
	 * @author Ted-wuhuhu
	 * @Time 2016年12月2日 上午11:12:25
	 * @param request
	 * @return
	 */
	@RequestMapping("/changeEnterActyStatus")
	@ResponseBody
	public JsonResultBean<String> changeEnterActyStatus(HttpServletRequest request){
		JsonResultBean<String> ret = new JsonResultBean<String>();
		String opType = request.getParameter("opType");
		Integer actyId = 0;
		try {
			actyId = Integer.parseInt(request.getParameter("actyId"));
		} catch (Exception e) {
			ret.setRetCode("1004");
			ret.setRetMsg("不存在该活动");
			return ret;
		}

		if(StringUtils.isBlank(opType)){
			ret.setRetCode("1001");
			ret.setRetMsg("未选择操作类型");
			return ret;
		}
		if(!Activity.STATUS_invisible.equals(opType) && 
				!Activity.STATUS_normal.equals(opType) &&
				!Activity.STATUS_deleted.equals(opType)){
			ret.setRetCode("1002");
			ret.setRetMsg("操作类型错误");
			return ret;
		}
		Activity acty = activityService.getActivityById(actyId);
		if(acty == null){
			ret.setRetCode("1004");
			ret.setRetMsg("不存在该活动");
			return ret;
		}
		if(opType.equals(acty.getStatus())){
			ret.setRetCode("1003");
			ret.setRetMsg("已经处于该状态，无需重复操作");
			return ret;
		}
		acty.setStatus(opType);
		activityService.changeEnterActyInfo(acty);
		ret.setRetCode("0000");
		ret.setRetMsg("操作成功");
		return ret;
	} 

	/**
	 * 保存添加的活动
	 * @author Ted-wuhuhu
	 * @Time 2016年12月2日 上午11:36:54
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/addActivity")
	public String addActivity(@RequestParam("file")CommonsMultipartFile file,HttpServletRequest request){
		if(file == null){
			request.setAttribute("addActivityPrompt", "没有图片，请重新添加");
			return "forward:/activity/actyManage";
		}
		long  startTime=System.currentTimeMillis();
		String title = request.getParameter("title");
		String outSideUrl = request.getParameter("outSideUrl");
		if(StringUtils.isBlank(title) || StringUtils.isBlank(outSideUrl)){
			log.warn("ActivityController--addActivity==> 输入参数错误"+title+" "+outSideUrl+" ");
			request.setAttribute("addActivityPrompt", "输入参数错误，请重新添加");
			long  endTime=System.currentTimeMillis();
			log.info("方法的运行时间："+String.valueOf(endTime-startTime)+"ms");
			return "forward:/activity/actyManage";//异常
		}
		String path=StorageConstant.ROOT_IMG + StorageConstant.ACTY_IMG_FILE_PATH + File.separator;
		File newFile=new File(path + new Date().getTime()+file.getOriginalFilename());
		try {
			if(!new File(path).exists()){
				newFile.mkdirs();
			}
			file.transferTo(newFile);
			SysUser admin = (SysUser) request.getSession().getAttribute(CommonConstant.CurrentSysUser);
			Activity acty = new Activity();
			acty.setBgImgUrl(newFile.getAbsolutePath());
			acty.setTitle(title);
			acty.setOutSideUrl(outSideUrl);
			acty.setAdminId(admin.getId());
			int ret = activityService.saveActivity(acty);
			System.out.println(ret);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			request.setAttribute("addActivityPrompt", "添加活动失败，请稍后再试。");
			return "forward:/activity/actyManage";//异常
		}
		long  endTime=System.currentTimeMillis();
		log.info("方法的运行时间："+String.valueOf(endTime-startTime)+"ms");
		return "forward:/activity/actyManage";
	}
	
	/**
	 * 审核报名状态
	 * @author Ted-wuhuhu
	 * @Time 2016年12月2日 下午4:11:50
	 * @return
	 */
	@RequestMapping("/approvalTheEnter")
	public JsonResultBean<String> approvalTheEnter(HttpServletRequest request){
		JsonResultBean<String> ret = new JsonResultBean<String>();
		String approvalStatus = request.getParameter("opType");
		if(approvalStatus == null || 
				!ActivityEnter.ApprovalStatus_Approved.equals(approvalStatus)
			|| !ActivityEnter.ApprovalStatus_Refused.equals(approvalStatus)){
			ret.setRetCode("1001");
			ret.setRetMsg("请求参数错误: opType.");
			return ret;
		}
		if(approvalLimitCount == alreadyApprovedCount){
			ret.setRetCode("1002");
			ret.setRetMsg("当前审核人数已满，不能通过审核");
			return ret;
		}
		int actyId = 1;
		int oId = 0;
		try {
			actyId = Integer.parseInt(request.getParameter("actyId"));
			oId = Integer.parseInt(request.getParameter("oId"));
			int approvalRet = activityService.approvalTheActy(actyId,oId,approvalStatus);
			if(approvalRet > 0){
				//审核成功
				if(ActivityEnter.ApprovalStatus_Approved.equals(approvalStatus)){
					//通过审核，将缓存值+1
					alreadyApprovedCount = alreadyApprovedCount + 1;
				}
				ret.setRetCode("0000");
				ret.setRetFlag(true);
				ret.setRetMsg("操作成功");
				return ret;
			}
			ret.setRetFlag(false);
			if(approvalRet == -1){
				ret.setRetCode("1003");
				ret.setRetMsg("审核失败： 报名信息不存在");
			}else if(approvalRet == 0){
				ret.setRetCode("1004");
				ret.setRetMsg("审核失败： 状态相同，无需重复操作");
			}
			return ret;
		} catch (Exception e) {
			log.warn("approvalTheEnter--- actyId:" + actyId);
			ret.setRetCode("1001");
			ret.setRetMsg("请求参数错误");
			return ret;
		}
	}

}
