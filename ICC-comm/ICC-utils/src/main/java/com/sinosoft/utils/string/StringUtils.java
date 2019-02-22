package com.sinosoft.utils.string;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对字符的操作
 * 
 * @author zouren
 * 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z' };

	private static char[] upperChars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	private static int charsLen = chars.length;
	private static int upperCharsLen = upperChars.length;

	public static String longToString(long n) {
		StringBuffer sb = new StringBuffer();
		while (n != 0) {
			sb.insert(0, chars[(int) (n % charsLen)]);
			n /= charsLen;
		}
		return sb.toString();
	}

	public static String longToUpperString(long n) {
		StringBuffer sb = new StringBuffer();
		while (n != 0) {
			sb.insert(0, upperChars[(int) (n % upperCharsLen)]);
			n /= upperCharsLen;
		}
		return sb.toString();
	}

	public static boolean isCode(String str) {
		return Pattern.compile("[a-zA-Z][a-zA-Z0-9]*").matcher(str).matches();
	}

	/**
	 * &#25226;&#23383;&#31526;&#20018;&#36716;&#20026;&#117;&#110;&#105;&#99;&#
	 * 111;&#100;&#101;&#32534;&#30721;
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToNumString(String str) {
		StringBuffer re = null;
		if (str != null && !"".equals(str)) {
			re = new StringBuffer();
			char[] strArry = str.toCharArray();
			for (int i = 0; i < strArry.length; i++) {
				re.append("&#").append(Integer.toString(strArry[i]))
						.append(";");
			}
			return re.toString();
		}
		return null;
	}

	public static String UnicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\w{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	/**
	 * &#36827;&#34892;&#23383;&#31526;&#20018;&#35268;&#26684;&#21270;&#65288;&
	 * #20840;&#35282;&#36716;&#21322;&#35282;&#65292;&#22823;&#20889;&#36716;&#
	 * 23567;&#20889;&#22788;&#29702;&#65289;
	 * 
	 * @param str
	 * @param charset
	 * @return
	 */
	public static String regularizeString(String str, String charset) {
		StringBuffer re = new StringBuffer();
		try {
			str = new String(str.getBytes(), charset);
			for (int i = 0; i < str.length(); i++) {
				char emp = str.charAt(i);
				re.append(regularize(emp));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return re.toString();
	}

	/**
	 * &#36827;&#34892;&#23383;&#31526;&#35268;&#26684;&#21270;&#65288;&#20840;&
	 * #35282;&#36716;&#21322;&#35282;&#65292;&#22823;&#20889;&#36716;&#23567;&#
	 * 20889;&#22788;&#29702;&#65289;
	 * 
	 * @param input
	 * @return char
	 */
	private static char regularize(char input) {
		if (input == 12288) {
			input = (char) 32;
		} else if (input > 65280 && input < 65375) {
			input = (char) (input - 65248);

		} else if (input >= 'A' && input <= 'Z') {
			input += 32;
		}

		return input;
	}

	/**
	 * &#21462;&#24471;&#19968;&#20010;&#27721;&#23383;&#30340;&#85;&#110;&#105;
	 * &#99;&#111;&#100;&#101;&#30721;
	 * &#25226;&#85;&#110;&#105;&#99;&#111;&#100;
	 * &#101;&#30721;&#20998;&#35299;&
	 * #20026;&#20004;&#20010;&#49;&#54;&#36827;&#
	 * 21046;&#25968;&#25454;&#23383;&
	 * #31526;&#20018;&#65288;&#20002;&#24323;&#21069
	 * ;&#20004;&#20010;&#23383;&#33410;&#65289;
	 * &#25226;&#36825;&#20004;&#20010;
	 * &#49;&#54;&#36827;&#21046;&#25968;&#25454;
	 * &#23383;&#31526;&#20018;&#36716;
	 * &#25442;&#25104;&#20108;&#36827;&#21046;&
	 * #25968;&#25454;&#23383;&#31526;&#20018;
	 * &#25226;&#20108;&#36827;&#21046;&#
	 * 25968;&#25454;&#23383;&#31526;&#20018;&#
	 * 20998;&#35299;&#20026;&#19977;&#20010
	 * ;&#20018;&#65292;&#31532;&#19968;&#20010
	 * ;&#20018;&#20026;&#52;&#65288;&#48
	 * ;&#126;&#52;&#65289;&#20010;&#20301;&#65292
	 * ;&#22312;&#39640;&#20301;&#21152;
	 * &#19978;&#26631;&#35760;&#20301;&#8220;&
	 * #49;&#49;&#49;&#48;&#8221;&#65292;
	 * &#31532;&#20108;&#65288;&#52;&#126;&#49
	 * ;&#48;&#65289;&#12289;&#19977;&#20010
	 * ;&#65288;&#49;&#48;&#126;&#49;&#54;&#
	 * 65289;&#20018;&#22343;&#20026;&#54;&#
	 * 20010;&#20301;&#65292;&#20998;&#21035
	 * ;&#22312;&#39640;&#20301;&#21152;&#19978
	 * ;&#8220;&#49;&#48;&#8221;&#26631;&#35760;&#20301;
	 * &#32;&#25226;&#36825;&#19977
	 * ;&#20010;&#20108;&#36827;&#21046;&#20018;&#20998
	 * ;&#21035;&#36716;&#25442;&
	 * #20026;&#49;&#48;&#36827;&#21046;&#25968;&#25454
	 * ;&#24182;&#36171;&#20540;&#32473;&#23383;&#33410;&#22411;&#25968;&#32452;
	 * &#26681;&#25454;&#36825;&#20010;&#23383;&#33410;&#22411;&#25968;&#32452;&
	 * #26500;&#36896;&#85;&#84;&#70;&#45;&#56;&#23383;&#31526;
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String gb2312ToUtf8(String str)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			String s = str.substring(i, i + 1);
			if (s.charAt(0) > 0x80) {
				byte[] bytes = s.getBytes("Unicode");
				String binaryStr = "";
				for (int j = 2; j < bytes.length; j += 2) {
					// the first byte
					String hexStr = getHexString(bytes[j + 1]);
					String binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
					// the second byte
					hexStr = getHexString(bytes[j]);
					binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
				}
				// convert unicode to utf-8
				String s1 = "1110" + binaryStr.substring(0, 4);
				String s2 = "10" + binaryStr.substring(4, 10);
				String s3 = "10" + binaryStr.substring(10, 16);
				byte[] bs = new byte[3];
				bs[0] = Integer.valueOf(s1, 2).byteValue();
				bs[1] = Integer.valueOf(s2, 2).byteValue();
				bs[2] = Integer.valueOf(s3, 2).byteValue();
				String ss = new String(bs, "UTF-8");
				sb.append(ss);
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}

	private static String getHexString(byte b) {
		String hexStr = Integer.toHexString(b);
		int m = hexStr.length();
		if (m < 2) {
			hexStr = "0" + hexStr;
		} else {
			hexStr = hexStr.substring(m - 2);
		}
		return hexStr;
	}

	private static String getBinaryString(int i) {
		String binaryStr = Integer.toBinaryString(i);
		int length = binaryStr.length();
		for (int l = 0; l < 8 - length; l++) {
			binaryStr = "0" + binaryStr;
		}
		return binaryStr;
	}

	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	public static String StringToUnicode(String str) {
		StringBuffer re = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			re.append("\\u").append(Integer.toHexString(str.charAt(i)));
		}
		return re.toString();
	}

	/**
	 * &#24471;&#21040;&#24322;&#24120;&#20449;&#24687;&#32;&#23436;&#20840;&#20449;&#24687;
	 * 
	 * @param t
	 * @return
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

	public static boolean isNotNull(String str) {
		if (str != null && !("null".equals(str)) && !("".equals(str))
				&& str.trim().length() > 0)
			return true;
		return false;
	}

	/**
	 * &#25226;&#32;&#49;&#50;&#51;&#97;&#97;&#44;&#49;&#50;&#51;&#49;&#50;&#32;
	 * &
	 * #26684;&#24335;&#20026;&#32;&#39;&#49;&#50;&#51;&#97;&#97;&#39;&#44;&#39;
	 * &#49;&#50;&#51;&#49;&#50;&#39;
	 * 
	 * @param ids
	 * @return
	 */
	public static String formatIdsSql(String ids) {
		if (ids != null && ids.indexOf(",") >= 0) {
			return "'" + ids.replaceAll(",", "','") + "'";
		}
		return ids;
	}

	/**
	 * &#31227;&#38500;&#21322;&#20010;&#23383;&#31526;
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHalfCharacter(String str) {
		return str.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
	}

	/**
	 * 转换JavaScript解析不了的特殊字符
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String changForHTML(String s) {
		char[] arr = s.toCharArray();
		s = "";

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '"' || arr[i] == '\'') {
				s = s + "\\";
			}

			if (arr[i] == '\n') {
				s = s + "<br>";
				continue;
			}
			if (arr[i] == '\r')
				continue;

			s = s + arr[i];
		}

		return s;
	}
	/**
     * 如果一个字符串数字中小数点后全为零，则去掉小数点及零
     *
     * @param Value String
     * @return String
     */
    public static String getInt(String Value) {
        if (Value == null) {
            return null;
        }
        String result = "";
        boolean mflag = true;
        int m = 0;
        m = Value.lastIndexOf(".");
        if (m == -1) {
            result = Value;
        } else {
            for (int i = m + 1; i <= Value.length() - 1; i++) {
                if (Value.charAt(i) != '0') {
                    result = Value;
                    mflag = false;
                    break;
                }
            }
            if (mflag) {
                result = Value.substring(0, m);
            }
        }
        return result;
    }
  	/**获取指定长度的数字验证码
  	 * @param length 长度
  	 * @return
  	 * @author zouren
  	 */
  	public static String getValidCode(int length) {
  		String res = "";
  		if(length<=18&&length>0){
  			res =(Math.random()+"").substring(2, 2+length); 
  		}
  		return res; 
  	}
  	/**
	 * 
	 * @param value 需要加密的手机号或者银行卡号
	 * @param cont  "*"号的个数只能是4~6
	 * @return 返回加密后的字符串
	 */
	public static String getFormatKey(String value,int cont){
		return getFormatKey(value,cont,4);
	}
	/**
	 * @param key 需要加密的手机号或者银行卡号
	 * @param maxHid "*"号的个数最大数 级别高
	 * @param fixx  最大可以留尾数 级别低
	 * @return
	 * @author zouren
	 */
	public static String getFormatKey(String key,int maxHid, int fixx){
		String re = key;
		if(maxHid>0){
			if(isNotBlank(re)){
				if(fixx<0){
					fixx = 0;
				}
				int len = re.length();
				if(len<maxHid){
					re= key.replaceAll(".", "*");
				}else if(len<(maxHid+fixx)){
					re= key.substring(0,maxHid).replaceAll(".", "*")+key.substring(maxHid);
				}else{
					int pixx =  len-(maxHid+fixx);
					re= key.substring(0,pixx)+key.substring(pixx,pixx+maxHid).replaceAll(".", "*")+key.substring(pixx+maxHid);
				}
			}
		}
		return re;
	}
	/**当jsonStr为 空 空串 [] 时 返回 defaultStr 否则返回 jsonStr
	 * @param jsonStr 一个json字符串
	 * @param defaultStr 默认返回
	 * @return  返回对应的字串;
	 * @author zouren
	 */
	public static String defaultIfJsonEmpty(String jsonStr,String defaultStr){
		if("[]".equals(jsonStr)||"null".equals(jsonStr)){
			return defaultStr;
		}else{
			return defaultIfEmpty(jsonStr, defaultStr);
		}
	}
	
	/**
	 * 小数型字符串 转化为 百分比字符串， 若字符串中含除小数点外的非数字型字符，则返回"-1"
	 * @param str 需处理的字串(如：0.9900)
	 * @param len 需处理字符串的长度（len不小于3，字符串长度小于len,则补0）
	 * <p>（现最长处理字符串长度为6，超出6某些数据显示不正确。）</p>
	 * @return ##.##%
	 */
	public static String formateFloatStr(String str, int len) {
		/*if(len < 3) {
			System.out.println("需处理字符串长度不小于3！");
			return "";
		}
		if(len < 6) {
			for(int i=0;i<6-len;i++) {
				str = str + "0";
			}
		} else {
			if(str.length() >= len) {
				str = str.substring(0, len);
			} else {
				for(int i=0;i<len-str.length();i++) {
					str = str + "0";
				}
			}
		}
		float temp;
		String temp1;
		
		try {
			temp = Float.parseFloat(str);
		} catch(NumberFormatException e) {
			return "-1";
		}
		if(!"0.0".equals(temp + "")) {
			temp1 = temp * 100 + "";
			if("0".equals(str.substring(str.length()-1))) {
				if(temp1.length() > 4) {
					temp1 = temp1.substring(0, 4);
				}
				return  temp1 + "0%";
			} else {
				if(temp1.length() > 5) {
					temp1 = temp1.substring(0, 5);
				}
				return temp1 + "%";
			}
		} else {
			return "0";
		}*/
		
		try {
//			if(!"0".equals(str)){//去除0
				Double tempD = Double.parseDouble(str)*100;
				BigDecimal tempB = new BigDecimal(tempD);
				str = tempB.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//				if(str.indexOf(".00") > 0){//处理100.00  200.00
//					str = str.replaceAll(".00", "");
//				}
				str += "%";
//			}
			return str;
			
		} catch(NumberFormatException e) {
			return "-1";
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(StringUtils.getFormatKey("通用日null期字串格化为Date",4));
//		System.out.println(defaultIfJsonEmpty("null","1"));
		String str = "0.01";
		System.out.println(formateFloatStr(str,4));
	}
}
