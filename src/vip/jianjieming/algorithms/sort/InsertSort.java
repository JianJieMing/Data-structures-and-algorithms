package vip.jianjieming.algorithms.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @Classname InsertSort
 * @Date 2019/10/12 10:14
 * @Created by jianjieming
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i]; // 要插入的数
            int insertIndex = i - 1; // 要插入数的位置,即arr[1]的前面这个数的下标
        /*
        给insertVal 找到插入的位置
        说明:
            1.insertIndex >= 0 保证在给insertVal找插入位置时,不越界
            2.insertVal < arr[insertIndex] 说明待插入的数,还没有找到插入位置
            3.将arr[insertIndex] 后移
         */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出循环时,说明插入的位置找到, 就是: insertIndex + 1 的位置
            // 判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            System.out.println("第" + i + "轮插入后: ");
            System.out.println(Arrays.toString(arr));
        }
    }

}
