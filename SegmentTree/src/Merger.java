public interface Merger<E> {
    // 用于元素合成的
    // 用户可以自定义如何融合两个元素
    E merge(E a, E b);
}
