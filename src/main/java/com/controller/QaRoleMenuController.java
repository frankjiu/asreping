package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.QaRoleMenu;
import com.service.QaRoleMenuService;
import com.utils.LogAssist;
import com.utils.LogOperation;

import net.sf.json.JSONObject;

/**
 * 角色菜单控制层
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Controller
@RequestMapping("/auth")
public class QaRoleMenuController {

	@Autowired
	private QaRoleMenuService qaRoleMenuService;

	/**
	 * 跳转页面
	 */
	@RequestMapping("/")
	@LogAssist(operationType = LogOperation.OP_GOTO, operationModule = LogOperation.WP_SYSTEM, describe = "权限--跳转页面")
	public String to(String id) {
		return "/auth_manage";
	}
	
	/**
	 * 主键查询
	 */
	@RequestMapping("/getOne")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_SYSTEM, describe = "角色菜单--主键查询")
	public String getOne(String id) {
		JSONObject js = new JSONObject();
		try {
			QaRoleMenu qaRoleMenu = qaRoleMenuService.getOne(id);
			js.put("data", qaRoleMenu);
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
	@RequestMapping("/getBy")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_SYSTEM, describe = "角色菜单--条件查询")
	public String getBy(QaRoleMenu query) {
		JSONObject js = new JSONObject();
		try {
			List<QaRoleMenu> list = qaRoleMenuService.getByRoleId(query.getRoleId());
			js.put("data", list);
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
	@LogAssist(operationType = LogOperation.OP_ADD, operationModule = LogOperation.WP_SYSTEM, describe = "角色菜单--新增")
	public String saveQaRoleMenu(String role_id, @RequestParam(value = "menu_ids[]") String[] menu_ids) {
		JSONObject js = new JSONObject();
		try {
			boolean flag = qaRoleMenuService.save(role_id, menu_ids);
			js.put("data", flag);
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
	@LogAssist(operationType = LogOperation.OP_DEL, operationModule = LogOperation.WP_SYSTEM, describe = "角色菜单--删除")
	public String delete(String role_id, @RequestParam(value = "menu_ids[]") String[] menu_ids) {
		JSONObject js = new JSONObject();
		try {
			qaRoleMenuService.deleteByRoleIdAndMenuIds(role_id, menu_ids);
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
