package base.daoimpl;

import org.springframework.jdbc.core.JdbcTemplate;

import base.dao.IMobiDAO;
import base.util.Pagination;

public class MobiDAOImpl implements IMobiDAO {

	private JdbcTemplate mJdbcTemplate;
	private String SQL_SEARCH_STRING = "select * from mobi where content like ";

	public MobiDAOImpl(JdbcTemplate jdbcTemplate) {
		this.mJdbcTemplate = jdbcTemplate;
	}

	@Override
	public Pagination searchMobi(String content,int currentPage) {
		// 实现一些操作
		String newContent = "'%" + content + "%'";
		String searchSQL=SQL_SEARCH_STRING+newContent;
		Pagination pagination=new Pagination(searchSQL,currentPage, Pagination.NUMBERS_PER_PAGE, mJdbcTemplate,content);
		return pagination;
	}
}
