package vip.jianjieming.datastructures.linkedlist;

import java.util.Stack;

/**
 * @Classname SingleLinkedListDemo
 * @Date 2019/9/25 15:26
 * @Created by jianjieming
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SingleLinkedList list = new SingleLinkedList();
        // 普通加入
//        list.add(hero4);
//        list.add(hero3);
//        list.add(hero2);
//        list.add(hero1);

        // 按照编号的顺序加入
        list.addByOrder(hero4);
        list.addByOrder(hero3);
        list.addByOrder(hero2);
        list.addByOrder(hero1);

        list.list();

        // 修改节点的代码
//        list.list();
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "铁麒麟");
//        list.update(newHeroNode);
//        System.out.println("修改后的链表变化");
//        list.list();

        // 删除节点的代码
//        list.list();
//        HeroNode delHeroNode = new HeroNode(4, "林冲", "豹子头");
//        list.delete(delHeroNode);
//        System.out.println("删除后的链表变化");

        // 获取单链表有效节点的个数
//        System.out.println("有效的节点个数: " + getLength(list.getHead()));

        // 查找单链表中的倒数第n个节点
//        HeroNode lastIndexNode = findLastIndexNode(list.getHead(), 4);
//        System.out.println("倒数第4个是: " + lastIndexNode);

        // 将单链表反转
//        reverseLinkedList(list.getHead());
//        System.out.println("反转后的链表: ");
//        list.list();

        // 反向打印单链表
        System.out.println("反向打印单链表");
        reversePrint(list.getHead());
    }

    /**
     * 获取单链表节点的个数(如果是带头节点的链表,不统计头节点)
     *
     * @param head 链表的头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        // 没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; // 遍历
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第n个节点
     * 思路:
     * 1.编写一个方法,接收head节点,同时接收一个index
     * 2.index 表示是倒数第index个节点
     * 3.先把链表从头到尾遍历,得到链表的总长度getLength()
     * 4.得到size后,从链表的第一个开始遍历(size-index)个,就可以得到
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        // 遍历size-index位置, 就是我们倒数第n个节点
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 将单链表反转
     * 思路:
     * 1.先定义一个节点 reverseHead = new HeroNode();
     * 2.从头到尾遍历原来的链表,每遍历一个节点,就将其取出,并放在新的链表的最前端
     * 3.原来的链表的head.next = reverseHead.next
     */
    public static void reverseLinkedList(HeroNode heroNode) {
        // 如果当前链表为空,或者只有一个,无需反转,直接返回
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        // 定义一个辅助指针,用来遍历原来的链表
        HeroNode cur = heroNode.next;
        // 定义指向当前节点[cur]的写一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表,并从头到尾遍历原来的链表,每遍历一个节点,就将其取出,并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点,因为后面需要使用
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将cur连接到新的链表上
            cur = next; // 让cur后移
        }
        // 将head.next 指向reverseHead.next, 实现单链表的反转
        heroNode.next = reverseHead.next;
    }

    /**
     * 从尾到头显示列表
     *
     * @param heroNode
     */
    public static void reversePrint(HeroNode heroNode) {
        if (heroNode.next == null) {
            return;
        }
        HeroNode temp = heroNode.next;
        Stack<Object> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}

// 定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点,头节点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 思路: 当不考虑编号的顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的next指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 因为head节点不能动,因此我们需要一个辅助变量 temp
        HeroNode temp = head;
        // 遍历链表,找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后,将temp后移
            temp = temp.next;
        }
        // 当退出这个while循环时,temp就指向了链表的最后
        temp.next = heroNode;
    }

    /**
     * 根据排名将英雄插入到指定位置
     * 如果有这个排名,则添加失败,并给出提示
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动,因此我们仍然通过一个辅助指针(变量)来帮助我们找到添加的位置
        // 因为单链表,因此我们找的这个temp是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false; // 标志添加的编号是否存在,默认为false
        while (true) {
            if (temp.next == null) { // 说明temp在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到,就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { // 说明希望添加的heroNode的编号已然存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移,相当于遍历当前的列表
        }
        if (flag) { // 不能添加,说明编号存在
            System.out.printf("准备插入的这个英雄的编号: %d,已经存在了,不能加入!\n", heroNode.no);
        } else {
            // 插入到链表中,temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息,根据no编号来修改,即no编号不能改
     * 说明:
     * 1. 根据newHeroNode的no来修改即可
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的这个节点, 根据no编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表了
            }
            if (temp.no == newHeroNode.no) {
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else { // 没有找到
            System.out.printf("没有找到编号: %d 的节点,不能修改", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * 思路:
     * 1.先找到需要删除的这个节点的前一个节点
     * 2.使前一个节点的next 指向 前一个节点.next.next
     * 3.被删除的节点,将不会被其他引用指向,会被垃圾回收机制回收
     *
     * @param delHeroNode
     */
    public void delete(HeroNode delHeroNode) {
        HeroNode temp = head;
        if (temp.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == delHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号: %d 的节点,不能删除", delHeroNode.no);
        }
    }

    /**
     * 显示链表(遍历)
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动,因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 将temp后移,一定小心
            temp = temp.next;
        }
    }

}

// 定义一个HeroNode, 每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
