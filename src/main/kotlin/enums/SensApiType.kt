package enums

enum class SensApiType(
    val url: String,
) {
    SMS("/sms/v2/services"),
    ALIM_TALK("/alimtalk/v2/services"),
}
