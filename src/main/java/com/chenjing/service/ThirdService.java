package com.chenjing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Lady Gaga on 2017/3/5.
 */
//https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_name=guishudi&query=phoneNo

/**
 * 第三方接口
 */
public class ThirdService {

    /***https://www.nowapi.com 接口数据
     * 有效期为2017-9-8
     * */
    final String nowAPISecret = "dff2d1631781ca916bf6a4dad2e79434";
    final String nowAPISign = "aee5206e66413fdb9d1559f2e1e44107";
    final String nowAPIAppKey = "23769";


    /**
     * 获取手机归属地
     *
     * @param phoneNo
     * @return jsonobject
     */
    HttpConnect httpConnect = new HttpConnect();

    public String getPhoneNoLocation(String phoneNo) {
        String result = "";
        result = HttpConnect.get("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_name=guishudi&query=" + phoneNo, "gbk");
        if (result==null){
            return "抱歉，查询失败";
        }
        System.out.println("result=>" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println("json=>" + jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        JSONObject data = jsonArray.getJSONObject(0);
        return "号码：" + phoneNo + "\n所属省份：" + data.getString("prov") + "\n所属城市：" + data.getString("city") + "\n号码类型：" + data.getString("type");
    }

    public String getIPInfo(String ip) {
        String result = "";
        result = HttpConnect.get("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip + "&resource_id=6006&format=json", "gbk");
        if (result==null){
            return "抱歉，查询失败";
        }
        System.out.println("result=>" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println("json=>" + jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        JSONObject data = jsonArray.getJSONObject(0);
        return "IP：" + ip + "\nIP信息：" + data.getString("location");
    }

    /**
     * 获取身份证信息
     */
    public String getIdCardInfo(String id) {
        String result = "";
        result = HttpConnect.get("http://api.k780.com:88/?app=idcard.get&idcard=" + id + "&appkey=" + nowAPIAppKey + "&sign=" + nowAPISign + "&format=json", "utf-8");
        if (result==null){
            return "抱歉，查询失败";
        }
        System.out.println("result=>" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println("json=>" + jsonObject);
        if ("0".equals(jsonObject.getString("success"))) {
            return "调用接口系统级别错误";
        } else {
            JSONObject resultjson = jsonObject.getJSONObject("result");
            if ("WAIT_PROCESS".equals(resultjson.getString("status"))) {
                return "等待系统处理中...";
            } else if ("NOT_ATT".equals(resultjson.getString("status"))) {
                return "抱歉，无信息";
            } else if ("ALREADY_ATT".equals(resultjson.getString("status"))) {
                return "身份证号码：" + id + "\n性别：" + resultjson.getString("sex") + "\n归属地：" + resultjson.getString("att") + "\n邮编："
                        + resultjson.getString("postno") + "\n区号：" + resultjson.getString("areano");
            } else {
                return "";
            }
        }
    }

    public String getWeather(String str) {
        String addr = str.substring(0, str.length() - 2);
        if (addr.equals("") || addr == null) {
            return "请输入正确的天气格式（XX天气）";
        }
        System.out.println("分隔后的天气地区：" + addr);
        String result = "";
        result = HttpConnect.get("http://api.k780.com:88/?app=weather.future&weaid=" + addr + "&appkey=" + nowAPIAppKey + "&sign=" + nowAPISign + "&format=json", "utf-8");
        if (result==null){
            return "抱歉，查询失败";
        }
        System.out.println("result=>" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println("json=>" + jsonObject);
        StringBuilder sb = new StringBuilder();
        if ("1".equals(jsonObject.getString("success"))) {
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject temp = jsonArray.getJSONObject(i);
                    sb.append(temp.getString("citynm"));
                    sb.append("  " + temp.getString("days"));
                    sb.append("  " + temp.getString("week") + "\n");
                    sb.append("  " + temp.getString("weather"));
                    sb.append("  " + temp.getString("temperature"));
                    sb.append("  " + temp.getString("wind"));
                    sb.append("  " + temp.getString("winp") + "\n");
                }
                return sb.toString();
            } else {
                return "获取不到" + addr + "的天气";
            }
        } else {
            return "获取不到" + addr + "的天气";
        }
    }

}
