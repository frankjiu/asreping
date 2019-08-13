package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.domain.QaUser;
import com.repository.QaUserRepository;
import com.service.QaUserService;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class QaUserServiceImpl implements QaUserService {
	
	@Autowired
	QaUserRepository qaUserRepository;
	
	public Optional<QaUser> findById(String id){
		return qaUserRepository.findById(id);
	}

	@Override
	public QaUser getOne(String id) {
		QaUser qaUser = qaUserRepository.findById(id).get();
		return qaUser;
	}

	@Override
	public List<QaUser> findByUserName(String userName) {
		List<QaUser> list = qaUserRepository.findByUserName(userName);
		return list;
	}
	
	@Override
	public List<QaUser> findByLoginNameAndPassWord(QaUser qaUser) {
		List<QaUser> list = qaUserRepository.findByLoginNameAndPassWord(qaUser.getLoginName(), qaUser.getPassWord());
		return list;
	}

	@Override
	public QaUser save(QaUser qaUser) {
		qaUser = qaUserRepository.saveAndFlush(qaUser);
		return qaUser;
	}

	@Override
	public void delete(String id) {
		qaUserRepository.deleteById(id);
	}

	@Override
	public void update(QaUser newQaUser) {
		qaUserRepository.saveAndFlush(newQaUser);
	}

	@Override
	public Integer count(QaUser query) {
		Integer count = (int) qaUserRepository.count();
		return count;
	}

	@Override
	//public List<QaUser> paging(QaUser query, PageUtil page) {
	public Page<Map<String,Object>> findPage(String loginName, Date createTimeBefore, Date createTimeAfter, Pageable pageable) {
		return qaUserRepository.findPage(loginName, createTimeBefore, createTimeAfter, pageable);
	}
}
