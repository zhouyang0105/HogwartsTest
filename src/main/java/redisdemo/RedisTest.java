package redisdemo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @ClassName： RedisTest
 * @Description：Redis使用入门. 每个都是独立的demo
 * @Author： Yangyang
 * @Date： 2021/10/19 19:21 星期二
 * @Version： 1.0
 */
public class RedisTest {
    public static void main(String[] args) {
        connectRedis();
        System.out.println("=======================");

        redisString();
        System.out.println("=======================");

        redisList();
        System.out.println("=======================");



    }

    public static void connectRedis(){
        //连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("hogwrts");
        System.out.println("Redis连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行"+jedis.ping());
    }

    public static void redisString(){
        //连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("hogwrts");
        System.out.println("Redis连接成功");

        //设置redis字符串数据
        jedis.set("hogwartskey","霍格沃兹测试学院");
        //获取存储的数据并输出
        System.out.println("redis 存储的字符串为："+jedis.get("hogwartskey"));
    }

    public static void redisList(){
        //连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("hogwrts");
        System.out.println("Redis连接成功");

        //存储数据到列表中
        jedis.lpush("hogwarts-list","霍格沃兹测试学院");
        jedis.lpush("hogwarts-list","测试开发工程师");
        jedis.lpush("hogwarts-list","hogwartsTest");
        //获取存储的数据并输出
        List<String> list = jedis.lrange("hogwarts-list",0,2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为："+list.get(i));
        }
    }

    public static void redisKey(){
        //连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("hogwrts");
        System.out.println("Redis连接成功");
        
        //获取数据并输出
        Set<String> keys = jedis.keys("*");
        for (String key:keys){
            System.out.println(key);
        }
    }

}
