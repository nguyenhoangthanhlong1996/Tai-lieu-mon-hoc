package vn.edu.hcmut.core.utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.hcmut.core.enums.CookieName;
import vn.edu.hcmut.core.utilities.encrypt.EncryptAESProvider;

public class ServletCookies {
	@Autowired
	protected HttpServletResponse servletResponse;
	@Autowired
	protected HttpServletRequest servletRequest;
	@Autowired
	private EncryptAESProvider encryptAESProvider;

	public void setCookie(CookieName cookieName, String cookieData) throws Exception {
		//Delete cookie old
		deleteCookie(cookieName);
		//Encrypt data
		cookieData = encryptAESProvider.encrypt(cookieData);
		//Save to cookie
		Cookie cookie = new Cookie(cookieName.toString(), cookieData);
		cookie.setHttpOnly(true);
		//cookie.setSecure(true); /* requires HTTPS */
		cookie.setMaxAge(60 * 60 * 1);
		servletResponse.addCookie(cookie);
	}
	
	public String getCookieValue(CookieName cookieName) throws Exception {
		Cookie cookies[] = servletRequest.getCookies();
		Cookie myCookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName.toString())) {
					myCookie = cookies[i];
					break;
				}
			}
		}
		if (myCookie == null) {
			return null;
		}
		//Decrypt d
		String cookieData = encryptAESProvider.decrypt(myCookie.getValue());
		return cookieData;
	}
	
	public void deleteAllCookies() {
		Cookie[] cookies = servletRequest.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				cookie.setValue("");
				cookie.setMaxAge(-1);
				servletResponse.addCookie(cookie);
			}
	}
	
	public void deleteCookie(CookieName cookieName) {
		Cookie[] cookies = servletRequest.getCookies();
		if (cookies != null)
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName.toString())) {
					cookies[i].setValue("");
					cookies[i].setMaxAge(-1);
					servletResponse.addCookie(cookies[i]);
					break;
				}
			}
	}
}
