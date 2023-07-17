package cn.jack.rxu

import android.os.Handler
import android.os.Looper

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述  线程切换，影响的是下游的被观察者 先订阅  在上游的观察者传递数据下来时再做线程切换
 */
class SingleObserveOnU<T>(private val sourceU: ObservableU<T>) : SingleU<T>() {
    override fun subscribeActual(observer: ObserverU<T>) {
        sourceU.subscribe(ObserveOnSingleObserver1(observer))
    }

    internal class ObserveOnSingleObserver1<T>(private val actual: ObserverU<T>) : ObserverU<T> {
        private val handler = Handler(Looper.getMainLooper())
        override fun onSubscribe() {
            actual.onSubscribe()
        }

        override fun onSuccess(t: T) {
            handler.post { actual.onSuccess(t) }
        }

        override fun onError() {
            handler.post { actual.onError() }
        }
    }
}
