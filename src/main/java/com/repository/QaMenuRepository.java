package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.domain.QaMenu;

/**
 * 持久层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaMenuRepository extends JpaRepository<QaMenu, String>, JpaSpecificationExecutor<QaMenu> {
	
	//List<QaMenu> getByAuth(QaMenu qaMenu, String[] authArr);
	
	//List<QaMenu> findTree(String[] authArr);
	
}
