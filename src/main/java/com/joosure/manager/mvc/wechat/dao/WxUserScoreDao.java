package com.joosure.manager.mvc.wechat.dao;

import com.joosure.manager.mvc.wechat.bean.WxUserScore;

public interface WxUserScoreDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-08-17 23:52:17
     */
    int deleteByPrimaryKey(Integer oid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-17 23:52:17
     */
    int insert(WxUserScore record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-17 23:52:17
     */
    int insertSelective(WxUserScore record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-08-17 23:52:17
     */
    WxUserScore selectByPrimaryKey(Integer oid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-17 23:52:17
     */
    int updateByPrimaryKeySelective(WxUserScore record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-17 23:52:17
     */
    int updateByPrimaryKey(WxUserScore record);
}