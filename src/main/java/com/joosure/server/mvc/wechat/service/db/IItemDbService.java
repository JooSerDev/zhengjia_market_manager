package com.joosure.server.mvc.wechat.service.db;

import java.util.List;

import com.joosure.server.mvc.wechat.entity.pojo.Exchange;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.ItemComment;
import com.joosure.server.mvc.wechat.entity.pojo.ItemLike;
import com.joosure.server.mvc.wechat.entity.pojo.ItemType;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserCpt;

public interface IItemDbService {
	
	/**
	 * 保存宝贝
	 * 
	 * @param item
	 */
	void saveItem(Item item);

	void updateItem(Item item);

	Item getItemById(int itemId);

	List<Item> getItemsByOwnerId(int ownerId);

	List<Item> getExchangeableItemsByOwnerId(int ownerId);

	List<Item> getItemsByOwnerIdPages(int ownerId, int startRow, int limitSize);

	List<Item> getItemsRecommended();

	/**
	 * 市集宝贝列表分页查询
	 * 
	 * @param startRow
	 * @param limitSize
	 * @return
	 */
	List<Item> getMarketItemsPages(String keyword, int itemType,
			int isRecommended, int startRow, int limitSize);

	/**
	 * 宝贝分类
	 * 
	 * @return
	 */
	List<ItemType> getItemTypes();

	/*
	 * 宝贝评论
	 * #########################################################################
	 */

	void saveItemComment(ItemComment itemComment);

	List<ItemComment> getItemCommentByItemIdPages(int itemId, int startRow, int limitSize);

	void saveExchange(Exchange exchange);

	void updateExchange(Exchange exchange);

	void updateExchanges4cancelOthersWhenAgreeExchange(int exchangeId, int ownerItemId, int changerItemId);

	Exchange getExchangeById(int exchangeId);

	/**
	 * 通过双方物品查询交易<br>
	 * 传入两个物品id，取得该两个物品有关系的交易，不论owner与changer
	 * 
	 * @param ownerItemId
	 * @param changerItemId
	 * @return
	 */
	Exchange getExchangeByBothSideItemId(int ownerItemId,
			int changerItemId);

	/**
	 * 获得与当前userid相关的交易
	 * 
	 * @param userId
	 * @param startRow
	 * @param limitSize
	 * @return
	 */
	List<Exchange> getExchangesByUserIdPages(int userId, int startRow, int limitSize);

	List<Exchange> getExchangesByUserIdInOwnerSidePages(int userId, int startRow,int limitSize);

	List<Exchange> getExchangesByUserIdInChangerSidePages(int userId, int startRow, int limitSize);

	void saveItemLike(ItemLike itemLike);

	ItemLike getItemLike(int itemId, int userId);

	void saveItemReport(WxUserCpt wxUserCpt);
}
