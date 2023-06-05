package sender

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import exception.SensException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import template.MessageBody

abstract class AbstractSender {

    private val client = OkHttpClient()
    private val mapper: ObjectMapper =
        jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun <T> send(request: Request, clazz: Class<T>): T {
        val response = client.newCall(request).execute()
        return mappedResponse(response, clazz)
    }

    fun send(request: Request): Int {
        val response = client.newCall(request).execute()
        return response.code
    }

    fun mappedRequestBody(messageBody: MessageBody): RequestBody {
        return mapper.writeValueAsString(messageBody).toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun <T> mappedResponse(response: Response, clazz: Class<T>): T {
        response.body ?: throw SensException("Response body is null")
        return mapper.readValue(response.body?.string(), clazz)
    }

}
