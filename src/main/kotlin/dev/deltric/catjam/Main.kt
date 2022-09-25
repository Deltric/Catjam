package dev.deltric.catjam

import com.google.gson.GsonBuilder
import dev.deltric.catjam.config.CatjamConfig
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag

fun main() {
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()

    val config = CatjamConfig.load(gson) ?: throw Exception("Failed to load the config, check the JSON validity.")
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