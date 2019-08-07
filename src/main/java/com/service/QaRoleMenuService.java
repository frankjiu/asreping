package com.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.domain.QaRoleMenu;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaRoleMenuService {
	
	QaRoleMenu getOne(String id);
	
	List<QaRoleMenu> getByRoleId(String id);
	
	boolean save(String role_id, @RequestParam(value = "menu_ids[]") String[] menu_ids);
	
	void deleteByRoleIdAndMenuIds(String role_id, @RequestParam(value = "menu_ids[]") String[] menu_ids);
	
	
}
