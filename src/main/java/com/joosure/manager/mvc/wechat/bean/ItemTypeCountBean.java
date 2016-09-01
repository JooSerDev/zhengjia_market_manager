package com.joosure.manager.mvc.wechat.bean;

public class ItemTypeCountBean implements Comparable<ItemTypeCountBean>{
	
	private Integer typeId; //类型id
	private int count;//类型数量
	
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public int compareTo(ItemTypeCountBean target) {
		if(target!=null){
			if(target.count>this.count){
				return -1;
			}else {
				return 1;
			}
		}
		return 0;
	}
	
}
