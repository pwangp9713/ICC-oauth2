package com.sinosoft.enums.webservice;


import com.sinosoft.utils.string.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**对应webService中的code
 * @author zouren
 *
 */
public enum WebServiceCode {

	//产品模块
	/**
	 * 查询全部产品列表
	 */
	FINDPRODUCTLIST("findProductList","查询全部产品列表"),
	/**
	 * 查询推广产品列表接口
	 */
	FINDPOPPRODUCTLIST("findPopProductList","查询推广产品列表接口"),
	/**
	 * 查询产品详情
	 */
	FINDPRODUCTDETAIL("findProductdetail","查询产品详情"),
	/**
	 * 产品对比
	 */
	PRODUCTCOMPARE("productCompare","产品对比"),
	/**
	 * 查询产品总览分类
	 */
	FINDPRODUCTTYPELIST("findProductTypeList","查询产品总览分类"),
	/**
	 * 留言给我们
	 */
	LEAVEMESSAGE("LeaveMessage","留言给我们"),
	//发送短信邮件
	/**
	 * 发送短信
	 */
	SENDMESSAGE("SendMessage","发送信息"),
	/**
	 * 发送邮件
	 */
	SENDEMAIL("SendEmail","发送信息"),
	//人脸识别
	/**
	 * 获取腾讯人脸识别token
	 */
	GETFACEACCESSTOKEN("getFaceAccessToken","获取腾讯人脸识别token"),
	/**
	 * 获取腾讯人脸识别ticket
	 */
	GETFACEACCESSTICKET("getFaceAccessTicket","获取腾讯人脸识别ticket"),
	//保单模块
	/**
	 * 客户保单总览查询
	 */
	CUSTOMERPOLICYOVERVIEW("CustomerPolicyOverview","客户保单总览查询"),
	/**
	 * 客户保单列表数据
	 */
	CUSTOMERPOLICYPOLICYLIST("CustomerPolicyPolicyList","客户保单列表数据"),
	/**
	 * 保单详情信息
	 */
	POLICYQUERYDETAIL("PolicyQueryDetail","保单详情信息"),
	/**
	 * 保单计划查询
	 */
	POLICYQUERYQUERYPOLICYPLAN("PolicyQueryQueryPolicyPlan","保单计划查询"),
	/**
	 * 查询客户续期保单列表信息-非autopay
	 */
	QUERYRENEWALPOLICYNOTAUTO("QueryRenewalPolicyNotAuto","查询客户续期保单列表信息-非autopay"),
	/**
	 * 查询客户续期保单列表信息-autopay
	 */
	QUERYRENEWALPOLICYAUTOPAY("QueryRenewalPolicyAutoPay","查询客户续期保单列表信息-autopay"),
	/**
	 * 保单缴费计划信息列表查询
	 */
	QUERYPOLICYPAYMENTPLAN("QueryPolicyPaymentPlan","保单缴费计划信息列表查询"),
	//客户模块
	/**
	 * 客户信息绑定（证件号，出生日期查询）
	 */
	CUSTOMERINFOBINDING("CustomerInfoBinding","客户信息绑定（证件号，出生日期查询）"),
	/**
	 * 客户信息查询
	 */
	CUSTOMERINFOQUERY("CustomerInfoQuery","客户信息查询"),
	/**
	 * 提交OPT-OUT方式
	 */
	MODEOPTOUT("ModeOPTOUT","提交OPT-OUT方式"),
	/**
	 * 客户代理人信息查询
	 */
	AGENTINFORMATION("AgentInformation","客户代理人信息查询"),
	/**
	 * 客户风险数据接口
	 */
	RISKDATA("RiskData","客户风险数据接口"),
	/**
	 * 电子通知书查询
	 */
	EBOOK("EBook","电子通知书查询"),
	/**
	 * 电子通知书查询
	 */
	EBOOKDOWNLOAD("EBookDownload","电子通知书下载"),
	/**
	 * 客户OPT_OUT/DM_ALLOWED查询
	 */
	OPTOBJECT("OPTObject","客户OPT_OUT/DM_ALLOWED查询"),
	/**
	 * 客户信息绑定推送
	 */
	PUSHCUSACCOUNTINFO("PushCusAccountInfo","客户信息绑定推送"),
	/**
	 * App用户登录记录
	 */
	LOGINRECORD("LoginRecord","App用户登录记录"),
	//理赔
	/**
	 * 理赔申请提交
	 */
	CLAIMAPPLICATION("ClaimApplication","理赔申请提交"),
//	/**
//	 * 理赔进度列表
//	 */
//	CLAIMLIST("ClaimList","理赔进度列表"),
//	/**
//	 * 理赔进度详情
//	 */
//	CLAIMDETAIL("ClaimDetail","理赔进度详情"),
	/**
	 * 受保人信息查询
	 */
	QUERYINSURED("QueryInsured","受保人信息查询"),
	/**
	 * 保险顾问登记校验
	 */
	CHECKAGENT("CheckAgent","保险顾问登记校验"),
	/**
	 * 图片上传
	 */
	SUBMITIMAGE("SubmitImage","图片上传"),
	/**
	 * 理赔资料上传和补传
	 */
	CLAIMIMAGESUPPLEMENT("ClaimImageSupplement","理赔资料上传和补传"),
	/**
	 * 取消理赔
	 */
	CANCELCLAIM("CancelClaim","取消理赔"),
	//保单服务
	/**
	 * 联络人资料转换提交
	 */
	CONTACTCONVERSION("ContactConversion","联络人资料转换"),
	/**
	 * 查询可以基金转换的保单列表
	 */
	FUNDCONVERSION("FundConversion","查询可以基金转换的保单列表"),
	/**
	 * 查詢客戶名下所有基金列表
	 */
	ALLFUNDBYPARTID("AllFundByPartId","查詢客戶名下所有基金列表"),
	/**
	 * 查詢所有基金列表
	 */
	ALLFUND("AllFund","查詢所有基金列表"),
	/**
	 * 查询保单下可以做基金转换的列表信息
	 */
	AVAILABLEFUNDCONVERSION("AvailableFundConversion","查询保单下可以做基金转换的列表信息"),
	/**
	 * 校验基金卖出比例是否支持
	 */
	AVAILABLEFUNDPERCENT("AvailableFundPercent","校验基金卖出比例是否支持"),
	/**
	 * 基金转换提交
	 */
	SUBMITFUNDCONVERSION("SubmitFundConversion","基金转换提交"),
	/**
	 * 基金转换提交（重复）
	 */
	SUBMITFUNDCONVERSIONREPEAT("SubmitFundConversionRepeat","基金转换提交（重复）"),
	/**
	 * 未来资金分配提交
	 */
	SUBMITALLOCATIONOFFUNDS("SubmitAllocationOfFunds","未来资金分配提交"),
	/**
	 * 未来资金分配提交（重复）
	 */
	SUBMITALLOCATIONOFFUNDSREPEAT("SubmitAllocationOfFundsRepeat","未来资金分配提交（重复）"),
	/**
	 * 查询保单的红利/可支取现金
	 */
	POLICYBONUS("PolicyBonus","查询保单的红利/可支取现金"),
	/**
	 * 保单红利/可支取现金缴付提交
	 */
	SUBMITPOLICYBONUS("SubmitPolicyBonus","保单红利/可支取现金缴付提交"),
	/**
	 * 查询保单下投资分配比例信息
	 */
	INVESTMENTRATIOBYPOLICY("InvestmentRatioByPolicy","查询保单下投资分配比例信息"),
	//咨询中心
	/**
	 * 客户反馈
	 */
	FEEDBACK("Feedback","客户反馈"),
	/**
	 * OTP发送验证码
	 */
	OTPSEND("OPTSend","OTP发送验证码"),
	/**
	 * OTP验证验证码
	 */
	OTPVALID("OPTValid","OTP验证验证码"),
	//服务进度
	/**
	 * 服务进度详情查询
	 */
	SERVICEPROGRESSDETAIL("ServiceProgressDetail","服务进度详情查询"),
	/**
	 * 服务进度列表查询
	 */
	SERVICEPROGRESSLIST("ServiceProgressList","服务进度列表查询"),
	/**
	 * 国家编码查询
	 */
	COUNTRYCODE("CountryCode","国家编码查询"),
	/**
	 * 查询基金最低分配比例信息
	 */
	FUNDALLOCATIONRATIOMIN("FundAllocationRatioMin","查询基金最低分配比例信息");

	private String code;
	private String name;
	private WebServiceCode(String code, String name){
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**通过编码得到
	 * @param code
	 * @return
	 * @author zouren
	 */
	public static WebServiceCode getByCode(String code){
		WebServiceCode re = null;
		if(StringUtils.isNotNull(code)){
			re = all.get(code);

		}
		return re;
	}

	private static Map<String,WebServiceCode> all = null;
	static{
		all=Arrays.stream(WebServiceCode.values()).collect(Collectors.toMap(WebServiceCode::getCode,webServiceCode->webServiceCode));

	}

	public static void main(String[] args) {
		System.out.printf("1");
	}
}
