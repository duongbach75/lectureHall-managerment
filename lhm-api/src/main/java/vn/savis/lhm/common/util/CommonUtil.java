package vn.savis.lhm.common.util;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.dto.ServiceResponse;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
public class CommonUtil {
	public static boolean isNumeric(String str)
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
	
	public static boolean isAuth() {
		
		return true;
	}
	
	public static boolean isQueryPage(HttpServletRequest request) {
		String goToURL = request.getParameter("page");
		if(goToURL == null) {
			return false; 
		}
		return true;
	}
	/**
	 * is Not a Error
	 * */
	public static Boolean isNaE(Pageable pageable, HttpServletRequest request, ServiceResponse<?> response) {
		Boolean isNaE = true;
		
		if (!CommonUtil.isAuth()) {
			ErrCodeForbidden(response);
			isNaE = false;
		} else if(request != null) {
			String goToURL = request.getParameter("page");
			if(goToURL == null || !CommonUtil.isNumeric(goToURL)) {
				ErrCodeBadRequest(response);
				isNaE = false;
			}
		}
		return isNaE;
	}
	
	
	
	public static String getCurrentUserName( ){
//		try {
//			Object principal = getAuthorization().getPrincipal();
//			if (principal instanceof User) {
//				User user = (User) getAuthorization().getPrincipal();
//				return user.getUsername();
//			} else {
//				return principal.toString();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "admin";
	}
	
	public static String standardized(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }
	
	public static String standardizedCode(String str) {
        str = str.trim();
        str = str.replaceAll(" ", "");
        
        return str;
    }
	
	public static String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String currentTime = df.format(today);
		
		return currentTime;
	}
	
	public static String getCurrentStatus(String status) {
		String result = null;
		if (status != null && status.equals("0")) {
			result = "Deactive";
		} else if (status != null && status.equals("1")) {
			result = "Active";
		} else {
			result = "Unknown";
		}
		
		return result;
	}
	
	public static void toHandleSuccess(Pageable pageable, Page<?> pages, ServiceResponse<?> response) throws Exception {
		// page truyền vào >= tổng số page (dấu bằng vì page tính từ 0)
		if(pageable.getPageNumber() >= pages.getTotalPages()) {
			throw new Exception();
		}
		
		SuccessCode(response);
	}
	
	public static void SuccessCode(ServiceResponse<?> response) {
		response.setCode(Constants.SUCCESS_CODE);
		response.setMessage(Constants.SUCCESS_MSG);
	}
	public static void ErrCodeMethodNotAllow(ServiceResponse<?> response) {
		response.setData(null);
		response.setCode(Constants.ERR_CODE_METHOD_NOT_ALLOW);
		response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_METHOD_NOT_ALLOW);
	}
	
	public static void ErrCodeNotFound(ServiceResponse<?> response) {
		response.setData(null);
		response.setCode(Constants.ERR_CODE_NOT_FOUND);
		response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_NOT_FOUND);
	}
	
	public static void ErrCodeBadRequest(ServiceResponse<?> response) {
		response.setData(null);
		response.setCode(Constants.ERR_CODE_BAD_REQUEST);
		response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
	}
	
	public static void ErrCodeForbidden(ServiceResponse<?> response) {
		response.setData(null);
		response.setCode(Constants.ERR_CODE_FORBIDDEN);
		response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_FORBIDDEN);
	}
	
	public static String setStatusDefault() {
		return "1";
	}
}
