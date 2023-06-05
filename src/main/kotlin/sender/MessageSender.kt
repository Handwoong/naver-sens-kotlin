package sender

import enums.RequestMethodType.*
import enums.SensApiType.ALIM_TALK
import request.MessageRequest
import response.*
import template.MessageBody

class MessageSender(
    private val accessKey: String,
    private val secretKey: String,
    private val serviceId: String,
) : AbstractSender() {

    private val messageRequest = MessageRequest(accessKey, secretKey)

    fun sendAlimTalk(messageBody: MessageBody): AlimTalkSendResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/messages"
        val request = messageRequest.createRequest(POST, url, mappedRequestBody(messageBody))
        return send(request, AlimTalkSendResponse::class.java)
    }

    fun alimTalkSendRequestCheck(requestId: String): AlimTalkSendRequestCheckResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/messages?requestId=${requestId}"
        val request = messageRequest.createRequest(GET, url)
        return send(request, AlimTalkSendRequestCheckResponse::class.java)
    }

    fun alimTalkSendResultCheck(messageId: String): AlimTalkSendResultCheckResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/messages/${messageId}"
        val request = messageRequest.createRequest(GET, url)
        return send(request, AlimTalkSendResultCheckResponse::class.java)
    }

    fun alimTalkReserveStatusCheck(reserveId: String): AlimTalkReserveStatusCheckResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/reservations/${reserveId}/reserve-status"
        val request = messageRequest.createRequest(GET, url)
        return send(request, AlimTalkReserveStatusCheckResponse::class.java)
    }

    fun alimTalkReserveCancel(reserveId: String): Int {
        val url = "${ALIM_TALK.url}/${serviceId}/reservations/${reserveId}"
        val request = messageRequest.createRequest(DELETE, url)
        return send(request)
    }

    fun alimTalkScheduleCancel(scheduleCode: String, messageId: String): Int {
        val url = "${ALIM_TALK.url}/${serviceId}/schedules/${scheduleCode}/messages/${messageId}"
        val request = messageRequest.createRequest(DELETE, url)
        return send(request)
    }

    fun kakaoChannelCheck(pageSize: Int = 100, pageIndex: Int = 0): Array<KakaoChannelCheckResponse> {
        val url = "${ALIM_TALK.url}/${serviceId}/channels?pageSize=${pageSize}&pageIndex=${pageIndex}"
        val request = messageRequest.createRequest(GET, url)
        return send(request, Array<KakaoChannelCheckResponse>::class.java)
    }

    fun alimTalkTemplateCheck(
        channelId: String,
        templateCode: String,
        templateName: String? = null,
        pageSize: Int = 100,
        pageIndex: Int = 0,
    ): Array<AlimTalkTemplateCheckResponse> {
        var url =
            "${ALIM_TALK.url}/${serviceId}/templates?channelId=${channelId}&templateCode=${templateCode}&pageSize=${pageSize}&pageIndex=${pageIndex}"
        templateName.let { url += "&templateName=${it}" }

        val request = messageRequest.createRequest(GET, url)
        return send(request, Array<AlimTalkTemplateCheckResponse>::class.java)
    }

}
