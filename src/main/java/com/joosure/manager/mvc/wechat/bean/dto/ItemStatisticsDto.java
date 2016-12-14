package com.joosure.manager.mvc.wechat.bean.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.joosure.server.mvc.wechat.dao.cache.ItemCache;
import com.joosure.server.mvc.wechat.entity.pojo.ItemType;

public class ItemStatisticsDto {
	public static final String LOCK_NORMAL = "normal";
	public static final String LOCK_EXCHANGING = "exchanging";
	public static final String LOCK_EXCHANGED = "exchanged";

	public static final String RankType_PV = "pvRate";
	public static final String RankType_EXG = "exgFinishRate";
	
	private Integer itemId;
	private String name;
	private String ownerNickname;
	private Integer itemType;
	private String exgStatus;
	private Date createTime;
	private Integer itemPv;
	private String exgFinishRate;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getOwnerNickname() {
		return ownerNickname;
	}

	public void setOwnerNickname(String ownerNickname) {
		this.ownerNickname = ownerNickname;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getItemTypeName() {
		if (itemType != null) {
			ItemType it = ItemCache.getItemType(itemType);
			if (it != null) {
				return it.getName();
			}
		}
		return "";
	}

	public Integer getItemPv() {
		return itemPv;
	}

	public void setItemPv(Integer itemPv) {
		this.itemPv = itemPv;
	}

	public String getExgFinishRate() {
		return exgFinishRate;
	}

	public void setExgFinishRate(String exgFinishRate) {
		this.exgFinishRate = exgFinishRate;
	}

	public String getExgStatus() {
		return exgStatus;
	}

	public void setExgStatus(String exgStatus) {
		this.exgStatus = exgStatus;
	}
	
	public String getShowCreateTime(){
		if(this.createTime != null){
			return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(this.createTime);
		}
		return "";
	}
}
