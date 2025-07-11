package jp.ac.tbc_u.typing_game

import com.formdev.flatlaf.FlatIntelliJLaf
import java.awt.Color
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.util.concurrent.Executors
import javax.swing.JComponent
import javax.swing.JOptionPane

object Main {

    val BRAND_COLOR = Color(38, 117, 191)

    val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    lateinit var fallbackFont: Font
    lateinit var serifFont: Font
    lateinit var monospaceFont: Font

    lateinit var window: GameWindow

    @JvmStatic
    fun main(args: Array<String>) {
        fallbackFont = Font.createFont(Font.TRUETYPE_FONT, javaClass.classLoader.getResourceAsStream("NotoSansJP.ttf"))
            .deriveFont(Font.BOLD, 24f);
        serifFont =
            Font.createFont(Font.TRUETYPE_FONT, javaClass.classLoader.getResourceAsStream("Serif.ttf")).deriveFont(24f);
        monospaceFont = Font.createFont(Font.TRUETYPE_FONT, javaClass.classLoader.getResourceAsStream("Monospace.ttf"))
            .deriveFont(24f);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(serifFont)
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(monospaceFont)

        FlatIntelliJLaf.setup()

        window = GameWindow()
    }

    fun getOptionPane(component: JComponent?): JOptionPane? {
        if (component is JOptionPane)
            return component

        val parent = component?.parent
        if (parent != null && parent is JComponent)
            return getOptionPane(parent)

        return null
    }
}