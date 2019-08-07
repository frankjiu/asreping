package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.domain.QaRoleMenu;

/**
 * 持久层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface MacoRoleMenuRepository extends JpaRepository<QaRoleMenu, String>, JpaSpecificationExecutor<QaRoleMenu> {
	
	List<QaRoleMenu> getByRoleId(String id);
	
	//void deleteByRoleIdAndMenuIds(String role_id, @RequestParam(value = "menu_ids[]") String[] menu_ids);
	
}
