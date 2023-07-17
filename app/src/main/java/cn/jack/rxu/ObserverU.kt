package cn.jack.rxu

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述 观察者的顶层接口
 */
interface ObserverU<T> {
    fun onSubscribe()
    fun onSuccess(t: T)
    fun onError()
}
