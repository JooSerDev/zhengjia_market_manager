package com.joosure.manager.mvc.wechat.dao;

import com.joosure.manager.mvc.wechat.bean.UserWechat;

public interface UserWechatDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:53
     */
    int deleteByPrimaryKey(String openid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:53
     */
    int insert(UserWechat record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:53
     */
    int insertSelective(UserWechat record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-08-16 17:16:53
     */
    UserWechat selectByPrimaryKey(String openid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:53
     */
    int updateByPrimaryKeySelective(UserWechat record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:53
     */
    int updateByPrimaryKey(UserWechat record);
}