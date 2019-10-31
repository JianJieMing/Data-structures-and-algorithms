package vip.jianjieming.algorithms.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author jianjieming
 * @date 2019/10/14 10:02
 */
public class ShellSort {
    public static int temp = 2;
//    public static int temp = 5;
//    public static int temp2 = 2;
//    public static int temp3 = 1;

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort1(arr);
        shellSort2(arr);
    }

    /**
     * shellSort1,对有序序列在插入时采用交换法,效率不是很高
     *
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        int count = 0;
        for (int gap = arr.length / temp; gap > 0; gap /= temp) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组),步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素,说明交换
                    if (arr[j] > arr[j + gap]) {
                        arr[j] = arr[j] ^ arr[j + gap];
                        arr[j + gap] = arr[j] ^ arr[j + gap];
                        arr[j] = arr[j] ^ arr[j + gap];
                    }
                }
            }
            System.out.printf("希尔排序交换法,第%d轮: \n", ++count);
            System.out.println(Arrays.toString(arr));
        }

        /*
        逐步推导分析思路:
        希尔排序的第一轮排序:
        因为第一轮排序,是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组,每组有两个元素),步长5
            for (int j = i - temp; j >= 0; j -= temp) {
                // 如果当前元素大于加上步长后的那个元素,说明交换
                if (arr[j] > arr[j + 5]) {
                    arr[j] = arr[j] ^ arr[j + 5];
                    arr[j + 5] = arr[j] ^ arr[j + 5];
                    arr[j] = arr[j] ^ arr[j + 5];
                }
            }
        }
        System.out.println("希尔排序1轮后: ");
        System.out.println(Arrays.toString(arr));


        思路:
        希尔排序的第二轮排序:
        因为第二轮排序,是在第一轮的基础上,将数据分成了5 / 2 = 2组

        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组,每组有两个元素),步长5
            for (int j = i - temp2; j >= 0; j -= temp2) {
                // 如果当前元素大于加上步长后的那个元素,说明交换
                if (arr[j] > arr[j + 2]) {
                    arr[j] = arr[j] ^ arr[j + 2];
                    arr[j + 2] = arr[j] ^ arr[j + 2];
                    arr[j] = arr[j] ^ arr[j + 2];
                }
            }
        }
        System.out.println("希尔排序2轮后: ");
        System.out.println(Arrays.toString(arr));


        思路:
        希尔排序的第三轮排序:
        因为第三轮排序,是在第二轮的基础上,将数据分成了2 / 2 = 1组

        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组,每组有两个元素),步长5
            for (int j = i - temp3; j >= 0; j -= temp3) {
                // 如果当前元素大于加上步长后的那个元素,说明交换
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
        System.out.println("希尔排序3轮后: ");
        System.out.println(Arrays.toString(arr));
        */
    }

    /**
     * shellSort2,对有序序列在插入时采用移位法,效率更高
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        int count = 0;
        for (int gap = arr.length / temp; gap > 0; gap /= temp) {
            // 从第gap个元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                // 待插入的位置的下标
                int j = i;
                // 记录要插入的值
                int temp = arr[i];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出while循环后,就给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
            System.out.printf("希尔排序移位法,第%d轮: \n", ++count);
            System.out.println(Arrays.toString(arr));
        }
    }
}
