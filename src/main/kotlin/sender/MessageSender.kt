package sender

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import response.*
import template.MessageBody
import util.RequestMethod.*
import util.SendType.ALIM_TALK

class MessageSender(
    private val accessKey: String,
    private val secretKey: String,
    private val serviceId: String,
) : MessageRequest(accessKey, secretKey) {

    private val mapper: ObjectMapper =
        jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val client = OkHttpClient()
    private val alimTalkSender = AlimTalkSender(client, mapper)

    fun sendAlimTalk(messageBody: MessageBody): AlimTalkSendResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/messages"
        val request = createRequest(POST, url, messageBody, mapper)
        return alimTalkSender.sendAlimTalk(request)
    }

    fun alimTalkSendRequestCheck(requestId: String): AlimTalkSendRequestCheckResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/messages?requestId=${requestId}"
        val request = createRequest(GET, url)
        return alimTalkSender.alimTalkSendRequestCheck(request)
    }

    fun alimTalkSendResultCheck(messageId: String): AlimTalkSendResultCheckResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/messages/${messageId}"
        val request = createRequest(GET, url)
        return alimTalkSender.alimTalkSendResultCheck(request)
    }

    fun alimTalkReserveStatusCheck(reserveId: String): AlimTalkReserveStatusCheckResponse {
        val url = "${ALIM_TALK.url}/${serviceId}/reservations/${reserveId}/reserve-status"
        val request = createRequest(GET, url)
        return alimTalkSender.alimTalkReserveStatusCheck(request)
    }

    fun alimTalkReserveCancel(reserveId: String): Int {
        val url = "${ALIM_TALK.url}/${serviceId}/reservations/${reserveId}"
        val request = createRequest(DELETE, url)
        return alimTalkSender.alimTalkReserveCancel(request)
    }

    fun alimTalkScheduleCancel(scheduleCode: String, messageId: String): Int {
        val url = "${ALIM_TALK.url}/${serviceId}/schedules/${scheduleCode}/messages/${messageId}"
        val request = createRequest(DELETE, url)
        return alimTalkSender.alimTalkScheduleCancel(request)
    }

    fun kakaoChannelCheck(pageSize: Int = 100, pageIndex: Int = 0): Array<KakaoChannelCheckResponse> {
        val url = "${ALIM_TALK.url}/${serviceId}/channels?pageSize=${pageSize}&pageIndex=${pageIndex}"
        val request = createRequest(GET, url)
        return alimTalkSender.kakaoChannelCheck(request)
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

        val request = createRequest(GET, url)
        return alimTalkSender.alimTalkTemplateCheck(request)
    }

}
