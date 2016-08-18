package com.joosure.manager.mvc.wechat.dao;

import com.joosure.manager.mvc.wechat.bean.ItemExchange;

public interface ItemExchangeDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:37
     */
    int deleteByPrimaryKey(Integer exchangeid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:37
     */
    int insert(ItemExchange record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:37
     */
    int insertSelective(ItemExchange record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-08-16 17:16:37
     */
    ItemExchange selectByPrimaryKey(Integer exchangeid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:37
     */
    int updateByPrimaryKeySelective(ItemExchange record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:37
     */
    int updateByPrimaryKey(ItemExchange record);
}