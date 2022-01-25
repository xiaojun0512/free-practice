package com.cd.wj.dataStructureAlgorithms.queue;

import java.util.Scanner;

/**
 * 环形数组实现队列
 */
public class CircleQueue {
    public static void main(String[] args) {
        CirQueue queue = new CirQueue(3);
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

//使用环形数组模拟队列，编写一个ArrQueue类
class CirQueue{
    //数组最大容量
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //数组
    private int[] arr;

    public CirQueue(int maxSize){
        this.maxSize = maxSize+1;
        this.arr = new int[maxSize];
    }

    //判断队列满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //取数据
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int v = arr[front];
        front = (front + 1) % maxSize;
        return v;
    }

    //显示队列数据
    public void showQueue(){
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front;i <front + size() ;i ++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //当前队列有效数据个数
    public int size() {
        return (rear + maxSize -front) % maxSize;
    }

    //显示队列头数据
    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
