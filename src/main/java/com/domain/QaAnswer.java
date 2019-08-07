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
 * 回答实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "QA_ANSWER")
public class QaAnswer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// 主键
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;
	
	// 回答对应问题的主键
	@Column(name = "QUESTION_ID")
	private String questionId;
	
	// 回答文字描述
	@Column(name = "ANSWER")
	private String answer;
	
	// 回答辅助图片路径
	@Column(name = "ANSWER_PIC")
	private String answerPic;
	
	// 评价星级:1-5星级
	@Column(name = "STARS")
	private Integer stars;
	
	// 获得金币数10*K
	@Column(name = "COINS")
	private Integer coins;
	
	// 评价系数常量K:1星:0.1/2星:0.3/3星:0.5/4星:0.8/5星:1
	@Column(name = "K")
	private double K;
	
	// 创建时间
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	// 更新时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
}
