package base.web.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

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
}
