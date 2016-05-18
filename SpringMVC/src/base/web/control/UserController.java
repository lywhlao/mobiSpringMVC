package base.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.bean.UserBean;
import base.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService mUserService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "/user/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitRegister(UserBean userBean) {
		String view = mUserService.createUser(userBean);
		return view;
	}
}
