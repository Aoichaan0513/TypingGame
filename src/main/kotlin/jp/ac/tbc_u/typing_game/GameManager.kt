package jp.ac.tbc_u.typing_game

import jp.ac.tbc_u.typing_game.panels.EndPanel
import jp.ac.tbc_u.typing_game.panels.InGamePanel
import jp.ac.tbc_u.typing_game.panels.NonePanel
import jp.ac.tbc_u.typing_game.panels.ReadyPanel
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

object GameManager {

    lateinit var scheduledFuture: ScheduledFuture<*>

    lateinit var words: Words
    lateinit var result: GameResult

    var state: GameState = GameState.NONE
        set(value) {
            field = value

            if (value != GameState.READY)
                return
        }

    var isShuffled = true
    var readyTime: Int = 3
    var gameTime: Int = 300

    fun start(words: Words) {
        val window = Main.window

        this.words = words
        result = GameResult.of(
            words.words.let {
                if (isShuffled) it.toList().shuffled().toTypedArray() else it
            }
        )
        state = GameState.READY
        window.content = ReadyPanel(window)

        scheduledFuture = Main.scheduledExecutorService.scheduleAtFixedRate(
            {
                val window = Main.window
                val panel = window.content

                when (state) {
                    GameState.READY -> {
                        if (readyTime < 0) {
                            state = GameState.GAME
                            window.content = InGamePanel(window)
                            return@scheduleAtFixedRate
                        }

                        panel.update()
                        readyTime--
                    }

                    GameState.GAME -> {
                        if (gameTime < 0) {
                            state = GameState.END
                            window.content = EndPanel(window)
                            return@scheduleAtFixedRate
                        }

                        gameTime--
                        panel.update()
                    }

                    GameState.END -> {
                        scheduledFuture.cancel(true)
                    }

                    else -> return@scheduleAtFixedRate
                }
            },
            0,
            1,
            TimeUnit.SECONDS
        )
    }

    fun end() {
        scheduledFuture.cancel(true)
        isShuffled = true
        readyTime = 3
        gameTime = 300
        state = GameState.NONE

        Main.window.content = NonePanel(Main.window)
    }

    enum class GameState {
        NONE,
        READY,
        GAME,
        END
    }
}