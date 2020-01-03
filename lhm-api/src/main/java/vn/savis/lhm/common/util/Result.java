package vn.savis.lhm.common.util;

/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
public enum Result {

	SUCCESS(1, "Success"),
	UNAUTHORIZED(401, "Unauthorized"),
	TOKEN_EXPIRE_TIME(401, "Token expire time"),
	BAD_REQUEST(400, "Bad request"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Api not found"),
	METHOD_NOT_ALLOW(405, "Method not allow"),
	SERVER_ERROR(500, "Server error"),
	
	COUNTRY_NOT_EXIST(404, "Country not existed"),
	CURRENCY_NOT_EXIST(404, "Currency not existed"),
	DEPARTMENT_NOT_EXIST(404, "Deparement not existed"),
	DISTRICT_GROUP_NOT_EXIST(404, "District not existed"),
	PROVINCE_NOT_EXIST(404, "Province not existed"),
	DM_DV_KIEM_TOAN_NOT_EXIST(404, "Dau moi don vi kiem toan not existed"),
	GROUP_USER_NOT_EXIST(404, "Group user not existed"),
	LINH_VUC_KIEM_TOAN_NOT_EXIST(404, "Linh vuc kiem toan not existed"),
	NATION_NOT_EXIST(404, "Nation not existed"),
	OGRANIZATION_NOT_EXIST(404, "Ogranization not existed"),
	POSIION_NOT_EXIST(404, "Position not existed"),
	RELIGION_NOT_EXIST(404, "Religion not existed"),
	REPORT_FORM_NOT_EXIST(404, "Report form not existed"),
	REPORT_TARGET_NOT_EXIST(404, "Report target not existed"),
	WARD_NOT_EXIST(404, "Ward not existed"),
	
	COUNTRY_IS_EXISTED(400, "Country is existed"),
	CURRENCY_IS_EXISTED(400, "Currency is existed"),
	DEPARTMENT_IS_EXISTED(400, "Deparement is existed"),
	DISTRICT_GROUP_IS_EXISTED(400, "District is existed"),
	PROVINCE_IS_EXISTED(400, "Province is existed"),
	DM_DV_KIEM_TOAN_IS_EXISTED(400, "Dau moi don vi kiem toan is existed"),
	GROUP_USER_IS_EXISTED(400, "Group user is existed"),
	LINH_VUC_KIEM_TOAN_IS_EXISTED(400, "Linh vuc kiem toan is existed"),
	NATION_IS_EXISTED(400, "Nation is existed"),
	OGRANIZATION_IS_EXISTED(400, "Ogranization is existed"),
	POSIION_IS_EXISTED(400, "Position is existed"),
	RELIGION_IS_EXISTED(400, "Religion is existed"),
	REPORT_FORM_IS_EXISTED(400, "Report form is existed"),
	REPORT_TARGET_IS_EXISTED(400, "Report target is existed"),
	WARD_IS_EXISTED(400, "Ward is existed"),
	CODE_IS_EXISTED(400, "Code is existed"),
	
	REPORT_TARGET_CODE_IS_EXISTED(400, "Report target code is existed"),
	REPORT_FORM_CODE_IS_EXISTED(400, "Report form code is existed"),
	MA_LINH_VUC_KIEM_TOAN_IS_EXISTED(400, "Ma linh vuc kiem toan is existed"),
	
	;
	
	private int code;
	private String message;
	
	Result(int code, String message){
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isSuccess() {
		return (this.code==1);
	}
}
