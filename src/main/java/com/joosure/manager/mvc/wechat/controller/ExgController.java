package com.joosure.manager.mvc.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.common.base.entity.QryCondBean;
import com.joosure.manager.mvc.wechat.bean.dto.ExchangeDetailInfo;
import com.joosure.manager.mvc.wechat.buz.WxExgBuz;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.server.mvc.wechat.entity.pojo.Exchange;

@Controller
@RequestMapping("/exg")
public class ExgController {

	@Autowired
	private WxExgBuz wxExgBuz;

	@RequestMapping("/exgManage")
	public String toExgManage() {
		return "exchangeManage";
//		return "exgMain";
	}

	@RequestMapping("/exgList")
	@ResponseBody
	public JsonResultBean<ExchangeDetailInfo> showParamsInfo(QryCondBean qryCond, HttpSession session) {
		List<ExchangeDetailInfo> exgDetailInfo = wxExgBuz.getExgList(qryCond);
		int count = wxExgBuz.getExgListCount(qryCond);
		session.setAttribute("currentPageExgDetailInfo", exgDetailInfo);
		JsonResultBean<ExchangeDetailInfo> ret = new JsonResultBean<ExchangeDetailInfo>(exgDetailInfo, count);
		return ret;
	}

	@RequestMapping("/exgDetail")
	public String toExgDetail(@RequestParam("exchangeId") int exchangeId, HttpServletRequest request) {
		List<ExchangeDetailInfo> exgDetailInfo = (List<ExchangeDetailInfo>) request.getSession().getAttribute("currentPageExgDetailInfo");
		if (exgDetailInfo != null) {
			for (ExchangeDetailInfo exgInfo : exgDetailInfo) {
				if (exgInfo.getExchange().getExchangeId().equals(exchangeId)) {
					request.setAttribute("exgDetail", exgInfo);
					request.getSession().setAttribute("exgDetail", exgInfo);
					break;
				}
			}
		}
		request.setAttribute("exchangeId", exchangeId);
		return "exchangeDetail";
//		return "exgDetailMain";
	}

	/**
	 * 认证成功，交易完成
	 * 
	 * @param qryCond
	 * @param session
	 * @return
	 */
	@RequestMapping("/exgFaceSuccess")
	@ResponseBody
	public JsonResultBean<ExchangeDetailInfo> exgFaceSuccess(HttpSession session) {
		ExchangeDetailInfo exgDetailInfo = (ExchangeDetailInfo) session.getAttribute("exgDetail");
		String retMsg = "";
		if (exgDetailInfo != null) {
			retMsg = wxExgBuz.dealSuccessExg(exgDetailInfo);
		}
		JsonResultBean<ExchangeDetailInfo> ret = new JsonResultBean<ExchangeDetailInfo>();
		ret.setRetMsg(retMsg);
		return ret;
	}

	/**
	 * 认证失败，交易完成
	 * 
	 * @param qryCond
	 * @param session
	 * @return
	 */
	@RequestMapping("/exgFaceFail")
	@ResponseBody
	public JsonResultBean<ExchangeDetailInfo> exgFaceFail(HttpServletRequest request) {
		ExchangeDetailInfo exgDetailInfo = (ExchangeDetailInfo) request.getSession().getAttribute("exgDetail");
		String retMsg = "";
		if (exgDetailInfo != null) {
			String[] infoNotReal = request.getParameterValues("infoNotReal");
			String[] charge = request.getParameterValues("charge");
			String chargeTargetMsg = request.getParameter("chargeMsgTarget");
			String chargeOwnMsg = request.getParameter("chargeMsgOwn");
			retMsg = wxExgBuz.dealFailExg(exgDetailInfo, infoNotReal, charge, chargeTargetMsg, chargeOwnMsg);
		}
		JsonResultBean<ExchangeDetailInfo> ret = new JsonResultBean<ExchangeDetailInfo>();
		ret.setRetMsg(retMsg);
		return ret;
	}

	private Exchange getExgSession(Integer exgId, HttpSession session) {
		ExchangeDetailInfo exgInfo = (ExchangeDetailInfo) session.getAttribute("exgDetail");
		if (exgInfo != null) {
			if (exgId.equals(exgInfo.getExchange().getExchangeId())) {
				return exgInfo.getExchange();
			}
		}
		return null;
	}

}
