package cn.com.project.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.project.domain.User;
import cn.com.project.mapper.UserMapper;
import cn.com.project.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;
	
	public User login(String account,String password)   {
		Map<String , String> map = new HashMap<String,String>();
		map.put("account", account);
		map.put("password", password);
		User u = null;
		try {
			u = userMapper.login(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
}
