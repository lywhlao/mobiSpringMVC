package base.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import base.bean.ContentSimilarBean;
import base.bean.DownloadRecordBean;
import base.dao.IRecommandDAO;
import base.mapper.ContentRecommandMapper;
import base.mapper.DownloadRecordMapper;

public class RecommandDAOimpl implements IRecommandDAO {

	private JdbcTemplate mJdbcTemplate;

	private static final String INSERT_DOWNLOAD_RECORD = "insert into user_download values(?,?,?)";

	private static final String GET_DOWLOAD_RECORD="select * from user_download";
	
	private static final String INSERT_SIMILAR_VALUE="insert into content_similar values(?,?,?)";
	
	private static final String GET_RECOMMEND_CONTENT = "SELECT * from ("
			+ "SELECT DISTINCT c.content_source, c.content_dest,c.similar "
			+ "from user_download u join content_similar c "
			+ "on u.content=c.content_source where u.name=? and "
			+ "c.content_dest not in ( SELECT content from user_download "
			+ "WHERE user_download.name= ?) "
			+ "ORDER BY c.similar DESC) new_table "
			+ "GROUP BY new_table.content_dest "
			+ "ORDER BY new_table.similar DESC ";
	
	
	
	
	public RecommandDAOimpl(JdbcTemplate jdbcTemplate) {
		this.mJdbcTemplate = jdbcTemplate;
	}

	@Override
	public void recordDowload(String content, String user, Date time) {
		try {
			mJdbcTemplate.update(INSERT_DOWNLOAD_RECORD, content, user, time);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<DownloadRecordBean> getRecords() {
		List<DownloadRecordBean> downloadRecordBeans = mJdbcTemplate.query(
				GET_DOWLOAD_RECORD, new DownloadRecordMapper());
		return downloadRecordBeans;
	}

	@Override
	public void recordSimlar(List<ContentSimilarBean> contentSimilarBeans) {
      for(ContentSimilarBean temp:contentSimilarBeans){
    	  String contentSource=temp.getContentSource();
    	  String contentDest=temp.getContentDest();
    	  double similar=temp.getSimilar();
    	  try {
			mJdbcTemplate.update(INSERT_SIMILAR_VALUE,contentSource,contentDest,similar);
			mJdbcTemplate.update(INSERT_SIMILAR_VALUE,contentDest,contentSource,similar);
    	  } catch (Exception e) {
    		  e.printStackTrace();
		}
      }
	}

	@Override
	public List<ContentSimilarBean> getRecommendList(String userName) {
		List<ContentSimilarBean> contentSimilarList=new ArrayList<ContentSimilarBean>();
		try {
			contentSimilarList=mJdbcTemplate.query(GET_RECOMMEND_CONTENT,new ContentRecommandMapper(),userName,userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contentSimilarList;
	}
	
	

}
