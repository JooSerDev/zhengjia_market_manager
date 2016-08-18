package com.joosure.manager.mvc.wechat.dao;

import com.joosure.manager.mvc.wechat.bean.CoreItemType;

public interface CoreItemTypeDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:45
     */
    int deleteByPrimaryKey(Integer typeid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:45
     */
    int insert(CoreItemType record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:45
     */
    int insertSelective(CoreItemType record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-08-16 17:16:45
     */
    CoreItemType selectByPrimaryKey(Integer typeid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:45
     */
    int updateByPrimaryKeySelective(CoreItemType record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:45
     */
    int updateByPrimaryKey(CoreItemType record);
}