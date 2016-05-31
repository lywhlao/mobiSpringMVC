package base.dao;

import java.sql.Date;

public interface IRecommandDAO {

	public void recordDowload(String content,String user,Date time);
}
