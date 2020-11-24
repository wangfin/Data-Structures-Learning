import java.util.Objects;

public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity int 容量
     */

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 无参数构造函数
     */
    public Array(){
        this(10);
    }

    /**
     * 获取数组中元素的个数
     * @return size int
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组的容量
     * @return 数组的长度 int
     */
    public int getCapactiy(){
        return data.length;
    }

    /**
     * 检查数组是否为空
     * @return 是否为空 boolean
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向数组的最后添加元素
     * @param e 添加的元素
     */
    public void addLast(E e){
        if(size == data.length) {
            throw new IllegalArgumentException("AddLast failed.Array is full");
        }

        data[size] = e;
        size++;
    }

    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 向数组的指定位置添加元素
     * @param index 添加元素的位置 int
     * @param e 添加元素
     */
    public void add(int index, E e){
        // 输入index异常
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed.Require index >= 0 and index < size");
        }
        // 数组满了，对数组进行扩容
        if(size == data.length) {
            resize(2 * data.length);
        }
        // 将指定位置和该位置后面的元素向后移一位，为index腾出空间
        for(int i = size - 1; i>= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;

    }

    /**
     * 获取index索引位置的元素
     * @param index 索引
     * @return 该索引的元素
     */
    public E get(int index){
        if (index < 0 || index >= size )
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    /**
     * 修改index索引位置的元素为e
     * @param index 索引位置
     * @param e 元素值
     */
    void set(int index, E e){
        if (index < 0 || index >= size )
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e 查找的元素
     * @return 返回布尔结果
     */
    public boolean contains(E e){
        for(int i = 0; i < size; i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e的索引，如果不存在元素e，则返回-1
     * @param e 元素e
     * @return 索引
     */
    public int find(E e){
        for(int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     * @param index 索引
     * @return 返回删除元素
     */
    public E remove(int index){
        if (index < 0 || index >= size )
            throw new IllegalArgumentException("Get failed. Index is illegal.");

        E ret = data[index];
        for(int i = index + 1; i < size; i ++){
            data[i-1] = data[i];
        }
        size --;
        data[size] = null; // loitering objects != memory leak

        // 删除元素之后可以进行数组大小缩减，为了防止复杂度震荡，将删除的界限放在数组长度的1/4
        if(size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);

        return ret;
    }

    /**
     * 从数组中删除第一个元素，返回删除的元素
     * @return 返回元素
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素，返回删除的元素
     * @return 返回元素
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     * @param e 如果数组中有元素e，则删除这个元素（只能删除一个e）
     */
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if(i != size - 1){
                res.append(",");
            }
        }
        res.append(']');
        return res.toString();
    }

    /**
     * 内部方法，进行数组扩容
     * @param newCapacity 扩容大小
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

}
