package vip.jianjieming.algorithms.sort;

import java.util.Arrays;

/**
 * 冒号排序
 *
 * @author JianJieMing
 * @date 2019/10/11 14:24
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 1, 7, 5, 6};
        bubbleSort(arr);
    }

    /**
     * 时间复杂度O(n^2)
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
            if (!flag) {
                break;
            } else {
                // 重置flag, 进行下次判断
                flag = false;
            }
            System.out.println("第" + (i + 1) + "次排序后的结果: ");
            System.out.println(Arrays.toString(arr));
        }
    }
}
