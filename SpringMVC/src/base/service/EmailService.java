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

@Service
public class EmailService {
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	TaskExecutor taskExecutor;

	@Async("CustomThreadPool")
	public void sendMobiEmail(MobiBean mobiBean) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("jasonxtu@163.com");
		helper.setTo("billxtu@163.com");
		helper.setText("这是我第一次尝试发送带有附件的邮件！！！" + mobiBean.toString());
		helper.setSubject("你好Json");
		FileSystemResource fileSystemResource = new FileSystemResource(
				"c:\\通往市场之路.mobi");
		//helper.addAttachment("first.mobi", fileSystemResource);
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
