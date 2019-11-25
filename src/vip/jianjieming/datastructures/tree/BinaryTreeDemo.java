package vip.jianjieming.datastructures.tree;

/**
 * @author jianjieming
 * @date 2019/11/5 15:55
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroHode root = new HeroHode(1, "宋江");
        HeroHode hode1 = new HeroHode(2, "吴用");
        HeroHode hode2 = new HeroHode(3, "林冲");
        HeroHode hode3 = new HeroHode(4, "武松");
        HeroHode hode4 = new HeroHode(5, "孙权");
        HeroHode hode5 = new HeroHode(6, "曹操");
        HeroHode hode6 = new HeroHode(7, "刘备");

        // 说明,先手动创建该二叉树,以后会使用递归的方式创建二叉树
        root.setLeft(hode1);
        root.setRight(hode2);
        hode1.setLeft(hode3);
        hode1.setRight(hode4);
        hode2.setLeft(hode5);
        hode2.setRight(hode6);

        binaryTree.setRoot(root);

//        System.out.println(binaryTree.preOrderSearch(5));
//        System.out.println(binaryTree.infixOrderSearch(2));
//        System.out.println(binaryTree.postOrderSearch(4));


        // 1 2 3 5 4
        System.out.println("前序遍历");
        binaryTree.preOrder();

        // 2 1 5 3 4
        System.out.println("中序遍历");
        binaryTree.infixOrder();

        // 2 4 3 1
        System.out.println("后序遍历");
        binaryTree.postOrder();

        binaryTree.deleteNode(5);
        System.out.println("删除后: ");
        binaryTree.preOrder();

    }
}

/**
 * 二叉树
 */
class BinaryTree {

    private HeroHode root;

    public void setRoot(HeroHode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    public HeroHode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroHode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroHode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void deleteNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树,不能删除!!!");
        }
    }
}

class HeroHode {
    private int no;
    private String name;
    private HeroHode left;
    private HeroHode right;

    public HeroHode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroHode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroHode getLeft() {
        return left;
    }

    public void setLeft(HeroHode left) {
        this.left = left;
    }

    public HeroHode getRight() {
        return right;
    }

    public void setRight(HeroHode right) {
        this.right = right;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后续遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 根据no前序遍历查找
     *
     * @param no
     * @return
     */
    public HeroHode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroHode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            // 说明在左子树上找到了
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 根据no中序遍历查找
     *
     *
     * @param no
     * @return
     */
    public HeroHode infixOrderSearch(int no) {
        HeroHode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 根据no后序遍历查找
     *
     * @param no
     * @return
     */
    public HeroHode postOrderSearch(int no) {

        HeroHode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    /**
     * 递归删除节点
     *
     * @param no
     */
    public void deleteNode(int no) {
//        if (this.left != null) {
//            if (this.left.no == no) {
//                this.left = null;
//                return;
//            } else {
//                this.left.deleteNode(no);
//            }
//        }
//        if (this.right != null) {
//            if (this.right.no == no) {
//                this.right = null;
//                return;
//            } else {
//                this.right.deleteNode(no);
//            }
//        }
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }
}
