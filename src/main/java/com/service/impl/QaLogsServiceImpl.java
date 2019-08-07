package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.QaLogs;
import com.repository.QaLogsRepository;
import com.service.QaLogsService;

/**
 * 服务层实现
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class QaLogsServiceImpl implements QaLogsService {

	@Autowired
	private QaLogsRepository qaLogsRepository;

	/**
	 * 主键查询
	 */
	@Override
	public QaLogs getOne(String id) {
		QaLogs qaLogs = qaLogsRepository.getOne(id);
		return qaLogs;
	}
	
	/**
	 * 条件查询
	 */
	@Override
	public List<QaLogs> findByUserId(String userId) {
		List<QaLogs> QaLogs = qaLogsRepository.findByUserId(userId);
		return QaLogs;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional
	public QaLogs save(QaLogs qaLogs) {
		return qaLogsRepository.saveAndFlush(qaLogs);
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public Page<Map<String,Object>> findPage(String userId, Date reateTimeBefore, Date createTimeAfter, Pageable pageable) {
		Page<Map<String,Object>> page = qaLogsRepository.findPage(userId, reateTimeBefore, createTimeAfter, pageable);
		return page;
	}
	
}
