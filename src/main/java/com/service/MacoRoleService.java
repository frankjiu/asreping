package com.service;

import java.util.List;

import com.domain.QaRole;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface MacoRoleService {
	
	QaRole getOne(String id);
	
	List<QaRole> findTree();
	
	QaRole save(QaRole macoRole);
	
	void delete(String id);
	
	void update(QaRole newMacoRole);
	
}
