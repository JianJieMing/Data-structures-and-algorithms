package vip.jianjieming.algorithms.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @Classname SelectSort
 * @Date 2019/10/11 15:56
 * @Created by jianjieming
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, 64, 412, 10};
        selectSort(arr);
//        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            // 假如最小值就是下标为0的元素
            int minIndex = j;
            int min = arr[j];
            for (int i = j + 1; i < arr.length; i++) {
                if (min > arr[i]) { // 说明假定的最小值,并不是最小的
                    // 重置最小值
                    min = arr[i];
                    minIndex = i;
                }
            }
            // 将最小值,放在arr[0],即交换
            if (minIndex != j) {
                arr[minIndex] = arr[j];
                arr[j] = min;
            }
            System.out.println("第" + (j + 1) + "轮后: ");
            System.out.println(Arrays.toString(arr));
        }
    }
}
