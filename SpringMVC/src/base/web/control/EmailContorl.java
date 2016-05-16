package base.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
public class EmailContorl {

	@Autowired
	JavaMailSender mailSender;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	@ResponseBody
	public String sendEmail() throws MessagingException{

//		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		 simpleMailMessage.setFrom("jasonxtu@163.com");
//		 simpleMailMessage.setTo("lywhlao@163.com");
//		 simpleMailMessage.setText("你好，为什么我的邮件发送不成功呢？？？");
//		 simpleMailMessage.setSubject("世界你好");
//		 mailSender.send(simpleMailMessage);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("jasonxtu@163.com");
		helper.setTo("lywhlao@163.com");
		helper.setText("这是我第一次尝试发送带有附件的邮件！！！");
		helper.setSubject("你好Json");
		FileSystemResource fileSystemResource = new FileSystemResource(
				"c:\\通往市场之路.mobi");
		helper.addAttachment("first.mobi", fileSystemResource);
	//	mailSender.send(mimeMessage);
		// MimeMailMessage mailMessage=mailSender.
		return "ok!!!";
	}

}
