package com.joosure.manager.mvc.wechat.dao;

import com.joosure.manager.mvc.wechat.bean.CoreItem;

public interface CoreItemDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:41
     */
    int deleteByPrimaryKey(Integer itemid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:41
     */
    int insert(CoreItem record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-16 17:16:41
     */
    int insertSelective(CoreItem record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-08-16 17:16:41
     */
    CoreItem selectByPrimaryKey(Integer itemid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:41
     */
    int updateByPrimaryKeySelective(CoreItem record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:41
     */
    int updateByPrimaryKeyWithBLOBs(CoreItem record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-16 17:16:41
     */
    int updateByPrimaryKey(CoreItem record);
}