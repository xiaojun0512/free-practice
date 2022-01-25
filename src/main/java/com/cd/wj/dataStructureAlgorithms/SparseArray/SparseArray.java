package com.cd.wj.dataStructureAlgorithms.SparseArray;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        /**
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 1 0 0 0 0 0 0 0 0
         *               0 0 0 0 2 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         *               0 0 0 0 0 0 0 0 0 0 0
         */
        //新建一个二维数组11*11
        //0:表示没有棋子，1表示黑子，2表示蓝子
        int[][] arr = new int[11][11];
        arr[1][2] = 1;
        arr[2][4] = 2;
        //输出原始的二维数组
        for (int[] row : arr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        /**
         * 二维数组转稀疏数组
         */
        //总数
        int sum = 0;
        for (int[] row : arr) {
            for (int data : row) {
                if (data != 0) {
                    sum += 1;
                }
            }
        }
        //新建稀疏数组
        /**
         *      行   列   非0总数
         *      11	11	2
         *      行   列   值
         *      1	2	1
         *      2	4	2
         */
        int[][] sparseArr = new int[sum+1][3];
        //稀疏数组第一行
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //稀疏数组其它行
        int sparseRow = 0;
        for (int i = 0;i < arr.length;i ++) {
            for (int j = 0;j < arr[0].length;j ++) {
                if (arr[i][j] != 0) {
                    sparseRow++;
                    sparseArr[sparseRow][0] = i;
                    sparseArr[sparseRow][1] = j;
                    sparseArr[sparseRow][2] = arr[i][j];
                }
            }
        }
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        /**
         * 稀疏数组转二维数组
         */
        int[][] list = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1;i < sparseArr.length;i ++) {
            int row = sparseArr[i][0];
            int column = sparseArr[i][1];
            int value = sparseArr[i][2];
            list[row][column] = value;
        }
        for (int[] row : list) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
