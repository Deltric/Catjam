package dev.deltric.catjam

import com.google.gson.Gson
import dev.deltric.catjam.config.CatjamConfig
import net.dv8tion.jda.api.JDA

class Catjam(
    private val jda: JDA,
    private val config: CatjamConfig,
    private val gson: Gson
) {
    init {

    }
}