package jp.ac.tbc_u.typing_game.panels

import com.formdev.flatlaf.FlatClientProperties
import jp.ac.tbc_u.typing_game.GameManager
import jp.ac.tbc_u.typing_game.GameWindow
import jp.ac.tbc_u.typing_game.Main
import jp.ac.tbc_u.typing_game.WORD_MAPPING
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.*

class NonePanel : GamePanel {

    private lateinit var categoryComboBox: JComboBox<String>
    private lateinit var startButton: JButton
    private lateinit var wordOrderButton: JToggleButton
    private lateinit var readyTimeComboBox: JComboBox<String>
    private lateinit var gameTimeComboBox: JComboBox<String>

    private val readyTimeMap: Map<String, Int> = mapOf(
        "なし" to 0,
        "3秒" to 3,
        "5秒" to 5,
        "10秒" to 10
    )

    private val gameTimeMap: Map<String, Int> = mapOf(
        "制限なし" to Int.MAX_VALUE,
        "1分" to 60 * 1,
        "3分" to 60 * 3,
        "5分" to 60 * 5,
        "10分" to 60 * 10
    )

    constructor(window: GameWindow) : super(window) {
        val layout = GridLayout(7, 1)
        this.layout = layout

        for (i in 0 until layout.rows * layout.columns) {
            when (i) {
                1 -> add(
                    JLabel("タイピングゲーム", SwingConstants.CENTER).apply {
                        size = preferredSize
                        font = Main.serifFont.deriveFont(80f)
                    }
                )

                3 -> {
                    add(
                        JPanel().apply {
                            val gridLayout = GridLayout(2, 3)
                            this.layout = gridLayout
                            border = BorderFactory.createEmptyBorder(0, 0, 30, 0)

                            for (i in 0 until gridLayout.rows * gridLayout.columns) {
                                when (i) {
                                    1 -> add(
                                        JLabel("カテゴリを選択：").apply {
                                            size = preferredSize
                                            font = Main.serifFont.deriveFont(28f)
                                        }
                                    )

                                    4 -> {
                                        categoryComboBox = JComboBox<String>(
                                            WORD_MAPPING
                                                .map { it.key }
                                                .toTypedArray()
                                        ).apply {
                                            size = preferredSize
                                            font = Main.serifFont.deriveFont(36f)
                                        }
                                        add(categoryComboBox)
                                    }

                                    else -> add(JPanel())
                                }
                            }
                        }
                    )
                }

                4 -> add(
                    JPanel().apply {
                        this.layout = GridLayout(1, 3)

                        add(JPanel())

                        startButton = JButton("スタート").apply {
                            font = Main.serifFont.deriveFont(80f)
                            background = Main.BRAND_COLOR
                            foreground = Color.WHITE

                            addActionListener {
                                GameManager.isShuffled = wordOrderButton.isSelected
                                GameManager.readyTime = readyTimeMap[readyTimeComboBox.selectedItem as String]!!
                                GameManager.gameTime = gameTimeMap[gameTimeComboBox.selectedItem as String]!!
                                GameManager.start(WORD_MAPPING[categoryComboBox.selectedItem as String]!!)
                            }
                        }
                        add(startButton)

                        add(JPanel())
                    }
                )

                6 -> add(
                    JPanel().apply {
                        val gridBagLayout = GridBagLayout()
                        this.layout = gridBagLayout
                        // this.border = BorderFactory.createEmptyBorder(24, 12, 12, 12)

                        val gridBagConstraints = GridBagConstraints()
                        gridBagConstraints.fill = GridBagConstraints.BOTH

                        val wordOrderLabel = JLabel("単語順").apply {
                            size = preferredSize
                            horizontalAlignment = SwingConstants.CENTER
                            font = Main.serifFont.deriveFont(20f)
                        }
                        wordOrderButton = JToggleButton("ランダム", true).apply {
                            size = preferredSize
                            font = Main.serifFont.deriveFont(28f)
                            addChangeListener {
                                text = if (isSelected) "ランダム" else "　固定　"
                            }
                        }
                        val readyTimeLabel = JLabel("準備時間").apply {
                            size = preferredSize
                            horizontalAlignment = SwingConstants.CENTER
                            font = Main.serifFont.deriveFont(20f)
                        }
                        readyTimeComboBox = JComboBox(readyTimeMap.keys.toTypedArray()).apply {
                            size = preferredSize
                            font = Main.serifFont.deriveFont(28f)
                            selectedIndex = 1

                            putClientProperty(
                                FlatClientProperties.STYLE,
                                mapOf<String, Any>(
                                    "padding" to Insets(0, 16, 0, 6)
                                )
                            )
                        }
                        val gameTimeLabel = JLabel("ゲーム時間").apply {
                            size = preferredSize
                            horizontalAlignment = SwingConstants.CENTER
                            font = Main.serifFont.deriveFont(20f)
                        }
                        gameTimeComboBox = JComboBox(gameTimeMap.keys.toTypedArray()).apply {
                            size = preferredSize
                            font = Main.serifFont.deriveFont(28f)
                            selectedIndex = 3

                            putClientProperty(
                                FlatClientProperties.STYLE,
                                mapOf<String, Any>(
                                    "padding" to Insets(0, 16, 0, 6)
                                )
                            )
                        }

                        gridBagConstraints.gridheight = 1
                        gridBagConstraints.ipady = 24

                        gridBagConstraints.gridx = 1
                        gridBagLayout.setConstraints(wordOrderLabel, gridBagConstraints)
                        gridBagConstraints.gridy = 1
                        gridBagLayout.setConstraints(wordOrderButton, gridBagConstraints)

                        gridBagConstraints.insets = Insets(0, 12, 0, 0)

                        gridBagConstraints.gridx = 2
                        gridBagConstraints.gridy = 0
                        gridBagLayout.setConstraints(readyTimeLabel, gridBagConstraints)
                        gridBagConstraints.gridy = 1
                        gridBagLayout.setConstraints(readyTimeComboBox, gridBagConstraints)

                        gridBagConstraints.gridx = 3
                        gridBagConstraints.gridy = 0
                        gridBagLayout.setConstraints(gameTimeLabel, gridBagConstraints)
                        gridBagConstraints.gridy = 1
                        gridBagLayout.setConstraints(gameTimeComboBox, gridBagConstraints)

                        add(wordOrderLabel)
                        add(wordOrderButton)
                        add(readyTimeLabel)
                        add(readyTimeComboBox)
                        add(gameTimeLabel)
                        add(gameTimeComboBox)
                    },
                    BorderLayout.CENTER
                )

                else -> add(JPanel())
            }
        }

        registerKey()
    }

    private fun registerKey() {
        val inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW)
        val actionMap = getActionMap()

        "startGameAction".run {
            val spaceKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0)
            inputMap.put(spaceKeyStroke, this)

            actionMap.put(this, object : AbstractAction() {

                override fun actionPerformed(e: ActionEvent) {
                    startButton.doClick()
                }
            })
        }
    }
}