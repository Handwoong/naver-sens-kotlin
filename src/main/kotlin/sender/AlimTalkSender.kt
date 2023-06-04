package sender

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import response.*

class AlimTalkSender(
    private val client: OkHttpClient,
    private val mapper: ObjectMapper,
) : Sender {

    fun sendAlimTalk(request: Request): AlimTalkSendResponse {
        val responseBody = sendRequest(client, request)
        return mappedClass(mapper, responseBody, AlimTalkSendResponse::class.java)
    }

    fun alimTalkSendRequestCheck(request: Request): AlimTalkSendRequestCheckResponse {
        val responseBody = sendRequest(client, request)
        return mappedClass(mapper, responseBody, AlimTalkSendRequestCheckResponse::class.java)
    }

    fun alimTalkSendResultCheck(request: Request): AlimTalkSendResultCheckResponse {
        val responseBody = sendRequest(client, request)
        return mappedClass(mapper, responseBody, AlimTalkSendResultCheckResponse::class.java)
    }

    fun alimTalkReserveStatusCheck(request: Request): AlimTalkReserveStatusCheckResponse {
        val responseBody = sendRequest(client, request)
        return mappedClass(mapper, responseBody, AlimTalkReserveStatusCheckResponse::class.java)
    }

    fun alimTalkReserveCancel(request: Request): Int {
        val response = sendRequestReturnResponse(client, request)
        return response.code
    }

    fun alimTalkScheduleCancel(request: Request): Int {
        val response = sendRequestReturnResponse(client, request)
        return response.code
    }

    fun kakaoChannelCheck(request: Request): Array<KakaoChannelCheckResponse> {
        val responseBody = sendRequest(client, request)
        return mappedClass(mapper, responseBody, Array<KakaoChannelCheckResponse>::class.java)
    }

    fun alimTalkTemplateCheck(request: Request): Array<AlimTalkTemplateCheckResponse> {
        val responseBody = sendRequest(client, request)
        return mappedClass(mapper, responseBody, Array<AlimTalkTemplateCheckResponse>::class.java)
    }

}
