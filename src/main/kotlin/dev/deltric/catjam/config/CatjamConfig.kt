package dev.deltric.catjam.config

import net.dv8tion.jda.api.entities.Activity

data class CatjamConfig(
    val botToken: String,
    val activity: Pair<Activity.ActivityType, String>
)