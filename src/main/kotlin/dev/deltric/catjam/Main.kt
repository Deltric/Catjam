package dev.deltric.catjam

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dev.deltric.catjam.config.CatjamConfig
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Activity.ActivityType
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.io.File
import java.io.FileReader
import java.io.FileWriter

fun main() {
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()

    val config = loadConfig(gson) ?: throw Exception("Failed to load the config, check the JSON validity.")
    val builder = JDABuilder.createDefault(config.botToken)
    builder.setActivity(Activity.of(config.activity.first, config.activity.second))
    builder.disableCache(CacheFlag.ACTIVITY)
    builder.setMemberCachePolicy(MemberCachePolicy.VOICE.or(MemberCachePolicy.OWNER))

    val jda = builder.build()
    jda.awaitReady()
    Catjam(
        jda = jda,
        config = config,
        gson = gson
    )
}

/**
 * Handles the loading of the config and default config generation.
 * @param gson - Gson instance
 * @return catjam config or null if the config failed to load.
 */
private fun loadConfig(gson: Gson): CatjamConfig? {
    val configFile = File("./config.json")

    // Generate default config if it doesn't exist.
    if (!configFile.exists()) {
        val fileWriter = FileWriter(configFile)
        gson.toJson(
            CatjamConfig(
                botToken = "SECRET_TOKEN",
                activity = Pair(ActivityType.LISTENING, "to some bops")
            ),
            fileWriter
        )
        fileWriter.flush()
        fileWriter.close()
        throw Exception("Default config has been generated, please set the bot token.")
    }

    // Read the file and returned the parsed config object
    val fileReader = FileReader(configFile)
    val config = gson.fromJson(fileReader, CatjamConfig::class.java)
    fileReader.close()
    return config
}