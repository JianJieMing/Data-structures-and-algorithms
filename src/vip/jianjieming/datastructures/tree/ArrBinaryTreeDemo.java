package vip.jianjieming.datastructures.tree;

/**
 * @author jianjieming
 * @date 2019/11/14 11:20
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder();
//        arrBinaryTree.infixOrder();
        arrBinaryTree.postOrder();
    }
}

class ArrBinaryTree {
    // 存储数据节点的数组
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        // 输出当前元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder() {
        infixOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        // 输出当前元素
        System.out.println(arr[index]);
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder() {
        postOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        // 输出当前元素
        System.out.println(arr[index]);
    }

}
