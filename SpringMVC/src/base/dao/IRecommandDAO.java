package base.dao;

import java.sql.Date;
import java.util.List;

import base.bean.DownloadRecordBean;

public interface IRecommandDAO {

	/**
	 * 写下载记录到数据库
	 * @param content
	 * @param user
	 * @param time
	 */
	public void recordDowload(String content,String user,Date time);
	
	
	public List<DownloadRecordBean> getRecords();
}
