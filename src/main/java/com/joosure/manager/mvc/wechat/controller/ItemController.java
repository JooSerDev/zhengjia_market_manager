package com.joosure.manager.mvc.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.dto.WxItemDetail;
import com.joosure.manager.mvc.wechat.buz.WxItemBuz;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.service.WxItemService;
import com.joosure.manager.mvc.wechat.service.db.IWxItemDbService;
import com.joosure.server.mvc.wechat.dao.cache.ItemCache;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.ItemType;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private WxItemService wxItemService;
	@Autowired
	private IWxItemDbService wxItemDbService;

	@Autowired
	private WxItemBuz wxItemBuz;

	@RequestMapping("/itemManage")
	public String toItemManage() {
		return "itemManage";
	}

	@RequestMapping("/itemList")
	@ResponseBody
	public JsonResultBean<WxItemDetail> getItemList(QryCondBean qryCondBean, HttpServletRequest request) {
		if ("wait".equals(qryCondBean.getItemFilter())) {// 审核状态
			qryCondBean.setApprovalStatus(qryCondBean.getItemFilter());
		} else if ("yes".equals(qryCondBean.getItemFilter())) {
			qryCondBean.setApprovalStatus(qryCondBean.getItemFilter());
		}  else if ("no".equals(qryCondBean.getItemFilter())) {
			qryCondBean.setApprovalStatus(qryCondBean.getItemFilter());
		} else if ("complaint".equals(qryCondBean.getItemFilter())) {
			qryCondBean.setIsCpt(1);// 被举报
		} else if ("recommended".equals(qryCondBean.getItemFilter())) {
			qryCondBean.setIsRecommended(1);// 被推荐
		} else if("all".equals(qryCondBean.getItemFilter())){
			
		}
		List<WxItemDetail> datas = wxItemBuz.getDetailItemList(qryCondBean);
		int count = wxItemBuz.getItemsCount(qryCondBean);
		JsonResultBean<WxItemDetail> ret = new JsonResultBean<WxItemDetail>(datas, count);
		request.getSession().setAttribute("qryCondBean", qryCondBean);
		return ret;
	}

	@RequestMapping("/itemDetail")
	public String toItemDetail(@RequestParam("itemId") String itemId, HttpServletRequest request) {
		request.setAttribute("itemId", itemId);
		return "itemDetail";
	}

	@RequestMapping("/itemDetailInfo")
	@ResponseBody
	public JsonResultBean<WxItemDetail> getItemDetail(@RequestParam("itemId") int itemId, HttpSession session) {
		QryCondBean qryCondBean = new QryCondBean();
		qryCondBean.setItemId(itemId);
		List<WxItemDetail> retData = wxItemBuz.getDetailItemList(qryCondBean);
		if(retData!=null && retData.size()>0){
			session.setAttribute("itemDetailInfo", retData.get(0));
		}else{
			session.setAttribute("itemDetailInfo", null);
		}
		JsonResultBean<WxItemDetail> ret = new JsonResultBean<WxItemDetail>(retData, 1);
		return ret;
	}

	@RequestMapping("/itemTypes")
	@ResponseBody
	public JsonResultBean<ItemType> getItemTypes() {
		List<ItemType> itemTypes = ItemCache.getItemTypes();
		// TODO 当缓存中获取不到物品类型信息，则直接到数据库总获取
		// List<ItemType> itemTypes = wxItemService.getItemTypes();
		JsonResultBean<ItemType> ret = new JsonResultBean<ItemType>();
		ret.setResultList(itemTypes);
		return ret;
	}

	@RequestMapping("/backToItem")
	public String backToList(HttpSession session) {
		QryCondBean qryCondBean = (QryCondBean) session.getAttribute("qryCondBean");

		return "itemManage";
	}

	/**
	 * 审核 物品
	 * 
	 * @return
	 */
	@RequestMapping("/approvalItem")
	@ResponseBody
	public JsonResultBean<String> approvalItem(Item item, HttpSession session) {
		Item temp = getItemSession(item, session);
		if(temp!=null){
			item.setOwnerId(temp.getOwnerId());
		}
		int count = wxItemService.approvalItem(item);
		JsonResultBean<String> ret = new JsonResultBean<String>();
		if (count > 0) {
			ret.setRetMsg("审核操作成功");
		}else if(count == 0){
			ret.setRetMsg("该物品已被审核完成,无需重复操作");
		}
		if (null == item) {
			return ret;
		}
		// 将session中的宝贝信息进行更新
		if (temp != null) {
			temp.setIsRecommended(item.getIsRecommended());
			temp.setApprovalStatus(item.getApprovalStatus());
			temp.setApprovalMsg(item.getApprovalMsg());
		}
		return ret;
	}

	/**
	 * 强制下架物品
	 * 
	 * @return
	 */
	@RequestMapping("/forceToDowm")
	@ResponseBody
	public JsonResultBean<ItemType> forceToDown(Item item, HttpSession session) {
		int count = wxItemService.forceToDowm(item);
		JsonResultBean<ItemType> ret = new JsonResultBean<ItemType>();
		if (count > 0) {
			ret.setRetMsg("宝贝下架成功");
		}else if(count == 0){
			ret.setRetMsg("宝贝已被下架，无需重复操作");
		}else{
			ret.setRetMsg("宝贝下架失败，请联系维护人员检查相关数据");
		}
		if (null == item) {
			return ret;
		}
		// 将session中的宝贝信息进行更新
		Item temp = getItemSession(item, session);
		if (temp != null) {
			temp.setStatus(item.getStatus());
		}
		return ret;
	}

	private Item getItemSession(Item item, HttpSession session) {
		WxItemDetail itemDetail = (WxItemDetail) session.getAttribute("itemDetailInfo");
		if (null == item) {
			return null;
		}
		// 将session中的宝贝信息进行更新
		if (itemDetail != null) {
			if (null != itemDetail.getItem()) {
				if (item.getItemId().equals(itemDetail.getItem().getItemId())) {
					return itemDetail.getItem();
				}
			}
		}
		return null;
	}
	
	@RequestMapping("/showItemInfo")
	public String toUserInfo(@RequestParam("itemId")String itemId,HttpServletRequest request){
		request.setAttribute("itemId", itemId);
		return "showItemInfo";
	}
}
