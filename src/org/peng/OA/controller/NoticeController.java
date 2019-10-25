package org.peng.OA.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.peng.OA.domain.Notice;
import org.peng.OA.domain.User;
import org.peng.OA.service.OAService;
import org.peng.OA.util.common.OAConstants;
import org.peng.OA.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 处理公告请求控制器
 

@Controller
public class NoticeController {

	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("oaService")
	private OAService oaService;
	
	/**
	 * 处理/login请求
	 * */
	@RequestMapping(value="/notice/selectNotice")
	 public String selectNotice(Model model,Integer pageIndex,
			 @ModelAttribute Notice notice){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Notice> notices = oaService.findNotice(notice, pageModel);
		model.addAttribute("notices", notices);
		model.addAttribute("pageModel", pageModel);
		return "notice/notice";
		
	}
	
	/**
	 * 处理添加请求
	 * @param Integer id  要显示的公告id
	 * @param Model model
	 * */
	@RequestMapping(value="/notice/previewNotice")
	 public String previewNotice(
			 Integer id,Model model){
		
		Notice notice = oaService.findNoticeById(id);
		
		model.addAttribute("notice", notice);
		// 返回
		return "notice/previewNotice";
	}
	
	/**
	 * 处理删除公告请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/notice/removeNotice")
	 public ModelAndView removeNotice(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除公告
			oaService.removeNoticeById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/notice/selectNotice");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Notice notice  要添加的公告对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/notice/addNotice")
	 public ModelAndView addNotice(
			 String flag,
			 @ModelAttribute Notice notice,
			 ModelAndView mv,
			 HttpSession session){
		if(flag.equals("1")){
			mv.setViewName("notice/showAddNotice");
		}else{
			User user = (User) session.getAttribute(OAConstants.USER_SESSION);
			notice.setUser(user);
			oaService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Notice notice  要添加的公告对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/notice/updateNotice")
	 public ModelAndView updateNotice(
			 String flag,
			 @ModelAttribute Notice notice,
			 ModelAndView mv,
			 HttpSession session){
		if(flag.equals("1")){
			Notice target = oaService.findNoticeById(notice.getId());
			mv.addObject("notice",target);
			mv.setViewName("notice/showUpdateNotice");
		}else{
			oaService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		// 返回
		return mv;
	}
	
	
}
