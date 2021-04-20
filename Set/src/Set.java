public interface Set<E> {
    // 定义接口
    // 添加元素
    void add(E e);
    // 删除元素
    void remove(E e);
    // 是否包含
    boolean contains(E e);
    // 获取大小
    int getSize();
    // 是否为空
    boolean isEmpty();

}
