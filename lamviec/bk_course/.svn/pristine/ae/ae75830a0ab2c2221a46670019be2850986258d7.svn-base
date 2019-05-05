package vn.edu.hcmut.core.enums;

public enum Errors {
	/*
	 * 
	 * ==========================
     * 200 - OK
     * 404 - Not Found
     * 500 - Internal Server Error
     * 201 - Created
     * 304 - Not Modified
     * 400 - Bad Request
     * 401 - Unauthorized
     * 403 - Forbidden
     */
	NOT_MODIFED("304", "Thông tin không thể cập nhật"),
	CREATED("201","Thông tin đã được cập nhật"),
	OK("200","Hoàn thành"),
	NOT_FOUND_DATA("404", "Không tìm thấy dữ liệu yêu cầu"),
	UNKNOWN("500", "Không xác định được nguyên nhân");

	private String code;
	private String text;
	private Errors(String code, String text) {
		this.code = code;
		this.text = text;
	}
	
	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return this.code.concat(":").concat(this.text);
	}
}
