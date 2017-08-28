package com.chenjing.service;

import com.chenjing.entity.response.Article;
import com.chenjing.entity.response.NewsMessage;
import com.chenjing.entity.response.TextMessage;
import com.chenjing.utils.MessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lady Gaga on 2017/3/5.
 */
public class PicAndArtic {
    public String example(String fromUserName,String toUserName,String content){
        String respMessage = null;
        // 创建图文消息
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);

        List<Article> articleList = new ArrayList<Article>();
        // 单图文消息
        if ("1".equals(content)) {
            Article article = new Article();
            article.setTitle("陈静的GitHub");
            article.setDescription("道州吴彦祖？");
            article.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article.setUrl("https://github.com/min-min-min");
            articleList.add(article);
            // 设置图文消息个数
            newsMessage.setArticleCount(articleList.size());
            // 设置图文消息包含的图文集合
            newsMessage.setArticles(articleList);
            // 将图文消息对象转换成xml字符串
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        }
        // 单图文消息---不含图片
        else if ("2".equals(content)) {
            Article article = new Article();
            article.setTitle("以下是描述");
            // 图文消息中可以使用QQ表情、符号表情
            article.setDescription("陈静牛逼\n陈静牛逼。。。。。。。。。\n<h1>can i fuck you </h1>");
            // 将图片置为空
            article.setPicUrl("");
            article.setUrl("https://github.com/min-min-min");
            articleList.add(article);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        }
        // 多图文消息
        else if ("3".equals(content)) {
            Article article1 = new Article();
            article1.setTitle("以下是各种文章：\n引言\n");
            article1.setDescription("");
            article1.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article1.setUrl("https://github.com/min-min-min");

            Article article2 = new Article();
            article2.setTitle("第一篇：\n这是第一篇文章");
            article2.setDescription("");
            article2.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article2.setUrl("https://github.com/min-min-min");

            Article article3 = new Article();
            article3.setTitle("第二篇：\n这是第二篇文章");
            article3.setDescription("");
            article3.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article3.setUrl("https://github.com/min-min-min");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        }
        // 多图文消息---首条消息不含图片
        else if ("4".equals(content)) {
            Article article1 = new Article();
            article1.setTitle("这条内容包含了很多消息");
            article1.setDescription("");
            // 将图片置为空
            article1.setPicUrl("");
            article1.setUrl("https://github.com/min-min-min");

            Article article2 = new Article();
            article2.setTitle("第一篇：\n这是第一篇文章");
            article2.setDescription("");
            article2.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article2.setUrl("https://github.com/min-min-min");

            Article article3 = new Article();
            article3.setTitle("第二篇：\n这是第二篇文章");
            article3.setDescription("");
            article3.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article3.setUrl("https://github.com/min-min-min");

            Article article4 = new Article();
            article4.setTitle("第三篇：\n这是第三篇文章");
            article4.setDescription("");
            article4.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article4.setUrl("https://github.com/min-min-min");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            articleList.add(article4);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        }
        // 多图文消息---最后一条消息不含图片
        else if ("5".equals(content)) {
            Article article1 = new Article();
            article1.setTitle("我是第一个文章：\n这是第一个文章的内容");
            article1.setDescription("");
            article1.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article1.setUrl("https://github.com/min-min-min");

            Article article2 = new Article();
            article2.setTitle("我是第二个文章\n这是第二个文章的内容");
            article2.setDescription("");
            article2.setPicUrl("https://avatars0.githubusercontent.com/u/24689130?v=3&u=78a3383094aeace9c7213b120613d7a420114080&s=400");
            article2.setUrl("https://github.com/min-min-min");

            Article article3 = new Article();
            article3.setTitle("如果你喜欢我，请联系我");
            article3.setDescription("");
            // 将图片置为空
            article3.setPicUrl("");
            article3.setUrl("https://github.com/min-min-min");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        }else{
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            textMessage.setContent("为什么不试试发送数字1-5呢？宝贝");
            respMessage = MessageUtil.textMessageToXml(textMessage);
        }
        return  respMessage;
    }
}
