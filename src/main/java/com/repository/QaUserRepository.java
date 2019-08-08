package com.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.domain.QaUser;

/**
 * 服务层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface QaUserRepository extends JpaRepository<QaUser, String>, JpaSpecificationExecutor<QaUser> {

	/**
	 *  根据登录名查询用户
	 * @param loginName
	 * @return
	 */
	@Query(value = " SELECT * FROM qa_user u WHERE u.login_name=:loginName ", nativeQuery = true)
    List<QaUser> findByUserName(@Param("loginName") String loginName);
	
	/**
	 *  根据登录名和密码查询用户
	 * @param loginName
	 * @param password
	 * @return
	 */
	@Query(value = " SELECT * FROM qa_user u WHERE u.login_name=:loginName and u.pass_word=:password ", nativeQuery = true)
	List<QaUser> findByLoginNameAndPassWord(@Param("loginName") String loginName, @Param("password") String password);
	
	/**
	 *  根据登录名和(非)ID查询用户
	 * @param loginName
	 * @param id
	 * @return
	 */
	@Query(value = " SELECT * FROM qa_user u WHERE u.login_name=:loginName and u.id<>:id ", nativeQuery = true)
	List<QaUser> findByNameAndId(@Param("loginName") String loginName, @Param("id") Integer id);
	
	/**
	 *  根据ID删除用户
	 * @param id
	 */
	@Transactional 
	@Modifying 
	@Query(value = " DELETE FROM qa_user WHERE id=:id ", nativeQuery = true)
	void deleteByNativeId(@Param("id") Integer id);
	
	/**
	 * 根据干警查询其是否有设置账户
	 */
	@Query(value = " SELECT u.* FROM qa_user u LEFT JOIN police_info p ON u.police_id = p.id WHERE p.id=:id ", nativeQuery = true)
	List<QaUser> findUserByPoliceId(@Param("id") Integer id);
	
	/**
	 *  分页查询
	 * @param name
	 * @param createTimeBefore
	 * @param createTimeAfter
	 * @param pageable
	 * @return
	 */
	@Query(value = " select u.*, p.`name` policeName from qa_user u "
			+ " LEFT JOIN police_info p ON u.police_id = p.id "
			+ " where if(?1 !='', u.login_name like ?1,1=1) and if(?2 !='', u.create_time>=?2,1=1) and if(?3 !='', u.create_time<=?3,1=1) "
			+ " order by u.update_time desc, u.create_time desc ", 
			countQuery = " select count(*) from qa_user u "
			+ " LEFT JOIN police_info p ON u.police_id = p.id "
			+ " where if(?1 !='', u.login_name like ?1,1=1) and if(?2 !='', u.create_time>=?2,1=1) and if(?3 !='', u.create_time<=?3,1=1) "
			+ " order by u.update_time desc, u.create_time desc ",
			nativeQuery = true)
	Page<Map<String,Object>> findPage(String name, Date createTimeBefore, Date createTimeAfter, Pageable pageable);
	
}
