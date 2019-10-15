package vip.jianjieming.datastructures.sparsearray;

/**
 * 稀疏数组就是当数组中大部分的内容值都未被使用（或都为零），
 * 在数组中仅有少部分的空间使用。因此造成内存空间的浪费，为了节省内存空间，
 * 并且不影响数组中原有的内容值，我们可以使用稀疏数组去压缩数据
 *
 * @Classname SparseArray
 * @Date 2019/9/23 11:56
 * @Created by jianjieming
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0表示没有棋子, 1表示黑子, 2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组: ");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组转稀疏数组的思想
        // 1.先遍历二维数组,得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.printf("有效数据共: %s 个\n", sum);

        // 2.创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // 遍历二维数组,将非0的值,存放到稀疏数组
        int count = 0; // 用来记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println("\n得到的稀疏数组: ");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();

        // 将稀疏数组 ==> 恢复成原来的二维数组
        /**
         * 1.先读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
         * 2.在读取稀疏数组后几行的数据,在把值赋给原始的二维数组即可
         */
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 0; i < sparseArray.length; i++) {
            if (i != 0) {
                int[] datas = sparseArray[i];
                chessArr2[datas[0]][datas[1]] = datas[2];
            }
        }

        // 恢复后的二维数组
        System.out.println("恢复后的二维数组: ");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 稀疏数组存放到磁盘
        Utils.uploadFile(sparseArray);

        // 从磁盘读取稀疏数组
        int[][] result = Utils.download();

        // 文件读取到的的二维数组
        System.out.println("\n文件读取到的的二维数组: ");
        for (int[] row : result) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
