package jp.ac.tbc_u.typing_game

data class KeyRule(
    val keys: Array<String>,
    val nameLength: Int,
    val rubyLength: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (javaClass != other?.javaClass)
            return false

        other as KeyRule

        if (nameLength != other.nameLength)
            return false
        if (rubyLength != other.rubyLength)
            return false
        if (!keys.contentEquals(other.keys))
            return false

        return true
    }

    override fun hashCode(): Int {
        var result = nameLength
        result = 31 * result + rubyLength
        result = 31 * result + keys.contentHashCode()
        return result
    }
}
