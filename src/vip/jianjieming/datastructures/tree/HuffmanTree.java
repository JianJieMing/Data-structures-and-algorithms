package vip.jianjieming.datastructures.tree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 赫夫曼树
 *
 * @author jianjieming
 * @date 2019/11/20 14:09
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        perOrder(root);
    }

    /**
     * 创建赫夫曼树
     *
     * @param arr 需要创建成赫夫曼树的数组
     * @return 返回创建好的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            nodes.sort(Comparator.comparing(Node::getValue));
            System.out.println(nodes);

            // 1.取出权值最小的两个二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // 2.构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 3.从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 4.将parent加入到nodes
            nodes.add(parent);
        }
        // 返回赫夫曼树的root节点
        return nodes.get(0);
    }

    public static void perOrder(Node root) {
        if (root != null) {
            root.perOrder();
        } else {
            System.out.println("树是空的,无法遍历!!!");
        }
    }
}

/**
 * 节点类
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    /**
     * 前序遍历
     */
    public void perOrder() {
        System.out.println(this.value);
        if (this.left != null) {
            this.left.perOrder();
        }
        if (this.right != null) {
            this.right.perOrder();
        }
    }
}
