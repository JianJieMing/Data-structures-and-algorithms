package vip.jianjieming.algorithms.dynamic;

/**
 * 背包问题
 *
 * @author jianjieming
 * @date 2019/11/28 14:39
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        // 物品的重量
        int[] w = {1, 4, 3};
        // 物品的价值
        int[] val = {1500, 3000, 2000};
        // 背包容量
        int m = 4;
        // 物品个数
        int n = val.length;

        // v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];
        for (int i = 0; i < v.length; i++) {
            // 第一列初始为0
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            // 第一行初始为0
            v[0][i] = 0;
        }

        // 根据公式动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int i1 = 0; i1 < v[i].length; i1++) {
                System.out.print(v[i][i1] + " ");
            }
            System.out.println();
        }
        System.out.println("`````````````````");
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }

    }
}
