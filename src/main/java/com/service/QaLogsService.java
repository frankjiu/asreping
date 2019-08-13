package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.domain.QaLogs;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaLogsService {
	
	QaLogs getOne(String id);
	
	QaLogs save(QaLogs qaLogs);
	
	List<QaLogs> findByUserId(String userId);
	
	Page<Map<String,Object>> findPage(String userId, Date reateTimeBefore, Date createTimeAfter, Pageable pageable);
	
}
