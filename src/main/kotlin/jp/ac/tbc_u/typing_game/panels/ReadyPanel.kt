package jp.ac.tbc_u.typing_game.panels

import jp.ac.tbc_u.typing_game.GameManager
import jp.ac.tbc_u.typing_game.GameWindow
import jp.ac.tbc_u.typing_game.Main
import jp.ac.tbc_u.typing_game.Words
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*

class ReadyPanel : GamePanel {

    val words: Words

    lateinit var readyTimeLabel: JLabel
    lateinit var cancelButtonPanel: JPanel

    constructor(window: GameWindow, words: Words) : super(window) {
        this.words = words

        layout = BorderLayout()
        add(headerPanel(), BorderLayout.NORTH)
        add(bodyPanel(), BorderLayout.CENTER)

        GameManager.start()
    }

    override fun update() {
        readyTimeLabel.text = if (GameManager.readyTime > 0) GameManager.readyTime.toString() else "スタート！"
        repaint()
        revalidate()
    }

    private fun headerPanel() = JPanel().apply {
        preferredSize = Dimension(window.width, 70)
        layout = BorderLayout()
        background = Main.BRAND_COLOR
        border = BorderFactory.createEmptyBorder(0, 12, 0, 0)

        add(
            JLabel("タイピングゲーム ― ${words.name}", SwingConstants.CENTER).apply {
                size = preferredSize
                font = Main.serifFont.deriveFont(36f)
                foreground = Color.WHITE
            },
            BorderLayout.WEST
        )
    }

    private fun bodyPanel() = JPanel().apply {
        val layout = GridLayout(5, 1)
        this.layout = layout
        border = BorderFactory.createEmptyBorder(12, 12, 12, 12)

        for (i in 0 until layout.rows * layout.columns) {
            when (i) {
                1 -> {
                    readyTimeLabel = JLabel(if (GameManager.readyTime > 0) GameManager.readyTime.toString() else "スタート！").apply {
                        size = preferredSize
                        horizontalAlignment = SwingConstants.CENTER
                        font = Main.serifFont.deriveFont(180f)
                        foreground = Main.BRAND_COLOR
                    }
                    add(readyTimeLabel)
                }

                3 -> {
                    cancelButtonPanel = JPanel().apply {
                        this.layout = GridLayout(1, 3)
                        border = BorderFactory.createEmptyBorder(24, 120, 24, 120)

                        add(JPanel())

                        add(
                            JButton("キャンセル").apply {
                                size = preferredSize
                                font = Main.serifFont.deriveFont(48f)
                            }
                        )

                        add(JPanel())
                    }
                    add(cancelButtonPanel)
                }

                else -> add(JPanel())
            }
        }
    }
}