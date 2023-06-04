package template.alimtalk

import exception.SensException
import template.MessageBody

data class AlimTalkBody(
    val plusFriendId: String,
    val templateCode: String,
    val messages: MutableList<Message> = mutableListOf(),
    val reserveTime: String? = null,
    val reserveTimeZone: String? = null,
    val scheduleCode: String? = null,
) : MessageBody {

    init {
        if (this.messages.size > 100) {
            throw SensException("메시지는 최대 100개만 가능합니다.")
        }
    }

}
