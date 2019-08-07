package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.QaUserRole;
import com.repository.MacoUserRoleRepository;
import com.service.MacoUserRoleService;

/**
 * 服务层实现
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class MacoUserRoleServiceImpl implements MacoUserRoleService {

	@Autowired
	private MacoUserRoleRepository macoUserRoleRepository;

	/**
	 * 主键查询
	 */
	@Override
	public QaUserRole getOne(String id) {
		QaUserRole macoUserRole = macoUserRoleRepository.getOne(id);
		return macoUserRole;
	}

	/**
	 * 条件UserId查询
	 */
	@Override
	public List<QaUserRole> findByUserId(String userId) {
		return macoUserRoleRepository.findByUserId(userId);
	}

	/**
	 * 新增
	 */
	@Override
	@Transactional
	public QaUserRole save(QaUserRole macoUserRole) {
		macoUserRole = macoUserRoleRepository.saveAndFlush(macoUserRole);
		return macoUserRole;
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delete(String id) {
		macoUserRoleRepository.deleteById(id);
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional
	public void update(QaUserRole newMacoUserRole) {
		macoUserRoleRepository.saveAndFlush(newMacoUserRole);
	}

}
