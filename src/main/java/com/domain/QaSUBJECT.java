package com.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 学科实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "QA_SUBJECT")
public class QaSUBJECT implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// 主键
	@Id  
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;

	// 学科名称
	@Column(name = "SUBJECT_NAME")
	private String subjectName;

	// 父学科ID
	@Column(name = "PID")
	private String pid;
	
	// 学科层级
	@Column(name = "SUBJECT_LEVEL")
	private Integer subjectLevel;
	
	// 学科序号,默认未排序0
	@Column(name = "INDEX_ORDER")
	private Integer indexOrder;
	
	// 创建时间
	@Column(name = "CREATE_TIME")
	private Date createTime;

	// 更新时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
}
