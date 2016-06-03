package base.dao;

import java.sql.Date;
import java.util.List;

import base.bean.ContentSimilarBean;
import base.bean.DownloadRecordBean;

public interface IRecommandDAO {

	/**
	 * 写下载记录到数据库
	 * @param content
	 * @param user
	 * @param time
	 */
	public void recordDowload(String content,String user,Date time);
	
	
	/**
	 * 获得记录所有下载记录列表
	 * @return
	 */
	public List<DownloadRecordBean> getRecords();
	
	
	/**
	 * 写 书籍相似度到数据库
	 * @param contentSimilarBeans
	 */
	public void recordSimlar(List<ContentSimilarBean> contentSimilarBeans);
	
	
	/**获得某个用户的推荐列表
	 * @param userName
	 * @return
	 */
	public List<ContentSimilarBean> getRecommendList(String userName);
	
}
