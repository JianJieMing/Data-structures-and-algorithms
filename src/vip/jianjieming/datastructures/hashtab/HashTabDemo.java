package vip.jianjieming.datastructures.hashtab;

import java.util.Scanner;

/**
 * @author jianjieming
 * @date 2019/11/4 15:08
 */
public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

/**
 * 创建HashTab,管理多条链表
 */
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    /**
     * 表示有多少链表
     */
    private int size;

    //构造器
    public HashTab(int size) {
        this.size = size;
        // 初始化
        empLinkedListArray = new EmpLinkedList[size];
        // TODO 留一个坑 这时不要忘了分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     */
    public void add(Emp emp) {
        // 根据员工的id,得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        // 将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    /**
     * 遍历所有的链表,遍历hashTab
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 编写散列函数,使用一个简单的取模法
     */
    public int hashFun(int id) {
        return id % size;
    }

    /**
     * 根据输入的id,查找雇员
     */
    public void findEmpById(int id) {
        // 根据员工的id,得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            // 找到
            System.out.printf("在第%d条链表中,找到雇员id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在hashTab中没有找到该雇员");
        }
    }
}

/**
 * 表示一个雇员
 */
class Emp {
    public int id;
    public String name;
    /**
     * next默认为空
     */
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 创建EmpLinkedList,表示链表
 */
class EmpLinkedList {
    /**
     * 头指针,执行第一个Emp,因此这个链表的head,是直接指向第一个Emp
     * 默认为null
     */
    private Emp head;

    /**
     * 添加雇员到链表
     * 说明:
     * 1. 假定,当添加雇员时,id是自增长的,即id的分配总是从小到大
     * 因此将雇员直接加入到本链表的最后即可
     *
     * @param emp
     */
    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员,则使用一个辅助的指针,帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                // 说明到链表最后
                break;
            }
            // 后移
            curEmp = curEmp.next;
        }
        // 退出时直接将emp 加入链表
        curEmp.next = emp;
    }

    /**
     * 遍历链表的雇员信息
     */
    public void list(int no) {
        if (head == null) {
            // 说明链表为空
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表的信息为: ");
        // 辅助指针
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                // 说明curEmp已经是最后节点
                break;
            }
            // 后移,遍历
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     */
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                // 找到,这时curEmp就指向要查找的雇员
                break;
            }
            if (curEmp.next == null) {
                // 说明遍历当前链表,没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
