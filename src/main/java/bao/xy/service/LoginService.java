package bao.xy.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @CreateTime: 2020-10-13-08-18
 */
public interface LoginService {

    int login(String uname, String pwd, HttpServletRequest request);
}
