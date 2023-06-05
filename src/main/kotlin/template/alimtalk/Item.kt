package template.alimtalk

data class Item(
    val list: MutableList<ItemList>,
    val summary: Summary? = null,
)
