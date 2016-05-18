package base.configure;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import base.dao.IMobiDAO;
import base.dao.IUserDAO;
import base.daoimpl.MobiDAOImpl;
import base.daoimpl.UserDAOImpl;

//配置非web方面的bean
@Configuration
@ComponentScan(basePackages = { "base" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@EnableAsync
public class RootConfigure {

	public static final int THREAD_POOL_QUEUE_NUM = 25;

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
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	// 设置具体的Reposity
	@Bean
	public IMobiDAO getMobiDAO(JdbcTemplate jdbcTemplate) {
		return new MobiDAOImpl(jdbcTemplate);
	}

	@Bean
	public IUserDAO getUserDAO(JdbcTemplate jdbcTemplate) {
		return new UserDAOImpl(jdbcTemplate);
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

	@Bean(name = "CustomThreadPool")
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		int cpuNum = Runtime.getRuntime().availableProcessors();
		taskExecutor.setCorePoolSize(cpuNum);
		taskExecutor.setMaxPoolSize(2 * cpuNum);
		taskExecutor.setQueueCapacity(THREAD_POOL_QUEUE_NUM);
		return taskExecutor;
	}

}
