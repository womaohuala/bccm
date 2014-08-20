package com.cn.bccm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class StringUtil {
	
	public static boolean isEmpty(String str) {
		return null == str || str.length() < 1;
	}

	public static boolean isEmptyTrim(String str) {
		return null == str || str.trim().length() < 1;
	}

	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 移除 最后一个mark
	 * 
	 * @param orig
	 *            源对象
	 * @param mark
	 * @return
	 */
	public static String removeLast(String orig, String mark) {
		return removeLastMark(new StringBuilder(orig), mark).toString();
	}

	/**
	 * 移除 最后一个mark
	 * 
	 * @param builder
	 *            源对象
	 * @param mark
	 * @return
	 */
	public static StringBuilder removeLastMark(StringBuilder builder,
			String mark) {
		if (notEmpty(builder) && notEmpty(mark)) {
			int begin = builder.lastIndexOf(mark);
			if (begin > 0) {
				builder.delete(begin, begin + mark.length());
			}
		}
		return builder;
	}

	public static boolean notEmpty(StringBuilder str) {
		return !(null == str || str.length() < 1);
	}

//
//	/**
//	 * 将集合 转换为 按默认“,” 连接的字符串 如：aaa,bbb,ccc,ddd 形式 当 field 不为空时 取field值进行连接
//	 * 
//	 * @param <T>
//	 * @param list
//	 * @param field
//	 *            连接数据 对应的T类型的属性名
//	 * @param builder
//	 *            连接的builder对象
//	 * @return
//	 */
//	public static <T> StringBuilder collectionJoin(Collection<T> list,
//			String field, StringBuilder... builder) {
//		return collectionJoin(list, ",", field, builder.length > 0 ? builder[0]
//				: null);
//	}

//	/**
//	 * 将字符串集合 转换为 按默认“,” 连接的字符串 如：aaa,bbb,ccc,ddd 形式
//	 * 
//	 * @param list
//	 * @param builder
//	 *            连接的builder对象
//	 * @return
//	 */
//	public static StringBuilder collectionJoin(Collection<String> list,
//			StringBuilder... builder) {
//		return collectionJoin(list, ",", null, builder.length > 0 ? builder[0]
//				: null);
//	}

	/**
	 * 消除fck 传入后台文本内容 为html时，附加的空格。 add by huangyimin ${date} Nov 5, 2010
	 * 
	 * @param orig
	 * @return
	 */
	public static String filterBlankOutTag(String orig) {
		if (notEmpty(orig)) {
			Matcher mat = Pattern.compile("> +<").matcher(orig);
			while (mat.find()) {
				String res = mat.group();
				orig = orig.replaceFirst(res, res.replace(" ", ""));
			}
		}
		return orig;
	}

	/**
	 * 第一个字母转换成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upperFirstLetter(String str) {
		String first = String.valueOf(str.charAt(0));
		return str.replaceFirst(first, first.toUpperCase());
	}

	/**
	 * 第一个字母转换成小写
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerFirstLetter(String str) {
		String first = String.valueOf(str.charAt(0));
		return str.replaceFirst(first, first.toLowerCase());
	}

	/**
	 * 生成包含字母与数字的随机字符串
	 * 
	 * @param length
	 *            要生成的目标长度
	 * 
	 * 
	 * @return
	 */
	public static String randomString(int length) {
		int aVal = (int) new Character('A').charValue();
		String[] arr = new String[36];
		for (int i = 0; i < 26; i++) {
			arr[i] = new String(new char[] { (char) (aVal + i) });
		}
		for (int i = 0; i < 10; i++) {
			arr[i + 26] = new String(i + "");
		}

		Random r = new Random();
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < length; i++) {
			res.append(arr[r.nextInt(36)]);
		}

		return res.toString();
	}

	/**
	 * 向前填充字符串
	 * 
	 * @param str
	 *            原字符串
	 * @param key
	 *            填充的字符
	 * @param length
	 *            填充后的字符串长度
	 * @return
	 * @author yzw
	 * @date 2012-6-19 下午2:18:40
	 */
	public static String cramStringWithKeyAtBefore(String str, String key,
			int length) {

		if (str == null || str.length() >= length) {
			return str;
		}

		int strLength = str.length();
		String beforeStr = "";
		for (int i = 0; i < length - strLength; i++) {
			beforeStr += key;
		}
		return beforeStr + str;
	}

	/**
	 * 
	 * 功能说明:字符串替换函数 创建人:程宏 创建时间:2013-1-11 下午5:35:07
	 * 
	 * @param strSource
	 *            要替换的字符串
	 * @param strFrom
	 *            要替换的字符
	 * @param strTo
	 *            要替换成的目标字符
	 * @param type
	 *            模板路径
	 * @return String
	 * 
	 */
	public static String replace(String strSource, String strFrom,
			String strTo, String type) {
		if (strSource == null) {
			return null;
		}
		// 判断是否已经包含模板路径
		if (strSource.indexOf(type) != -1) {
			return strSource;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	/**
	 * 根据分隔符分割字符串
	 * 
	 * @param str
	 * @param delim
	 * @return String[] 分割后的数组
	 */
	public static String[] splitString(String str, String delim) {
		if (str == null || delim == null)
			return new String[0];

		java.util.StringTokenizer st = new StringTokenizer(str, delim);
		List<String> list = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list.toArray(new String[list.size()]);
	}

	public static String ClobToString(Clob clob) {

		if (clob == null) {
			return "";
		}
		String reString = "";
		try {
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reString;
	}

	/**
	 * 
	 * 功能说明:判断是否包含一个字符串 创建人: g.chengh 创建时间:2013-6-27 下午4:33:07
	 * 
	 * @param a
	 * @param b
	 * @return boolean
	 * 
	 */
	public static boolean include(String a, String b) {
		return a.contains(b);

	}

	/**
	 * 
	 *功能说明:将数组转换为 按默认“,” 连接的字符串 如：aaa,bbb,ccc,ddd 形式
	 *创建人: g.chengh
	 *创建时间:2013-7-11 下午5:49:37
	 *@param arr
	 *@return String
	 *
	 */
	public static String ArrayToString(String[] arr) {
		String str = Arrays.toString(arr).trim();
		int len = str.length();
		//去空格去换行
		str = str.substring(1, len - 1).replaceAll("\n", "").replaceAll("\r", "").replaceAll(" ", "").trim();;
		return str;

	}
	/**
	 * 
	 *功能说明:将数组转换为 按默认“@,@” 连接的字符串 如：@aaa@,@bbb@,@ccc@,@ddd@ 形式
	 *创建人: g.chengh
	 *创建时间:2013-7-17 上午11:49:19
	 *@param arr
	 *@return String
	 *
	 */
	public static String replaceToString(String[] arr) {
		String str = ArrayToString(arr);
		//添加特殊符号 以：@aaa@,@bbb@,@ccc@,@ddd@ 形式输出
		String tmbstr=("@"+StringUtil.replace(str.trim(),",","@,@","@,@")+"@").trim();
		return tmbstr;

	}
	
	public static boolean isEmpty(Object o) throws IllegalArgumentException {
		if(o == null) return true;
		if(o instanceof String) {
			return (o == null) || (o.toString().trim().length() == 0) || "null".equalsIgnoreCase(o.toString());
		} else if(o instanceof Collection) {
			if(((Collection)o).isEmpty()){
				return true;
			}
		} else if(o.getClass().isArray()) {
			if(Array.getLength(o) == 0){
				return true;
			}
		} else if(o instanceof Map) {
			if(((Map)o).isEmpty()){
				return true;
			}
		}else {
			return false;
		}

		return false;
	}

    // 替换Null为空值
    public static String convertNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    
    // 不判断null
    public static boolean equals(String str1, String str2) {
    	if(str1.compareTo(str2) == 0) {
    		return true;
    	}
    	return false;
    }

    public static String toString(Integer iSource){
        if(iSource==null) return "";
        else return iSource.toString();
    }

    public static boolean isNullString(String str){
        if(str==null || "".equals(str) || "null".equals(str)) return true;
        return false;
    }

    public static String replaceFirst(String strSource,String regex, String replacement){
        if(isEmpty(strSource)) return "";
        return strSource.replaceFirst(regex,replacement);
    }

    public static String getAvailableURL(String srcURI){
        StringBuffer bufferURI=new StringBuffer();
        if(!isEmpty(srcURI)){
            String[] covertURI=srcURI.split("\\?");
            if(covertURI.length>0 && !isEmpty(covertURI[0])) bufferURI.append(covertURI[0]);
            bufferURI.append("?");
            if(covertURI.length==2){
                String[] parameters=covertURI[1].split("\\&");
                for(int i=0;i<parameters.length;i++){
                    String[] valuePair=parameters[i].split("=");
                    if (valuePair.length == 2) {
                        try {
                            bufferURI.append(valuePair[0]).append("=").append(URLEncoder.encode(valuePair[1], "UTF-8")).append("&");
                        } catch (Exception e) {}
                    }
                }
            }
            return bufferURI.toString();
        }
        return srcURI;
    }

    public static String getStringBeforeSplit(String str, String separator) {
        if (str == null || separator == null || str.length() == 0 || separator.length() == 0) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String getStringAfterSplit(String str, String separator) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (separator == null || separator.length() == 0) {
            return "";
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == (str.length() - separator.length())) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 比较页为空时显示无
     * @param str
     * @return
     */
    public static String getEmptyDefaultValue(String str){
        if(StringUtil.isEmpty(str)){
            return "无";
        }else{
            return str;
        }
    }

    public static String getDoubleStr(Double value){
        if(value ==null || "null".equals(value)){
            value = 0.0;    
        }
        return String.valueOf(value);
    }

    public static String getIntegerStr(Integer value){
        if(value ==null || "null".equals(value)){
            value = 0;
        }
        return String.valueOf(value);
    }
    public static Integer getIntegerByStr(String value){
        if(StringUtil.isEmpty(value)){
            value = "0";
        }
        return Integer.valueOf(value);
    }


    public static String parseDoubleStr(String str){
        if(str==null || "".equals(str) || "null".equals(str)){
            str =  "0";    
        }
        return str;
    }
   
    public static String stringValue(Object obj, String defaultValue) {
                if (obj == null) {
                        return defaultValue;
                } else if (obj instanceof String) {
                        return (String) obj;
                } else if (obj instanceof Clob) {
                        return stringValue((Clob) obj, "");
                } else {
                        return obj.toString();
                }
        }
   
    public static String formatDate(Date date,String format){
        if(!StringUtil.isEmpty(format)){
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(format);
        String c=sdf.format(date);
        return c;
        }
        return null;
    }

   
  
   
    public static String right(String mainStr,int lngLen) {
        if (mainStr.length()-lngLen>=0 && mainStr.length()>=0 && mainStr.length()-lngLen<=mainStr.length()) {
            return mainStr.substring(mainStr.length()-lngLen,mainStr.length());
        }else{
                return null;
        }
    }
   
    /**
     * 长串数字处理，如用千分位分隔，小数点保留几位
     * @param value
     * @param decimalPrecision
     * @param thousandsSeparator
     * @return
     */
    public static String getFormatNumbers(String value, int decimalPrecision,
            String thousandsSeparator) {
        Pattern integerPattern = Pattern.compile("^-?[0-9]*$");
        Pattern thousandsSeparatePattern = Pattern.compile("(\\d{1,3})(?=(\\d{3})+(?:$|\\D))");
        if (decimalPrecision > 0) {
            if (value == null || value.equals("")) {
                if (!Pattern.matches("^-?[0-9]*\\.{0,1}\\d{0,"+ decimalPrecision + "}$", value)) {
                    value = "0";
                }
            }
            String pattern = "0.";
            for (int i = 0; i < decimalPrecision; i++) {
                pattern += "0";
            }
            value = new DecimalFormat(pattern)
                    .format(Double.parseDouble(value));
            String integerPart = value.split("\\.")[0];
            String decimalPart = value.split("\\.")[1];
 
            Matcher matcher = thousandsSeparatePattern.matcher(integerPart);
            integerPart = matcher.replaceAll("$1" + thousandsSeparator);
            value = integerPart + "." + decimalPart;
        } else {
            if (value == null || value.equals("")) {
                if (!integerPattern.matcher(value).find()) {
                    value = "0";
                }
            }
            Matcher matcher = thousandsSeparatePattern.matcher(value);
            value = matcher.replaceAll("$1" + thousandsSeparator);
        }
        return value;
    }
   
    /**
     * 在subject中最先出现search的位置，不区分大小写
     * @param subject 被查询的字符串
     * @param search  要查询的字符串
     * @param soffset
     * @return
     */
    public static int ignoreIndexOf(String subject,String search,int soffset,boolean isIngoreCase){  
        //当被查找字符串或查找子字符串为空时，抛出空指针异常。  
        if (subject == null || search == null) {  
            throw new NullPointerException("输入的参数为空");  
        }  
        if(soffset>=subject.length() && search.equals("")){  
            return subject.length();  
        }  
        for (int i = soffset; i < subject.length(); i++) {  
            if(subject.regionMatches(isIngoreCase, i, search, 0, search.length())){  
                return i;  
            }  
        }  
        return -1;  
    }  
         
    /***
     * 在subject中最后出现search的位置
     * @param subject
     * @param search
     * @return
     */
        public static int ignoreLastIndexOf(String subject,String search) {  
            return ignoreLastIndexOf(subject, search, subject.length(),true);  
        }  
         
        public static int ignoreLastIndexOf(String subject,String search,int soffset,boolean isIngoreCase) {  
           //当被查找字符串或查找子字符串为空时，抛出空指针异常。  
           if (subject == null || search == null) {  
            throw new NullPointerException("输入的参数为空");  
           }  
           if(soffset<=0 && search.equals("")){  
            return 0;  
           }  
           for (int i = soffset; 0 < i; i--) {  
            if(subject.regionMatches(isIngoreCase, i, search, 0, search.length())){  
                return i;  
            }  
           }  
         
           return -1;  
        }
       
        /**
         * 求next数组
         * @param key
         * @return
         */
        public static int[] next(String key) {
                try {
                        int i = 1, j = 0;
                        char[] keys = key.toCharArray();
                        int[] next = new int[keys.length];
                        while (i < keys.length) {
                                if (j == 0 || keys[i - 1] == keys[j - 1]) {
                                        ++j;
                                        next[i] = j;
                                        ++i;
                                } else {
                                        j = next[j - 1];
                                }
                        }
                        return next;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * 求next数组(前一个next方法的改进版)
         * @param key
         * @return
         */
        public static int[] next2(String key) {
                try {
                        int i = 1, j = 0;
                        char[] keys = key.toCharArray();
                        int[] next = new int[keys.length];
                        while (i < keys.length) {
                                if (j == 0 || keys[i - 1] == keys[j - 1]) {
                                        ++j;
                                        if (keys[i] != keys[j - 1]) {
                                                next[i] = j;
                                        } else {
                                                next[i] = next[j];
                                        }
                                        ++i;
                                } else {
                                        j = next[j - 1];
                                }
                        }
                        return next;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * 统计在主串中出现模式串的次数
         * @param input
         *            :主串
         * @param key
         *            :模式串
         * @return
         */
        public static int calcKeysFrequency(String input, String key) {
                int i = 0, j = 0, times = 0;
                int[] next = next2(key);
                char[] inputs = input.toCharArray();
                char[] keys = key.toCharArray();
                while (i < inputs.length) {
                        if (j == 0 || inputs[i] == keys[j - 1]) {
                                ++i;
                                if ((++j) > keys.length) {
                                        times++;
                                        j = 0;
                                }
                        } else {
                                j = next[j - 1];
                        }
                }
                return times;
        }
       
        /**
         * 计算百分比
         * @param p1
         * @param total
         * @return
         */
        public static String percent( double  p1,  double  total)  {
        String str;
        double  p3  =  p1  /  total;
        NumberFormat nf  =  NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits( 2 );
        str = nf.format(p3);
        return  str;
    }
       
        /**
         * 计算字符串字节长度
         * @param s
         * @return
         */
        public static int getStringByteCount(String s)
    {
         int  length  =   0 ;
         for ( int  i  =   0 ; i  <  s.length(); i ++ )
        {
             int  ascii  =  Character.codePointAt(s, i);
             if (ascii  >=   0   &&  ascii  <= 255 )
                length ++ ;
             else
                length  +=   2 ;
               
        }
         return  length;
    }
        
    	/**
    	 * 可以用于判断 Map,Collection,String,Array是否不为空
    	 * @param o
    	 * @return
    	 */
    	public static boolean isNotEmpty(Object o) {
    		return !isEmpty(o);
    	}

    	public static boolean isNotBlank(Object o) {
    		return !isBlank(o);
    	}

    	public static boolean isNumber(Object o) {
    		if(o == null) return false;
    		if(o instanceof Number) {
    			return true;
    		}
    		if(o instanceof String) {
    			String str = (String)o;
    			if(str.length() == 0) return false;
    			if(str.trim().length() == 0) return false;
    			return org.apache.commons.lang.StringUtils.isNumeric(str);
    		}
    		return false;
    	}

    	public static boolean isBlank(Object o) {
    		if(o == null)
    			return true;
    		if(o instanceof String) {
    			String str = (String)o;
    			return isBlank(str);
    		}
    		return false;
    	}

    	public static boolean isBlank(String str) {
    		if(str == null || str.length() == 0) {
    			return true;
    		}

    		for (int i = 0; i < str.length(); i++) {
    			if (!Character.isWhitespace(str.charAt(i))) {
    				return false;
    			}
    		}
    		return true;
    	}
    	
    	
    	public static Long getUniqueID(){
    		Long time =  System.currentTimeMillis();
    		int random = (int)Math.floor(Math.random()*100);
    		String id = time.toString()+random;
    		return Long.valueOf(id);
    	}
    	
    	public static boolean isChinaTelPhone(String phoneNum){
    		if(!StringUtil.isEmpty(phoneNum)){
    			Pattern p = Pattern.compile("^(133|153|180|189|181)\\d{8}$");     
    	        Matcher m = p.matcher(phoneNum);
    	        if(m.matches()){
    	        	return true;
    	        }
    		}
    		return false;
    	}
    	
    	/**
    	* 去除所有空格
    	*
    	* @param sql
    	* @return
    	*/

    	public static String TransactSQLInjection(String sql) {
    		return sql.replaceAll(" ", "");  
    	}
    	
    	
    	/**
    	 * 生成大小为size的字符串 生成的字符串为62个字母
    	 * @param size
    	 * @return
    	 */
    	public static String getRandomString(int size){
    		String[] chars = new String[]{          //要使用生成URL的字符 
    	        "a","b","c","d","e","f","g","h", 
    	        "i","j","k","l","m","n","o","p", 
    	        "q","r","s","t","u","v","w","x", 
    	        "y","z","0","1","2","3","4","5", 
    	        "6","7","8","9","A","B","C","D", 
    	        "E","F","G","H","I","J","K","L", 
    	        "M","N","O","P","Q","R","S","T", 
    	        "U","V","W","X","Y","Z" 
    	    }; 
    		String s = "";
    		for(int i = 0;i<size;i++){
    			int num = (int)Math.floor(62*Math.random());
    			s+=chars[num];
    		}
    		return s;
    	}
    	/**
    	 * 获取ip
    	 * @param request
    	 * @return
    	 */
		public static String getIpAddr(HttpServletRequest request) {
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
		
		/**
		 * 
		 * 功能说明:获取表头信息
		 * 创建人:g.wfd
		 * 创建时间:2013-12-21 上午11:31:28
		 * @param request
		 * @return
		 */
		public static String getHeader(HttpServletRequest request) {
			Enumeration  names = request.getHeaderNames(); 
			StringBuffer sb=new StringBuffer("");
			while(names.hasMoreElements()){    
				String name = (String)names.nextElement(); 
				sb.append(name+":"+request.getHeader(name)+"<br/>");	
			}
			//System.out.println("sb=" + sb + ""); 
			return sb.toString();
		}
		
		/**
		 * 判断是否铃音盒
		 * @param ringId
		 * @return
		 */
		public static boolean isRingBox(String ringId){
			if(!StringUtil.isEmpty(ringId)){
				Pattern p = Pattern.compile("^(2700|9400|9436|1500|1800|9437|9186|9438|4700|9439|9440|2701|9410|9446|1501|810090|810099|1801|9441|9196|9442|4701|9443|9444)\\d{0,}$");     
		        Matcher m = p.matcher(ringId);
		        if(m.matches()){
		        	return true;
		        }
			}
			return false;
		}
//		/**
//		 * 远程接口
//		 * @param url
//		 * @return
//		 */
//		public static String getRemoteString(String url,String type){
//			HttpClient http = new DefaultHttpClient();
//			HttpPost httppost = new HttpPost(url.toString());
//			HttpResponse response;
//			JSONObject object = null;
//			try {
//				response = http.execute(httppost);
//				HttpEntity entity = response.getEntity();
//				StringBuffer sb = new StringBuffer();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(
//						entity.getContent(), type));
//				String line = null;
//				
//		
//				while ((line = reader.readLine()) != null) {
//					sb.append(line);
//				}
//				if(StringUtil.isNotEmpty(sb)){
//					return sb.toString();
//				}
//			} catch (ClientProtocolException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return "";
//		}
		
//		public static String validateCiphertextAPI (HttpServletRequest request){
//    		String username = request.getParameter("username");//唯一识别
//    		String ciphertext = request.getParameter("ciphertext");//加密文本
//    		String time = request.getParameter("time");//时间戳
//    		String userid = request.getParameter("userid");
//    		if(!StringUtil.isEmpty(time)){
//    			if(System.currentTimeMillis()-Long.valueOf(time)>24*60*60*1000){//过期时间一天 86400000
//    				return "";
//    			}
//    		}
//    		if(StringUtil.isEmpty(userid)){
//    			userid="";
//    		}
//    		if(StringUtil.isEmpty(username) || StringUtil.isEmpty(Constant.commonProp)){
//	    		return "";
//	    	} 
//    		String password = Constant.commonProp.getProperty(username);//根据用户名获取密码
//    		if(StringUtil.isEmpty(password)){
//	    		return "";
//	    	}
//    		String myMd5text = MD5.getMD5(username+password+time+userid);//用户名+密码+时间戳
//    		if(!myMd5text.equalsIgnoreCase(ciphertext)){
//    			return "";
//    		}else {
//    			return userid;
//    		}
//    	}
	public static void main(String[] args) {
		// PopWeekCardLog log = new PopWeekCardLog();
		// PopWeekCardLog log2 = new PopWeekCardLog();
		// UgcTone tone = new UgcTone();
		// tone.setUgcID(12321312l);
		// tone.setToneName("sdfs");
		// log.setTone(tone);
		// log2.setTone(tone);
		// List<PopWeekCardLog> logs = new ArrayList<PopWeekCardLog>();
		// logs.add(log);
		// logs.add(log2);
		// System.out.println(collectionJoin(logs, "tone.ugcID"));
		System.out.println(StringUtil.cramStringWithKeyAtBefore("67", "0", 6));
		// System.err.println(StringUtil.replace("main/asdasd","main","http://192.168.2.49:8100/main","http://"));
		// System.out.println(StringUtil.cramStringWithKeyAtBefore("1", "0",
		// 3));
//		getRemoteString(url, "UTF-8");
	}

//	public static String convertUrl(HttpServletRequest request,String userid, String redirectUrl) {
//		// TODO Auto-generated method stub
//		String basePath = "http://corpapi.ringss.cn:9091";
//		String url=basePath;
////		System.out.println(basePath);
//		String username = request.getParameter("username");//唯一识别
//		String time = request.getParameter("time");//时间戳
//		String password = Constant.commonProp.getProperty(username);//根据用户名获取密码
//		
//		String ciphertext = MD5.getMD5(username+password+time+userid);//用户名+密码+时间戳
//		url+=redirectUrl;//重定向URL
//		url+="?username="+username+"&time="+time+"&ciphertext="+ciphertext+"&userid="+userid;
//		return url;
//	}

	 /**
     * 以行的形式读取话单文件信息，并将信息保存在数组中  读取文件直接传入
     * HX
     * 创建时间：2014-8-13
     * @param file
     * @return object[2] object[0]为主叫统计数，object[1]为被叫统计数
     * @throws IOException
     */
    public static Object[] fileInputStreamByFile(File file) throws IOException{ 
      if(!file.exists()||file.isDirectory())
    	  throw new FileNotFoundException();
      Object[] reobject= new Object[2];
      Map caller=new HashMap();//呼叫统计
      Map beCalled=new HashMap();//被呼叫统计
      
      FileReader fileReader = new FileReader(file);  
      BufferedReader br = new BufferedReader(fileReader); 
      String str; 
      while((str = br.readLine() ) != null){        
    	  if(!str.equals("null")&&!str.equals("")){
    		  String[] spst=str.split("\\|");
    		  
    		  //主动呼叫统计
    		  if(!spst[0].equals("")){
    			  if(caller.get(spst[0])!=null){
    				  int num=Integer.parseInt(caller.get(spst[0]).toString());
    				  caller.put(spst[0], ++num);
    			  }else{
    				  caller.put(spst[0], 1);
    			  }
    		  }
    		  
    		  //被叫统计
    		  if(!spst[1].equals("")){
    			  if(beCalled.get(spst[1])!=null){
    				  int num=Integer.parseInt(beCalled.get(spst[1]).toString());
    				  beCalled.put(spst[1], ++num);
    			  }else{
    				  beCalled.put(spst[1], 1);
    			  }
    		  }
    	  } 
      }  
      br.close();
      reobject[0]=caller;
      reobject[1]=beCalled;
      return reobject;
    }
    
    /**
     * 以行的形式读取话单文件信息，并将信息保存在数组中  读取文件直接传入
     * HX
     * 创建时间：2014-8-13
     * @param file
     * @return object[2] object[0]为主叫统计数，object[1]为被叫统计数
     * @throws IOException
     */
    public static Object[] memberCallObject(File file) throws IOException{ 
    	if(!file.exists()||file.isDirectory())
    		throw new FileNotFoundException();
    	Object[] reobject= new Object[2];
    	Map caller=new HashMap();//呼叫统计
    	Map beCalled=new HashMap();//被呼叫统计
    	
    	FileReader fileReader = new FileReader(file);  
    	BufferedReader br = new BufferedReader(fileReader); 
    	String str; 
    	while((str = br.readLine() ) != null){        
    		if(!str.equals("null")&&!str.equals("")){
    			String[] spst=str.split("\\|");
    			
    			//主叫统计
    			caller=searchPhone(caller,spst[0]);
    			
    			//被叫统计
    			beCalled=searchPhone(beCalled, spst[1]);
    		} 
    	} 
    	
    	br.close();
    	
    	
    	reobject[0]=caller;
    	reobject[1]=beCalled;
    	return reobject;
    }

//	private static Map swapPhone(int currentNum, int swapNum, Map caller, String spst) {
//		// TODO Auto-generated method stub
//		String currentValue=caller.get(currentNum).toString();
//		String swapValue="";
//		if(caller.get(swapNum)!=null){
//			current=
//		}
//		return null;
//	}

	private static Map searchPhone(Map m, String phonenum) {
		// TODO Auto-generated method stub
		
		int keySet=1;//待更新的KEY，默认是1
		String swapValue="";//待更新的VALUE值
		
		if(!phonenum.equals("")){
			/**
			 * 循环查询对应的号码的拨打次数
			 */
			Set set = m.keySet();//将map的kye放到set里了
			Iterator iter = set.iterator();
			while(iter.hasNext()){
				int key =  Integer.parseInt(iter.next().toString());
				String value=  m.get(key).toString();
				
				if(value.indexOf(phonenum)>-1){
					value=value.replaceAll(phonenum+",", "");
					if(StringUtil.isNotEmpty(value)){
						m.put(key, value);
					}else{
						m.remove(key);
					}
					keySet=++key;
					break;
				}
			}
			
			//更新KEY-VALUE值
			if(m.get(keySet)!=null){
				swapValue=m.get(keySet).toString()+phonenum+",";
			}else{
				swapValue=phonenum+",";
			}
			
			m.put(keySet, swapValue);
		}
		return m;
	}

}

