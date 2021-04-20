public interface Map<K, V> {
    // 添加kv
    void add(K key, V value);
    // 删除
    V remove(K key);
    // 是否存在
    boolean contains(K key);
    // 取值
    V get(K key);
    // 设置值
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
