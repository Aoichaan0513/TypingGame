package jp.ac.tbc_u.typing_game.panels

import jp.ac.tbc_u.typing_game.GameManager
import jp.ac.tbc_u.typing_game.GameWindow
import jp.ac.tbc_u.typing_game.Main
import java.awt.*
import javax.swing.*
import javax.swing.border.Border

class EndPanel(window: GameWindow) : GamePanel(window) {

    val result
        get() = GameManager.result

    init {
        layout = BorderLayout()
        add(headerPanel(), BorderLayout.NORTH)
        add(bodyPanel(), BorderLayout.CENTER)

        repaint()
        revalidate()

        update()
    }

    private fun headerPanel() = JPanel().apply {
        preferredSize = Dimension(window.width, 70)
        layout = BorderLayout()
        background = Main.BRAND_COLOR
        border = BorderFactory.createEmptyBorder(0, 12, 0, 0)

        add(
            JLabel("タイピングゲーム ― ${GameManager.words.name}", SwingConstants.CENTER).apply {
                size = preferredSize
                font = Main.serifFont.deriveFont(36f)
                foreground = Color.WHITE
            },
            BorderLayout.WEST
        )
    }

    private fun bodyPanel() = JPanel().apply {
        val layout = GridLayout(1, 3)
        this.layout = layout
        border = BorderFactory.createEmptyBorder(12, 12, 12, 12)

        for (i in 0 until layout.rows * layout.columns) {
            when (i) {
                1 -> add(
                    JPanel().apply {
                        this.layout = BoxLayout(this, BoxLayout.Y_AXIS)
                        alignmentX = CENTER_ALIGNMENT
                        alignmentY = CENTER_ALIGNMENT

                        add(
                            JPanel().apply {
                                this.layout = GridBagLayout()

                                val constraints = GridBagConstraints().apply {
                                    fill = GridBagConstraints.BOTH
                                    anchor = GridBagConstraints.CENTER
                                }

                                add(
                                    JLabel("ゲーム終了！", SwingConstants.CENTER).apply {
                                        size = preferredSize
                                        font = Main.serifFont.deriveFont(80f)
                                        alignmentX = CENTER_ALIGNMENT
                                        foreground = Main.BRAND_COLOR
                                    }
                                )

                                add(Box.createRigidArea(Dimension(0, 24)))

                                add(
                                    JLabel("お疲れさまでした！", SwingConstants.CENTER).apply {
                                        size = preferredSize
                                        font = Main.serifFont.deriveFont(40f)
                                        alignmentX = CENTER_ALIGNMENT
                                    }
                                )

                                add(Box.createRigidArea(Dimension(0, 48)))

                                add(
                                    JPanel().apply {
                                        this.layout = BorderLayout()

                                        add(
                                            JPanel().apply {
                                                this.layout = FlowLayout(FlowLayout.CENTER, 0, 0)

                                                add(
                                                    JLabel("正答率：").apply {
                                                        size = preferredSize
                                                        font = Main.serifFont.deriveFont(28f)
                                                    }
                                                )
                                                add(
                                                    JLabel("${result.successInputCount / result.totalInputCount * 100}%").apply {
                                                        size = preferredSize
                                                        font = Main.serifFont.deriveFont(40f)
                                                        foreground = Main.BRAND_COLOR
                                                    }
                                                )
                                            },
                                            BorderLayout.WEST
                                        )
                                    }
                                )

                                add(Box.createRigidArea(Dimension(0, 6)))

                                add(
                                    JProgressBar(SwingConstants.HORIZONTAL).apply {
                                        size = preferredSize
                                        alignmentX = CENTER_ALIGNMENT
                                        foreground = Main.BRAND_COLOR

                                        value = result.successInputCount / result.totalInputCount * 100
                                    }
                                )

                                add(Box.createRigidArea(Dimension(0, 12)))

                                add(
                                    JPanel().apply {
                                        this.layout = BorderLayout()

                                        add(
                                            JPanel().apply {
                                                this.layout = FlowLayout(FlowLayout.CENTER, 0, 0)

                                                add(
                                                    JLabel("総入力数：").apply {
                                                        size = preferredSize
                                                        font = Main.serifFont.deriveFont(28f)
                                                    }
                                                )
                                                add(
                                                    JLabel(result.totalInputCount.toString()).apply {
                                                        size = preferredSize
                                                        font = Main.serifFont.deriveFont(40f)
                                                        foreground = Main.BRAND_COLOR
                                                    }
                                                )
                                            },
                                            BorderLayout.WEST
                                        )
                                    }
                                )
                            }
                        )
                    },
                    BorderLayout.CENTER
                )

                else -> add(JLabel())
            }
        }
    }
}