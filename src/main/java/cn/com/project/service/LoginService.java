package cn.com.project.service;

import cn.com.project.domain.User;

public interface LoginService {
   public User login(String account,String password) throws Exception;
}
