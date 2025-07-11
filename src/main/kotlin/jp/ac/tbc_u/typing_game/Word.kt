package jp.ac.tbc_u.typing_game

import com.github.jikyo.romaji.Substring
import com.github.jikyo.romaji.Transliteration
import java.util.regex.Pattern
import java.util.stream.IntStream

data class Word(val name: String, val ruby: String, val rules: Array<KeyRule>) {

    companion object {

        val PATTERN = Pattern.compile("^(?<character>[^\\\\])(?:\\{(?<nameLength>\\d+)(?:, ?(?<rubyLength>\\d+))?\\})?")
        val CONTRACTED_SOUNDS = arrayOf("ぁ", "ぃ", "ぅ", "ぇ", "ぉ", "ゃ", "ゅ", "ょ")

        fun of(name: String, ruby: String): Word {
            val list = mutableListOf<KeyRule>()

            var cleanedRuby = ""

            var index = 0
            while (index < ruby.length) {
                val matcher = PATTERN.matcher(ruby.substring(index))
                if (!matcher.find())
                    break

                val length = matcher.end() - matcher.start()
                val character = matcher.group("character")
                val nameLength = matcher.group("nameLength")?.toIntOrNull() ?: 1
                val rubyLength = matcher.group("rubyLength")?.toIntOrNull() ?: 1

                index += length
                cleanedRuby += character

                val nextMatcher = PATTERN.matcher(ruby.substring(index))
                if (nextMatcher.find()) {
                    // 次の文字が存在する場合
                    val nextCharacter = nextMatcher.group("character")
                    val nextNameLength = nextMatcher.group("nameLength")?.toIntOrNull() ?: 1
                    val nextRubyLength = nextMatcher.group("rubyLength")?.toIntOrNull() ?: 1

                    if (CONTRACTED_SOUNDS.any { it == nextCharacter }) {
                        // 次の文字が拗音の場合、前の文字と結合する
                        val key = character + nextCharacter

                        if (character == "ゔ") {
                            index += nextMatcher.end() - nextMatcher.start()
                            cleanedRuby += nextCharacter
                            list.add(
                                KeyRule(
                                    Key.KEY_MAPPING[key]!!,
                                    nextNameLength,
                                    nextRubyLength
                                )
                            )
                            continue
                        }

                        val keys = getKeys(key)
                        if (keys.isNotEmpty()) {
                            index += nextMatcher.end() - nextMatcher.start()
                            cleanedRuby += nextCharacter
                            list.add(
                                KeyRule(
                                    keys,
                                    nextNameLength,
                                    nextRubyLength
                                )
                            )
                            continue
                        }
                    }

                    if (character == "っ") {
                        // 現在の文字が促音の場合
                        val nextKey = Key.CONSONANT_KEY_MAPPINGS.entries.firstOrNull { it.key.contains(nextCharacter) }
                        if (nextKey != null) {
                            list.add(
                                KeyRule(
                                    arrayOf(nextKey.value, "xtu", "xtsu", "ltu", "ltsu"),
                                    nameLength,
                                    rubyLength
                                )
                            )
                            continue
                        }
                    }

                    if (character == "ん") {
                        // 現在の文字が撥音の場合
                        list.add(
                            KeyRule(
                                if (Key.CONSONANT_KEY_MAPPINGS.entries.any { it.key.contains(nextCharacter) }) Key.N else Key.NN,
                                nameLength,
                                rubyLength
                            )
                        )
                        continue
                    }
                } else if (character == "ん") {
                    // 現在の文字が撥音の場合
                    list.add(
                        KeyRule(
                            Key.N,
                            nameLength,
                            rubyLength
                        )
                    )
                    continue
                }

                list.add(
                    KeyRule(
                        when (character) {
                            "ー", "-" -> arrayOf("-")
                            "、", "," -> arrayOf(",")
                            "。", "." -> arrayOf(".")
                            "・" -> arrayOf("/")
                            " ", "　" -> arrayOf(" ")
                            else -> getKeys(character)
                                .ifEmpty { arrayOf(character.lowercase()) }
                                .filter { it != "n'" }
                                .toTypedArray()
                        },
                        nameLength,
                        rubyLength
                    )
                )

                /*
                val nextMatcher = PATTERN.matcher(ruby.substring(index))
                if (nextMatcher.find()) {
                    // 次の文字が存在する場合
                    val nextCharacter = nextMatcher.group("character")
                    val nextNameLength = nextMatcher.group("nameLength")?.toIntOrNull() ?: 1
                    val nextRubyLength = nextMatcher.group("rubyLength")?.toIntOrNull() ?: 1

                    if (CONTRACTED_SOUNDS.any { it == nextCharacter }) {
                        // 次の文字が拗音の場合、前の文字と結合する
                        val key = character + nextCharacter
                        if (Key.KEY_MAPPING.containsKey(key)) {
                            index += nextMatcher.end() - nextMatcher.start()
                            cleanedRuby += nextCharacter
                            list.add(
                                KeyRule(
                                    Key.KEY_MAPPING[key]!!,
                                    nextNameLength,
                                    nextRubyLength
                                )
                            )
                            continue
                        }
                    }

                    if (character == "っ") {
                        // 現在の文字が促音の場合
                        val nextKey = Key.CONSONANT_KEY_MAPPINGS.entries.firstOrNull { it.key.contains(nextCharacter) }
                        if (nextKey != null) {
                            list.add(
                                KeyRule(
                                    arrayOf(nextKey.value, "xtu", "xtsu"),
                                    nameLength,
                                    rubyLength
                                )
                            )
                            continue
                        }
                    }

                    if (character == "ん") {
                        // 現在の文字が撥音の場合
                        list.add(
                            KeyRule(
                                if (Key.CONSONANT_KEY_MAPPINGS.entries.any { it.key.contains(nextCharacter) }) Key.N else Key.NN,
                                nameLength,
                                rubyLength
                            )
                        )
                        continue
                    }
                }

                list.add(
                    KeyRule(
                        Key.KEY_MAPPING[character] ?: arrayOf(character.lowercase()),
                        nameLength,
                        rubyLength
                    )
                )
                */
            }

            println("- Name: $name, Ruby: $cleanedRuby")
            for (rule in list)
                println("  - Rule: ${rule.keys.joinToString(", ")} (Name Length: ${rule.nameLength}, Ruby Length: ${rule.rubyLength})")

            return Word(
                name,
                cleanedRuby,
                list.toTypedArray()
            )
        }

        private fun getKeys(character: String): Array<String> {
            if (character.isBlank())
                return emptyArray()

            val lookAhead1 = Transliteration.valueOf()
            val lookAhead0 = Transliteration.valueOf()

            IntStream.range(0, character.length).forEachOrdered {
                lookAhead1.tryToAppend(Substring.lookahead(1, character, it))
                val substring = Substring.valueOf(character, it)
                lookAhead1.tryToAppend(substring)
                lookAhead0.tryToAppend(substring)
            }

            return (lookAhead1.sortedRomajis() + lookAhead0.sortedRomajis())
                .filterNot { it.equals(character) }
                .distinct()
                .toTypedArray()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (javaClass != other?.javaClass)
            return false

        other as Word

        if (name != other.name)
            return false
        if (ruby != other.ruby)
            return false
        if (!rules.contentEquals(other.rules))
            return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + ruby.hashCode()
        result = 31 * result + rules.contentHashCode()
        return result
    }
}