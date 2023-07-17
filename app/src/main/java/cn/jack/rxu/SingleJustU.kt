package cn.jack.rxu

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述 创建操作符
 */
class SingleJustU<T>(private val value: T) : SingleU<T>() {
    override fun subscribeActual(observer: ObserverU<T>) {
        observer.onSubscribe()
        observer.onSuccess(value)
    }
}
