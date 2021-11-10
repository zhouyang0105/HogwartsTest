package com.lubo.autointerface.mianshi;

import com.hogwarts.service.wechat.department.Demo_01_base;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @ClassName： ATest
 * @Description： 面试题演练
 * @Author： Yangyang
 * @Date： 2021/10/14 19:03 星期四
 * @Version： 1.0
 *
 */
public class ATest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class); //打印日志

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        //int[] A = {1};
        //int[] B = {2};
        //System.out.println( Arrays.toString(array) );
        //for(int a:array){
        //    System.out.println(a);
        //}
        //System.out.println(Arrays.toString(bubbleSort(array)));

        //Fibonacci(4);
        // System.out.println( gcd(4,21) ); 最大公约数结果=1
        // System.out.println(lostTest(array));
        //merge(A,1,B,2);

        //int[] a={1,2,3,4,5,6,7,8,9,};
        //binarysearch1(a,3); //二分查找

        //System.out.println(test1(a,11)); //2数之和对应下标

        // String s = "()[]{}"; // 有效括号
        // System.out.println( isValid(s));

        int[] arr = {6,-3,-2,7,-15,1,2,2}; // NC19 连续子数组的最大和
        System.out.println(findGreatestSumOfSubArray(arr));
    }
    /**
     *1.冒泡: 前面比后面大，进行交换。一次循环。
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
        return array;
    }

    /**
     * 2.选择： 首先选一个最小的，放在开始位置。从未排序列中找最小的一次放置。
     */
    public static int[] selectionSort(int[] array) {
        if (array.length == 0) return array;

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;

            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    /**
     * 3.插入： 比前面大，比后面小，插入中间位置
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 4.快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] QuickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(array, smallIndex + 1, end);
        return array;
    }
    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }
    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 数组打印3种方式
     */
    @Test
    public void forTest(){
        //1.传统的for循环方式
        int[] array = {1,2,3,4,5};
        for(int i=0;  i< array.length;i++){
            System.out.println(array[i]);
        }
        //for each循环
        for(int a:array)
            System.out.println(a);
        //利用Array类中的toString方法
        System.out.println(Arrays.toString(array));
    }

    /** 1.NC65 斐波那契数列- 入门级
     *
     */
    public static int Fibonacci(int n) {

        if (n < 1) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int[] arr = new int[n];
        arr[0] = arr[1] = 1;		//第一个和第二个数据特殊处理
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i -2] + arr[i - 1];
        }

        return arr[n-1];
    }


    /**
     *2. NC103 字符串反转
     */
    public String solve (String str) {
        //方法一
        //return new StringBuilder(str).reverse().toString();

        //方法二
        // 采用双指针，首尾指针互相交换数据，直到相遇
        char[] a = str.toCharArray();

        for(int i=0, j=a.length-1; i<j; i++,j-- ){
            char tmp = a[i];
            a[i] = a[j];
            a[j] =tmp;
        }
        return new String(a);//把数组再转换成字符串
    }

    /**
     * 3.NC151 最大公约数
     */
    public static int gcd (int a, int b) {
        // write code here
        //方法一
/*        while(a!=b)
        {
            if(a>b) a=a-b;
            else b=b-a;
        }
        return a; //此时a==b*/

        //方法二
        if(a%b==0){ return b; }
        else { return gcd(b,a%b); }
    }

    /**
     * 4.NC141 判断回文
     */
    public boolean judge (String str) {
        // write code here
        char[] arr = str.toCharArray();
        // boolean flag=false;
        for(int i=0 , j=arr.length-1; i< arr.length/2 ; i++ ,j--){
            if(arr[i]!=arr[j]) return false;
        }
        return true;
    }

    /**
     * 数组长度取 length, 字符串长度取 length()
     */
    public void testLength(){
        int[] arr = new int[4];
        System.out.println(arr.length);// 获取数组的长度

        String str = "沉默王二";
        System.out.println(str.length());// 获取字符串的长度
    }

    /**
     * 5.NC101 缺失数字
     */
    public static int lostTest(int[] a){
        //方法思路： 求和
        int aLen = a.length;
        int sum1 = 0;
        int sum2 = 0;
        //int presum = aLen * (aLen + 1) / 2;
        for(int i = 0; i< aLen +1 ;i++) sum2 +=i;  //n+1个数后之和.等价于presum
        for(int j=0; j< aLen; j++)  sum1 += a[j];//缺失某个数后之和

        return sum2-sum1;
    }

    /**
     * 6.NC107 寻找峰值
     * 寻找最后的山峰
     */
    public int solve (int[] a) {
        if(a == null || a.length == 0) {
            return -1;
        }
        for(int i = a.length -1;i>=1;i--) {
            if(a[i] >= a[i-1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 7.NC38 螺旋矩阵-入门级
     * @param matrix
     * @return
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();

        if(matrix.length == 0) {
            return list;
        }

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int x = 0;


        while(true) {//无限循环语句。除非语句块中有break语句才都能跳出循环
            for(int i = left; i <= right; i++) {  //从左到右
                list.add(matrix[top][i]) ;
            }

            if(++top > bottom){//++top: top 先+1，再执行
                break;
            }
            for(int i = top; i <= bottom; i++){
                list.add( matrix[i][right]);    //从上到下
            }

            if(left > --right){
                break;
            }
            for(int i = right; i >= left; i--){
                list.add(matrix[bottom][i]); //从右到左
            }

            if(top > --bottom){
                break;
            }
            for(int i = bottom; i >= top; i--){
                list.add(matrix[i][left]);   //从下到上
            }

            if(++left > right){
                break;
            }
        }
        return list;
    }

    /**
     * 8.NC78 反转链表-简单
     */
    public static ListNode ReverseList(ListNode head) {
        // 判断链表为空或长度为1的情况
        if(head == null || head.next == null){
            return head;
        }

        ListNode pre = null; // 当前节点的前一个节点
        ListNode next = null; // 当前节点的下一个节点
        while( head != null){
            next = head.next; // 记录当前节点的下一个节点位置；
            head.next = pre; // 让当前节点指向前一个节点位置，完成反转
            pre = head; // pre 往右走
            head = next;// 当前节点往右继续走
        }
        return pre; //当循环结束时,pre所指的就是反转链表的头结点
    }

    /**
     * 9. NC4 判断链表中是否有环
     */
    public static boolean hasCycle1(ListNode head) {
        //方法一：快慢指针解决
        if (head == null) return false;
        //快慢两个指针
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            //慢指针每次走一步
            slow = slow.next;
            //快指针每次走两步
            fast = fast.next.next;
            //如果相遇，说明有环，直接返回true
            if (slow == fast)
                return true;
        }
        //否则就是没环
        return false;
    }

    public static boolean hasCycle2(ListNode head) {
        //方法二：存放到集合中
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            //如果重复出现说明有环
            if (set.contains(head))
                return true;
            //否则就把当前节点加入到集合中
            set.add(head);
            head = head.next;
        }
        return false;
    }
    
    public static boolean hasCycle3(ListNode head) {
        //方法三：逐个删除. 不推荐。
        if (head == null || head.next == null)//如果head为空，或者他的next指向为空，直接返回false
            return false;
        if (head.next == head)//如果出现head.next = head表示有环
            return true;

        ListNode nextNode = head.next;
        head.next = head; //当前节点的next指向他自己，相当于把它删除了

        return hasCycle3(nextNode);//然后递归，查看下一个节点
    }

    /**
     * 10.NC22 合并两个有序的数组-简单
     */
    //用例未全通过-弃
    public static void merge(int A[], int m, int B[], int n) {
        //思路：1.AB合并在一起；2.排序：升序

        int C[] = new int[m+n+1];
        if(A.length==0 ) {C= B;return;}
        if(B.length==0 ) {C= A;return;}

        for(int i=0; i< m; i++){
            C[i]=A[i];
            System.out.print(C[i]);
        }
        for(int i=0; i<n ; i++){
            C[i+m]=B[i];
            System.out.print(C[i+m]);
        }

        //对C[]排序
        for(int a=0; a< m+n-1 ; a++){
            for(int b=0; b< m+n-a-1 ; b++){
                if(C[b]>C[b+1]){
                    int tmp= C[b];
                    C[b]=C[b+1];
                    C[b+1]=tmp;
                }
            }
        }
        for(int i=0; i< m+n; i++){
            A[i]=C[i];
            System.out.print(B[i]);
        }
    }
    public static void merge1(int A[], int m, int B[], int n) {

        int[] res = new int[m+n];
        int i=0,j=0,r=0;
        while(i<m && j<n){      //遍历A.B中的元素
            if(A[i]<=B[j]){    //如果A<=B,A先放
                res[r++] = A[i++];
            }else{             //如果A>B，B先放
                res[r++] = B[j++];
            }
        }
        //如果A，B其中有一个遍历完，另一个没遍历完，则将未遍历完的数组中的元素全部加入res中
        while(i<m){     //当A中的元素未遍历完时，将A中剩下的元素全部放入res中
            res[r++]=A[i++];
        }
        while(j<n){     //当B中的元素未遍历完时，将B中剩下的元素全部放入res中
            res[r++]=B[j++];
        }
        for(int k=0;k<res.length;k++){ //最后将数组res中的元素全部存入数组A中
            A[k]=res[k];
        }
    }

    /**
     * 11.NC61 两数之和
     */
    public int[] twoSum (int[] numbers, int target) {
        // write code here
        Map<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int tmp = numbers[i];
            if (m.get(target - tmp) != null) {
                return new int[]{m.get(target - tmp)+1, i+1};
            }
            m.put(tmp, i);
        }
        //throw new RuntimeException("results not exits");
        return new int[]{0, 0};
    }

    /**
     * 14.Java将数组的奇数排在前面偶数排在后面
     * public static void main(String[] args) {
     *         int arr[] = new int[]{6,5,4,6,1,1,2,3,4};
     *         test1(arr);
     *         System.out.println(Arrays.toString(arr));
     *
     *}
     */
    public static void test1(int[] arr) {

        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] % 2 == 0 && arr[j + 1] % 2 == 1) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 二分查找
     *
     public  static void main(String[] args){
        int[] arr = {1,3,5,7,9,11};
         int key = 9;
        int position = binarySearch(arr,key,0,arr.length - 1);

         if(position == -1){
            System.out.println("查找的数="+key+"不再数列中");
        }else{
            System.out.println("查找的数="+key+",index="+position);
     }
     */
    public static int binarySearch(int[] arr,int target,int left,int right) {

        if (target < arr[left] || target > arr[right] || left > right) {
            return -1;
        }

        int middle = (left + right) / 2;            //初始中间位置
        if (target< arr[middle] ) {
            //左边接着查找
            return binarySearch(arr, target, left, middle - 1);
        } else if (arr[middle] < target) {
            //右边查找
            return binarySearch(arr, target, middle + 1, right);
        } else {//arr[middle] == target找到
            return middle;
        }
    }

    public static int binarysearch1(int[] nums, int target){
        int left=0;
        int right= nums.length;

        if(nums.length==0) return -1;

        while(left<right){
            int mid=(left+right)/2;

            if( nums[mid]<target ){
                left=mid;
            }else if( nums[mid] > target){
                right=mid;
            }else{
                return mid;
            }
        }
    return -1;
    }

    /**
     * 给一个数组，一个目标值，输出2数之和等于目标值的数的下标。
     * 神策数据
     */
    public static List<Integer> test1(int[] a, int target){
        List<Integer> list= new ArrayList<Integer>();

        if(a.length==0 || a.length==1) return null;

        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                int temp=a[i]+a[j];
                //int[] arr = new int[2];
                if(target==temp)
                {
                    //arr[0]=i;
                    //arr[1]=j;
                    list.add(i);
                    list.add(j);
                    return  list;
                }
            }
        }
        return null;
    }
    public int[] twoSum1(int[] nums, int target) {

        if(nums.length==0) return null;

        int[] arr = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                int temp= nums[i]+nums[j];
                if(target==temp ){
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }
        return arr;
    }

    /**17. 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     */
    public static boolean isValid(String s) { //java，建立一个新的栈，然后遍历字符串的字符，进行比较
        Stack<Character> stack = new Stack<Character>();
        for(char c: s.toCharArray()){
            if(c=='(') stack.push(')');
            else if(c=='[') stack.push(']');
            else if(c=='{') stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop()) return false;
        }
        return stack.isEmpty();
    }

    public static boolean isValid1(String s) {
        while(s.contains("()")||s.contains("[]")||s.contains("{}")){
            if(s.contains("()")){
                s=s.replace("()","");
            }
            if(s.contains("{}")){
                s=s.replace("{}","");
            }
            if(s.contains("[]")){
                s=s.replace("[]","");
            }
        }
        return s.length()==0;
    }

    /**
     * 18. NC31 第一个只出现一次的字符
     *
     */
    public int FirstNotRepeatingChar(String str) {
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

    /**
     * 19. NC76 用两个栈实现队列
     *
     * import java.util.Stack;
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    // 思路：借助栈的先进后出规则模拟实现队列的先进先出
    public void push(int node) {
        // 第一步：当插入时，直接插入 stack1
        stack1.push(node);
    }

    // 第二步：当弹出时，当 stack2 不为空，弹出 stack2 栈顶元素。如果 stack2 为空，将 stack1 中的全部数逐个出栈入栈 stack2，再弹出 stack2 栈顶元素
    public int pop() {
        if(stack2.size() <= 0){
            while(stack1.size()!=0){
                stack2.push(stack1.pop());
            }
        }
        return  stack2.pop();
    }


    /**
     * 20. NC68 跳台阶
     */
    public int jumpFloor(int target) {

        if(target<=2){
            return target;
        }

        int pre2=1, pre1=2;
        for(int i=3; i<=target; i++){ //dp(i) = dp(i-2) + dp(i-1)
            int cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     * 21. NC19 连续子数组的最大和
     */
    /**
     * 思路：
     * 1、 遍历数组，统计连续子序列的和，记录当前子序列的和的最大值
     * 2、如果连续字符列的和小于等于0，置位连续子序列的和为0，起始元素变为下一个元素
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        // 初始化sum为第一个元素的值
        int sum = array[0];
        // 初始化maxSum为第一个元素的值
        int maxSum = sum;
        // 遍历元素
        for (int i=1, len=array.length; i<len; i++) {
            // 当前连续字符列的和
            int curSum = sum + array[i];
            // 更新连续子序列的最大和
            maxSum = Math.max(maxSum, curSum);
            // 更新sum，小于等于0，置位连续子序列的和为0
            sum = curSum < 0 ? 0 : curSum;
        }
        return maxSum;
    }

    /**
     * 22. NC156 数组中只出现一次的数（其它数出现k次）
     */
    public int foundOnceNumber (int[] arr, int k) {
        // write code here
        HashMap<Integer,Integer> map= new HashMap<Integer,Integer>();
        for(int i=0; i< arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i],0)+1);

        }

        for(int i = 0; i < arr.length; i++){
            if(map.get(arr[i]) == 1){
                return arr[i];
            }
        }
        return -1;
    }

}
