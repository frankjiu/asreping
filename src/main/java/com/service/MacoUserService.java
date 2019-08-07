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
public interface MacoUserService {
	
	QaUser getOne(String id);

	List<QaUser> findByUserName(String userName);

	List<QaUser> findByLoginNameAndPassWord(QaUser macoUser);
	
	QaUser save(QaUser macoUser);

	void delete(String id);

	void update(QaUser newMacoUser);

	Integer count(QaUser query);

	//List<MacoUser> findPage(MacoUser macoUser, PageUtil page);
	Page<Map<String,Object>> findPage(String loginName, Date createTimeBefore, Date createTimeAfter, Pageable pageable);

}
