package base.web.control;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.bean.UserBean;
import base.service.UserService;
import base.util.Constent;

@Controller
public class UserController {

	@Autowired
	@Qualifier("CustomValidator")
	Validator mValidator;
	
	@Autowired
	UserService mUserService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "/user/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitRegister(@Valid UserBean userBean,Errors errors) {
		if(errors.hasErrors()){
			return Constent.ERROR_PAGE;
		}
		String view = mUserService.createUser(userBean);
		return "/user/"+view;
	}
}
