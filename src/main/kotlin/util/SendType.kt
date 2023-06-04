package util

enum class SendType(
    val url: String,
) {
    SMS("/sms/v2"),
    ALIM_TALK("/alimtalk/v2/services"),
}
