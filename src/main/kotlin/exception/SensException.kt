package exception

class SensException(
    override val message: String,
) : RuntimeException(message)
