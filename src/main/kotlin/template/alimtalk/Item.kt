package template.alimtalk

data class Item(
    val list: MutableList<ItemList> = mutableListOf(),
    val summary: Summary?,
)
