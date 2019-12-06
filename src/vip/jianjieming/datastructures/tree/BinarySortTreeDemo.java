package vip.jianjieming.datastructures.tree;

/**
 * 二叉排序树测试
 *
 * @author jianjieming
 * @date 2019/11/21 9:48
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new NodeDemo(arr[i]));
        }
        System.out.println("中序遍历二叉排序树: ");
        binarySortTree.infixOrder();

        // 测试删除叶子节点(2, 5, 9, 12)
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除节点后: ");
        binarySortTree.infixOrder();

    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree {
    private NodeDemo root;

    /**
     * 添加节点的方法
     */
    public void add(NodeDemo node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空,无法遍历!!!");
        }
    }

    /**
     * 删除节点
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1. 先找到要删除的节点 targetNode
            NodeDemo targetNode = search(value);
            // 如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            // 如果当前这颗二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 查找targetNode的父节点
            NodeDemo parent = searchParent(value);
            // 如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    // 是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    // 是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                // 删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
//                int maxVal = delLeftTreeMax(targetNode.right);
                targetNode.value = minVal;
            } else {
                // 删除只有一颗子树的节点
                // 如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            // targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        // 要删除的节点有右子节点
                        // 如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            // targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 查找要删除的节点
     */
    public NodeDemo search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找父节点
     */
    public NodeDemo searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 删除以node 为根节点的二叉排序树最小节点
     *
     * @param node 传入的节点(当做二叉排序树的根节点)
     * @return 返回的 以node 为根节点的二叉排序树最小节点的值
     */
    public int delRightTreeMin(NodeDemo node) {
        NodeDemo target = node;
        // 循环查找左节点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这时target就指向了最小节点, 删除最小节点
        delNode(target.value);
        return target.value;
    }

    public int delLeftTreeMax(NodeDemo node) {
        NodeDemo target = node;
        // 循环查找左节点,就会找到最小值
        while (target.right != null) {
            target = target.right;
        }
        // 这时target就指向了最小节点, 删除最小节点
        delNode(target.value);
        return target.value;
    }
}

class NodeDemo {
    int value;
    NodeDemo left;
    NodeDemo right;

    public NodeDemo(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NodeDemo{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点的方法
     */
    public void add(NodeDemo node) {
        if (node == null) {
            return;
        }
        // 判断传入节点的值,和当前子树的根节点的值关系
        if (node.value < this.value) {
            // 如果当前节点左子节点是否为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归向左子树添加
                this.left.add(node);
            }
        } else {
            // 添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归向左子树添加
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 希望删除节点的值
     */
    public NodeDemo search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            // 如果查找的值,小于当前节点,则向左子树递归查找
            if (this.left == null) {
                // 如果左子节点为空
                return null;
            }
            return this.left.search(value);
        } else {
            // 如果查找的值,不小于当前节点,则向右子树递归查找
            if (this.right == null) {
                // 如果左子节点为空
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要找到的节点的值
     * @return 返回的是要删除节点的父节点, 没有返回null
     */
    public NodeDemo searchParent(int value) {
        // 如果当前节点就是要删除的节点的父节点,就返回
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果要查找的值小于当前节点的值,并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                // 向左子树递归查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                // 向右子树递归查找
                return this.right.searchParent(value);
            } else {
                // 没有找到父节点
                return null;
            }
        }
    }

}
