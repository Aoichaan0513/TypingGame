package jp.ac.tbc_u.typing_game.panels

import jp.ac.tbc_u.typing_game.GameWindow
import javax.swing.JFrame
import javax.swing.JPanel

abstract class GamePanel(val window: GameWindow) : JPanel() {

    open fun update() {

    }
}