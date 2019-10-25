package org.peng.OA.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.peng.OA.domain.User;
import org.peng.OA.util.common.OAConstants;
import org.peng.OA.service.OAService;
import org.peng.OA.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//处理用户请求控制器

@Controller
public class UserController {
	
	//自动注入UserService

	@Autowired
	@Qualifier("oaService")
	private OAService oaService;
		
	//处理登录请求

	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("loginname") String loginname,@RequestParam("password") String password,HttpSession session,ModelAndView mv){
		// 调用业务逻辑组件判断用户是否可以登录
		User user = oaService.login(loginname, password);
		if(user != null){
			// 将用户保存到HttpSession当中
			session.setAttribute(OAConstants.USER_SESSION, user);
			// 客户端跳转到main页面
			mv.setViewName("redirect:/main");
		}else{
			// 设置登录失败提示信息
			mv.addObject("message", "登录名或密码错误!请重新输入");
			// 服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
		return mv;
		
	}
	
	// 处理查询请求

	@RequestMapping(value="/user/selectUser")
	 public String selectUser(Integer pageIndex,@ModelAttribute User user,Model model){
		System.out.println("user = " + user);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		// 查询用户信息
		List<User> users = oaService.findUser(user, pageModel);
		model.addAttribute("users", users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";
		
	}
	
	
    //处理删除用户请求

	@RequestMapping(value="/user/removeUser")
	 public ModelAndView removeUser(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			oaService.removeUserById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/user/selectUser");
		// 返回ModelAndView
		return mv;
	}
	
	
    //处理修改用户请求

	@RequestMapping(value="/user/updateUser")
	 public ModelAndView updateUser(String flag,@ModelAttribute User user,ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询用户
			User target = oaService.findUserById(user.getId());
			// 设置Model数据
			mv.addObject("user", target);
			// 返回修改员工页面
			mv.setViewName("user/showUpdateUser");
		}else{
			// 执行修改操作
			oaService.modifyUser(user);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/selectUser");
		}
		// 返回
		return mv;
	}
	

    //处理添加请求

	@RequestMapping(value="/user/addUser")
	 public ModelAndView addUser(String flag,@ModelAttribute User user,ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("user/showAddUser");
		}else{
			// 执行添加操作
			oaService.addUser(user);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/selectUser");
		}
		// 返回
		return mv;
	}
	

//	 处理注销退出请求
	@RequestMapping(value="/logout")
	 public ModelAndView logout(ModelAndView mv,HttpSession session) {
		// 注销session
		session.invalidate();
		// 跳转到登录页面
		mv.setViewName("redirect:/loginForm");
		return mv;
	}
	
}
