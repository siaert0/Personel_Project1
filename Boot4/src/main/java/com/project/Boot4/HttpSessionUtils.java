package com.project.Boot4;

import javax.servlet.http.*;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionedUser"; // 상수타입 잊지 말자.
	
	public static boolean isLoginUser(HttpSession session){
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		if(sessionedUser == null){
			return false;
		}
		return true;
	}
	
	public static UserVO getUserFromSession(HttpSession session){
		if(!isLoginUser (session)){
			return null;
		}
		return (UserVO)session.getAttribute(USER_SESSION_KEY);
	}
	
}
