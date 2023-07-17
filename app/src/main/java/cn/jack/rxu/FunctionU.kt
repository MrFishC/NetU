package cn.jack.rxu

/**
 * @创建者 Jack
 * @创建时间 2023/7/13
 * @描述
 */
interface FunctionU<T, R> {
    fun apply(t: T): R
}
