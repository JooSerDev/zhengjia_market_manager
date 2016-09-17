package com.joosure.manager.mvc.wechat.dao;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;

public interface SysConfParamDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    int deleteByPrimaryKey(SysConfParam key);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    int insert(SysConfParam record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    int insertSelective(SysConfParam record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    SysConfParam selectByPrimaryKey(SysConfParam key);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    int updateByPrimaryKeySelective(SysConfParam record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    int updateByPrimaryKey(SysConfParam record);

    /**
     * 条件查询字典
     * @param cond
     * @return
     */
	List<SysConfParam> qryParamsInfo(SysConfParam cond);

	int qryParamsCount(SysConfParam cond);

	SysConfParam chkIfExist(SysConfParam record);

	int changeSysConfParam(SysConfParam sysConfParam);
}