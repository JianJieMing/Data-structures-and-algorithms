package vip.jianjieming.algorithms.search;

/**
 * 线性查找
 * @author jianjieming
 * @date 2019/11/1 11:42
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = serSearch(arr, 11);
        if (index == -1) {
            System.out.println("没有找到该值");
        } else {
            System.out.println("找到,下标为: " + index);
        }
    }

    /**
     * 线性查找,找到一个满足条件的值就返回
     */
    public static int serSearch(int[] arr, int value) {
        // 线性查找是逐一对比,发现有相同的值,就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
