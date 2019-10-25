package vip.jianjieming.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author jianjieming
 * @date 2019/10/14 13:42
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * @param arr   数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 中轴
        int pivot = arr[(left + right) / 2];

        // while循环的目的是让比pivot值小的放到左边,比pivot大的值放到右边
        while (l < r) {
            // 在pivot左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            /*
             如果 l >= r 说明pivot的左右俩的值,已经按照左边全部是小于等于pivot的值,
             右边全是是大于等于pivot的值
            */
            if (l >= r) {
                break;
            }
            // 交换
            arr[l] = arr[l] ^ arr[r];
            arr[r] = arr[l] ^ arr[r];
            arr[l] = arr[l] ^ arr[r];

            // 如果交换完后,发现这个 arr[l] == pivot 值 相等 r--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完后,发现这个 arr[r] == pivot 值 相等 l--,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果 l == r, 必须 l++, r--, 否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

}
