package com.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 日志实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "Qa_LOGS")
public class QaLogs implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// 主键
	@Id  
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;

	// 操作用户
	@Column(name = "USER_ID")
	private String userId;

	// 用户IP地址
	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	// 操作模块
	@Column(name = "OP_MODULE")
	private String opModule;

	// 操作描述
	@Column(name = "INTRODUCE")
	private String introduce;
	
	// 操作时间
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	// 起始操作时间
	@Transient
	private Date createTimeBefore;
	
	// 结束操作时间
	@Transient
	private Date createTimeAfter;

}
