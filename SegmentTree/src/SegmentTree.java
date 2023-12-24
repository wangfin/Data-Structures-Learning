

public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    // 构造函数
    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i ++){
            data[i] = arr[i];
        }

        // 树的空间是4*n
        // 这里的树的空间大小是经过计算的，
        // 如果n=2^k，那么从第一层到倒数第二层的总空间大小为2n
        // 如果是n=2^k+1，再加最后一层，最后一层的大小为2n，所以总共4n
        tree = (E[])new Object[4 * arr.length];

        // 创建线段树
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 线段树构建函数
     * @param treeIndex 树的根节点
     * @param l 区间左端点
     * @param r 区间右端点
     */
    private void buildSegmentTree(int treeIndex, int l, int r){

        // 区间大小为1
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChile(treeIndex);

        // 防止使用l+r会造成整型溢出
        int mid = l + (r - l) / 2;

        // 递归的构建左子树和右子树
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    // 获取数据长度
    public int geiSize(){
        return data.length;
    }

    // get
    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 右孩子
    private int rightChile(int index){
        return 2*index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++){
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(",");
        }
        res.append(']');
        return res.toString();
    }
}
