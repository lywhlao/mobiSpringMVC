package base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.bean.UserBean;
import base.dao.IUserDAO;

@Service
public class UserService {

	public static final String CREATE_USER_SUCCESS_VIEW = "create_user_success";
	public static final String CREATE_USER_FAIL_VIEW = "create_user_fail";

	@Autowired
	IUserDAO mUserDAO;

	public String createUser(UserBean userBean) {
		boolean result = mUserDAO.createUser(userBean);
		if (result) {
			return CREATE_USER_SUCCESS_VIEW;
		} else {
			return CREATE_USER_FAIL_VIEW;
		}
	}
	
	public void searchUser(String name){
		UserBean userBean=mUserDAO.searchUser(name);
		if(userBean==null){
			//TODO
		}else{
			
		}
	}
	
}
