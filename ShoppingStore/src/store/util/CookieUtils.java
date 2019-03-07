package store.util;

import javax.servlet.http.Cookie;

public class CookieUtils {
	/**
	 * get cookie from array of cookies by name
	 * @param name cookie's name
	 * @param cookies  cookie array
	 * @return cookie
	 */
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if(cookies!=null){
			for (Cookie c : cookies) {
				//get cookie by name
				if(name.equals(c.getName())){
					return c;
				}
			}
		}
		return null;
	}
}
