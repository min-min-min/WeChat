package com.chenjing.service;


import com.chenjing.entity.response.TextMessage;
import com.chenjing.utils.MessageUtil;
import com.chenjing.utils.RegxUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by Lady Gaga on 2017/3/4.
 */
public class BaseService {

    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "本公众号目前只支持以下功能：\n1、手机号归属地查询\n2、IP地址归属地查询\n3、身份证查询\n4、天气查询（格式为：XX天气）";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 接收用户发送的文本消息内容
                String content = requestMap.get("Content");
                ThirdService thirdService = new ThirdService();
                System.out.println("这是手机号 if" + content);
                if (RegxUtil.isMobileNO(content)) {
                    //是手机号 那么直接给用户返回手机的情况
                    System.out.println("这是手机号" + content);
                    respContent = thirdService.getPhoneNoLocation(content);
                }
                if (RegxUtil.isIP(content)) {
                    System.out.println("这是ip" + content);
                    respContent = thirdService.getIPInfo(content);
                }
                if (RegxUtil.isIdCard(content)) {
                    System.out.println("这是idcard" + content);
                    respContent = thirdService.getIdCardInfo(content);
                }
                if (RegxUtil.isWeather(content)) {
                    System.out.println("这是天气" + content);
                    respContent = thirdService.getWeather(content);
                }

                if (content.equals("1") || content.equals("2") || content.equals("3") || content.equals("4") || content.equals("5")) {
                    //回复图文消息
                    PicAndArtic picAndArtic = new PicAndArtic();
                    return picAndArtic.example(fromUserName, toUserName, content);
                }
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "感谢您的关注！\n本公众号目前只支持以下功能：\n1、手机号归属地查询\n2、IP地址归属地查询\n3、身份证号码查询\n4、天气查询（格式为：XX天气）";
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                }
            } else {
                respContent = "谢谢你的反馈！";
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }

}
