package com.lightconf.admin.controller;

import com.lightconf.admin.controller.annotation.PermessionLimit;
import com.lightconf.admin.core.model.XxlConfGroup;
import com.lightconf.admin.core.model.XxlConfNode;
import com.lightconf.admin.core.util.ReturnT;
import com.lightconf.admin.dao.IXxlConfGroupDao;
import com.lightconf.admin.service.IXxlConfNodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 配置管理
 * @author xuxueli
 */
@Controller
@RequestMapping("/conf")
public class ConfController {

	@Resource
	private IXxlConfGroupDao xxlConfGroupDao;
	@Resource
	private IXxlConfNodeService xxlConfNodeService;
	
	@RequestMapping("")
	@PermessionLimit
	public String index(Model model, String znodeKey){

		List<XxlConfGroup> list = xxlConfGroupDao.findAll();

		model.addAttribute("XxlConfNodeGroup", list);
		return "conf/conf.index";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	@PermessionLimit
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length,
			String nodeGroup, String nodeKey) {
		return xxlConfNodeService.pageList(start, length, nodeGroup, nodeKey);
	}
	
	/**
	 * get
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@PermessionLimit
	public ReturnT<String> delete(String nodeGroup, String nodeKey){
		return xxlConfNodeService.deleteByKey(nodeGroup, nodeKey);
	}

	/**
	 * create/update
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@PermessionLimit
	public ReturnT<String> add(XxlConfNode xxlConfNode){
		return xxlConfNodeService.add(xxlConfNode);
	}
	
	/**
	 * create/update
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@PermessionLimit
	public ReturnT<String> update(XxlConfNode xxlConfNode){
		return xxlConfNodeService.update(xxlConfNode);
	}
	
}