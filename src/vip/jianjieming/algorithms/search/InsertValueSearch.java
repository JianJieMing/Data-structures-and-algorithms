package vip.jianjieming.algorithms.search;

import java.util.Arrays;

/**
 * 插值查找
 *
 * @author jianjieming
 * @date 2019/11/4 11:46
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        insertValueSearch(arr, 0, arr.length - 1, 66);
    }

    /**
     * 插值查找
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return 找到返回下标, 没有找到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("查找次数");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            // 说明应该向右边递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 说明应该向左边递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
