package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.domain.QaUser;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaUserService {
	
	QaUser getOne(String id);

	List<QaUser> findByUserName(String userName);

	List<QaUser> findByLoginNameAndPassWord(QaUser qaUser);
	
	QaUser save(QaUser qaUser);

	void delete(String id);

	void update(QaUser newQaUser);

	Integer count(QaUser query);

	//List<QaUser> findPage(QaUser qaUser, PageUtil page);
	Page<Map<String,Object>> findPage(String loginName, Date createTimeBefore, Date createTimeAfter, Pageable pageable);

}
