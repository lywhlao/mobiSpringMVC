package base.daoimpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import base.dao.MobiDAO;
import base.util.Pagination;

public class MobiDAOImpl implements MobiDAO {

	private JdbcTemplate mJdbcTemplate;
	private String SQL_SEARCH_STRING = "select * from mobi where content like ";

	public MobiDAOImpl(DataSource dataSource) {
		this.mJdbcTemplate = new JdbcTemplate(dataSource);
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
