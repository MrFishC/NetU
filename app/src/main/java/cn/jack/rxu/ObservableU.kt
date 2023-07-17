package cn.jack.rxu

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述 被观察者的顶层接口
 */
interface ObservableU<T> {
    fun subscribe(observer: ObserverU<T>)
}
