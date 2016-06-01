package base.daoimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import base.bean.DownloadRecordBean;
import base.dao.IRecommandDAO;
import base.mapper.DownloadRecordMapper;

public class RecommandDAOimpl implements IRecommandDAO {

	private JdbcTemplate mJdbcTemplate;

	private static final String INSERT_DOWNLOAD_RECORD = "insert into user_download values(?,?,?)";

	private static final String GET_DOWLOAD_RECORD="select * from user_download";
	
	public RecommandDAOimpl(JdbcTemplate jdbcTemplate) {
		this.mJdbcTemplate = jdbcTemplate;
	}

	@Override
	public void recordDowload(String content, String user, Date time) {
		try {
			mJdbcTemplate.update(INSERT_DOWNLOAD_RECORD, content, user, time);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<DownloadRecordBean> getRecords() {
		List<DownloadRecordBean> downloadRecordBeans = mJdbcTemplate.query(
				GET_DOWLOAD_RECORD, new DownloadRecordMapper());
		return downloadRecordBeans;
	}
	
	

}
