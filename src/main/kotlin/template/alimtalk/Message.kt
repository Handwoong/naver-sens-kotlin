package template.alimtalk

data class Message(
    val countryCode: String?,
    val to: String,
    val title: String?,
    val content: String,
    val headerContent: String?,
    val itemHighlight: ItemHighlight?,
    val item: Item?,
    val buttons: MutableList<Button>? = mutableListOf(),
    val useSmsFailover: Boolean?,
    val failoverConfig: FailOverConfig?,
)
