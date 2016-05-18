package base.web.control;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import base.bean.MobiBean;
import base.service.EmailService;

@Controller
public class EmailContorl {

	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmail(MobiBean mobiBean) throws MessagingException {
		emailService.sendMobiEmail(mobiBean);
		return "ok!!!";
	}

}
