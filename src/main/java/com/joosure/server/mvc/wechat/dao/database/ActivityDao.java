package com.joosure.server.mvc.wechat.dao.database;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.server.mvc.wechat.entity.pojo.Activity;
import com.joosure.server.mvc.wechat.entity.pojo.ActivityEnter;


public interface ActivityDao {

    /**
     * 通过id获取活动信息
     * @author Ted-wuhuhu
     * @Time 2016年11月29日 下午4:28:19
     * @param id
     * @return
     */
    Activity selectById(@Param("id")Integer id);
    
    /**
     * 获取所有有效的活动信息
     * @author Ted-wuhuhu
     * @param cond 
     * @Time 2016年11月29日 下午4:28:05
     * @return
     */
    List<Activity> selectAllAvailable(Activity cond);
    
    /**
     * 获取所有展示的活动
     * @author Ted-wuhuhu
     * @Time 2016年11月30日 上午10:10:38
     * @return
     */
    List<Activity> selectAllVisible();
    
    /**
     * 获取报名活动
     * @author Ted-wuhuhu
     * @Time 2016年11月30日 上午10:10:48
     * @return
     */
    Activity selectTheEnterActy();
    
    /**
     * 插入 活动报名者信息
     * @author Ted-wuhuhu
     * @Time 2016年11月29日 下午4:41:08
     * @param enter
     * @return
     */
    int insertEnter(ActivityEnter enter);

    /**
     * 通过手机号和当期活动时间获取活动报名者信息
     * @author Ted-wuhuhu
     * @Time 2016年11月29日 下午4:43:04
     * @param mobile
     * @param actyId
     * @param actyTime
     * @return
     */
    ActivityEnter getCurrentCycleEnterInfo(ActivityEnter enter);

	/**
	 * 正常已发布的活动的数量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午5:28:42
	 * @param cond
	 * @return
	 */
	int getAllAvailableActyCount(Activity cond);

	/**
	 * 活动报名的记录
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午6:40:32
	 * @param cond
	 * @return
	 */
	List<ActivityEnter> getEnterListByPage(ActivityEnter cond);
	
	/**
	 * 活动报名的记录数量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午6:40:32
	 * @param cond
	 * @return
	 */
	int getEnterListCount(ActivityEnter cond);

	/**
	 * 更新报名活动的信息
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午8:31:02
	 * @param enterActy
	 * @return
	 */
	int updateActivity(Activity enterActy);

	/**
	 * 添加自定义活动
	 * @author Ted-wuhuhu
	 * @Time 2016年12月2日 下午2:07:09
	 * @param record
	 * @return
	 */
	int insertActivity(Activity record);
	
	/**
	 * 通过oId获取报名信息
	 * @author Ted-wuhuhu
	 * @Time 2016年12月5日 上午10:52:21
	 * @param oId
	 * @return
	 */
	ActivityEnter getEnterInfoById(Integer oId);

	/**
	 * 审核报名信息
	 * @author Ted-wuhuhu
	 * @Time 2016年12月5日 上午11:03:08
	 * @param enter
	 * @return
	 */
	int changeEnterInfo(ActivityEnter enter);
}
