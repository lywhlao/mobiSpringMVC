package base.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import base.bean.MobiBean;
import base.bean.UserBean;
import base.util.FileUtil;
import base.util.StringUtil;

@Service
public class EmailService {
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	TaskExecutor taskExecutor;


	@Async("CustomThreadPool")
	public void sendMobiEmail(MobiBean mobiBean,UserBean userBean) throws MessagingException {
		String email=userBean.getEmail();
		String content=mobiBean.getContent();
		if(StringUtil.isEmpty(email)||StringUtil.isEmpty(content)){
			return;
		}
		FileSystemResource fileSystemResource = new FileSystemResource(
				FileUtil.getPath(mobiBean.getContent()));
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("jasonxtu@163.com");
		helper.setTo(email);
		helper.setText("很高兴能够使用本网站，您此次浏览的书籍的是"+mobiBean.getContent()+"\n"+mobiBean.getDescription());
		helper.setSubject("尊敬先生|女士 您好!");
		helper.addAttachment(mobiBean.getContent(), fileSystemResource);
		mailSender.send(mimeMessage);
	}

	public void sendSimpleEmail(MobiBean mobiBean) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("jasonxtu@163.com");
		simpleMailMessage.setTo("lywhlao@163.com");
		simpleMailMessage.setText("你好，为什么我的邮件发送不成功呢？？？");
		simpleMailMessage.setSubject("世界你好");
		mailSender.send(simpleMailMessage);
	}
}
