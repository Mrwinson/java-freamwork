package com.cloudm.framework.common.sms;

import com.cloudm.framework.common.util.SmsUtil;
import com.google.gson.Gson;

/**
 * @description: Sms 单元测试
 * @author: Courser
 * @date: 2017/3/17
 * @version: V1.0
 */
public class SmsUtilTest {
 public static void main(String[] args){
     Demo demo  =  new Demo();
     demo.setProductName("wesd");
     System.out.print(SmsUtil.send(new Gson().toJson(demo),"18910274841"));
 }

}
class Demo{
    private String productName ;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}