/**
 * 
 */
//table Fmt function
function idFmt(value,row,index){
	return value.itemId;
}
function itemNameFmt(value,row,index){
	return value.name;
}
function approvalFmt(value,row,index){
	return value.approvalStatus;
}
//审核时间 TODO
function approvalTimeFmt(value,row,index){
	return dateTimeFormatter(value);
}
function nickNameFmt(value,row,index){
	return value.ownerNickname;
}
function typeFmt(value,row,index){
	return value.itemType;
}
function statusFmt(value,row,index){
	return value.status;
}
function recommendFmt(value,row,index){
	return value.isRecommended;
}
function buzFmt(value,row,index){
	return value.lockStatus;
}

//itemDetail.jsp
function createTimeFmt(value){
	return dateTimeFormatter(value);
}