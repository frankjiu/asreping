package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.constant.Constants;
import com.domain.QaSubject;
import com.service.QaSubjectService;
import com.utils.LogAssist;
import com.utils.LogOperation;

import net.sf.json.JSONObject;

/**
 * 学科控制层
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Controller
@RequestMapping("/article")
public class QaSubjectController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private QaSubjectService qaSubjectService;

	/**
	 * 跳转页面
	 */
	@RequestMapping("/")
	@LogAssist(operationType = LogOperation.OP_GOTO, operationModule = LogOperation.WP_ARTICLE, describe = "学科--跳转页面")
	public String to(String id) {
		return "/article_manage";
	}
	
	/**
	 * 主键查询
	 */
	@RequestMapping("/getOne")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_ARTICLE, describe = "学科--主键查询")
	public String getOne(String id) {
		JSONObject js = new JSONObject();
		try {
			QaSubject qaSubject = qaSubjectService.getOne(id);
			js.put("data", qaSubject);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

	/**
	 * 学科名称条件查询
	 */
	@RequestMapping("/findBySubjectName")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_ARTICLE, describe = "学科--学科名称条件查询")
	public String findBySubjectName(QaSubject qaSubject) {
		JSONObject js = new JSONObject();
		try {
			List<QaSubject> list = qaSubjectService.findBySubjectName(qaSubject.getSubjectName());
			js.put("data", list);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}
	
	/**
	 * 学科级别条件查询
	 */
	@RequestMapping("/findBySubjectLevel")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_ARTICLE, describe = "学科--学科级别条件查询")
	public String findBySubjectLevel(QaSubject qaSubject) {
		JSONObject js = new JSONObject();
		try {
			List<QaSubject> list = qaSubjectService.findBySubjectLevel(qaSubject.getSubjectLevel());
			js.put("data", list);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}
	
	/**
	 * 树查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/findTree")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_ARTICLE, describe = "学科--树查询")
	public String findTree() {
		JSONObject js = new JSONObject();
		List<QaSubject> list = new ArrayList<>();
		// 从session获取当前用户的权限(菜单ID数组)
		Object object = request.getSession().getAttribute(Constants.SESSION_MENU_IDS_ARRAY);
		String[] menuIdsArr = (String[]) object == null ? new String[0] : (String[]) object;
		if (menuIdsArr.length == 0) {
			return null;
		}
		//String menudbstr = ArrayToDbString.transform(menuIdsArr);
		try {
			list = qaSubjectService.findTree();
			List newList = new ArrayList<>();
			for(QaSubject qaSubject : list){
				Map<Object,Object> map  = new LinkedHashMap<>();
				//TB.ID, TB.PID, TB.MNAME, TB.INDEX_ORDER, TB.UPDATE_TIME
				map.put("id", qaSubject.getId());
				map.put("pid", qaSubject.getPid());
				map.put("name", qaSubject.getSubjectName());
				/*map.put("times", article.getTimes());
				map.put("keyword", article.getKeyword());
				map.put("content", article.getContent());
				map.put("author", article.getAuthor());
				map.put("update_time", article.getUpdate_time());
				map.put("create_time", article.getCreate_time());*/
				newList.add(map);
			}
			js.put("data", newList);
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}
	
	/**
	 * 新增
	 */
	@RequestMapping("/save")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_ADD, operationModule = LogOperation.WP_ARTICLE, describe = "学科--新增")
	public String saveQaSubject(QaSubject qaSubject) {
		JSONObject js = new JSONObject();
		try {
			qaSubject.setCreateTime(new Date());
			qaSubject = qaSubjectService.save(qaSubject);
			js.put("id", qaSubject.getId());
			js.put("flag", true);
			js.put("msg", "success");
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_DEL, operationModule = LogOperation.WP_ARTICLE, describe = "学科--删除")
	public String delete(String id) {
		JSONObject js = new JSONObject();
		try {
			QaSubject subject = qaSubjectService.getOne(id);
			// 禁止删除更目录和一级学科
			if (subject.getSubjectLevel().equals(0) || subject.getSubjectLevel().equals(1)) {
				js.put("flag", false);
				js.put("msg", "failure");
			} else {
				qaSubjectService.delete(id);
				js.put("flag", true);
				js.put("msg", "success");
			}
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	@LogAssist(operationType = LogOperation.OP_MODIFY, operationModule = LogOperation.WP_ARTICLE, describe = "学科--修改")
	public String update(QaSubject newQaSubject) {
		JSONObject js = new JSONObject();
		try {
			QaSubject subject = qaSubjectService.getOne(newQaSubject.getId());
			// 禁止删除更目录和一级学科
			if (subject.getSubjectLevel().equals(0) || subject.getSubjectLevel().equals(1)) {
				js.put("flag", false);
				js.put("msg", "failure");
			} else {
				String id = newQaSubject.getId();
				QaSubject oldQaSubject = qaSubjectService.getOne(id);
				oldQaSubject.setSubjectName(newQaSubject.getSubjectName());
				oldQaSubject.setPid(newQaSubject.getPid());
				oldQaSubject.setIndexOrder(newQaSubject.getIndexOrder());
				oldQaSubject.setSubjectLevel(newQaSubject.getSubjectLevel());
				oldQaSubject.setUpdateTime(new Date());
				qaSubjectService.update(oldQaSubject);
				js.put("flag", true);
				js.put("msg", "success");
			}
		} catch (Exception e) {
			js.put("flag", false);
			js.put("msg", "failure");
			e.printStackTrace();
		}
		return js.toString();
	}

}
