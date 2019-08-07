package com.service;

import java.util.List;

import com.domain.QaMenu;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaMenuService {
	
	QaMenu getOne(String id);
	
	List<QaMenu> getByAuth(QaMenu qaMenu, String[] authArr);
	
	List<QaMenu> findTree(String[] authArr);
	
	QaMenu save(QaMenu qaMenu);
	
	void delete(String id);
	
	void update(QaMenu newQaMenu);
	
}
