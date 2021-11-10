package javademo;

import java.util.Stack;

/**
 * @ClassName： StackDemo
 * @Description： 堆栈：先进后出 。 另队列：先进先出
 * @Author： Yangyang
 * @Date： 2021/10/30 22:59 星期六
 * @Version： 1.0
 */
public class StackDemo {
    public static void main(String args[]) {
        testStack();
    }

    public static void testStack(){
        // Creating an empty Stack
        Stack<String> stack = new Stack<String>();

        // Use push() to add elements into the Stack
        stack.push("Welcome");
        stack.push("To");
        stack.push("Geeks");
        stack.push("For");
        stack.push("Geeks");

        // Displaying the Stack
        System.out.println("Initial Stack: " + stack);

        // Pushing elements into the stack
        stack.push("Hello");
        stack.push("World");

        // Displaying the final Stack
        System.out.println("Final Stack: " + stack);

        //结果
        // Initial Stack: [Welcome, To, Geeks, For, Geeks]
        // Final Stack: [Welcome, To, Geeks, For, Geeks, Hello, World]
    }
}
