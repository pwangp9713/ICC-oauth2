package com.sinosoft.enums;

/**是与否枚举　
 * @author zouren
 *
 */
public enum YesOrNoEnum {
	YES("1","是"),
	NO("0","否");
	private String code;
	private String codeName;
	private YesOrNoEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
}
