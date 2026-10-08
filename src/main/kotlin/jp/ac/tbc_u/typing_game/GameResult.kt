package jp.ac.tbc_u.typing_game

data class GameResult(val words: Array<Word>) {

    companion object {

        fun of(words: Array<jp.ac.tbc_u.typing_game.Word>) = GameResult(words.map { Word(it) }.toTypedArray())
    }

    var activeWordIndex = 0
    val activeWord
        get() = words.getOrNull(activeWordIndex)

    var name: String? = null

    val successInputCount
        get() = words.sumOf { it.successInputCount }
    val failedInputCount
        get() = words.sumOf { it.failedInputCount }
    val totalInputCount
        get() = successInputCount + failedInputCount

    private var inputKey = ""

    fun tryInput(key: String): Boolean {
        val word = activeWord ?: return false
        if (word.activeCharacterIndex >= word.characters.size)
            return false

        val character = word.activeCharacter ?: return false

        if (character.rule.keys.contentEquals(Key.N)) {
            if (inputKey.length < 2 && key == "n") {
                inputKey += key
                character.selectedKeyIndex = character.rule.keys.indexOfFirst { it.length == inputKey.length }
                character.completedInputIndex = inputKey.length
                word.successInputCount++
                return true
            }

            if (inputKey.isNotEmpty()) {
                character.selectedKeyIndex = character.rule.keys.indexOfFirst { it.length == inputKey.length }
                character.completedInputIndex = inputKey.length
                word.activeCharacterIndex++
                inputKey = ""
                return tryInput(key)
            }
        }


        val characterKey = character.rule.keys.indexOfFirst { it.startsWith(inputKey + key) }
        if (characterKey < 0) {
            word.failedInputCount++
            return false
        }

        inputKey += key
        character.selectedKeyIndex = characterKey
        character.completedInputIndex++
        word.successInputCount++

        if (character.completedInputIndex >= character.rule.keys[character.selectedKeyIndex].length) {
            word.activeCharacterIndex++
            inputKey = ""
        }

        /*
        if (word.activeCharacterIndex >= word.characters.size) {
            word.elapsedTime = System.currentTimeMillis() - word.elapsedTime
            activeWordIndex++
            inputKey = ""
        }
        */

        return true
    }

    fun tryNextWord(): Boolean {
        val word = activeWord ?: return false
        if (word.activeCharacterIndex < word.characters.size)
            return false

        if (activeWordIndex == words.lastIndex)
            return false

        word.elapsedTime = System.currentTimeMillis() - word.elapsedTime
        activeWordIndex++
        inputKey = ""

        return true
    }

    fun isCompleteWords(): Boolean {
        if (activeWordIndex < words.lastIndex)
            return false

        val word = activeWord ?: return false
        if (word.activeCharacterIndex < word.characters.size)
            return false

        return true
    }

    data class Word(val word: jp.ac.tbc_u.typing_game.Word) {

        val characters = word.rules.map { Character(it) }
        var activeCharacterIndex = 0
        val activeCharacter
            get() = characters.getOrNull(activeCharacterIndex)

        val name: Pair<String, String>
            get() {
                var length = 0
                return StringBuilder().apply {
                    for (character in characters) {
                        val keyRule = character.rule
                        val keyRuleLength = keyRule.nameLength
                        if (keyRuleLength < 1 || character.completedInputIndex < keyRule.keys[character.selectedKeyIndex].length)
                            continue

                        val startIndex = length
                        length += keyRuleLength
                        append(word.name.substring(startIndex, length))
                    }
                }.toString() to word.name.substring(length)
            }

        val ruby: Pair<String, String>
            get() {
                var length = 0
                return StringBuilder().apply {
                    for (i in 0 until characters.size) {
                        val character = characters[i]
                        val keyRule = character.rule
                        val keyRuleLength = keyRule.rubyLength
                        if (keyRuleLength < 1 || character.completedInputIndex < keyRule.keys[character.selectedKeyIndex].length)
                            continue

                        val startIndex = length
                        length += keyRuleLength
                        append(word.ruby.substring(startIndex, length))
                    }
                }.toString() to word.ruby.substring(length)
            }

        val key: Pair<String, String>
            get() {
                return StringBuilder().apply {
                    for (i in 0 until characters.size) {
                        val character = characters[i]
                        val keyRule = character.rule
                        append(keyRule.keys[character.selectedKeyIndex].substring(0, character.completedInputIndex))
                    }
                }.toString().uppercase() to StringBuilder().apply {
                    for (i in activeCharacterIndex.coerceAtLeast(0) until word.rules.size) {
                        if (i >= characters.size) {
                            append(word.rules[i].keys[0])
                            continue
                        }

                        val character = characters[i]
                        val keyRule = character.rule
                        append(keyRule.keys[character.selectedKeyIndex].substring(character.completedInputIndex))
                    }
                }.toString().uppercase()
            }

        var elapsedTime = 0.0

        var successInputCount = 0
        var failedInputCount = 0

        val totalInputCount
            get() = successInputCount + failedInputCount

        data class Character(val rule: KeyRule) {

            var selectedKeyIndex = 0
            var completedInputIndex = 0
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (javaClass != other?.javaClass)
            return false

        other as GameResult

        if (activeWordIndex != other.activeWordIndex)
            return false
        if (!words.contentEquals(other.words))
            return false
        if (name != other.name)
            return false

        return true
    }

    override fun hashCode(): Int {
        var result = activeWordIndex
        result = 31 * result + words.contentHashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}