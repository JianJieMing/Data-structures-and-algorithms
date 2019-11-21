package vip.jianjieming.algorithms.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author jianjieming
 * @date 2019/11/14 15:06
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, 39, 23, 531, 321, 1241, 512};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i] ^ arr[0];
            arr[0] = arr[i] ^ arr[0];
            arr[i] = arr[i] ^ arr[0];
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 数组,调整成一个大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整, length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 取出当前元素的值,保存在临时变量
        int temp = arr[i];
        // 开始调整
        /*
        1. k = i * 2 + 1 是 i 节点的左子节点
         */
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 说明左子节点的值小于右子节点的值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // k 指向右子节点
                k++;
            }
            // 如果子节点大于父节点
            if (arr[k] > temp) {
                // 把较大的值赋给当前节点
                arr[i] = arr[k];
                // i 指向 k, 继续循环比较
                i = k;
            } else {
                break;
            }
        }
        // 当for循环结束后,我们已经将以 i 为父节点的树的最大值,放在了 最顶(局部)

        // 将temp值放到调整后的位置
        arr[i] = temp;
    }
}
