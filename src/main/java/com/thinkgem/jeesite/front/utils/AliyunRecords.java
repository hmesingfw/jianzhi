package com.thinkgem.jeesite.front.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.DataFormat;
import org.dom4j.*;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AliyunRecords {

    @Test
    public void test() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        try{
            System.out.println(getYears(new Date(), 1));
        }catch(Exception e){

        }
    }

    /**
     * 通过视频id获取播放凭证
     * @param videoId
     * @return
     */
    public static List<Map<String, Object>> getVideoPlayAuth(String videoId){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Action", "GetVideoPlayAuth");
        parameters.put("VideoId", videoId);
        parameters.put("Version", "2017-03-21");

        String bs = getConnection(parameters, "http://vod.cn-shanghai.aliyuncs.com/");
        System.out.println(bs.toString());

        // 解析返回参数
        try {
            String testSource = "<?xml version='1.0' encoding='UTF-8'?>" +
                    "<GetVideoPlayAuthResponse>" +
                        "<RequestId>0A73578E-1483-4CB1-9DED-ED925E0C4C8E</RequestId>" +
                        "<VideoMeta>" +
                            "<CoverURL>http://video.qijia61.cn/snapshot/6282258ff5044b60bdf1df967197b08700001.jpg</CoverURL>" +
                            "<Status>Normal</Status>" +
                            "<VideoId>6282258ff5044b60bdf1df967197b087</VideoId>" +
                            "<Duration>844.5599975585938</Duration>" +
                            "<Title>元旦节活动方案000</Title>" +
                        "</VideoMeta>" +
                        "<PlayAuth>eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklwcG5uSXREQTNwdHN4S3F1ZDJEQWcwTmpYZmRwbHFyc3FqejJJSHBLZVhkdUFlQVhzL28wbW1oWjcvWVlsck1xRk1jWUdoeWNNNWN2c2NzSXIxUDdKcExGc3QySjZyOEpqc1ZkaitnKzVscXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0ZOLzducFNVUmJ2M2I0eGxMZXVrQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQWRQTDVwZ2lhTTFyOUVQYmRnZmhtaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDR4ckRoRzVnS3NNUWFnQUdXUjh5UW94dU1iQ2RReXJGMVNsNUNBSS9CUXMyeUtvb1ZZN2NPRXVsMk5LWmtCQ2hNbXc2WkRGUDFQMTBaL1d4YksxcC9OY0ZPOWtTTGdyMTJtK1VCb2o4TVVORXoyMTdZOUZLM0hmRHVmSjlVTUhaTjBZL3RQZmducGVlSGZzWWpSM0NqT2pZYWY1YnNTV2RPNVZDUEdsTWdqdVhHWmF3bkF1b0FQSzlZaWc9PSIsIkF1dGhJbmZvIjoie1wiQ2FsbGVyXCI6XCI0RXYyVThnMXdnMlhnY1diRkpXd1dzMFl2NEVPTkVHaWhYbERFckZjaXc0PVxcclxcblwiLFwiRXhwaXJlVGltZVwiOlwiMjAxNy0xMi0xNVQwNDo1MzowOVpcIixcIk1lZGlhSWRcIjpcIjYyODIyNThmZjUwNDRiNjBiZGYxZGY5NjcxOTdiMDg3XCIsXCJTaWduYXR1cmVcIjpcImt4b01zSmxpQW9qTXZuaVZCdm9OTldFanVtQT1cIn0iLCJWaWRlb01ldGEiOnsiU3RhdHVzIjoiTm9ybWFsIiwiVmlkZW9JZCI6IjYyODIyNThmZjUwNDRiNjBiZGYxZGY5NjcxOTdiMDg3IiwiVGl0bGUiOiLlhYPml6boioLmtLvliqjmlrnmoYgwMDAiLCJDb3ZlclVSTCI6Imh0dHA6Ly92aWRlby5xaWppYTYxLmNuL3NuYXBzaG90LzYyODIyNThmZjUwNDRiNjBiZGYxZGY5NjcxOTdiMDg3MDAwMDEuanBnIiwiRHVyYXRpb24iOjg0NC41Nn0sIkFjY2Vzc0tleUlkIjoiU1RTLkdaUmlqdDNETXNoRXVGcWdDNlF4RXlvR0giLCJQbGF5RG9tYWluIjoidmlkZW8ucWlqaWE2MS5jbiIsIkFjY2Vzc0tleVNlY3JldCI6IkJuWVN2b1h0V0tYS1h0WmZZMlVYbnRKeXdDc0pUNW1BVHo2aTJNOUhwSmVNIiwiUmVnaW9uIjoiY24tc2hhbmdoYWkiLCJDdXN0b21lcklkIjoxNzA1NzgwNjQ3Nzc3NjgyfQ==</PlayAuth>" +
                    "</GetVideoPlayAuthResponse>";

            // 返回数据集
            List<Map<String, Object>> list = Lists.newArrayList();
            if ("".equals(bs.toString())){
                return list;
            }

            Document document = DocumentHelper.parseText(bs.toString());
            Element root = document.getRootElement();

            Map<String, Object> map = Maps.newHashMap();
            Element ele_PlayAuth = root.element("PlayAuth");
            map.put(ele_PlayAuth.getName(), ele_PlayAuth.getText());

            Element el_onlineInfo = root.element("VideoMeta");
            Iterator it = el_onlineInfo.elementIterator();
            while(it.hasNext()) {
                Element el_VideoMeta = (Element) it.next();
                map.put(el_VideoMeta.getName(), el_VideoMeta.getText());
            }
            list.add(map);
            return list;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过视频id查询视频播放地址
     * @param videoId
     * @return
     */
    public static List<Map<String, Object>> getVideoPayUrl(String videoId){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Action", "GetPlayInfo");
        parameters.put("VideoId", videoId);
//        parameters.put("Formats", "xml");
        parameters.put("Version", "2017-03-21");

        String bs = getConnection(parameters, "http://vod.cn-shanghai.aliyuncs.com/");
        System.out.println(bs.toString());

        // 解析返回参数
        try {        
            // 返回数据集
            List<Map<String, Object>> list = Lists.newArrayList();
            if ("".equals(bs.toString())){
                return list;
            }

            Document document = DocumentHelper.parseText(bs.toString());
            System.out.println("--------------root--------------------");
            Element root = document.getRootElement();
            Element el_onlineInfo = root.element("PlayInfoList");
            Iterator it = el_onlineInfo.elementIterator();
            
            while(it.hasNext()){
                Element el_liveStreamPublishInfo = (Element) it.next();
                Iterator it2 = el_liveStreamPublishInfo.elementIterator();
                Map<String, Object> map = Maps.newHashMap();
                while (it2.hasNext()){
                    Element element = (Element) it2.next();
                    String key = element.getName();
                    Object value = element.getText();
                    if("PlayURL".equals(key)){
                    	System.out.println(value);
                    }
                    if ("Definition".equals(key)){
                        if ("LD".equals(value)){
                            map.put("DefinitionCN", "普清");
                        } else if ("SD".equals(value)){
                            map.put("DefinitionCN", "标清");
                        } else if ("HD".equals(value)){
                            map.put("DefinitionCN", "高清");
                        }
                    }
                    map.put(key, value);
                }
                System.out.println(map);
                list.add(map);
            }
            return list;
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 查询直播历史记录
     * @return
     */
    public static List<Map<String, Object>> getHistoryList(String streamName){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Action", "DescribeLiveStreamsPublishList");
        parameters.put("DomainName", "zb.qijia61.cn");
        parameters.put("AppName", "");
        parameters.put("Version", "2016-11-01");
        parameters.put("StreamName", streamName);
        parameters.put("StartTime", formatIso8601Date(getDays(new Date(), -7))); // 查询当前时间往后7天的历史记录
        parameters.put("EndTime", formatIso8601Date(getDays(new Date(), 2)));

        String bs = getConnection(parameters, "https://live.aliyuncs.com/");
        System.out.println(bs.toString());

        // 解析返回参数
        try {
            String testSource = "<?xml version='1.0' encoding='UTF-8'?>" +
                    "<DescribeLiveStreamsPublishListResponse>" +
                        "<PublishInfo>" +
                            "<LiveStreamPublishInfo>" +
                                "<EdgeNodeAddr></EdgeNodeAddr>" +
                                "<PublishTime>2017-11-06T05:41:52Z</PublishTime>" +
                                "<StreamName>ceshi</StreamName>" +
                                "<ClientAddr>183.67.56.231</ClientAddr>" +
                                "<PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl>" +
                                "<StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl>" +
                                "<StopTime>2017-11-06T05:45:39Z</StopTime>" +
                                "<DomainName>zb.cswzsoft.com</DomainName>" +
                                "<AppName>AppName</AppName>" +
                            "</LiveStreamPublishInfo>" +
                        "<LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-06T05:45:58Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-06T05:49:31Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T04:35:00Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T04:43:22Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T05:16:49Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T05:22:55Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T05:39:35Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T05:43:52Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T05:54:31Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T05:54:39Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T05:58:05Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T06:07:10Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T06:07:29Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T06:21:21Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T07:28:32Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T07:29:16Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T07:30:31Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T07:30:53Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T07:43:36Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T07:43:53Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T07:44:17Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T07:44:37Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T08:38:31Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T08:39:14Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T08:50:25Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T08:51:59Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T08:52:22Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T08:59:49Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T10:04:03Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T10:13:02Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo><LiveStreamPublishInfo><EdgeNodeAddr></EdgeNodeAddr><PublishTime>2017-11-07T10:44:28Z</PublishTime><StreamName>ceshi</StreamName><ClientAddr>183.67.56.231</ClientAddr><PublishUrl>rtmp://video-center.alivecdn.com/AppName</PublishUrl><StreamUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</StreamUrl><StopTime>2017-11-07T11:00:00Z</StopTime><DomainName>zb.cswzsoft.com</DomainName><AppName>AppName</AppName></LiveStreamPublishInfo></PublishInfo><RequestId>4E2076C7-6018-455C-8E0B-52E8CA6D1665</RequestId></DescribeLiveStreamsPublishListResponse>";

            // 返回数据集
            List<Map<String, Object>> list = Lists.newArrayList();
            if ("".equals(bs.toString())){
                return list;
            }

            Document document = DocumentHelper.parseText(bs.toString());

            Element root = document.getRootElement();
            Element el_onlineInfo = root.element("PublishInfo");
            Iterator it = el_onlineInfo.elementIterator();

            while(it.hasNext()){
                Element el_liveStreamPublishInfo = (Element) it.next();
                Iterator it2 = el_liveStreamPublishInfo.elementIterator();
                Map<String, Object> map = Maps.newHashMap();
                while (it2.hasNext()){
                    Element element = (Element) it2.next();
                    String key = element.getName();
                    Object value = element.getText();
                    if ("StopTime".equals(key)){
                        value = uTCFormat((String) value);
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value));
                    }
                    map.put(key, value);
                }
                list.add(map);
            }
            return list;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询直播在线列表
     * @param appName
     * @return
     */
    public static List<Map<String, Object>> getOnlineList(String appName){
        try{
            // 加入请求参数
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("Action", "DescribeLiveStreamsOnlineList");
            parameters.put("DomainName", "zb.qijia61.cn");
            parameters.put("AppName", appName);
            parameters.put("Version", "2016-11-01");

            String bs = getConnection(parameters, "https://live.aliyuncs.com/");

            System.out.println(bs.toString());

            String testXML = "<?xml version='1.0' encoding='UTF-8'?>" +
                    "<DescribeLiveStreamsOnlineListResponse>" +
                        "<RequestId>90626E81-3960-4491-8CA2-D926EF59D2B3</RequestId>" +
                        "<OnlineInfo>" +
                            "<LiveStreamOnlineInfo>" +
                                "<PublishTime>2017-11-07T07:44:17Z</PublishTime>" +
                                "<StreamName>ceshi</StreamName>" +
                                "<PublishUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</PublishUrl>" +
                                "<DomainName><a>dd</a></DomainName>" +
                                "<AppName>dd</AppName>" +
                            "</LiveStreamOnlineInfo>" +
//                            "<LiveStreamOnlineInfo>" +
//                                "<PublishTime>2017-11-07T07:44:17Z</PublishTime>" +
//                                "<StreamName>ceshi</StreamName>" +
//                                "<PublishUrl>rtmp://zb.cswzsoft.com/AppName/ceshi</PublishUrl>" +
//                                "<DomainName>zb.cswzsoft.com</DomainName>" +
//                                "<AppName>AppName</AppName>" +
//                            "</LiveStreamOnlineInfo>" +
                        "</OnlineInfo>" +
                    "</DescribeLiveStreamsOnlineListResponse>";

            List<Map<String, Object>> list = Lists.newArrayList();
            if ("".equals(bs.toString())){
                return list;
            }

            // 2. 转换成document对象
            Document document = DocumentHelper.parseText(bs.toString());
//            Document document = DocumentHelper.parseText(testXML);
            // 3. 获取根节点
            Element root = document.getRootElement();
            Element el_onlineInfo = root.element("OnlineInfo");
            Iterator it = el_onlineInfo.elementIterator();
            // 循环OnlineInfo的子节点
            while(it.hasNext()){
                Element el_LiveStreamOnlineInfo = (Element) it.next();
                Iterator it2 = el_LiveStreamOnlineInfo.elementIterator();
                Map<String, Object> map = Maps.newHashMap();
                // 循环 LiveStreamOnlineInfo的子节点
                while(it2.hasNext()){
                    Element element = (Element) it2.next();
                    String key = element.getName();
                    String value = element.getText();
                    // 1. 创建返回对象
                    map.put(key, value);
                }
             list.add(map);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 获取链接
    private static String getConnection(Map<String, Object> parameters, String httpUrl){

        try{
            URL url = new URL(httpUrl+"?"+getDescribeDomainRecords(parameters));
            HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
            urlcon.setRequestProperty("contentType", "UTF-8");
//            urlcon.setConnectTimeout(5 * 1000);
            urlcon.setRequestMethod("GET");
            urlcon.connect();         //获取连接
            InputStream is = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while((l=buffer.readLine())!=null){
                bs.append(l).append("\n");
            }
            return bs.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 日期位移
     * @param date -- 需要位移的日期
     * @param days -- 需要位移的天数（正数向前位移，负数向后位移。例如：-1：输入日期的前一天）
     * @return
     */
    public static Date getDays(Date date, int days) {
        if (date == null){
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        return date;
    }

    /**
     * 年位移
     * @param date -- 需要位移的日期
     * @param year -- 需要位移的年数（正数向前位移，负数向后位移。例如：-1：输入日期的前一年）
     * @return
     */
    public static Date getYears(Date date, int year) {
        if (date == null){
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        date = calendar.getTime();
        return date;
    }

    /**
     * 时间格式化 -- 返回一个时间对象
     * @param date -- 传null为当前时间
     * @param dateformat -- 格式化方式，例如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date formatDate(Date date, String dateformat){
        if (date == null ){
            date = new Date();
        }
        DateFormat df = new SimpleDateFormat(dateformat);
        try {
            return df.parse(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间格式化 -- 返回一个字符串
     * @param date -- 传null为当前时间
     * @param dateformat -- 格式化方式，例如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDate2(Date date, String dateformat){
        if (date == null ){
            date = new Date();
        }
        DateFormat df = new SimpleDateFormat(dateformat);
        return df.format(date);
    }

    /**
     * 时间格式化
     * @param date -- 时间字符串，传空返回当前时间
     * @param dateFormat -- 格式化方式，例如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseDate(String date, String dateFormat){
        DateFormat df = new SimpleDateFormat(dateFormat);
        if (date == null || "".equals(date)){
            try {
                return df.parse(df.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * UTC时间转换成北京时间
     * @param dateTime
     * @return
     */
    private static Date uTCFormat(String dateTime){
        dateTime = dateTime.replace("T", " ");
        dateTime = dateTime.replace("Z", "");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 8);
            date = calendar.getTime();
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    private static String getDescribeDomainRecords(Map<String, Object> parameters) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException{

        final String HTTP_METHOD = "GET";

//        Map<String, String> parameters = new HashMap<String, String>();
        // 加入请求参数
//        parameters.put("Version", "2016-11-01");
        parameters.put("AccessKeyId", "LTAIAHzrGT0Nk7kF");//输入你的 Access Key ID  LTAIgmjhXauh17iz
        parameters.put("Timestamp", formatIso8601Date(new Date()));
        parameters.put("SignatureMethod", "HMAC-SHA1");
        parameters.put("SignatureVersion", "1.0");
        parameters.put("SignatureNonce", UUID.randomUUID().toString());
        parameters.put("Format", "XML");

        // 对参数进行排序，注意严格区分大小写
        String[] sortedKeys = parameters.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);

        final String SEPARATOR = "&";

        // 生成stringToSign字符串
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);

        StringBuilder canonicalizedQueryString = new StringBuilder();
        for(String key : sortedKeys) {
            // 这里注意对key和value进行编码
            canonicalizedQueryString.append("&")
                    .append(percentEncode(key)).append("=")
                    .append(percentEncode((String) parameters.get(key)));
        }

        // 这里注意对canonicalizedQueryString进行编码
        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

        String signature = getSignature(stringToSign.toString());

        canonicalizedQueryString.append("&")
                .append("Signature").append("=")
                .append(percentEncode(signature));
        String returnStr = canonicalizedQueryString.toString();
        returnStr = returnStr.substring(1);
        return returnStr;
    }

    // 时间格式
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    // 时间格式化
    private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }


    // 解码编码
    private static final String ENCODING = "UTF-8";

    // 解码
    private static String percentEncode(String value)
            throws UnsupportedEncodingException{
        return value != null ?
                URLEncoder.encode(value, ENCODING).replace("+", "%20")
                        .replace("*", "%2A").replace("%7E", "~")
                : null;
    }


    // 以下是一段计算签名的示例代码
    private static String getSignature(String stringToSign) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException
    {
        final String ALGORITHM = "HmacSHA1";
        final String ENCODING = "UTF-8";
        String key = "2s6jfKV1B9Ff8wLpyMTkSTjTHmAeXe&";//注意 key = Access Key Secret +"&"
        				
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(
                key.getBytes(ENCODING), ALGORITHM));
        byte[] signData = mac.doFinal(
                stringToSign.getBytes(ENCODING));

        String signature =
                new String(Base64.encodeBase64(signData));
        return signature;
    }

}