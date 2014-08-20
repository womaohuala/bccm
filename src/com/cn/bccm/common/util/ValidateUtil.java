package com.cn.bccm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	private String check;
	private Pattern regex;
	private Matcher  matcher;
	public boolean getValidateResult(String check,String reg){
		regex = Pattern.compile(check);
	    matcher=regex.matcher(reg);	
	    return matcher.find();
	}
	
	/**
	 * 验证密码
	 * (6-16位字母或数字，区分大小写)
	 * @param pwd
	 * @return
	 */
	public boolean validatePwd(String pwd){
	    check = "^[a-zA-Z\\d]{6,16}$";
	    return getValidateResult(check,pwd);
	}
	
	public boolean validateEmail(String email){
	    check = "^[\\w-_\\.]+@([a-z|0-9|-]+\\.)+[a-z]{2,5}$";
	    return getValidateResult(check,email);
	}
	
	public boolean validateMobilePhone(String phone){
		check="^(01[3458]\\d{9})|(1[3458]\\d{9})$";
		return getValidateResult(check,phone);
	}
	public boolean validatePostcode(String postCode){
		check = "^\\d{6}$";
		return getValidateResult(check,postCode);
	}
	public boolean validateMobile(String mobile){
		check = "^\\d{11,20}$";
		return getValidateResult(check,mobile);
	}
	
	public boolean validateNum(String num){
		if(num==null){
			return false;
		}
		check = "^[0-9]+$";
		return getValidateResult(check,num);
	}
	public boolean validateStr(String str){
		check = "^[a-zA-Z]+$";
		return getValidateResult(check,str);
	}
	public boolean validateChinese(String chinese){
		check = "^[\\u4e00-\\u9fa5]+$";
		return getValidateResult(check,chinese);
	}
	public boolean validateIncludeChinese(String content){
		check = "[\\u4e00-\\u9fa5]";
		return getValidateResult(check,content);
	}
	public boolean validateLetterAndDigit(String content){
		check = "^[0-9a-zA-Z]+$";
		return getValidateResult(check,content);
	}
	public boolean validateLDC(String content){
		check = "^[0-9a-zA-Z\\u4e00-\\u9fa5]+$";
		return getValidateResult(check,content);
	}
	public boolean validateUser(String username){
		check = "^[a-zA-z][0-9a-zA-Z_]+$";
		return getValidateResult(check,username);
	}
	public boolean validateTimeFormat(String datetime){
		check="^[0-9]{4}(-[0-9]{2})?(-[0-9]{2})?$";
		return getValidateResult(check,datetime);
	}
	public int calculateLength(String str){
		int count=0;
		check = "^[\\u4e00-\\u9fa5]$";
		for(int i=0;i<str.length();i++)
			if(getValidateResult(check,String.valueOf(str.charAt(i))))
				count+=2;
			else
				count+=1;
		
		return count;
		
	}
	public boolean noteLenLimit(String note){
		if(calculateLength(note)>400)
			return true;
		return false;
			
	
	}
	public boolean validatePrice(String price){
		check = "^\\d{1,7}(\\.\\d{1,2})?$";
		return getValidateResult(check,price);
	}
	public boolean validateName(String name){
		check = "^(?!_|[0-9])[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
		return getValidateResult(check,name);
	}
	public boolean validateUserName(String userName){
		check = "^(?!_|[0-9])[a-zA-Z0-9_]+$";
		return getValidateResult(check,userName);
	}
	public String validateNote(String note){
		if (note == null)
			return "";
		note = note.replace("'", "‘");
		note = note.replace("%", "％");
		note = note.replace(";", "；");
		return note;
	}
	
	public String leftSpaceTrim(String s){
		return s.replaceAll("^\\s*", "");
	}
	public String rightSpaceTrim(String s){
		return s.replaceAll("\\s*$", "");
	}
	public String SpaceTrim(String s){
		if(s == null){
			return "";
		}
		return rightSpaceTrim(leftSpaceTrim(s));
	}
	public boolean isSpaceStr(String str){
		check="^\\s+$";
		return getValidateResult(check, str);
	}
	public String validateNote2(String note){
		if (note == null)
			return "";
		note = note.replace("'", "‘");
		note = note.replace("%", "％");
		note = note.replace(";", "；");
		note = note.replace("<", "＜");
		note = note.replace(">", "＞");
		note = note.replace("\"", "");
		return note;
	}
	
	public boolean isValidDate(String dateStr, String pattern) {
		check = "^\\d{4}-\\d{2}-\\d{2}$";
		if (!getValidateResult(check, dateStr))
			return false;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			df.setLenient(false);
			df.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;

		}
	}
	
	public static void main(String[] args) {
		ValidateUtil a = new ValidateUtil();
		System.out.println(a.validatePwd("111111"));
	}
	
}

