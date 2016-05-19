package base.daoimpl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import base.bean.UserBean;
import base.dao.IUserDAO;
import base.mapper.UserBeanMapper;

public class UserDAOImpl implements IUserDAO {

	private JdbcTemplate mJdbcTemplate;
	private static final String CRATE_USER_STRING = "insert into user(name,password,email) values(?,?,?)";
	private static final String QUERY_USER = "select * from user where name=?";
	private static final String LOGIN = "select count(*) from user where name=? and password=?";

	public UserDAOImpl(JdbcTemplate jdbcTemplate) {
		this.mJdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean createUser(UserBean userBean) {
		String name = userBean.getUserName();
		String passwrod = userBean.getPassword();
		String email = userBean.getPassword();
		boolean result = true;
		try {
			mJdbcTemplate.update(CRATE_USER_STRING, name, passwrod, email);
		} catch (DataAccessException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public UserBean searchUser(String name) {
		UserBean userBean = mJdbcTemplate.queryForObject(QUERY_USER,
				new Object[] { name }, new UserBeanMapper());
		return userBean;
	}

	@Override
	public boolean updatePaasword(UserBean userBean) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean login(UserBean userBean) {
		String name = userBean.getUserName();
		String password = userBean.getPassword();
		// 加入MD5校验
		int result = mJdbcTemplate.queryForObject(LOGIN, new Object[] { name,
				password }, Integer.class);
		if (result == 0) {
			return false;
		}
		return true;
	}

}
