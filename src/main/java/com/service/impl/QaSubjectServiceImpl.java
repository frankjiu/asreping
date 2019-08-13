package com.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.QaSubject;
import com.repository.QaSubjectRepository;
import com.service.QaSubjectService;
import com.utils.StringUtil;

/**
 * 服务层实现
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class QaSubjectServiceImpl implements QaSubjectService {

	@Autowired
	private QaSubjectRepository qaSubjectRepository;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public QaSubject getOne(String id) {
		QaSubject qaSubject = qaSubjectRepository.getOne(id);
		return qaSubject;
	}
	
	@Override
	public List<QaSubject> findBySubjectName(String subjectName) {
		List<QaSubject> QaSubject = qaSubjectRepository.findBySubjectName(subjectName);
		return QaSubject;
	}
	
	@Override
	public List<QaSubject> findBySubjectLevel(Integer subjectLevel) {
		List<QaSubject> QaSubject = qaSubjectRepository.findBySubjectLevel(subjectLevel);
		return QaSubject;
	}
	
	@Override
	public List<QaSubject> findTree() {
		List<QaSubject> list = qaSubjectRepository.findAll();
		return list;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional
	public QaSubject save(QaSubject qaSubject) {
		return qaSubjectRepository.saveAndFlush(qaSubject);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delete(String id){
		qaSubjectRepository.deleteById(id);
	}
	
	/**
	 * 修改
	 */
	@Override
	@Transactional
	public void update(QaSubject newQaSubject) {
		qaSubjectRepository.saveAndFlush(newQaSubject);
	}
	
}
