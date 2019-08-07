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
 * 用户角色实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "QA_USER_ROLE")
public class QaUserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// 主键
	@Id  
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;

	// 用户ID
	@Column(name = "USER_ID")
	private String userId;

	// 角色ID
	@Column(name = "ROLE_ID")
	private String roleId;

	// 创建时间
	@Column(name = "CREATE_TIME")
	private Date createTime;

	// 更新时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

}
