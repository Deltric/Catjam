package dev.deltric.catjam.config

import com.google.gson.Gson
import dev.deltric.catjam.command.RegisteredCommand
import net.dv8tion.jda.api.entities.Activity
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class CatjamConfig(
    val botToken: String,
    val activity: Pair<Activity.ActivityType, String>,
    var commands: MutableMap<Long, RegisteredCommand>
) {

    /**
     * Saves this config instance
     * @param gson - gson serializer
     */
    fun save(gson: Gson) {
        val configFile = File("./config.json")
        val fileWriter = FileWriter(configFile)
        gson.toJson(this, fileWriter)
        fileWriter.flush()
        fileWriter.close()
    }

    companion object {
        /**
         * Handles the loading of the config and default config generation.
         * @param gson - Gson instance
         * @return catjam config or null if the config failed to load.
         */
        fun load(gson: Gson): CatjamConfig? {
            val configFile = File("./config.json")

            // Generate default config if it doesn't exist.
            if (!configFile.exists()) {
                val fileWriter = FileWriter(configFile)
                gson.toJson(
                    CatjamConfig(
                        botToken = "SECRET_TOKEN",
                        activity = Pair(Activity.ActivityType.LISTENING, "bangers"),
                        mutableMapOf()
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
    }

}