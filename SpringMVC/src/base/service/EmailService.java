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
import base.util.Constent;
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
		helper.setFrom("lywhlao@sohu.com");
		helper.setTo(email);
		helper.setText(mobiBean.getContent()+"\n"+mobiBean.getDescription());
		helper.setSubject(userBean.getUserName()+"您好");
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
