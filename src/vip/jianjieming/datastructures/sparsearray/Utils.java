package vip.jianjieming.datastructures.sparsearray;

import java.io.*;

/**
 * @Classname Utils
 * @Date 2019/9/23 17:00
 * @Created by jianjieming
 */
public class Utils {

    private static String PATH = "C:\\Users\\jianjieming\\Desktop\\code\\MyCodeDemo\\DayDayDemo\\src\\datastructure\\sparseArray.txt";

    public static void uploadFile(int[][] arr) {
        File file = new File(PATH);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    fw.write(arr[i][j] + "\t");
                }
                fw.write("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int[][] download() {
        int[][] result = null;
        File file = new File(PATH);
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(file));
            boolean flag = true;
            int row = 1;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                int i1 = Integer.parseInt(split[0]);
                int i2 = Integer.parseInt(split[1]);
                int i3 = Integer.parseInt(split[2]);
                if (flag) {
                    result = new int[i3 + 1][3];
                    result[0][0] = i1;
                    result[0][1] = i2;
                    result[0][2] = i3;
                    flag = false;
                } else {
                    result[row][0] = i1;
                    result[row][1] = i2;
                    result[row][2] = i3;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
