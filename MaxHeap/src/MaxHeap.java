public class MaxHeap<E extends Comparable<E>> {

    // 数据
    private Array<E> data;

    public MaxHeap(int capacicy){
        data = new Array<>(capacicy);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    // heapify函数，可以将一个数组转换为堆的形式
    // 方式为，从最后一个非叶子节点开始，将前面每一个节点进行siftdown
    public MaxHeap(E[] arr){
        // 传入数组之后，使用动态数组创建一个新的数组
        data = new Array<>(arr);
        // 从最后一个非叶子节点开始，也就是最后一个叶子节点的父亲节点
        for (int i = parent(arr.length-1); i >= 0; i --){
            siftDown(i);
        }
    }

    // 返回堆中元素个数
    public int size(){
        return data.getSize();
    }

    // 返回一个布尔值，表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    // 向堆中添加元素，添加在最末尾
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    // 在加入一个节点之后，需要对这个节点进行上浮操作
    private void siftUp(int k){
        // 当index > 0并且 该节点的值大于这个节点目前父节点的值，就进行上浮
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            // 交换index k与k的父亲节点
            data.swap(k ,parent(k));
            k = parent(k);
        }
    }

    // 查看堆中的最大元素
    public E findMax(){
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        return data.get(0);
    }

    // 取出堆中最大元素，然后将末尾的元素放到头位置，然后对元素进行下沉
    public E extractMax(){
        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k){
        // 一直循环到叶子节点，即叶子节点的左孩子都越界了
        while (leftChild(k) < data.getSize()){
            // 左孩子节点的值
            int j = leftChild(k);
            // 有右孩子，并且右孩子比左孩子大
            if ( j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0){
                j = rightChild(k);
                // 此时data[j]是左孩子和右孩子中的最大值
            }
            // 如果k>左右孩子，那么不需要下沉
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            // 如果没有，则需要下沉
            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e){
        // 找到最大的元素
        E ret = findMax();
        // 将堆顶的元素替换为e
        data.set(0, e);
        // 进行siftDown来排序
        siftDown(0);
        return ret;
    }



}
