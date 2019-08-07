package com.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.utils.TreeNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "QA_MENU")
@EqualsAndHashCode(callSuper=true)
public class QaMenu extends TreeNode implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// 主键
	@Id  
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;

	// 菜单名称
	@Column(name = "MENU_NAME")
	private String menuName;

	// 菜单URL地址
	@Column(name = "MENU_URL")
	private String menuUrl;
	
	// 父菜单ID
	@Column(name = "PID")
	private String pid;
	
	// 菜单层级 0:根菜单/1:父菜单/2:子菜单
	@Column(name = "MENU_LEVEL")
	private Integer menuLevel;
	
	// 菜单序号,默认未排序0
	@Column(name = "INDEX_ORDER")
	private Integer indexOrder;
	
	// 创建时间
	//@JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
	@Column(name = "CREATE_TIME")
	private Date createTime;

	// 更新时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
}
