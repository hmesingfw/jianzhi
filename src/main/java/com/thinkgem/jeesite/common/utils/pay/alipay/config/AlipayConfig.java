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
	public static String app_id = "2018020102124783";								   
	
	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDKH7ax35JVuFD++c4kp1VPGBdOubcH5G7XXhH6RQhMf+3Y6bpQXm+rOOqOcAZkfyCkcYOo0/r3jQGpUZf/kuZns+waQXDFMGhzENAVqLiOUEwIQi7vwcQHoKLy0GyITe40Jt2yo2eR6eAGVciEbU1govzWCUt1Xhu2cytLJodA3W5FrZY9+cfANs7MYF40taSo7CsAS4Qlio1y9if4qrEsFhM00tD0UsKYmZvNx1YQzE4siqcssoN/eKXUrMEnoMPtryFNPj2YAbaOdEBiMB4o+CT3oHsqhpwy4Hb++ui7wVd01vB/xP9uqWYtE+38752j9BE+rlCAFZpXWRuw3tNpAgMBAAECggEAEhnG9aTs6OpJrpvWXyfxFtU0eSNehcFqP7U37O6zsI4gUbWUrs/WNkIxlzHnEKbl2HQl8z+QkQy6olwhPYz9Pzo4oZ1MqQwNvog56kaiyHPeazrqEySKFpjHHvGVVpuMAnWckvvZMZL9MfQKsflL3aSa+dv5OviDiNJFhURdBmzlPOa/wyhP9D9bR9KxWElNI/gg5of9DkMFQ9yGMTde4LYfBzGGvjJ1hntJg8cqzInS1JC6z1v9WQczkGJzPaCHyY5pJ8rGVvxBbd64AwYak00LX9CL8+UKv6JXv+nzrqgSteEWOJt9CGxBCLkxoSg4hGsd+Gs61DTS3gnigXwGgQKBgQDw9ix+1HpISRYJvPjIUXsRVIvatwpIpSJE5G+vKjJxTUkIXAxQ1vzLZqjT5jFxdCcZHrn6eG/bRSLSTalJYvfLFSq+guqqD3lmNnqKaSzrANilPFjGa0Xd70oeYARvriKy6shv/LYXDUVHzBy+0o6DdOV+YEp9lqsz2um0GPBC+QKBgQDWvQg84omc1xOHuVhxP6Ea7edGysO3BQypFzyJC0sv28qRRFDRsbgIg7DV50tHglDgJ4ajBE9c8NAyBjS65P3hVNKbfHg15M3pZbV+783ItWvvAoNuPU/lAwb0J7iurZTP/GQUAE0UnS4kWwX/0tesR5Y2Pf58cJspxj/WEHi/8QKBgQC3Z/joNJw6xv5yPRbziiltQHgO6sF2F9MksudbYXfxi4zqQv3QzkKuIx6f4xeBLpLP6PZP8jnZoR7nCduvkxfeyOa3CXjKJmsV5KOTFIju+r7rzioYJPCSA5/bzOOzicy5eEFGFAkZNK2l/3Nlv2hgIKrJbUtlmjgHiTnXLiA6KQKBgQCPecXjSElB7cIy0Pfy0tzFrWb1ucyCoHERGWFs/4rA802pOcf2E5QuvC6O/PcjVELNQhuHF/4ezxeMFPFuXknzIEWHdearu7CI3Vgi8/6+t9jh3QALzBNxpFAlGdUfgQMI5E3Jv1nBCDQoPlkH0MwhY8NNgqZiZqqFgtxsHp1PQQKBgQCNCfJQsRBUnPJSC2Fi/aG572jm8BQf0IrN0/kJJfHGyP8ncqoCkUpku8PZoYXX/rNjPEQJK4vx9vgGSd8vZpTfYJltei/0a2pqDdeJZbbVtB0VjgtxFQn+ZUc6i9D7qe/Hd4lHhkbgEOM5DAt4ihPCvD0eULqDWzZbzLNDUJugIA==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyh+2sd+SVbhQ/vnOJKdVTxgXTrm3B+Ru114R+kUITH/t2Om6UF5vqzjqjnAGZH8gpHGDqNP6940BqVGX/5LmZ7PsGkFwxTBocxDQFai4jlBMCEIu78HEB6Ci8tBsiE3uNCbdsqNnkengBlXIhG1NYKL81glLdV4btnMrSyaHQN1uRa2WPfnHwDbOzGBeNLWkqOwrAEuEJYqNcvYn+KqxLBYTNNLQ9FLCmJmbzcdWEMxOLIqnLLKDf3il1KzBJ6DD7a8hTT49mAG2jnRAYjAeKPgk96B7KoacMuB2/vrou8FXdNbwf8T/bqlmLRPt/O+do/QRPq5QgBWaV1kbsN7TaQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://liaoningjianzhi.cn/f/alipayaNotifyOrder";
	

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://liaoningjianzhi.cn/f/alipayReturn";
	


	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 日志地址
	public static String log_path = "/home/";
    
	// 收款方名字
	public static String seller_name = "沈阳建执教育咨询有限公司";
    
	// 签约支付宝账号或卖家收款支付宝帐户
	public static String seller_email = "liaoningjianzhi@163.com";

}
