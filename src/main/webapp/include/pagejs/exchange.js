/**
 * 
 */
//table Fmt function
function idFmt(value){
	if(value==null){
		return '';
	}
	return value.exchangeId;
}
function userNameFmt(value){
	if(value==null){
		return '';
	}
	return value.nickname
}
function ownerItemFmt(value){
	if(value==null){
		return '';
	}
	return value.ownerItemName;
}
function userMobileFmt(value){
	if(value==null){
		return '';
	}
	return value.mobile
}
function changerItemFmt(value){
	if(value==null){
		return '';
	}
	return value.changerItemName;
}
function exgTimeFmt(value){
	if(value==null){
		return '';
	}
	return dateTimeFormatter(value.createTime);
}
//TODO
function exgStatusFmt(value){
	if(value==null){
		return '';
	}
	return itemBuzFmt(value.exchangeState)
}
function finishExgStatusFmt(value){
	if(value==null){
		return '';
	}
	return itemFinishFmt(value.exchangeFinishStatus)
}

/*function showUserInfo(userId){
	alert(userId);
}

function showItemInfo(itemId){
	alert(itemId);
}*/