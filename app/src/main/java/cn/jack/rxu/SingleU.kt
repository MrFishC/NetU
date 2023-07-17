package cn.jack.rxu

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述 实现了被观察者顶层接口的抽象类，实现了订阅的方法
 *
 * 该类是供外界使用
 */
abstract class SingleU<T> : ObservableU<T> {
    override fun subscribe(observer: ObserverU<T>) {
        subscribeActual(observer)
    }

    protected abstract fun subscribeActual(observer: ObserverU<T>)
    fun <R> map(mapper: FunctionU<T, R>): SingleU<R> {
        return SingleMapU(this, mapper)
    }

    fun subscribeOn(): SingleU<T> {
        return SingleSubscribeOnU(this)
    }

    fun observeOn(): SingleU<T> {
        return SingleObserveOnU(this)
    }

    companion object {
        fun <T> just(value: T): SingleU<T> {
            return SingleJustU(value)
        }
    }
}
