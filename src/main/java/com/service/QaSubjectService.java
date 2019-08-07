package com.service;

import java.util.List;

import com.domain.QaSubject;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaSubjectService {
	
	QaSubject getOne(String id);
	
	List<QaSubject> findBySubjectName(String subjectName);
	
	List<QaSubject> findBySubjectLevel(Integer subjectLevel);
	
	List<QaSubject> findTree();
	
	QaSubject save(QaSubject macoArticle);
	
	void delete(String id);
	
	void update(QaSubject newQaSubject);

	
}
