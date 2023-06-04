package sender

import com.fasterxml.jackson.databind.ObjectMapper
import exception.SensException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

interface Sender {

    fun sendRequest(client: OkHttpClient, request: Request): ResponseBody {
        val response = client.newCall(request).execute()
        return response.body ?: throw SensException("Response body is null")
    }

    fun sendRequestReturnResponse(client: OkHttpClient, request: Request): Response {
        return client.newCall(request).execute()
    }

    fun <T> mappedClass(mapper: ObjectMapper, responseBody: ResponseBody, clazz: Class<T>): T {
        return mapper.readValue(responseBody.string(), clazz)
    }

}
