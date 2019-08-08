package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.utils.LogAssist;
import com.utils.LogOperation;

/**
 * 转向控制层
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	/**
	 * 索引登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@LogAssist(operationType = LogOperation.OP_GOTO, operationModule = LogOperation.WP_SYSTEM, describe = "索引登录页")
	public String indexLogin() {
		return "login";
	}
	
	/**
	 * 跳转登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	@LogAssist(operationType = LogOperation.OP_GOTO, operationModule = LogOperation.WP_SYSTEM, describe = "跳转登录页")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * 跳转到主页
	 */
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	@LogAssist(operationType = LogOperation.OP_GOTO, operationModule = LogOperation.WP_SYSTEM, describe = "跳转到主页")
	public String toMain() {
		return "main";
	}
	
	/**
	 * 跳转到测试项目登录页
	 */
	@RequestMapping(value = "/toTest", method = RequestMethod.GET)
	@LogAssist(operationType = LogOperation.OP_GOTO, operationModule = LogOperation.WP_SYSTEM, describe = "跳转到测试项目登录页")
	public String toTest() {
		return "forward:/jsp/template/login.html";
	}
	
}
