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
 * 问题实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "QA_QUESTION")
public class QaQuestion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// 主键
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;
	
	// 问题文字描述
	@Column(name = "QUESTION")
	private String question;
	
	// 问题辅助描述图片路径
	@Column(name = "QUESTION_PIC")
	private String questionPic;
	
	// 提问者
	@Column(name = "USER_ID")
	private String userId;
	
	// 创建时间
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	// 更新时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
}
