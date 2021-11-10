package javademo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName： ArrayBlockingQueueExample
 * @Description：有界的阻塞队列
 * 特点：
 *   1. ArrayBlockingQueue是一个用数组实现的有界阻塞队列，此队列按照先进先出（FIFO）的原则对元素进行排序。
 *   2. ArrayBlockingQueue是基于数组实现的，也就具有数组的特性：一旦初始化，大小就无法修改。
 *
 * @Author： Yangyang
 * @Date： 2021/10/28 11:27 星期四
 * @Version： 1.0
 */
public class ArrayBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        // add();
        // offer();
        // put();
        // poll();
        // peek();
        // element();
        //remove();

        // take();
        // size();
        // contains;
    }

    /** 如果可以在不超过队列的容量的情况下立即将其指定的元素插入到该队列的尾部，
     如果队列已满，则返回 true并抛出 IllegalStateException 。
     执行结果：
     =====执行add()签名方法-开始=====
     arrayBlockingQueue.add()执行返回结果：true
     arrayBlockingQueue.add()执行返回结果：true
     arrayBlockingQueue.add()执行返回结果：true
     Exception in thread "main" java.lang.IllegalStateException: Queue full
     at java.util.AbstractQueue.add(AbstractQueue.java:98)
     at java.util.concurrent.ArrayBlockingQueue.add(ArrayBlockingQueue.java:312)
     at com.yuanxw.thread.chapter25.ArrayBlockingQueueExample.add(ArrayBlockingQueueExample.java:28)
     at com.yuanxw.thread.chapter25.ArrayBlockingQueueExample.main(ArrayBlockingQueueExample.java:7)
     */
    public static void add() {
        System.out.println("=====执行add()签名方法-开始=====");
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        System.out.println("arrayBlockingQueue.add()执行返回结果："+arrayBlockingQueue.add("Message 1"));
        System.out.println("arrayBlockingQueue.add()执行返回结果："+arrayBlockingQueue.add("Message 2"));
        System.out.println("arrayBlockingQueue.add()执行返回结果："+arrayBlockingQueue.add("Message 3"));
        System.out.println("arrayBlockingQueue.add()执行返回结果："+arrayBlockingQueue.add("Message 4"));
        System.out.println("=====执行add()签名方法-结束=====");
    }

    /**
     * 如果可以在不超过队列容量的情况下立即将其指定的元素插入该队列的尾部，
     * 则在成功时true如果该队列已满，则返回false 。
     执行结果：
     =====执行offer()签名方法-开始=====
     arrayBlockingQueue.offer()执行返回结果：true
     arrayBlockingQueue.offer()执行返回结果：true
     arrayBlockingQueue.offer()执行返回结果：true
     arrayBlockingQueue.offer()执行返回结果：false
     =====执行offer()签名方法-结束=====
     */
    public static void offer(){
        System.out.println("=====执行offer()签名方法-开始=====");
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        System.out.println("arrayBlockingQueue.offer()执行返回结果："+arrayBlockingQueue.offer("Message 1"));
        System.out.println("arrayBlockingQueue.offer()执行返回结果："+arrayBlockingQueue.offer("Message 2"));
        System.out.println("arrayBlockingQueue.offer()执行返回结果："+arrayBlockingQueue.offer("Message 3"));
        System.out.println("arrayBlockingQueue.offer()执行返回结果："+arrayBlockingQueue.offer("Message 4"));
        System.out.println("=====执行offer()签名方法-结束=====");
    }

    /**
     * 在该队列的尾部插入指定的元素，如果队列已满，则等待空间变为可用。
     执行结果：
     =====执行put()签名方法-开始=====
     当前arrayBlockingQueue对象中的个数：3
     当前arrayBlockingQueue对象中的容量：0
     当前arrayBlockingQueue对象中take()数据值为：Message 1
     Message 2
     Message 3
     Message 4
     =====执行put()签名方法-结束=====
     */
    public static void put() throws InterruptedException {
        System.out.println("=====执行put()签名方法-开始=====");
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.put("Message 1");
        arrayBlockingQueue.put("Message 2");
        arrayBlockingQueue.put("Message 3");


        Executors.newSingleThreadExecutor().execute(()->{
            System.out.println("当前arrayBlockingQueue对象中的个数：" + arrayBlockingQueue.size());
            System.out.println("当前arrayBlockingQueue对象中的容量：" + arrayBlockingQueue.remainingCapacity());
            try {
                TimeUnit.SECONDS.sleep(5);
                // 检索并删除此队列的头
                Object take = arrayBlockingQueue.take();
                System.out.println("当前arrayBlockingQueue对象中take()数据值为：" + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        arrayBlockingQueue.put("Message 4");
        arrayBlockingQueue.forEach(System.out::println);
        System.out.println("=====执行put()签名方法-结束=====");
    }

    /**
     * 检索并删除此队列的头，则等待空间变为可用。
     执行结果：
     =====执行poll()签名方法-开始=====
     当前arrayBlockingQueue对象中的个数：【3】,容量：【0】
     当前arrayBlockingQueue对象中poll()数据值为：Message 1
     当前arrayBlockingQueue对象中poll()数据值为：Message 2
     当前arrayBlockingQueue对象中的个数：【1】,容量：【2】
     当前arrayBlockingQueue对象中poll()数据值为：Message 3
     当前arrayBlockingQueue对象中poll()数据值为：null
     当前arrayBlockingQueue对象中poll()数据值为：null
     =====执行poll()签名方法-结束=====
     */
    public static void poll() throws InterruptedException {
        System.out.println("=====执行poll()签名方法-开始=====");
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.put("Message 1");
        arrayBlockingQueue.put("Message 2");
        arrayBlockingQueue.put("Message 3");
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("当前arrayBlockingQueue对象中poll()数据值为：" + arrayBlockingQueue.poll());
        System.out.println("当前arrayBlockingQueue对象中poll()数据值为：" + arrayBlockingQueue.poll());
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("当前arrayBlockingQueue对象中poll()数据值为：" + arrayBlockingQueue.poll());
        System.out.println("当前arrayBlockingQueue对象中poll()数据值为：" + arrayBlockingQueue.poll());
        System.out.println("当前arrayBlockingQueue对象中poll()数据值为：" + arrayBlockingQueue.poll());
        System.out.println("=====执行poll()签名方法-结束=====");
    }


    /**
     * 检索但不删除此队列的头，如果此队列为空，则返回 null。
     执行结果：
     =====执行peek()签名方法-开始=====
     当前arrayBlockingQueue对象中的个数：【3】,容量：【0】
     当前arrayBlockingQueue对象中peek()数据值为：Message 1
     当前arrayBlockingQueue对象中peek()数据值为：Message 1
     当前arrayBlockingQueue对象中的个数：【3】,容量：【0】
     当前arrayBlockingQueue对象中peek()数据值为：Message 1
     当前arrayBlockingQueue对象中peek()数据值为：Message 1
     当前arrayBlockingQueue对象中peek()数据值为：Message 1
     =====执行peek()签名方法-结束=====
     */
    public static void peek() throws InterruptedException {
        System.out.println("=====执行peek()签名方法-开始=====");
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.put("Message 1");
        arrayBlockingQueue.put("Message 2");
        arrayBlockingQueue.put("Message 3");
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("当前arrayBlockingQueue对象中peek()数据值为：" + arrayBlockingQueue.peek());
        System.out.println("当前arrayBlockingQueue对象中peek()数据值为：" + arrayBlockingQueue.peek());
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("当前arrayBlockingQueue对象中peek()数据值为：" + arrayBlockingQueue.peek());
        System.out.println("当前arrayBlockingQueue对象中peek()数据值为：" + arrayBlockingQueue.peek());
        System.out.println("当前arrayBlockingQueue对象中peek()数据值为：" + arrayBlockingQueue.peek());
        System.out.println("=====执行peek()签名方法-结束=====");
    }

    /**
     * 检索，但不删除，这个队列的头。 此方法与peek的不同之处在于，如果此队列为空，它将抛出异常。
     执行结果：
     =====执行element()签名方法-开始=====
     当前arrayBlockingQueue对象中的个数：【3】,容量：【0】
     当前arrayBlockingQueue对象中take()数据值为：Message 1
     当前arrayBlockingQueue对象中take()数据值为：Message 2
     当前arrayBlockingQueue对象中take()数据值为：Message 3
     当前arrayBlockingQueue对象中的个数：【0】,容量：【3】
     Exception in thread "main" java.util.NoSuchElementException
     at java.util.AbstractQueue.element(AbstractQueue.java:136)
     at com.yuanxw.thread.chapter25.ArrayBlockingQueueExample.element(ArrayBlockingQueueExample.java:182)
     at com.yuanxw.thread.chapter25.ArrayBlockingQueueExample.main(ArrayBlockingQueueExample.java:14)
     */
    public static void element() throws InterruptedException {
        System.out.println("=====执行element()签名方法-开始=====");
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.put("Message 1");
        arrayBlockingQueue.put("Message 2");
        arrayBlockingQueue.put("Message 3");
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("当前arrayBlockingQueue对象中take()数据值为：" + arrayBlockingQueue.take());
        System.out.println("当前arrayBlockingQueue对象中take()数据值为：" + arrayBlockingQueue.take());
        System.out.println("当前arrayBlockingQueue对象中take()数据值为：" + arrayBlockingQueue.take());
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("当前arrayBlockingQueue对象中element()数据值为：" + arrayBlockingQueue.element());
        System.out.println("=====执行element()签名方法-结束=====");
    }

    /**
     * 检索并删除此队列的头。 此方法与poll不同之处在于，如果此队列为空，它将抛出异常。
     执行结果：
     =====执行remove()签名方法-开始=====
     当前arrayBlockingQueue对象中的个数：【3】,容量：【0】
     当前arrayBlockingQueue对象中remove()数据值为：Message 1
     当前arrayBlockingQueue对象中remove()数据值为：Message 2
     当前arrayBlockingQueue对象中remove()数据值为：Message 3
     Exception in thread "main" java.util.NoSuchElementException
     at java.util.AbstractQueue.remove(AbstractQueue.java:117)
     at com.yuanxw.thread.chapter25.ArrayBlockingQueueExample.remove(ArrayBlockingQueueExample.java:212)
     at com.yuanxw.thread.chapter25.ArrayBlockingQueueExample.main(ArrayBlockingQueueExample.java:15)
     */
    public static void remove() throws InterruptedException {
        System.out.println("=====执行remove()签名方法-开始=====");
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.put("Message 1");
        arrayBlockingQueue.put("Message 2");
        arrayBlockingQueue.put("Message 3");
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("当前arrayBlockingQueue对象中remove()数据值为：" + arrayBlockingQueue.remove());
        System.out.println("当前arrayBlockingQueue对象中remove()数据值为：" + arrayBlockingQueue.remove());
        System.out.println("当前arrayBlockingQueue对象中remove()数据值为：" + arrayBlockingQueue.remove());
        System.out.println("当前arrayBlockingQueue对象中remove()数据值为：" + arrayBlockingQueue.remove());
        System.out.println(String.format("当前arrayBlockingQueue对象中的个数：【%s】,容量：【%s】", arrayBlockingQueue.size(),arrayBlockingQueue.remainingCapacity()));
        System.out.println("=====执行remove()签名方法-结束=====");
    }
}
