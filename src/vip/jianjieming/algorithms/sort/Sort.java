package vip.jianjieming.algorithms.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * @author JianJieMing
 * @Date 2019/10/12 11:38
 */
public class Sort {
    public static int temp = 2;
    public static int num = 20000000;

    public static void main(String[] args) {
        // 测试排序
        int[] arr = {101, 34, 119, 1, -1, 89};
        // 测试执行时间
        int[] bigArr = new int[num];
        for (int i = 0; i < num; i++) {
            bigArr[i] = (int) (Math.random() * num);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new Date();
        String dateStr1 = simpleDateFormat.format(beginDate);
        System.out.println("排序前的时间是: " + dateStr1);

        // 20万 个数据测试速度对比
        // 冒号排序(78秒左右)
//        bubbleSort(bigArr);
        // 选择排序(12秒左右)
//        selectSort(bigArr);
        // 插入排序(3秒左右)
//        insertSort(bigArr);
        // 希尔排序-交换法(43秒左右)
//        shellSort1(bigArr);
        // 希尔排序-移位法(1秒不到) **提升到2000万数据测试,6秒左右**
//        shellSort2(bigArr);
        // 快速排序(1秒不到) **提升到2000万数据测试,3秒左右**
        quickSort(bigArr, 0, bigArr.length - 1);

        Date endDate = new Date();
        String dateStr2 = simpleDateFormat.format(endDate);
        System.out.println("排序后的时间是: " + dateStr2);
        System.out.println("总耗时: " + (endDate.getTime() - beginDate.getTime()) / 1000 + " 秒");

//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒号排序
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
                flag = false;
            }
//            System.out.printf("冒号排序: 第%d轮,遍历的结果为: \n", i + 1);
//            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int minVal = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minVal > arr[j]) {
                    minVal = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minVal;
            }
//            System.out.printf("选择排序: 第%d轮,遍历的结果为: \n", i + 1);
//            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        // 2 1 3
        for (int i = 1; i < arr.length; i++) {
            // 1
            int insertVal = arr[i];
            // 下标 0 值 2
            int insertIndex = i - 1;
            // 1 < 2
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 把当前值1改为上一个值2 结果: 2 2 3
                arr[insertIndex + 1] = arr[insertIndex];
                // -1
                insertIndex--;
            }
            // -1 + 1 != 1 false
            if (insertIndex + 1 != i) {
                // 下标为0的值2改为上面记录的值1 2 2 => 1 2
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.printf("插入排序: 第%d轮,遍历的结果为: \n", i);
//            System.out.println(Arrays.toString(arr));
        }
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
//            System.out.printf("希尔排序第%d轮: \n", ++count);
//            System.out.println(Arrays.toString(arr));
        }
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
//            System.out.printf("希尔排序移位法,第%d轮: \n", ++count);
//            System.out.println(Arrays.toString(arr));
        }
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

