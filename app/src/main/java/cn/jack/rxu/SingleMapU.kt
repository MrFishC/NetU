package cn.jack.rxu

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述 变换操作符
 */
class SingleMapU<T, R>(private val source: ObservableU<T>, private val mapper: FunctionU<T, R>) :
    SingleU<R>() {
    override fun subscribeActual(observer: ObserverU<R>) {
        source.subscribe(MapSingleObserverU1(observer, mapper))
    }

    internal class MapSingleObserverU1<T, R>(
        private val t: ObserverU<R>,
        private val mapper: FunctionU<T, R>
    ) : ObserverU<T> {
        override fun onSubscribe() {
            t.onSubscribe()
        }

        override fun onSuccess(value: T) {
            t.onSuccess(mapper.apply(value))
        }

        override fun onError() {
            t.onError()
        }
    }
}
