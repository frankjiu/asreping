package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.QaUserRole;
import com.repository.QaUserRoleRepository;
import com.service.QaUserRoleService;

/**
 * 服务层实现
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class QaUserRoleServiceImpl implements QaUserRoleService {

	@Autowired
	private QaUserRoleRepository qaUserRoleRepository;

	/**
	 * 主键查询
	 */
	@Override
	public QaUserRole getOne(String id) {
		QaUserRole qaUserRole = qaUserRoleRepository.getOne(id);
		return qaUserRole;
	}

	/**
	 * 条件UserId查询
	 */
	@Override
	public List<QaUserRole> findByUserId(String userId) {
		return qaUserRoleRepository.findByUserId(userId);
	}

	/**
	 * 新增
	 */
	@Override
	@Transactional
	public QaUserRole save(QaUserRole qaUserRole) {
		qaUserRole = qaUserRoleRepository.saveAndFlush(qaUserRole);
		return qaUserRole;
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delete(String id) {
		qaUserRoleRepository.deleteById(id);
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional
	public void update(QaUserRole newQaUserRole) {
		qaUserRoleRepository.saveAndFlush(newQaUserRole);
	}

}
