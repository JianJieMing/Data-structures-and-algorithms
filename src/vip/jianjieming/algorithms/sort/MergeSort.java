package vip.jianjieming.algorithms.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author jianjieming
 * @date 2019/10/15 11:09
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        // 归并排序需要一个额外空间
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println("归并排序后: ");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分+合 方法
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并 (栈的后进先出)
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 归并排序合并的方法
     *
     * @param arr   排序的元素数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 初始化i,左边有序序列的初始索引
        int i = left;
        // 初始化j,右边有序序列的初始索引
        int j = mid + 1;
        // 指向temp数组的当前索引
        int t = 0;

        /*
            1. 先把左右两边(有序)的数据按规则填充到temp数组,
            直到左右两边的有序序列,有一边处理完毕为止.

            2. 把有剩余数据的一边数据依次全部填充到temp数组.

            3. 将temp数组的元素copy到arr,注意:并不是每次都copy所有
        */

        // 1
        // 继续
        while (i <= mid && j <= right) {
            // 如果左边的有序序列的当前元素,小于等于右边有序序列的当前元素
            // 即将左边的当前元素,填充到temp数组
            // 然后t++,i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                // 反之,将右边有序序列的当前元素,填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        // 2
        // 说明左边的有序序列还有剩余的元素,就全部填充到temp数组
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        // 说明右边的有序序列还有剩余的元素,就全部填充到temp数组
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        // 3
        t = 0;
        int tempLeft = left;
        /*
          第一次合并 tempLeft = 0, right = 1
          第二次合并 tempLeft = 2, right = 3
          第三次合并 tempLeft = 0, right = 3
          最后一次 tempLeft = 0, right = 7
         */
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
