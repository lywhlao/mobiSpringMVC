package base.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import base.bean.ContentSimilarBean;
import base.bean.DownloadRecordBean;
import base.dao.IRecommandDAO;

@Component
public class RecommendService {

	@Autowired
	private IRecommandDAO mRecommandDAO;
	
	private Map<String,Map<String,Integer>> mContentMap=new HashMap<String, Map<String,Integer>>();
	
	private Set<String> mUserSet=new HashSet<String>();
	
	private List<ContentSimilarBean> mSimilarList=new ArrayList<ContentSimilarBean>();
	
	private static final int DOWNLOAD=1;
	
	private static final int NOT_DOWNLOAD=0;
	
	/**
	 * 记录下载
	 * @param content 书本名
	 * @param user 当前用户
	 */
	public void recordDowload(String content,String user){
		//TODO
		//Date currentTime=new Date(System.currentTimeMillis());
		//mRecommandDAO.recordDowload(content, user, currentTime);
		generateRecommend();
	}
	
	
	public void generateRecommend(){
		clearContainer();
		setContentData();
		fillAllUserToItem();
		showMap();
		caculateContentSimilar();
	}
	
	/**
	 * 从数据库获取内容，并存储到相应数据结构
	 */
	public void setContentData(){
		List<DownloadRecordBean> contentList=mRecommandDAO.getRecords();
		for(DownloadRecordBean temp:contentList){
			mUserSet.add(temp.getUserName());
			if(mContentMap.containsKey(temp.getContent())){
				//非第一次存
				Map<String, Integer> userMap=mContentMap.get(temp.getContent());
				userMap.put(temp.getUserName(), DOWNLOAD);
			}else{
				//第一次存content
				Map<String, Integer> userMap=new HashMap<String, Integer>();
				userMap.put(temp.getUserName(),DOWNLOAD);
				mContentMap.put(temp.getContent(), userMap);
			}
		}
	}
	
	/**
	 * 对所有书籍item，填充所有用户
	 */
	private void fillAllUserToItem(){
	  Iterator<String> userIterator=mUserSet.iterator();
	  System.out.println("mContentMap的大小是: "+mContentMap.size());
	  while(userIterator.hasNext()){
		  String userName=userIterator.next();
		  Set<String> contentSet= mContentMap.keySet();
		  Iterator<String> contentIterator=contentSet.iterator();
		  while(contentIterator.hasNext()){
			  String contentName=contentIterator.next();
			  Map<String, Integer> userItemMap=mContentMap.get(contentName);
			  if(!userItemMap.containsKey(userName)){
				  userItemMap.put(userName, NOT_DOWNLOAD);
			  }
		  }
	  }
	}
	
	/**
	 * 显示map数据
	 */
	private void showMap(){
	 Set<String> contentSet=mContentMap.keySet();
	 Iterator<String> iterator=contentSet.iterator();
	 while(iterator.hasNext()){
		 String key=iterator.next();
		 System.out.println("content="+key);
		 Map<String, Integer> map=mContentMap.get(key);
		 Iterator<String> iterator2=map.keySet().iterator();
		 while(iterator2.hasNext()){
              String nameString=iterator2.next();
              int value=map.get(nameString);
              System.out.println(" user="+nameString +" value="+value);
		 }
	 }
	}

	/**
	 * 清除数据结构
	 */
	private void clearContainer(){
		mContentMap.clear();
		mUserSet.clear();
	}
	
	private void caculateContentSimilar(){
		List<String> contentList = new ArrayList<String>(mContentMap.keySet());
		int length = contentList.size();
		for (int i = 0; i < length-1; i++) {
			String contentSource = contentList.get(i);
			for (int j = i + 1; j < length; j++) {
				String contentDest = contentList.get(j);
				double value=cacluete(mContentMap.get(contentSource),mContentMap.get(contentDest));
				System.out.println(contentSource+"  "+contentDest+ " value = "+value);
			}
		}
	}
	
	private double cacluete(Map<String,Integer>contentSourceMap,Map<String,Integer> contentDestMap){
		Iterator<String> userIterator=contentSourceMap.keySet().iterator();
		double sum=0;
		while(userIterator.hasNext()){
			String userName=userIterator.next();
			int contentSourceValue=contentSourceMap.get(userName);
			int contentDestValue=contentDestMap.get(userName);
			sum+=Math.pow(contentSourceValue-contentDestValue, 2);
		}
		double temp=1.0/(1.0+Math.sqrt(sum));
		BigDecimal   bigDecimal   =   new   BigDecimal(temp);  
		double similarValue = bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();  
		return similarValue;
	}
	

}
