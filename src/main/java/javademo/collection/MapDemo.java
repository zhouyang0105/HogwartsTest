package javademo.collection;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @ClassName： MapDemo
 * @Description：
 * @Author： Yangyang
 * @Date： 2021/11/3 20:21 星期三
 * @Version： 1.0
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();



    }
    @Test
    public static void putTest(){
        // 定义一个Map的容器对象
        Map<String, Integer > map1 = new HashMap<String, Integer >();
        map1.put("jack", 20);
        map1.put("rose", 18);
        map1.put("lucy", 17);
        map1.put("java", 25);
        // map1.put("jack", 30)； 在没有hashCode和equals方式   添加重复的键值（值不同）,会覆盖掉前面key值相同的值
        System.out.println(map1);

        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("张三丰", 100);
        map2.put("虚竹", 20);
        System.out.println("map2:" + map2);
        // 从指定映射中将所有映射关系复制到此映射中。
        map1.putAll(map2);
        System.out.println("map1:" + map1);



        // 指定key，返回删除的键值对映射的值。
        map1.remove("java");
        System.out.println(map1);
        map1.clear();
        System.out.println("map1:" + map1);



        // V get(Object key) 通过指定的key对象获取value对象
        System.out.println("value:" + map1.get("jack"));
        // int size() 获取容器的大小
        System.out.println("map.size:" + map1.size());




        // 判断：
        // boolean isEmpty() 判断集合是否为空   长度为0返回true否则false
        // boolean containsKey(Object key) 判断集合中是否包含指定的key
        // boolean containsValue(Object value)

        Map<String, Integer> map3 = new HashMap<String, Integer>();
        map3.put("jack", 20);
        map3.put("rose", 18);
        map3.put("lucy", 17);
        map3.put("java", 25);
        System.out.println(map3);
        System.out.println("isEmpty:" + map3.isEmpty());
        System.out.println("containskey:" + map3.containsKey("jack"));
        System.out.println("containsvalues:" + map3.containsValue(100));

    }

    /**
     * map 遍历
     */
    public void test1(){

        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("jack", 20);
        map1.put("rose", 18);
        map1.put("lucy", 17);
        map1.put("java", 25);

        //遍历Map 第一种方式
        //通过 map1.keySet() 获取key  通过key 找到value
        for (String key : map1.keySet()) {
            Integer value = map1.get(key);
            System.out.println("key : "+key+" value : "+value);
        }


        //遍历Map 第二种方式
        //通过Map.Entry(String,Integer) 获取，然后使用entry.getKey()获取到键，通过entry.getValue()获取到值
        for(Map.Entry<String, Integer> entry : map1.entrySet()){
            System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
        }


        //第三种只遍历键或者值，通过加强for循环
        for(String s1:map1.keySet()){//遍历map的键
            System.out.println("键key ："+s1);
        }
        for(Integer s2:map1.values()){//遍历map的值
            System.out.println("值value ："+s2);
        }
        System.out.println("====================================");



        //第四种Iterator遍历获取，然后获取到Map.Entry<String, String>，再得到getKey()和getValue()
        Iterator<Map.Entry<String, Integer>> it=map1.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> entry=it.next();
            System.out.println("键key ："+entry.getKey()+" value ："+entry.getValue());
        }
    }
}
