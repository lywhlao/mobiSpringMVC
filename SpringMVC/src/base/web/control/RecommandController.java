package base.web.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import base.bean.ContentSimilarBean;
import base.bean.ResponseData;
import base.bean.UserBean;
import base.service.RecommendService;
import base.util.Constent;
import base.util.StringUtil;

@Controller
@SessionAttributes("userBean")
public class RecommandController {

	@Autowired
	RecommendService mRecommendService;

	@RequestMapping(value = "/recordDownload", method = RequestMethod.POST)
	@ResponseBody
	public String recordDownload(String content,@ModelAttribute UserBean userBean, HttpServletRequest request) {
		String identifier = getIdentifier(userBean, request);
		mRecommendService.recordDowload(content, identifier);
		return Constent.RECORD_SUCCESS;
	}

	@ModelAttribute
	private UserBean getUserBean() {
		return new UserBean();
	}

	/**
	 * 获取用户标示，优先获取用户名，再获取ip
	 * @param userBean
	 * @param httpServletRequest
	 * @return
	 */
	private String getIdentifier(UserBean userBean,HttpServletRequest httpServletRequest) {
		String userName = userBean.getUserName();
		if (!StringUtil.isEmpty(userName)) {
			return userName;
		}
		String ipString = httpServletRequest.getRemoteAddr();
		return ipString;
	}
	
	@RequestMapping(value = "/getRecommendContent", headers={"Accept=application/json"},method = RequestMethod.POST)
	@ResponseBody
	public ResponseData<List<ContentSimilarBean>> getRecommendContent(@ModelAttribute UserBean userBean) {
		String userName = userBean.getUserName();
		List<ContentSimilarBean> list=getListByUserName(userName);
		return setResponseData(list);
	}

	/**根据列表长度，设置反馈信息
	 * @param list
	 * @return
	 */
	public ResponseData<List<ContentSimilarBean>> setResponseData(List<ContentSimilarBean> list){
		ResponseData<List<ContentSimilarBean>> data=new ResponseData<List<ContentSimilarBean>>();
		if(list.size()<=0){
			data.setResultCode(Constent.FAIL_CODE);	
		}else{
			data.setResultCode(Constent.SUCCESS_CODE);
			data.setData(list);
		}
		return data;
	}
	
	/**
	 * 根据用户名获取推荐信息
	 * @param userName
	 * @return
	 */
	public List<ContentSimilarBean> getListByUserName(String userName) {
		List<ContentSimilarBean> list;
		if (StringUtil.isEmpty(userName)) {
			list = mRecommendService.getRecommendContentByUser(Constent.DEFAULT_RECOMMAND_USER);
		} else {
			list = mRecommendService.getRecommendContentByUser(userName);
		}
		return list;
	}
}
