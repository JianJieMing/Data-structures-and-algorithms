package vip.jianjieming.algorithms.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 贪心算法
 *
 * @author jianjieming
 * @date 2019/11/29 10:03
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>(5);
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // allAreas存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 临时集合,保存在遍历过程中,存放遍历过程中的电台覆盖的地区,和当前还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();

        // maxKey 保存在一次遍历过程中,能够覆盖最大未覆盖地区对应的电台的key
        String maxKey = null;
        // 如果maxKey不为null, 则加入selects
        while (allAreas.size() != 0) {
            // 没进行一次循环,maxKey置空
            maxKey = null;
            for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
                // 每进行一次for
                tempSet.clear();
                String key = entry.getKey();
                HashSet<String> areas = entry.getValue();
                tempSet.addAll(areas);
                // 求出tempSet和allAreas集合的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖地区的数量,比max指向的集合未覆盖的地区还多
                // 就重置maxKey
                // tempSet.size() > broadcasts.get(maxKey).size()) 体现出贪心算法的特点,每次都选择最优的
                if (tempSet.size() > 0 && (maxKey == null
                        || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的广播电台覆盖的地区,从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果: " + selects);
    }
}
