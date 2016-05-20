package base.web.control;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import base.bean.MobiBean;
import base.bean.UserBean;
import base.service.EmailService;

@Controller
@SessionAttributes("userBean")
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmail(MobiBean mobiBean,@ModelAttribute UserBean userBean) throws MessagingException {
		System.out.println(userBean.getEmail());
		System.out.println(userBean.getEmail());
		emailService.sendMobiEmail(mobiBean,userBean);
		return "ok!!!";
	}

}
