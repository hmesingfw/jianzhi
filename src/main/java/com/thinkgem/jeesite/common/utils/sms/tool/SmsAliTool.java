package com.thinkgem.jeesite.common.utils.sms.tool;

import java.io.IOException;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里云短信工具类
 * Created by LV on 2017/6/7 0015.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * Email:LvLoveYuForever@gmail.com
 */
public class SmsAliTool {

	//    private static final String ACTION="SingleSendSms";//操作接口名，系统规定参数，取值：SingleSendSms   Action
    private static final String SIGN="OK教育网注册验证码";//管理控制台中配置的短信签名（状态必须是验证通过）   SignName   41500103  巨详网络
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIRsEsl2kxvnQ2";
    static final String accessKeySecret = "FWBJ8VMZzWRJ5D7PQsDg3ct03cv5oX";
    
    public static final String ZCYZ_MOULD_ID="SMS_120115203";//注册验证模板ID			欢迎使用青海高教，您正在注册成为平台用户，验证码：${number}（如您已成功注册，请忽略此条信息）
    public static final String ZHMM_MOULD_ID="SMS_119088283";//找回密码模板ID			欢迎使用青海高教，您正在操作找回密码功能，验证码：${number}（如不是本人操作，请忽略此条信息）
    public static final String BDSJ_MOULD_ID="SMS_87550029";//绑定手机模板ID			欢迎使用青海高教，您正在修改绑定手机号码，验证码：${number}（如不是本人操作，请忽略此条信息）
    public static final String CZDLMM_MOULD_ID="SMS_119088283";//重置登录密码模板ID	欢迎使用青海高教，您正在操作重置登录密码功能，验证码：${number}（如不是本人操作，请忽略此条信息）
    
//    public static final String GMTZ_MOULD_ID="SMS_41665119";//购买通知模板ID		尊敬的${name}，您已成功购买${goods}，谢谢您的支持，请登录查看详情
//    public static final String TXTZ_MOULD_ID="SMS_41540074";//提现通知模板ID    	尊敬的${name}，您申请的一笔提现，管理员已${result}，请登录查看详情

    /**
     * 模版发送短信通知
     * @param mouldId	模板编号
     * @param phone	接收者号码列表，JSONArray格式,如["186xxxxxxxx","186xxxxxxxx"]，限制接收者号码个数最多为100个
     * @param params	短信参数列表，用于依次填充模板，JSONArray格式，如["xxx","yyy"];对于不包含变量的模板，不填此参数表示模板即短信全文内容
     * @return
     * @throws IOException
     */
    public static void sendSms(String mouldId, String phone, String params) throws Exception{   
    	System.out.println(mouldId);
    	System.out.println(phone);
    	System.out.println(params);
//    	try {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient,暂时不支持多region
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(SmsAliTool.SIGN);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(mouldId);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam(params);
            //可选-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println("-------"+sendSmsResponse.getCode());
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            	//请求成功
            }else{
            	System.out.println("error");
            }
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
    }
    
    public static void main(String[] args) {
    	try {
//    		SmsAliTool.sendSms(SmsAliTool.GMTZ_MOULD_ID,"17783689928","{\"name\":\"邓军\",\"goods\":\"Iphone7 plus 金色 128G\"}");
        	SmsAliTool.sendSms(SmsAliTool.ZCYZ_MOULD_ID,"17783689928","{\"code\":\"123456\"}");
//        	SmsAliTool.sendSms(SmsAliTool.TXTZ_MOULD_ID,"17783689928","{\"name\":\"邓军\",\"result\":\"通过审核\"}");
        	//        	if(code.equals("500")) {
//        		System.out.println(0);
//        	} else {
//        		System.out.println(code);
//        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}
    
}