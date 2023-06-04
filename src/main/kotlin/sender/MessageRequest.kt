package sender

import com.fasterxml.jackson.databind.ObjectMapper
import exception.SensException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import template.MessageBody
import util.RequestMethod
import util.RequestMethod.*
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

const val APPLICATION_JSON = "application/json"

open class MessageRequest(
    private val accessKey: String,
    private val secretKey: String,
) {

    fun createRequest(
        method: RequestMethod,
        url: String,
        messageBody: MessageBody? = null,
        mapper: ObjectMapper? = null,
    ): Request {
        val timestamp = System.currentTimeMillis().toString()
        val builder = Request.Builder().addHeader("Content-type", APPLICATION_JSON)
            .addHeader("x-ncp-apigw-timestamp", timestamp)
            .addHeader("x-ncp-iam-access-key", accessKey)
            .addHeader("x-ncp-apigw-signature-v2", makeSignature(timestamp, method, url))
            .url("https://sens.apigw.ntruss.com${url}")

        when (method) {
            GET -> builder.get()

            POST -> {
                if (messageBody == null || mapper == null) {
                    throw SensException("POST request but MessageBody is null")
                }
                builder.post(mapper.writeValueAsString(messageBody).toRequestBody(APPLICATION_JSON.toMediaTypeOrNull()))
            }

            DELETE -> builder.delete()
        }
        return builder.build()
    }

    private fun makeSignature(timestamp: String, method: RequestMethod, uri: String): String {
        val message = """
            ${method.name} $uri
            $timestamp
            $accessKey
        """.trimIndent()

        val signingKey = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(signingKey)
        val rawHmac = mac.doFinal(message.toByteArray())
        return Base64.getEncoder().encodeToString(rawHmac)
    }

}
