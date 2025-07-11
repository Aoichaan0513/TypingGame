package jp.ac.tbc_u.typing_game

import jp.ac.tbc_u.typing_game.panels.GamePanel
import jp.ac.tbc_u.typing_game.panels.NonePanel
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame

class GameWindow : JFrame {

    var content: GamePanel = NonePanel(this)
        set(value) {
            field = value

            contentPane.removeAll()
            contentPane.add(field)
            contentPane.revalidate()
            contentPane.repaint()

            revalidate()
            repaint()

            field.requestFocusInWindow()
        }

    constructor() : super("GameWindow") {
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        extendedState = MAXIMIZED_BOTH
        isVisible = true

        contentPane.add(NonePanel(this))

        addComponentListener(object : ComponentAdapter() {

            override fun componentResized(e: ComponentEvent) {
                revalidate()
                repaint()
            }
        })

        revalidate()
        repaint()
    }
}