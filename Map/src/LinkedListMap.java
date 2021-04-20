public class LinkedListMap<K, V> implements Map<K, V> {

    // 定义节点
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){this(key, null, null);}

        public Node(){this(null, null, null);}

        @Override
        public String toString(){
            return key.toString() + ":" + value.toString();
        }
    }

    // 虚拟头节点
    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize(){ return size; }

    @Override
    public boolean isEmpty(){ return size == 0; }

    // 输入key，获取对应的节点
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    // 这个值是否存在
    public boolean contains(K key){
        return getNode(key) != null;
    }

    @Override
    // 获取key对应的值
    public V get(K key){
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    // 添加节点
    public void add(K key, V value){
        // 检查是否存在
        Node node = getNode(key);
        // 如果不存在
        if(node == null){
            // 直接添加元素
            dummyHead.next = new Node(key, value, dummyHead.next);
            size ++;
        }else{
            // 如果这个key存在了，那个就更新value
            node.value = value;
        }
    }

    @Override
    public void set(K key, V newValue){
        // 查询是否存在这个key
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException(key + "doesn't exists!");
        }else{
            // 存在key，就更新value
            node.value = newValue;
        }
    }

    @Override
    public V remove(K key){
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        return null;
    }


}
