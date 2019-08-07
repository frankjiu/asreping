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
 * 角色实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "QA_ROLE")
public class QaRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// 主键
	@Id  
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;

	// 角色名称:普通用户/专家用户/管理员/黑名单
	@Column(name = "ROLE_NAME")
	private String roleName;

	// 是否停用 0:否/1:是
	@Column(name = "IS_STOP")
	private Integer isStop;
	
	// 创建时间
	@Column(name = "CREATE_TIME")
	private Date createTime;

	// 更新时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

}
