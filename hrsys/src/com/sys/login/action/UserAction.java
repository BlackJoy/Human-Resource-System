package com.sys.login.action;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.hr.employee.Employee;
import com.sys.hr.employee.biz.IEmployeeBIZ;
import com.sys.login.ILoginConstant;
import com.sys.login.User;
import com.sys.login.biz.IUserBIZ;

/**
 * 登录用户Action
 * @author Administrator
 *
 */
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -3597363916490797515L;
	private IUserBIZ userBiz;
	private IEmployeeBIZ empBiz;
	private User user;
	
	private String checkNum;//用来存放输入的验证码值
	private String rememberMe;//用来存放记住我按钮是否被选中
	
	
	
	public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public IUserBIZ getUserBiz() {
		return userBiz;
	}
	public void setUserBiz(IUserBIZ userBiz) {
		this.userBiz = userBiz;
	}
	public IEmployeeBIZ getEmpBiz() {
		return empBiz;
	}
	public void setEmpBiz(IEmployeeBIZ empBiz) {
		this.empBiz = empBiz;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 登录方法
	 * @return
	 */
	public String login() throws Exception {	
		
		String rtn_val = ILoginConstant.LOGIN_FAIL;
		User u = userBiz.find_checkLogin(user);
		if (u != null) {
			if (user.getPassword().equals(u.getPassword()) && u.getEnable() == 1) {
				rtn_val = ILoginConstant.LOGIN_SUCCESS;
			} else if (user.getPassword().equals(u.getPassword()) && u.getEnable() == 0) {
				rtn_val = ILoginConstant.LOGIN_CLOSEACCOUNT;
			}
		}
		if (rtn_val.equals(ILoginConstant.LOGIN_SUCCESS)) {
			//将当前用户保存到Session中
			ActionContext.getContext().getSession().put(ILoginConstant.CURRENT_USER, u);
			Employee emp = empBiz.findEmployeeById(u.getEmployeeId());
			ActionContext.getContext().getSession().put("curr_employee", emp);
			return SUCCESS;
		} else {
			ActionContext.getContext().put("loginMsg", rtn_val);
			return ERROR;
		}
	}
	
	public String logout() throws Exception {
		ActionContext.getContext().getSession().clear();
		return "logout";
	}
}
