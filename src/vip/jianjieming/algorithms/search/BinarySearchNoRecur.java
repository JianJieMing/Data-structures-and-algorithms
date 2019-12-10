package vip.jianjieming.algorithms.search;

/**
 * 二分查找的非递归实现
 *
 * @author jianjieming
 * @date 2019/11/28 11:17
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 67);
        System.out.println(index);
    }

    /**
     * @param arr    待查找的数组, arr是升序排序
     * @param target 要查找的数
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                // 向左边查找
                right = mid - 1;
            } else {
                // 向右边查找
                left = mid + 1;
            }
        }
        return -1;
    }
}
