package com.joosure.manager.mvc.wechat.service.db;

import java.util.List;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserCpt;

public interface IWxUserCptDbService {

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-08-22 22:33:09
     */
    int insertSelective(WxUserCpt record);

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
}
