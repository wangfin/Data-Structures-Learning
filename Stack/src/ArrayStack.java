public class ArrayStack<E> implements Stack<E>{

    Array<E> array;
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        array = new Array<>();
    }

    //实现接口

    @Override
    //获取栈的大小
    public int getSize(){
        return array.getSize();
    }

    @Override
    // 是否为空
    public boolean isEmpty(){
        return array.isEmpty();
    }

    //获取容积
    public int getCapacity(){
        return array.getCapactiy();
    }

    //入栈
    @Override
    public void push(E e){
        array.addLast(e);
    }

    //出栈
    @Override
    public E pop(){
        return array.removeLast();
    }

    //查看栈顶的元素
    @Override
    public E peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0 ; i < array.getSize(); i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }

}
