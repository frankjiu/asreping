package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.QaRole;
import com.repository.QaRoleRepository;
import com.service.QaRoleService;

/**
 * 服务层实现
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class QaRoleServiceImpl implements QaRoleService {

	@Autowired
	private QaRoleRepository qaRoleRepository;

	/**
	 * 主键查询
	 */
	@Override
	public QaRole getOne(String id) {
		QaRole qaRole = qaRoleRepository.getOne(id);
		return qaRole;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional
	public QaRole save(QaRole qaRole) {
		return qaRoleRepository.saveAndFlush(qaRole);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delete(String id){
		qaRoleRepository.deleteById(id);
	}
	
	/**
	 * 修改
	 */
	@Override
	@Transactional
	public void update(QaRole newQaRole) {
		qaRoleRepository.saveAndFlush(newQaRole);
	}
	
	/**
	 * 树查询
	 */
	@Override
	public List<QaRole> findTree() {
		List<QaRole> list = qaRoleRepository.findTree();
		return list;
	}
}
