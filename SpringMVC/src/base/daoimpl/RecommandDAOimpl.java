package base.daoimpl;

import java.sql.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import base.dao.IRecommandDAO;

public class RecommandDAOimpl implements IRecommandDAO {

	private JdbcTemplate mJdbcTemplate;

	private static final String INSERT_DOWNLOAD_RECORD = "insert into user_download values(?,?,?)";

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

}
