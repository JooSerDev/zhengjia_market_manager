package com.joosure.manager.mvc.wechat.dao;

import java.util.List;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserCpt;

public interface WxUserCptDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-08-22 22:33:09
     */
    int deleteByPrimaryKey(Integer oid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-22 22:33:09
     */
    int insert(WxUserCpt record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-22 22:33:09
     */
    int insertSelective(WxUserCpt record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-08-22 22:33:09
     */
    WxUserCpt selectByPrimaryKey(Integer oid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-22 22:33:09
     */
    int updateByPrimaryKeySelective(WxUserCpt record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-08-22 22:33:09
     */
    int updateByPrimaryKey(WxUserCpt record);

    /**
     * 查询投诉的详细信息
     * @param qryCondBean
     * @return
     */
	List<WxUserCpt> getCptList(QryCondBean qryCondBean);

	/**
	 * 查询投诉的记录数
	 * @param qryCondBean
	 * @return
	 */
	int getCptListCount(QryCondBean qryCondBean);
	
	
	int getCptCountByUnionId(String unionId);
	WxUserCpt getLatestCptByUnionId(String unionId);
	
}