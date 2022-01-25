package com.cd.wj.dataStructureAlgorithms.queue;

import java.util.Scanner;

/**
 * 数组实现队列
 */
public class ArrayQueue {
    public static void main(String[] args) {
        ArrQueue queue = new ArrQueue(5);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("s：显示队列");
            System.out.println("a：添加队列元素");
            System.out.println("g：获取队列数据");
            System.out.println("h：显示队列头数据");
            System.out.println("e：退出");
            char key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据：");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int g = queue.getQueue();
                        System.out.println(g);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int h = queue.headQueue();
                        System.out.println(h);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }
}

//使用数组模拟队列，编写一个ArrQueue类
class ArrQueue{
    //数组最大容量
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //数组
    private int[] arr;

    public ArrQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    //判断队列满
    public boolean isFull(){
        return rear == (maxSize-1);
    }

    //判断队列空
    public boolean isEmpty(){
        return front == rear;
    }

    //添加队列
    public void addQueue(int n){
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //取数据
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    //显示队列数据
    public void showQueue(){
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int v : arr) {
            System.out.printf("%d\t",v);
        }
    }

    //显示队列头数据
    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }
}
