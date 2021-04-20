import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    // 二分搜索树
    // 输入的数据必须需要有可比较性

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 树的根节点
    private Node root;
    // 树中节点的个数
    private int size;

    // 无参构造函数
    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 递归添加元素、非递归添加元素

    // 递归添加元素
    // 向二分搜索树中添加新的元素e
    public void add(E e){
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
//    private void add(Node node, E e){
//        if (e.equals(node.e)){
//            // 树中已经有该元素
//            return;
//        }else if(e.compareTo(node.e) < 0 && node.left == null){
//            // 左子树
//            node.left = new Node(e);
//            size ++;
//            return;
//        }else if(e.compareTo(node.e) > 0 && node.right == null){
//            // 右子树
//            node.right = new Node(e);
//            size ++;
//            return;
//        }
//
//        if(e.compareTo(node.e) < 0)
//            // 递归插入左子树
//            add(node.left, e);
//        else
//            // 递归插入右子树
//            add(node.right, e);
//
//    }


    // 向以node为根的二分搜索树插入元素e，递归算法
    // 返回插入的新节点后二分搜索树的根
    private Node add(Node node, E e){

        if(node == null){
            size ++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0)
            // 递归插入左子树
            node.left = add(node.left, e);
        else if(e.compareTo(node.e) > 0)
            // 递归插入右子树
            node.right = add(node.right, e);

        return node;
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if(node == null){
            return false;
        }

        if(e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else // e.compareTo(node.e) > 0
            return contains(node.right, e);

    }

    // 二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node){
        if(node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);

    }

    // 中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){

        if(node == null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 后序遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){

        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 非递归的前序遍历
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            // 栈先进后出，所以先右再左
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    // 二叉树的层序遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    // 寻找二分搜索树的最小元素
    // 最小元素肯定就是二分搜索树的最左边的元素
    public E minimum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }

        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    // 遍历的写法
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    // 最大元素肯定就是二分搜索树的最右边的元素
    public E maximum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }

        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node maximum(Node node){
        if (node.right == null)
            return node;
        return minimum(node.right);
    }

    // 删除二分搜索树中的最小元素，返回这个值
    public E removeMin(){
        E ret = minimum();

        // 删除节点
        removeMin(root);

        return ret;
    }

    // 删除以node为根的二分搜索树中的最小节点
    // 返回删除节点后的新的二分搜索树的根
    private Node removeMin(Node node){
        // 终止条件
        if (node.left == null) {
            // 保留该节点的右子树，将右子树作为代替该节点的根
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        // 递归的往下找左子树
        node.left = removeMin(node.left);

        return node;
    }

    // 删除二分搜索树中的最小元素，返回这个值
    public E removeMax(){
        E ret = maximum();

        // 删除节点
        removeMax(root);

        return ret;
    }

    // 删除以node为根的二分搜索树中的最大节点
    // 返回删除节点后的新的二分搜索树的根
    private Node removeMax(Node node){
        // 终止条件
        if (node.right == null) {
            // 保留该节点的右子树，将右子树作为代替该节点的根
            Node rightNode = node.left;
            node.left = null;
            size --;
            return rightNode;
        }

        // 递归的往下找左子树
        node.right = removeMin(node.right);

        return node;
    }

    // 删除任意一个元素e
    // 如果这个元素即有左子树又有右子树，需要使用右子树中的最小的节点代替该节点，然后将该节点删除
    // 同样的，这个元素左子树中的最大元素也可以替代这个元素
    public void remove(E e){
        root = remove(root, e);
    }

    // 删除以node为根的二分搜索树中值为e的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node,E e){

        if (node == null)
            return null;
        // 找到这个元素
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            remove(node.right, e);
            return node;
        }else {// e.compareTo(node.e) == 0，就是删除这个节点
            // 如果左子树为空，那么只要保存右子树就可以了
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // 右子树为空，只要保存左子树
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 删除节点的左右子树均不为空的情况
            // 找到比待删除节点大的最小节点，也就是右子树的最小节点
            // 使用这个节点顶替删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        // 遍历以输出树的内容
        generateBSTString(root, 0, res);

        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){

        if (node == null){
            res.append(generateDepthString(depth) + "null\n");
            return ;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right , depth +1, res);
    }

    // 输出树的层数
    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++){
            res.append("-");
        }
        return res.toString();
    }
}
