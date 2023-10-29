package five.head.proftestium.common.vm

interface EventBase<T> {
    fun onEvent(event: T)
}