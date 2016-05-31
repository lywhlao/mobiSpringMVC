package base.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import base.dao.IRecommandDAO;

@Component
public class RecommendService {

	@Autowired
	private IRecommandDAO recommandDAO;
	
	/**
	 * 记录下载
	 * @param content 书本名
	 * @param user 当前用户
	 */
	public void recordDowload(String content,String user){
		Date currentTime=new Date(System.currentTimeMillis());
		recommandDAO.recordDowload(content, user, currentTime);
	}
	

}
