package config

/**
 * Конфигурация телеграм-бота.
 * @param token токен бота
 */
data class BotConfig(val token: String) {
    companion object {
        /**
         * Создает экземпляр конфигурации с использованием переменных окружения.
         * @return экземпляр BotConfig
         */
        fun fromEnv(defaultToken: String? = null): BotConfig {
            val token = EnvConfig.getBotToken(defaultToken)
            return BotConfig(token)
        }
    }
}
