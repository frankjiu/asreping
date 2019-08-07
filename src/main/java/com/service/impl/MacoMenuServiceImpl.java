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
import com.repository.MacoMenuRepository;
import com.service.MacoMenuService;
import com.utils.ArrayToDbString;
import com.utils.StringUtil;

/**
 * 服务层实现
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Service
public class MacoMenuServiceImpl implements MacoMenuService {

	@Autowired
	private MacoMenuRepository macoMenuRepository;

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 主键查询
	 */
	@Override
	public QaMenu getOne(String id) {
		QaMenu macoMenu = macoMenuRepository.getOne(id);
		return macoMenu;
	}
	
	/**
	 * 条件查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QaMenu> getByAuth(QaMenu obj, String[] authArr) {
		//List<MacoMenu> list = macoMenuRepository.getByAuth(macoMenu, authArr);
		//return list;
		
		if (obj instanceof QaMenu) {
			DetachedCriteria criteria = DetachedCriteria.forClass(QaMenu.class);
			QaMenu macoMenu = (QaMenu) obj;
			String[] filedName = ConditionsFunction.getFiledName(macoMenu);
			for (int i = 0; i < filedName.length; i++) {
				// 属性名
				String name = filedName[i];
				// 属性值
				Object value = ConditionsFunction.getFieldValueByName(name, macoMenu);
				// 循环加入非空查询条件
				if (value != null && !"children".equals(name)) {
					criteria.add(Restrictions.eq(name, value));
				}
			}
			// 如果macoMenu的ID值为空则则不属于根目录查询, 加入权限
			if (StringUtil.isEmpty(macoMenu.getId()) && StringUtil.isNotEmptyArray(authArr)) {
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
		//List<MacoMenu> list = macoMenuRepository.findTree(authArr);
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
	public QaMenu save(QaMenu macoMenu) {
		return macoMenuRepository.saveAndFlush(macoMenu);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delete(String id){
		macoMenuRepository.deleteById(id);
	}
	
	/**
	 * 修改
	 */
	@Override
	@Transactional
	public void update(QaMenu newMacoMenu) {
		macoMenuRepository.saveAndFlush(newMacoMenu);
	}
	
}
