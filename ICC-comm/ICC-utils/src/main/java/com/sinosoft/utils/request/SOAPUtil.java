package com.sinosoft.utils.request;

import com.alibaba.fastjson.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by  on 2016/8/5.
 */
public class SOAPUtil {
    private static final Logger logger = LoggerFactory.getLogger(SOAPUtil.class);

    /**
     * 用axis调用webservice服务
     * @param targetEndpoint targetEndpoint
     * @param namespace namespace
     * @param operationName operationName
     * @param parameters 参数键值对集合，有序
     * @return 响应报文
     */
    public static String axisInvoke(String targetEndpoint, String namespace, String operationName, LinkedHashMap<String, String> parameters) {
        logger.info("方法:axisInvoke \n targetEndpoint:{} \n namespace:{} \n operationName:{} \n parameters:{}", targetEndpoint, namespace, operationName, parameters);
        String result = null;
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(targetEndpoint));
        } catch (ServiceException e) {
            logger.error("方法:axisInvoke 出错", e);
        } catch (MalformedURLException e) {
            logger.error("方法:axisInvoke 出错", e);
        }
        if (call != null) {
            call.setOperationName(new QName(namespace, operationName));
            call.setReturnType(XMLType.XSD_STRING);
            Set<Map.Entry<String, String>> entrySet = parameters.entrySet();
            Stack<String> paramStack = new Stack<>();
            for (Map.Entry<String, String> entry : entrySet) {
                call.addParameter(entry.getKey(), XMLType.XSD_STRING, ParameterMode.IN);
                paramStack.add(entry.getValue());
            }
            Object[] paramArr = paramStack.toArray();
            try {
                result = (String) call.invoke(paramArr);
            } catch (RemoteException e) {
                logger.error("方法:axisInvoke 出错", e);
            }
        }
        logger.info("方法:axisInvoke 响应报文:{}", result);
        return result;
    }
    /**
	 * @param namespace 命名空间
	 * @param url 服务ＵＲＬ
	 * @param method　服务方法名
	 * @param methodParam　JSON用 请求方法的参数，传多个参数，为｛agr1:res,agr2:res｝
	 * @return
	 */
	public static Object aixService1(String namespace, String url, String method, JSONObject methodParam){
		Object res = "";
		Call call;
		Service service = new Service();
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			QName methodObj = null;
			if(StringUtils.isNotBlank(namespace)&&!namespace.startsWith("{")){
				methodObj=new QName(namespace,method);
			}else{
				methodObj=new QName(method);
			}
			call.setOperationName(methodObj);
			Set set = methodParam.keySet();
			Iterator it = set.iterator();
			Object[] paras = new Object[set.size()];
			int i = 0;
			while(it.hasNext()){
				String key = it.next().toString();
				call.addParameter(key, XMLType.XSD_ANY, ParameterMode.IN);
				if(methodParam.get(key)==null){
					paras[i++]=null;
				}else{
					paras[i++]=methodParam.get(key);
				}
			}
//			 call.setReturnType(XMLType.XSD_ANY);
//			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setEncodingStyle("UTF-8"); 
			res = call.invoke(paras);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(url+" "+method+" "+methodParam, e);
		}
		return res;
	}


}
