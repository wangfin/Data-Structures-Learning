public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 定义节点
    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    // 递归添加元素
    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树插入元素(key, value)，递归算法
    // 返回插入的新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            // 递归插入左子树
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            // 递归插入右子树
            node.right = add(node.right, key, value);
        else// key.compareTo(node.key) == 0
            // 修改key的value
            node.value = value;

        return node;
    }

    // 找到以node为根节点的二分搜索树中，key所在的节点
    // 递归
    private Node getNode(Node node, K key){

        if(node == null){
            return null;
        }

        // 找到该node
        if(key.compareTo(node.key) == 0)
            return node;
        // 左子树
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // key.compareTo(node.key) > 0 右子树
            return getNode(node.right, key);
    }

    @Override
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exists!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    // 遍历的写法
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
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

    @Override
    // 删除任意key 对应的 value
    // 如果这个元素即有左子树又有右子树，需要使用右子树中的最小的节点代替该节点，然后将该节点删除
    // 同样的，这个元素左子树中的最大元素也可以替代这个元素
    public V remove(K key){
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }

        return null;

    }


    // 删除以node为根的二分搜索树中key的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node,K key){

        if (node == null)
            return null;
        // 找到这个元素
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }else if(key.compareTo(node.key) > 0){
            remove(node.right, key);
            return node;
        }else {// key.compareTo(node.key) == 0，就是删除这个节点
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


}
