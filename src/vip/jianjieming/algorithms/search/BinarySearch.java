package vip.jianjieming.algorithms.search;

/**
 * 二分查找
 * 注意: 使用二分查找的前提是数据是有序的
 *
 * @author jianjieming
 * @date 2019/11/1 12:04
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int index = binarySearch(arr, 0, arr.length - 1, 1234);
        System.out.println("查找到的下标为: " + (index == -1 ? "没有找到该数据" : index));
    }

    /**
     * 二分查找
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标, 否则返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 当left > right 说明递归了整个数组,但是没有找到
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
