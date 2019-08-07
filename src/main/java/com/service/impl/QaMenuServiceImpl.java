package com.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.ConditionsFunction;
import com.domain.QaMenu;
import com.repository.QaMenuRepository;
import com.service.QaMenuService;
import com.utils.ArrayToDbString;
import com.utils.StringUtil;

/**
 * 服务层实现
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class QaMenuServiceImpl implements QaMenuService {

	@Autowired
	private QaMenuRepository qaMenuRepository;

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 主键查询
	 */
	@Override
	public QaMenu getOne(String id) {
		QaMenu qaMenu = qaMenuRepository.getOne(id);
		return qaMenu;
	}
	
	/**
	 * 条件查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QaMenu> getByAuth(QaMenu obj, String[] authArr) {
		//List<QaMenu> list = qaMenuRepository.getByAuth(qaMenu, authArr);
		//return list;
		
		if (obj instanceof QaMenu) {
			DetachedCriteria criteria = DetachedCriteria.forClass(QaMenu.class);
			QaMenu qaMenu = (QaMenu) obj;
			String[] filedName = ConditionsFunction.getFiledName(qaMenu);
			for (int i = 0; i < filedName.length; i++) {
				// 属性名
				String name = filedName[i];
				// 属性值
				Object value = ConditionsFunction.getFieldValueByName(name, qaMenu);
				// 循环加入非空查询条件
				if (value != null && !"children".equals(name)) {
					criteria.add(Restrictions.eq(name, value));
				}
			}
			// 如果qaMenu的ID值为空则则不属于根目录查询, 加入权限
			if (StringUtil.isEmpty(qaMenu.getId()) && StringUtil.isNotEmptyArray(authArr)) {
				criteria.add(Restrictions.in("id", authArr));
			}
			
			/*HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
			Session session = hEntityManager.getSession();
			Query query = session.createSQLQuery("delete from test");*/
			
			Configuration cfg = new Configuration().configure(); 
		    //SessionFactory sessionFactory = cfg.buildSessionFactory(); 
		    //Session session = sessionFactory.openSession(); 
			
			Criteria ct = criteria.getExecutableCriteria(cfg.buildSessionFactory().getCurrentSession());
			
			List<QaMenu> list = (List<QaMenu>) ct.list();
			return list;
		} else {
			return null;
		}
		
	}
	
	/**
	 * 树查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<QaMenu> findTree(String[] authArr) {
		//List<QaMenu> list = qaMenuRepository.findTree(authArr);
		//return list;
		
		String sql = " SELECT MM.* FROM " +
				" ( " + 
				" SELECT M.* " + 
				" FROM   MACO_MENU M " + 
				" WHERE  1 = 1 " + 
				" START  WITH M.PID = '0' " + 
				" CONNECT BY PRIOR M.ID = M.PID " + 
				" ) " + 
				" MM " + 
				" WHERE 1 = 1 "; 
				
		StringBuffer stb = new StringBuffer(sql);
		String authStr = null;
		if (authArr != null) {
			authStr = ArrayToDbString.transform(authArr);
			stb.append(" AND MM.ID IN (" + authStr + ") ");
		}
		stb.append(" ORDER BY MM.INDEX_ORDER ASC ");
		
		Query query = entityManager.createNativeQuery(stb.toString());
		List list = query.getResultList();
		return list;
		
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional
	public QaMenu save(QaMenu qaMenu) {
		return qaMenuRepository.saveAndFlush(qaMenu);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delete(String id){
		qaMenuRepository.deleteById(id);
	}
	
	/**
	 * 修改
	 */
	@Override
	@Transactional
	public void update(QaMenu newQaMenu) {
		qaMenuRepository.saveAndFlush(newQaMenu);
	}
	
}
