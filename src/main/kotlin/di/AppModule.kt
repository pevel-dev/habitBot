package di

import bot.TelegramBot
import config.BotConfig
import org.kodein.di.*


object AppModule {
    val mainModule = DI.Module("main") {
        bind<BotConfig>() with singleton {
            BotConfig.fromEnv(defaultToken = "token")
        }
        bind<TelegramBot>() with singleton { TelegramBot(di) }
    }

    fun setupDependencyInjection(handlerModules: List<DI.Module>): DI {
        return DI {
            import(mainModule)
            handlerModules.forEach { import(it) }
        }
    }
} 