package com.joosure.server.mvc.wechat.service.db;

import java.util.List;
import java.util.Map;

import com.joosure.server.mvc.wechat.entity.pojo.Score;

public interface IScoreDbService {
	List<Score> getScoreList(int oId, int userId, String eventKey, 
			int score, int startRow, int limitSize);

	int getUserScore(int userId);

	int insertScore(Score record);

	int updateUserScore(int userId, int score);
	
	int getSumScoreByCond(Map<String,Object> cond);
}
