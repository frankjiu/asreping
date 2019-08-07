package com.service;

import java.util.List;

import com.domain.QaUserRole;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaUserRoleService {

	QaUserRole getOne(String id);

	List<QaUserRole> findByUserId(String id);

	QaUserRole save(QaUserRole qaUserRole);

	void delete(String id);

	void update(QaUserRole newQaUserRole);

}
