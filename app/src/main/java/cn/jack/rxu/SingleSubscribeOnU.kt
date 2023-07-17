package cn.jack.rxu

import java.util.concurrent.Executors

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述  线程切换，影响的是上游的被观察者  先做线程切换，再订阅新建的观察者
 */
class SingleSubscribeOnU<T>(private val sourceU: ObservableU<T>) : SingleU<T>() {
    override fun subscribeActual(observer: ObserverU<T>) {
        executorService.submit(SubscribeOnObserverU1(observer, sourceU))
    }

    internal class SubscribeOnObserverU1<T>(
        private val actual: ObserverU<T>,
        private val source: ObservableU<T>
    ) : ObserverU<T>, Runnable {
        override fun onSubscribe() {
            actual.onSubscribe()
        }

        override fun onSuccess(t: T) {
            actual.onSuccess(t)
        }

        override fun onError() {
            actual.onError()
        }

        override fun run() {
            source.subscribe(this)
        }
    }

    companion object {
        private val executorService = Executors.newCachedThreadPool()
    }
}
