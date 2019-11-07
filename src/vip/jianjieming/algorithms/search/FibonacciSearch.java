package vip.jianjieming.algorithms.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 *
 * @author jianjieming
 * @date 2019/11/4 13:49
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 89));
    }

    /**
     * 因为后面 mid = low + F(k - 1) - 1,
     * 需要使用到斐波那契数列,因此需要先获取到一个斐波那契数列
     * 非递归方法得到一个斐波那契数列
     *
     * @return
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法
     * 使用非递归的方式
     *
     * @param a   数组
     * @param key 需要查找的关键码(值)
     * @return 返回对应的下标, 没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        // k表示斐波那契分割数值的下标
        int k = 0;
        // 存放mid值
        int mid = 0;
        // 获取到斐波那契数列
        int f[] = fib();
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为 f[k] 值 可能大于 a 的长度,因此需要使用Arrays类,构造一个新的数组,并指向temp[]
        // 不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        // 需要使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        // 使用while循环来处理,找到key
        while (low <= high) {
            // 只要这个条件满足,就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                // 说明我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                /*
                1. 全部元素 = 前面的元素 + 后边的元素
                2. f[k] = f[k - 1] + f[k - 2]
                因为前面有f[k - 1]个元素,所以可以继续拆分 f[k - 1] = f[k - 2] + f[k - 3]
                即在 f[k - 1] 的前面继续查找 k--
                下次循环的时候, mid = f[k-1-1]-1
                 */
                k--;
            } else if (key > temp[mid]) {
                // 向后面查找(右边)
                low = mid + 1;
                /*
                1. 全部元素 = 前面的元素 + 后边的元素
                2. f[k] = f[k - 1] + f[k - 2]
                3. 因为后面有 f[k - 2], 所以可以继续拆分 f[k - 1] = f[k - 3] + f[k - 4]
                4. 即在 f[k - 2] 的前面进行查找 k-=2
                即下次循环 mid = f[k - 1 - 2] - 1
                 */
                k -= 2;
            } else {
                // 找到了,需要确定返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}
