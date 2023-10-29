package five.head.proftestium.training.model

sealed class TestEvent {
    data class Answer(val index: Int) : TestEvent()
}