package jp.ac.tbc_u.typing_game

import jp.ac.tbc_u.typing_game.panels.InGamePanel
import jp.ac.tbc_u.typing_game.panels.NonePanel
import jp.ac.tbc_u.typing_game.panels.ReadyPanel
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

object GameManager {

    lateinit var scheduledFuture: ScheduledFuture<*>

    var state: GameState = GameState.NONE
        set(value) {
            field = value

            if (value != GameState.READY)
                return
        }

    var isShuffled = true
    var readyTime: Int = 3
    var gameTime: Int = 300

    fun start() {
        state = GameState.READY
        scheduledFuture = Main.scheduledExecutorService.scheduleAtFixedRate(
            {
                when (state) {
                    GameState.NONE -> {
                        // Do nothing
                    }

                    GameState.READY -> {
                        val window = Main.window
                        val panel = window.content as ReadyPanel

                        if (readyTime < 0) {
                            state = GameState.GAME
                            window.content = InGamePanel(window, panel.words)
                            return@scheduleAtFixedRate
                        }

                        panel.update()
                        readyTime--
                    }

                    GameState.GAME -> {
                        val window = Main.window
                        val panel = window.content as InGamePanel

                        if (gameTime < 0) {
                            state = GameState.END

                            window.content = NonePanel(window)
                            return@scheduleAtFixedRate
                        }

                        gameTime--
                        panel.update()
                    }

                    GameState.END -> {
                        scheduledFuture.cancel(true)
                    }
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