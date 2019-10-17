package vip.jianjieming.datastructures.linkedlist;

/**
 * @Classname DoubleLinkedListDemo
 * @Date 2019/10/8 9:56
 * @Created by jianjieming
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList linkedList = new DoubleLinkedList();
        // 无序插入
//        linkedList.add(hero4);
//        linkedList.add(hero3);
//        linkedList.add(hero2);
//        linkedList.add(hero1);
        // 有序插入
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero2);

        System.out.println("\n添加后的链表情况: ");
        linkedList.list();

        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        linkedList.update(newHeroNode);
        System.out.println("\n修改后的链表情况: ");
        linkedList.list();

        linkedList.delete(hero3);
        System.out.println("\n删除后的链表情况: ");
        linkedList.list();


    }
}

// 创建一个双向链表的类
class DoubleLinkedList {
    // 先初始化一个头节点,头节点不要动,不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
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
        HeroNode2 temp = head.next;
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

    /**
     * 添加节点到双向链表
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        // 因为head节点不能动,因此我们需要一个辅助变量 temp
        HeroNode2 temp = head;
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
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 根据排名将英雄插入到指定位置
     * 如果有这个排名,则添加失败,并给出提示
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode) {
        // 因为头节点不能动,因此我们仍然通过一个辅助指针(变量)来帮助我们找到添加的位置
        // 因为单链表,因此我们找的这个temp是位于添加位置的前一个节点,否则插入不了
        HeroNode2 temp = head;
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
//            heroNode.next = temp.next;
//            temp.next = heroNode;
            heroNode.next = temp.next;
            heroNode.pre = temp;
            temp.next = heroNode;
            temp.next.pre = heroNode;
        }
    }

    /**
     * 修改双向链表节点的内容,和单向链表的修改一样
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的这个节点, 根据no编号
        // 定义一个辅助变量
        HeroNode2 temp = head.next;
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
     * 从双向链表中删除一个节点
     * 1.对于双向链表,可以直接找到要删除的这个节点
     * 2.找到后自我删除即可
     *
     * @param delHeroNode
     */
    public void delete(HeroNode2 delHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next; // 辅助变量(指针)
        boolean flag = false;
        while (true) {
            if (temp == null) { // 已经到链表的最后
                break;
            }
            if (temp.no == delHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
//            temp.next = temp.next.next; 单向链表的删除方式
            temp.pre.next = temp.next;
            // 如果是最后一个节点就不需要执行下面这句话,否则会空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号: %d 的节点,不能删除", delHeroNode.no);
        }
    }

}

// 定义一个HeroNode, 每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; // 指向下一个节点, 默认为null
    public HeroNode2 pre; // 指向前一个节点, 默认为null

    public HeroNode2(int no, String name, String nickName) {
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
