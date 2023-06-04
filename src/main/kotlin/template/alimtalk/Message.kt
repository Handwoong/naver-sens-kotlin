package template.alimtalk

data class Message(
    val countryCode: String? = null,
    val to: String,
    val title: String? = null,
    val content: String,
    val headerContent: String? = null,
    val itemHighlight: ItemHighlight? = null,
    val item: Item? = null,
    val buttons: MutableList<Button>? = null,
    val useSmsFailover: Boolean? = null,
    val failoverConfig: FailOverConfig? = null,
)
