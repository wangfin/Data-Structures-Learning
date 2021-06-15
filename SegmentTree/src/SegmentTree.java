

public class SegmentTree<E> {

    private E[] data;
    private E[] tree;

    // 构造函数
    public SegmentTree(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i ++){
            data[i] = arr[i];
        }

        // 树的空间是4*n
        tree = (E[])new Object[4 * arr.length];
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
}
