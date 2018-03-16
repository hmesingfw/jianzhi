package com.thinkgem.jeesite.common.utils.pay.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2088921945967190";								   
	
	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDHjzaTe6Zx0LqWNLS9wcWq5X76fRnUFfDxn7TnlE1bGEPkDCr7q7FuczfRy9vHkIssWAsucOjnV/kJt/HhIaP/jF4UCf04g1DEsqvmXzrPGUnGS1tVIuyM13Q3ho04CnYzd/GPGzTIhPIlmcy+tYddfprq7SEq5WOm3rejDdHCWvPsUGfZIP7Mseqjd71X58QmhJXLYydfHYO0/fJ7TdYZb84kyE6FrcWk6g8VXRx95JylwRvs7WiLbfM73ejUHLBTE95X3Vt6R5tX730vbmD3AaYG7l4ZbvruSsGIW0UT+WH2gb56ZcJP7SL3eecxRJp6ciqkEgauHWd9yFvjgMZVAgMBAAECggEAb9uPrOWPa5ajL9TFRuQZp2xSW5fJ5X6S+u0ZWwFexuP4Ehjb8wvPPejpbfiF7fvQdv+27MYvVpWSK2dm9lmD8pc8mKDsxkOc/1RjVJMmhLVZDARrYnJcA6o1I3aRK8t5a+ochamrUYZrsLXf9ToNcVOpBGv8vm2sCPooFwaFABo7mzZ+pEzKZxzdy3IXwUzWL4JxD1al93mqJfdm24i0uLNYYFH2cf/XgWZfIadTfWPAeezyRbVgxIvQaE3NwesN3P5HCHltQBIOTGxVj2o3tkKP05QECXPTvaGOgOR1Sm2d2nOLYiEnoJdGFzAZds0iNnZbOSyMknas/Uze5a4Y1QKBgQDs3ep8tKLMep1Fp/0Mq7OXrKCkJWIv+yg3PIJerSDbmI6pfjEA/j3+JFRT5kBF1LPCPMSpGooN/FIzuuYguOqiylRiK/PxmMMVckt8puRChC0m7UOKALcZ3Iy0cowNCB7u0W+32JVYuqyTiT6ZpSE342qFukDnEMrEYXef0WSJiwKBgQDXrdSQTF2XawqGv9c95K6bEa5dFNvnbIvesXaIfQpnhV+3rnTbiH5wLl/bwZSRqhvpqshq+GmYMR9oMovR8rqUrGR/80s1iNnhtUIzndvVphkMW4RSSO36E8Z0X8NQnLqfBSS3q6hGDsS0mV/WATlvNNP+UBJ3C5+spq63BX8rnwKBgQCTwQZRjhhzdxT964FlUwHvXibC3fL2vTPj6OHmDxcNBIgRYH9PAElJa/4Sa/Gx3bHYOzHtznlJqhjY3gBJbzAmJhInPwWnskQW77n44t5GQ88qlhxoTHPrNPGsajvEsUkibhDDXN4kgCXAzn73NtOW2kyLaJityoLBp48IaYTOawKBgEWS8K//wm02/XVMa02u+FhTktC6CtoTnOcgjiEnXPTaBG+HWnD7DfZ4Lzn/r0JyAoEVM4O2ULHknsIJFCuMxn1xQxY3UHqbg9WYmBt4f9Lius/OsdetaP8z49/kiGf3twfXK+oH+MdoujGcDFtHl9R00TIHeH1ohYC3YELwBf73AoGARjya0KmI5GX3ZGJrXeycZzMw1m4qnCQt1vdNSxj8JjWPmP6BnX3bospf7H4rOxu3QdLqE73V1dHu7u9P8BrU/1S5vtqfc2SKR/QnT71KMUQhVmdisqFRnG9FX8vi8wUos9X/KBtggGEg80xBrZCFEvcqL1EcusXoMASKhAeRB0k=";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlGgXwBX+5LKd2ErOoLqv7rtkcgWSPK1ysfNp/uWpxQqs1CNvvze+wJshJR/yH9UqhZc1g0vgutiD4lRnJyOfRU/iNL6B2lP2+IfSj6W4Ml2xTdlarnIM/3tq66lonuTKgkAUef6RbrzqGcqe3pjqHNTmVgchwuL8n+eS2YsHa/os9DYPUSBrOii7codpDKTuDiJYLOFPPbWclgSfARddUyJx3nGjIdKoKi8UnvlGcorDZ1mH2HDno+Qbf//GAJ5oRYR3n3qWQJpBGFYx/W5DOO1orySywcZuTRRHQvOp/0rDJB8us8sTyA7PUTjBuGGXILu9dtDkPYvXrvadmG1GswIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://www.ok5588.cn/f/alipayaNotify";
	
	
	public static String notify_url_proxy = "http://www.ok5588.cn/f/alipayaNotifyProxy";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.ok5588.cn/f/alipayReturn";
	
	//pc  代理购买成功
	public static String return_url_proxy = "http://www.ok5588.cn/f/alipayReturnProxy";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 日志地址
	public static String log_path = "D:\\";
    
	// 收款方名字
	public static String seller_name = "沈阳建执教育咨询有限公司";
    
	// 签约支付宝账号或卖家收款支付宝帐户
	public static String seller_email = "liaoningjianzhi@163.com";

}
