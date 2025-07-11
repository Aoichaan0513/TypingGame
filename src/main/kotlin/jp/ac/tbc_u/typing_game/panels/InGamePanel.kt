package jp.ac.tbc_u.typing_game.panels

import jp.ac.tbc_u.typing_game.*
import kotlinx.coroutines.DelicateCoroutinesApi
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*

class InGamePanel : GamePanel, KeyListener {

    val words: Words
    val result: GameResult

    private lateinit var inputCountLabel: JLabel
    private lateinit var gameTimeLabel: JLabel
    private lateinit var endGameButton: JButton
    private lateinit var completedNameLabel: JLabel
    private lateinit var inCompletedNameLabel: JLabel
    private lateinit var completedRubyLabel: JLabel
    private lateinit var inCompletedRubyLabel: JLabel
    private lateinit var completedKeyLabel: JLabel
    private lateinit var inCompletedKeyLabel: JLabel

    constructor(window: GameWindow, words: Words) : super(window) {
        this.words = words
        result = GameResult.of(words.words)

        layout = BorderLayout()
        add(headerPanel(), BorderLayout.NORTH)
        add(bodyPanel(), BorderLayout.CENTER)

        repaint()
        revalidate()

        update()

        isFocusable = true

        addKeyListener(this)
        registerKey()
    }

    override fun update() {
        inputCountLabel.text = "入力文字数：${result.totalInputCount}"

        val minute = (GameManager.gameTime / 60).toString().padStart(2, '0')
        val second = (GameManager.gameTime % 60).toString().padStart(2, '0')
        gameTimeLabel.text = "残り：$minute:$second"

        val ruby = result.activeWord?.ruby
        completedRubyLabel.text = ruby?.first ?: ""
        inCompletedRubyLabel.text = ruby?.second ?: ""

        val name = result.activeWord?.name
        completedNameLabel.text = name?.first ?: ""
        inCompletedNameLabel.text = name?.second ?: ""

        val key = result.activeWord?.key
        completedKeyLabel.text = key?.first ?: ""
        inCompletedKeyLabel.text = key?.second ?: ""
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

        add(
            JPanel().apply {
                background = Main.BRAND_COLOR
                border = BorderFactory.createEmptyBorder(4, 0, 0, 0)

                inputCountLabel = JLabel("入力文字数：0", SwingConstants.CENTER).apply {
                    size = preferredSize
                    font = Main.serifFont.deriveFont(28f)
                    foreground = Color.WHITE
                    border = BorderFactory.createEmptyBorder(0, 0, 0, 4)
                }
                add(inputCountLabel)

                gameTimeLabel = JLabel(
                    "残り：${GameManager.gameTime / 60}:${GameManager.gameTime % 60}",
                    SwingConstants.CENTER
                ).apply {
                    size = preferredSize
                    font = Main.serifFont.deriveFont(28f)
                    foreground = Color.WHITE
                    border = BorderFactory.createEmptyBorder(0, 0, 0, 4)
                    isVisible = GameManager.gameTime <= 600
                }
                add(gameTimeLabel)

                endGameButton = JButton("ゲームを終了する").apply {
                    size = preferredSize
                    margin = Insets(8, 8, 8, 8)
                    font = Main.serifFont.deriveFont(28f)

                    isFocusable = false

                    addActionListener {
                        val result = JOptionPane.showConfirmDialog(
                            window,
                            "ゲームを終了しますか？",
                            "",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                        )

                        if (result != JOptionPane.YES_OPTION)
                            return@addActionListener

                        GameManager.end()
                    }
                }
                add(endGameButton)
            },
            BorderLayout.EAST
        )
    }

    private fun bodyPanel() = JPanel().apply {
        layout = GridLayout(3, 1)
        border = BorderFactory.createEmptyBorder(12, 12, 12, 12)

        add(JPanel())

        add(
            JPanel().apply {
                layout = BoxLayout(this, BoxLayout.Y_AXIS)

                add(
                    JPanel().apply {
                        layout = FlowLayout(FlowLayout.CENTER, 0, 0)

                        completedRubyLabel = JLabel("", SwingConstants.CENTER).apply {
                            alignmentX = CENTER_ALIGNMENT
                            size = preferredSize
                            font = Main.serifFont.deriveFont(40f)
                            foreground = Main.BRAND_COLOR
                        }
                        add(completedRubyLabel)

                        inCompletedRubyLabel = JLabel("", SwingConstants.CENTER).apply {
                            alignmentX = CENTER_ALIGNMENT
                            size = preferredSize
                            font = Main.serifFont.deriveFont(40f)
                        }
                        add(inCompletedRubyLabel)
                    }
                )

                add(Box.createRigidArea(Dimension(0, 12)))

                add(
                    JPanel().apply {
                        layout = FlowLayout(FlowLayout.CENTER, 0, 0)

                        completedNameLabel = JLabel("", SwingConstants.CENTER).apply {
                            alignmentX = CENTER_ALIGNMENT
                            size = preferredSize
                            font = Main.serifFont.deriveFont(80f)
                            foreground = Main.BRAND_COLOR
                        }
                        add(completedNameLabel)

                        inCompletedNameLabel = JLabel("", SwingConstants.CENTER).apply {
                            alignmentX = CENTER_ALIGNMENT
                            size = preferredSize
                            font = Main.serifFont.deriveFont(80f)
                        }
                        add(inCompletedNameLabel)
                    }
                )

                add(Box.createRigidArea(Dimension(0, 24)))

                add(
                    JPanel().apply {
                        layout = FlowLayout(FlowLayout.CENTER, 0, 0)

                        completedKeyLabel = JLabel("", SwingConstants.CENTER).apply {
                            alignmentX = CENTER_ALIGNMENT
                            size = preferredSize
                            font = Main.monospaceFont.deriveFont(64f)
                            foreground = Main.BRAND_COLOR
                        }
                        add(completedKeyLabel)

                        inCompletedKeyLabel = JLabel("", SwingConstants.CENTER).apply {
                            alignmentX = CENTER_ALIGNMENT
                            size = preferredSize
                            font = Main.monospaceFont.deriveFont(64f)
                        }
                        add(inCompletedKeyLabel)
                    }
                )
            }
        )
    }

    private fun registerKey() {
        val inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW)
        val actionMap = getActionMap()

        "endGameAction".run {
            val escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)
            inputMap.put(escapeKeyStroke, this)

            actionMap.put(this, object : AbstractAction() {

                override fun actionPerformed(e: ActionEvent) {
                    endGameButton.doClick()
                }
            })
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun keyTyped(e: KeyEvent) {
        // println(e.keyChar)

        if (Character.isISOControl(e.keyChar))
            return

        result.tryInput(e.keyChar.toString())

        if (result.tryNextWord()) {
            remove(1)
            repaint()
            revalidate()

            add(bodyPanel(), BorderLayout.CENTER)
            repaint()
            revalidate()
        }

        update()
    }

    override fun keyPressed(e: KeyEvent) {
    }

    override fun keyReleased(e: KeyEvent) {
    }
}