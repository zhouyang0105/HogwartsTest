package javademo.collection;

import java.util.*;

/**
 * @ClassName： HashMapDemo
 * @Description： HashMap用法
 * @Author： Yangyang
 * @Date： 2021/10/31 10:43 星期日
 * @Version： 1.0
 */
public class HashMapDemo {

    public static void main(String[] args) {

        //FirstNotRepeatingChar();

    }

    public static int FirstNotRepeatingChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] c = str.toCharArray();
        // 第一步：初始化哈希表，重复出现的计数
        for(int i = 0; i < c.length; i++){
            map.put(c[i], map.getOrDefault(c[i], 0)+1);
        }

        // 第二步：二次遍历字符数组，若其在哈希表中的value为1，则返回下标
        for(int i = 0; i < c.length; i++){
            if(map.get(c[i]) == 1){
                return i;
            }
        }
        // 没有符合条件的则返回-1
        return -1;

    }
}
