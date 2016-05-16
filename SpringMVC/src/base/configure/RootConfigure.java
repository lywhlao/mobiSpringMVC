package base.configure;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import base.dao.MobiDAO;
import base.daoimpl.MobiDAOImpl;

//配置非web方面的bean
@Configuration
@ComponentScan(basePackages = { "base" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfigure {
	// JDBC数据源
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/mobi");
		ds.setUsername("root");
		ds.setPassword("laojiaqi");
		return ds;
	}

	// 设置Jdbc模板，这里的dataSouce会自动注入（上方那个dataSource）
	@Bean
	public org.springframework.jdbc.core.JdbcTemplate JdbcTemplate(
			DataSource dataSource) {
		return new org.springframework.jdbc.core.JdbcTemplate(dataSource);
	}

	// 设置具体的Reposity
	@Bean
	public MobiDAO getMobiDAO() {
		return new MobiDAOImpl(getDataSource());
	}
	
	// 邮件发送
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost("smtp.163.com");
		mailSenderImpl.setPort(25);
		mailSenderImpl.setUsername("jasonxtu@163.com");
		mailSenderImpl.setPassword("laojiaqi0219");
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.timeout", 25000);
		
		mailSenderImpl.setJavaMailProperties(properties);
		return mailSenderImpl;
	}
	
}
