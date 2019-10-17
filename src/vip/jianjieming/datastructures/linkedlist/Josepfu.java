package vip.jianjieming.datastructures.linkedlist;

/**
 * 约斯夫问题:
 * 设编号为 1,2,…n 的 n 个人围坐一圈,约定编号为 k (1 <= k <= n) 的人从 1 开始报数,
 * 数到 m 的那个人出列,它的下一位又从 1 开始报数,数到 m 的那个人又出列,依次类推,
 * 直到所有人出列为止,由此产生一个出队编号的序列.
 * <p>
 * 思路:
 * 用一个不带头节点的环形链表来处理,先构成一个有 n 个节点的单向循环链表,
 * 然后由 k 节点起 从 1 开始计数,计到 m 时,对应节点从链表中删除,
 * 然后在从被删除节点的下一个节点又从 1 开始计数,直到最后一个节点从链表中删除.
 *
 * @Classname Josepfu
 * @Date 2019/10/8 11:11
 * @Created by jianjieming
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(5); // 加入5个小孩节点
        linkedList.showBoy();

        System.out.println();

        // 测试小孩出圈
        linkedList.countBoy(1, 2, 5);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点,当前没有编号
    private Boy first = new Boy(-1);

    /**
     * 添加小孩节点,构建成一个环形的链表
     *
     * @param nums 小孩的数量
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针(变量),帮助构建环形链表
        // 创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环状
                curBoy = first; // curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前环形链表
    public void showBoy() {
        System.out.println(first.getNo());
        if (first.getNo() == -1) {
            System.out.println("没有任何小孩!!!");
            return;
        }
        // 因为first不能动,创建辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext(); // 后移,遍历
        }
    }

    /**
     * 根据用户的输入,计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first.getNo() == -1 || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误,请重新输入!!!");
            return;
        }
        // 创建一个辅助指针(变量)helper,帮助完成小孩出圈
        // helper 事先应该指向环形链表中最后的那个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前,先让first 和 helper 移动 startNo - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当小孩报数时,先让first 和 helper 移动 countNum - 1 次,然后出圈
        // 这里是一个循环操作,知道圈中只有一个节点
        while (true) {
            if (helper == first) { // 说明圈中只有一个节点
                break;
            }
            // 让first 和 helper 指针同时的移动 countNum - 1 次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这是first指向的节点,就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            // 这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号是: %d \n", helper.getNo());

    }


}

// 创建一个Boy类,表示一个节点
class Boy {
    private int no; // 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
