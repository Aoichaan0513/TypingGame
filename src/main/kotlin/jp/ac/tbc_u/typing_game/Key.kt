package jp.ac.tbc_u.typing_game

object Key {

    // あ行
    val A = arrayOf("a")
    val I = arrayOf("i")
    val U = arrayOf("u", "wu", "whu")
    val E = arrayOf("e")
    val O = arrayOf("o")
    // 拗音
    val UXA = arrayOf("uxa", "ula", "wha")
    val UXI = arrayOf("uxi", "uli", "uxyi", "wi", "whi")
    val UXU = arrayOf("uxu", "ulu")
    val UXE = arrayOf("uxe", "ule", "uxye", "we", "whe")
    val UXO = arrayOf("uxo", "ulo", "who")
    // 濁点（拗音）
    val VA = arrayOf("va", "vuxa", "vula")
    val VI = arrayOf("vi", "vuxi", "vuli", "vuxyi", "vyi")
    val VE = arrayOf("ve", "vuxe", "vule", "vuxye", "vye")
    val VO = arrayOf("vo", "vuxo", "vulo")
    // 小文字
    val XA = arrayOf("xa", "la")
    val XI = arrayOf("xi", "li", "xyi")
    val XU = arrayOf("xu", "lu")
    val XE = arrayOf("xe", "le", "xye")
    val XO = arrayOf("xo", "lo")

    // か行
    val KA = arrayOf("ka", "ca")
    val KI = arrayOf("ki")
    val KU = arrayOf("ku", "cu")
    val KE = arrayOf("ke")
    val KO = arrayOf("ko", "co")
    // 拗音
    val KYA = arrayOf("kya")
    val KYI = arrayOf("kyi")
    val KYU = arrayOf("kyu")
    val KYE = arrayOf("kye")
    val KYO = arrayOf("kyo")
    // 濁点
    val GA = arrayOf("ga")
    val GI = arrayOf("gi")
    val GU = arrayOf("gu")
    val GE = arrayOf("ge")
    val GO = arrayOf("go")
    // 濁点 (拗音)
    val GYA = arrayOf("gya")
    val GYI = arrayOf("gyi")
    val GYU = arrayOf("gyu")
    val GYE = arrayOf("gye")
    val GYO = arrayOf("gyo")

    // さ行
    val SA = arrayOf("sa")
    val SI = arrayOf("si", "shi", "ci")
    val SU = arrayOf("su")
    val SE = arrayOf("se", "ce")
    val SO = arrayOf("so")
    // 拗音
    val SYA = arrayOf("sya", "sha")
    val SYI = arrayOf("syi")
    val SYU = arrayOf("syu", "shu")
    val SYE = arrayOf("sye", "she")
    val SYO = arrayOf("syo", "sho")
    // 濁点
    val ZA = arrayOf("za")
    val JI = arrayOf("zi", "ji")
    val ZU = arrayOf("zu")
    val ZE = arrayOf("ze")
    val ZO = arrayOf("zo")
    // 濁点 (拗音)
    val ZYA = arrayOf("zya", "jya", "ja", "zixya", "jixya", "zilya", "jilya")
    val ZYI = arrayOf("zyi", "jyi", "zixi", "jixi", "zixyi", "jixyi", "zili", "jili")
    val ZYU = arrayOf("zyu", "jyu", "ju", "zixyu", "jixyu", "zilyu", "jilyu")
    val ZYE = arrayOf("zye", "jye", "je", "zixe", "jixe", "zixye", "jixye", "zile", "jile")
    val ZYO = arrayOf("zyo", "jyo", "jo", "zixyo", "jixyo", "zilyo", "jilyo")

    // た行
    val TA = arrayOf("ta")
    val TI = arrayOf("ti", "chi")
    val TU = arrayOf("tu", "tsu")
    val TE = arrayOf("te")
    val TO = arrayOf("to")
    // 拗音
    val TYA = arrayOf("tya", "cya", "cha")
    val TYI = arrayOf("tyi", "cyi")
    val TYU = arrayOf("tyu", "cyu", "chu")
    val TYE = arrayOf("tye", "cye", "che")
    val TYO = arrayOf("tyo", "cyo", "cho")
    val TSA = arrayOf("tsa", "tuxa", "tsuxa", "tula", "tsula")
    val TSI = arrayOf("tsi", "tuxi", "tsuxi", "tuli", "tsuli")
    val TSE = arrayOf("tse", "tuxe", "tsuxe", "tule", "tsule")
    val TSO = arrayOf("tso", "tuxo", "tsuxo", "tulo", "tsulo")
    val THA = arrayOf("tha", "texya", "telya")
    val THI = arrayOf("thi", "texi", "teli")
    val THU = arrayOf("thu", "texyu", "telyu")
    val THE = arrayOf("the", "texe", "tele")
    val THO = arrayOf("tho", "texyo", "telyo")
    // 濁点
    val DA = arrayOf("da")
    val DI = arrayOf("di", "ji")
    val DU = arrayOf("du", "zu")
    val DE = arrayOf("de")
    val DO = arrayOf("do")
    // 濁点 (拗音)
    val DYA = arrayOf("dya")
    val DYI = arrayOf("dyi")
    val DYU = arrayOf("dyu")
    val DYE = arrayOf("dye")
    val DYO = arrayOf("dyo")

    // な行
    val NA = arrayOf("na")
    val NI = arrayOf("ni")
    val NU = arrayOf("nu")
    val NE = arrayOf("ne")
    val NO = arrayOf("no")
    // 拗音
    val NYA = arrayOf("nya")
    val NYI = arrayOf("nyi")
    val NYU = arrayOf("nyu")
    val NYE = arrayOf("nye")
    val NYO = arrayOf("nyo")

    // は行
    val HA = arrayOf("ha")
    val HI = arrayOf("hi")
    val HU = arrayOf("hu", "fu")
    val HE = arrayOf("he")
    val HO = arrayOf("ho")
    // 拗音
    val HYA = arrayOf("hya")
    val HYI = arrayOf("hyi")
    val HYU = arrayOf("hyu")
    val HYE = arrayOf("hye")
    val HYO = arrayOf("hyo")
    val FA = arrayOf("fa")
    val FI = arrayOf("fi")
    val FE = arrayOf("fe")
    val FO = arrayOf("fo")
    // 濁点
    val BA = arrayOf("ba")
    val BI = arrayOf("bi")
    val BU = arrayOf("bu")
    val BE = arrayOf("be")
    val BO = arrayOf("bo")
    // 濁点 (拗音)
    val BYA = arrayOf("bya")
    val BYI = arrayOf("byi")
    val BYU = arrayOf("byu")
    val BYE = arrayOf("bye")
    val BYO = arrayOf("byo")
    // 半濁点
    val PA = arrayOf("pa")
    val PI = arrayOf("pi")
    val PU = arrayOf("pu")
    val PE = arrayOf("pe")
    val PO = arrayOf("po")
    // 半濁点 (拗音)
    val PYA = arrayOf("pya")
    val PYI = arrayOf("pyi")
    val PYU = arrayOf("pyu")
    val PYE = arrayOf("pye")
    val PYO = arrayOf("pyo")

    // ま行
    val MA = arrayOf("ma")
    val MI = arrayOf("mi")
    val MU = arrayOf("mu")
    val ME = arrayOf("me")
    val MO = arrayOf("mo")
    // 拗音
    val MYA = arrayOf("mya")
    val MYI = arrayOf("myi")
    val MYU = arrayOf("myu")
    val MYE = arrayOf("mye")
    val MYO = arrayOf("myo")

    // や行
    val YA = arrayOf("ya")
    val YU = arrayOf("yu")
    val YO = arrayOf("yo")

    // ら行
    val RA = arrayOf("ra")
    val RI = arrayOf("ri")
    val RU = arrayOf("ru")
    val RE = arrayOf("re")
    val RO = arrayOf("ro")
    // 拗音
    val RYA = arrayOf("rya")
    val RYI = arrayOf("ryi")
    val RYU = arrayOf("ryu")
    val RYE = arrayOf("rye")
    val RYO = arrayOf("ryo")

    // わ行
    val WA = arrayOf("wa")
    val WO = arrayOf("wo")
    val N = arrayOf("n", "nn")
    val NN = arrayOf("nn")

    // 句読点・記号
    val COMMA = arrayOf(",")
    val PERIOD = arrayOf(".")
    val HYPHEN = arrayOf("-")
    val SPACE = arrayOf(" ")

    val KEY_MAPPING = hashMapOf(
        // あ行
        "あ" to A,
        "い" to I,
        "う" to U,
        "え" to E,
        "お" to O,
        // 拗音
        "うぁ" to UXA,
        "うぃ" to UXI,
        "うぅ" to UXU,
        "うぇ" to UXE,
        "うぉ" to UXO,
        // 濁点（拗音）
        "ゔぁ" to VA,
        "ゔぃ" to VI,
        "ゔぇ" to VE,
        "ゔぉ" to VO,
        // 小文字
        "ぁ" to XA,
        "ぃ" to XI,
        "ぅ" to XU,
        "ぇ" to XE,
        "ぉ" to XO,

        // か行
        "か" to KA,
        "き" to KI,
        "く" to KU,
        "け" to KE,
        "こ" to KO,
        // 拗音
        "きゃ" to KYA,
        "きぃ" to KYI,
        "きゅ" to KYU,
        "きぇ" to KYE,
        "きょ" to KYO,
        // 濁点
        "が" to GA,
        "ぎ" to GI,
        "ぐ" to GU,
        "げ" to GE,
        "ご" to GO,
        // 濁点 (拗音)
        "ぎゃ" to GYA,
        "ぎぃ" to GYI,
        "ぎゅ" to GYU,
        "ぎぇ" to GYE,
        "ぎょ" to GYO,

        // さ行
        "さ" to SA,
        "し" to SI,
        "す" to SU,
        "せ" to SE,
        "そ" to SO,
        // 拗音
        "しゃ" to SYA,
        "しぃ" to SYI,
        "しゅ" to SYU,
        "しぇ" to SYE,
        "しょ" to SYO,
        // 濁点
        "ざ" to ZA,
        "じ" to JI,
        "ず" to ZU,
        "ぜ" to ZE,
        "ぞ" to ZO,
        // 濁点 (拗音)
        "じゃ" to ZYA,
        "じぃ" to ZYI,
        "じゅ" to ZYU,
        "じぇ" to ZYE,
        "じょ" to ZYO,

        // た行
        "た" to TA,
        "ち" to TI,
        "つ" to TU,
        "て" to TE,
        "と" to TO,
        // 拗音
        "ちゃ" to TYA,
        "ちぃ" to TYI,
        "ちゅ" to TYU,
        "ちぇ" to TYE,
        "ちょ" to TYO,
        "つぁ" to TSA,
        "つぃ" to TSI,
        "つぇ" to TSE,
        "つぉ" to TSO,
        "てゃ" to THA,
        "てぃ" to THI,
        "てゅ" to THU,
        "てぇ" to THE,
        "てょ" to THO,
        // 濁点
        "だ" to DA,
        "ぢ" to DI,
        "づ" to DU,
        "で" to DE,
        "ど" to DO,
        // 濁点 (拗音)
        "ぢゃ" to DYA,
        "ぢぃ" to DYI,
        "ぢゅ" to DYU,
        "ぢぇ" to DYE,
        "ぢょ" to DYO,

        // な行
        "な" to NA,
        "に" to NI,
        "ぬ" to NU,
        "ね" to NE,
        "の" to NO,
        // 拗音
        "にゃ" to NYA,
        "にぃ" to NYI,
        "にゅ" to NYU,
        "にぇ" to NYE,
        "にょ" to NYO,

        // は行
        "は" to HA,
        "ひ" to HI,
        "ふ" to HU,
        "へ" to HE,
        "ほ" to HO,
        // 拗音
        "ひゃ" to HYA,
        "ひぃ" to HYI,
        "ひゅ" to HYU,
        "ひぇ" to HYE,
        "ひょ" to HYO,
        "ふぁ" to FA,
        "ふぃ" to FI,
        "ふぇ" to FE,
        "ふぉ" to FO,
        // 濁点
        "ば" to BA,
        "び" to BI,
        "ぶ" to BU,
        "べ" to BE,
        "ぼ" to BO,
        // 濁点 (拗音)
        "びゃ" to BYA,
        "びぃ" to BYI,
        "びゅ" to BYU,
        "びぇ" to BYE,
        "びょ" to BYO,
        // 半濁点
        "ぱ" to PA,
        "ぴ" to PI,
        "ぷ" to PU,
        "ぺ" to PE,
        "ぽ" to PO,
        // 半濁点 (拗音)
        "ぴゃ" to PYA,
        "ぴぃ" to PYI,
        "ぴゅ" to PYU,
        "ぴぇ" to PYE,
        "ぴょ" to PYO,

        // ま行
        "ま" to MA,
        "み" to MI,
        "む" to MU,
        "め" to ME,
        "も" to MO,
        // 拗音
        "みゃ" to MYA,
        "みぃ" to MYI,
        "みゅ" to MYU,
        "みぇ" to MYE,
        "みょ" to MYO,

        // や行
        "や" to YA,
        "ゆ" to YU,
        "よ" to YO,

        // ら行
        "ら" to RA,
        "り" to RI,
        "る" to RU,
        "れ" to RE,
        "ろ" to RO,
        // 拗音
        "りゃ" to RYA,
        "りぃ" to RYI,
        "りゅ" to RYU,
        "りぇ" to RYE,
        "りょ" to RYO,

        // わ行
        "わ" to WA,
        "を" to WO,
        "ん" to N,

        // 句読点・記号
        "、" to COMMA,
        "。" to PERIOD,
        "ー" to HYPHEN,
        "　" to SPACE,
        "," to COMMA,
        "." to PERIOD,
        "-" to HYPHEN,
        " " to SPACE,
    )

    val CONSONANT_KEY_MAPPINGS = hashMapOf(
        arrayOf("か", "き", "く", "け", "こ") to "k",
        arrayOf("が", "ぎ", "ぐ", "げ", "ご") to "g",
        arrayOf("さ", "し", "す", "せ", "そ") to "s",
        arrayOf("ざ", "じ", "ず", "ぜ", "ぞ") to "z",
        arrayOf("た", "ち", "つ", "て", "と") to "t",
        arrayOf("だ", "ぢ", "づ", "で", "ど") to "d",
        arrayOf("は", "ひ", "ふ", "へ", "ほ") to "h",
        arrayOf("ば", "び", "ぶ", "べ", "ぼ") to "b",
        arrayOf("ぱ", "ぴ", "ぷ", "ぺ", "ぽ") to "p",
        arrayOf("ま", "み", "む", "め", "も") to "m",
        arrayOf("や", "ゆ", "よ") to "y",
        arrayOf("ら", "り", "る", "れ", "ろ") to "r",
        arrayOf("わ", "を") to "w",
    )
}