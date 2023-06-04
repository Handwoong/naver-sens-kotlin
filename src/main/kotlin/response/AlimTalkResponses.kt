package response

data class AlimTalkSendResponse(
    val requestId: String,
    val requestTime: String,
    val statusCode: String,
    val statusName: String,
    val messages: MutableList<AlimTalkSendResponseMessage>,
)

data class AlimTalkSendResponseMessage(
    val messageId: String,
    val countryCode: String?,
    val to: String,
    val content: String,
    val requestStatusCode: String,
    val requestStatusName: String,
    val requestStatusDesc: String,
    val useSmsFailover: Boolean,
)

data class AlimTalkSendRequestCheckResponse(
    val requestId: String,
    val statusCode: String,
    val statusName: String,
    val messages: MutableList<AlimTalkSendRequestCheckMessage>
)

data class AlimTalkSendRequestCheckMessage(
    val requestTime: String,
    val messageId: String,
    val countryCode: String?,
    val to: String,
    val content: String,
    val plusFriendId: String,
    val templateCode: String,
    val completeTime: String?,
    val requestStatusCode: String,
    val requestStatusName: String,
    val requestStatusDesc: String,
    val messageStatusCode: String,
    val messageStatusName: String,
    val messageStatusDesc: String,
    val useSmsFailover: Boolean,
    val failover: FailOver?,
)

data class FailOver(
    val smsServiceId: String?,
    val requestId: String?,
    val requestStatusCode: String?,
    val requestStatusName: String?,
    val requestStatusDesc: String?,
)

data class AlimTalkSendResultCheckResponse(
    val messageId: String,
    val requestId: String,
    val requestTime: String,
    val completeTime: String?,
    val plusFriendId: String,
    val templateCode: String,
    val countryCode: String?,
    val to: String,
    val content: String,
    val requestStatusCode: String,
    val requestStatusName: String,
    val requestStatusDesc: String,
    val messageStatusCode: String,
    val messageStatusName: String,
    val messageStatusDesc: String,
    val useSmsFailover: Boolean,
    val failover: AlimTalkSendResultCheckFailOver?,
)

data class AlimTalkSendResultCheckFailOver(
    val smsServiceId: String?,
    val requestId: String?,
    val requestStatusCode: String?,
    val requestStatusName: String?,
    val requestStatusDesc: String?,
    val messageId: String?,
    val messageStatus: String?,
    val messageStatusCode: String?,
    val messageStatusName: String?,
    val messageStatusDesc: String?,
)

data class AlimTalkReserveStatusCheckResponse(
    val reserveId: String,
    val reserveTimeZone: String,
    val reserveTime: String,
    val reserveStatus: String,
)

data class KakaoChannelCheckResponse(
    val createTime: String,
    val updateTime: String?,
    val serviceId: String,
    val channelId: String,
    val channelName: String,
    val channelStatus: String,
    val useSmsFailover: Boolean,
)

data class AlimTalkTemplateCheckResponse(
    val createTime: String,
    val updateTime: String?,
    val channelId: String,
    val templateCode: String,
    val templateName: String,
    val content: String,
    val comments: MutableList<Comment>,
    val templateInspectionStatus: String,
    val templateStatus: String,
    val buttons: MutableList<AlimTalkTemplateCheckButton>
)

data class Comment(
    val commentId: String,
    val content: String,
    val status: String,
    val createTime: String,
)

data class AlimTalkTemplateCheckButton(
    val order: Int,
    val type: String,
    val name: String,
    val linkMobile: String?,
    val linkPc: String?,
    val schemeIos: String?,
    val schemeAndroid: String?,
)
