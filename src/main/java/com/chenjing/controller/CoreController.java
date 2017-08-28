package com.chenjing.controller;


import com.chenjing.service.BaseService;
import com.chenjing.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 核心请求处理类
 */
@RestController
public class CoreController {

    @Autowired
    private SignUtil signUtil;

    /**
     * 确认请求来自微信服务器
     */
    @GetMapping(value = "/chenjing")
    public String doGet(HttpServletRequest request) throws ServletException, IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    @PostMapping("/chenjing")
    public String doPost(HttpServletRequest request) throws ServletException, IOException {
        // 调用核心业务类接收消息、处理消息
        BaseService baseService = new BaseService();
        String respMessage = baseService.processRequest(request);
        return respMessage;
    }

}
