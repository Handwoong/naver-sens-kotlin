package request

import enums.RequestMethodType
import enums.RequestMethodType.*
import exception.SensException
import okhttp3.Request
import okhttp3.RequestBody
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class MessageRequest(
    private val accessKey: String,
    private val secretKey: String,
) {

    fun createRequest(
        method: RequestMethodType,
        url: String,
        requestBody: RequestBody? = null,
    ): Request {
        val timestamp = System.currentTimeMillis().toString()
        val builder = Request.Builder().addHeader("Content-type", "application/json")
            .addHeader("x-ncp-apigw-timestamp", timestamp)
            .addHeader("x-ncp-iam-access-key", accessKey)
            .addHeader("x-ncp-apigw-signature-v2", makeSignature(timestamp, method, url))
            .url("https://sens.apigw.ntruss.com${url}")

        when (method) {
            GET -> builder.get()

            POST -> {
                if (requestBody == null) {
                    throw SensException("POST request but MessageBody is null")
                }
                builder.post(requestBody)
            }

            DELETE -> builder.delete()
        }
        return builder.build()
    }

    private fun makeSignature(timestamp: String, method: RequestMethodType, uri: String): String {
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
