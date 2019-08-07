package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.QaUserRole;
import com.service.QaUserRoleService;
import com.utils.LogAssist;
import com.utils.LogOperation;

import net.sf.json.JSONObject;


/**
 * 用户角色控制层
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Controller
@RequestMapping("/urole")
public class QaUserRoleController {

	@Autowired
	private QaUserRoleService qaUserRoleService;

	/**
	 * 主键查询
	 */
	@RequestMapping("/getOne")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_SYSTEM, describe = "用户角色--主键查询")
	public String getOne(String id) {
		JSONObject js = new JSONObject();
		QaUserRole qaUserRole = qaUserRoleService.getOne(id);
		try {
			js.put("data", qaUserRole);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

	/**
	 * 条件查询
	 */
	@RequestMapping("/findByUserId")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_SYSTEM, describe = "用户角色--条件查询")
	public String findByUserId(String userId) {
		JSONObject js = new JSONObject();
		try {
			List<QaUserRole> list = new ArrayList<QaUserRole>();
			QaUserRole qaUserRole = new QaUserRole();
			list = qaUserRoleService.findByUserId(userId);
			if (list != null && list.size() > 0) {
				qaUserRole = list.get(0);
			}
			js.put("data", qaUserRole);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

	/**
	 * 新增
	 */
	@RequestMapping("/save")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_ADD, operationModule = LogOperation.WP_SYSTEM, describe = "用户角色--新增")
	public String saveQaUserRole(QaUserRole qaUserRole) {
		JSONObject js = new JSONObject();
		try {
			qaUserRole = qaUserRoleService.save(qaUserRole);
			js.put("id", qaUserRole.getId());
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_DEL, operationModule = LogOperation.WP_SYSTEM, describe = "用户角色--删除")
	public String delete(String id) {
		JSONObject js = new JSONObject();
		try {
			qaUserRoleService.delete(id);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_MODIFY, operationModule = LogOperation.WP_SYSTEM, describe = "用户角色--修改")
	public String update(String id) {
		JSONObject js = new JSONObject();
		try {
			QaUserRole newQaUserRole = new QaUserRole();
			qaUserRoleService.update(newQaUserRole);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

}
