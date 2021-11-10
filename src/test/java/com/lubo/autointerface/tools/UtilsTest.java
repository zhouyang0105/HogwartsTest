package com.lubo.autointerface.tools;

import java.util.Random;

/**
 * @ClassName： UtilsTest
 * @Description： 记录平常常用的一些封装方法
 * @Author： Yangyang
 * @Date： 2021/10/30 19:38 星期六
 * @Version： 1.0
 */
public class UtilsTest {
    public static void main(String[] args) {
        //System.out.println(getPhoneNum("171"));//生成随机的手机号码
    }

    /**
     * 生成随机的手机号码
     *
     * @param string
     * @return
     */
    public static String getPhoneNum(String string) {
        Random r = new Random();
        return string + String.format("%08d", r.nextInt(99999999));
    }

/*    public static String getPhoneNum1(String string){
        String phonePrefix = "171"; //前缀
        String timestamp = System.currentTimeMillis().toString();

        Random random = new Random();
        int random1 = random.nextInt(10);
        int random2 = random.nextInt(10);

        String phoneENDS = random1.toString() + timestamp.substring(7) + random2.toString();

        vars.put("phone_no", phonePrefix + phoneENDS);
    }*/

}
