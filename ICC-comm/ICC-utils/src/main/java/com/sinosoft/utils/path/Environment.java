package com.sinosoft.utils.path;

import java.io.File;
import java.net.URL;

/**得到当前项目工程的根路径
 * @author 
 * 
 */
public final class Environment {
    private static String context = null;

    /**
     * @return &#24471;&#21040;&#24403;&#21069;&#39033;&#30446;&#24037;&#31243;&#30340;&#26681;&#36335;&#24452;
     */
    public static final String getContext() {
        if (null != context) {
            return context;
        } else {
            String slash = "/";
            URL url = Environment.class.getResource(slash);

            String resourcPath = "";
            if (null != url) {
                resourcPath = Environment.class.getResource(slash).getPath();
                char endChar = resourcPath.charAt(resourcPath.length() - 1);
                if (!slash.equals(Character.toString(endChar))) {
                    context = resourcPath + slash;
                } else {
                    context = resourcPath;
                }
                context = unescape(context);
                
                return context;
            }
//            System.out.println("url is null!");
            return "";
        }
    }
    public static String getServerRealPath(){
    	String serPath =Environment.class.getResource("").toString(); 
    	int index = serPath.lastIndexOf("WEB-INF");
    	String path = "";
    	try {
			path = java.net.URLDecoder.decode(serPath.substring(0,index).replace("file:/", ""),"UTF-8");
			if(!path.subSequence(1, 2).equals(":")){
				path = "/" +path;
			}
		} catch (Throwable e) {
		}
		return path;
    	
    }
    /**对文件路径解码
     * @param src
     * @return
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static void main(String[] args) {
        System.out.println("context: " + getContext());
        System.out.println("conf: " + new File("conf").exists());
    }
}
