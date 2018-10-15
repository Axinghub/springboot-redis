package com.neo.controller;

import com.neo.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class TestController {

	@RequestMapping(value="/getSessionId")
	@ResponseBody
	public String getSessionId(HttpServletRequest request){
		
		Object o = request.getSession().getAttribute("springboot");
		if(o == null){
			o = "spring boot 牛逼了!!!有端口"+request.getLocalPort()+"生成";
			request.getSession().setAttribute("springboot", o);
		}
		
		return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
	}

	@RequestMapping("/uid")
	@ResponseBody
	String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}

//	@Cacheable、@CachePut、@CacheEvict 注释介绍
//	http://rensanning.iteye.com/blog/2362184
//	Spring 在执行 @Cacheable 标注的方法前先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；若没有数据，执行该方法并将方法返回值放进缓存。
	@RequestMapping("/getUser")
	@Cacheable(value="user-key")
	@ResponseBody
	public User getUser() {
		User user=new User("email", "nickName", "passWord", "userName123", "regTime");
		System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
		return user;
	}
}
