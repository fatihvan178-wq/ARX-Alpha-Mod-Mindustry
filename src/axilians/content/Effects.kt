package axilians.khimeris

import arc.graphics.Color
import mindustry.type.StatusEffect

open class ArxStatusEffect(name: String) : StatusEffect(name)

object ArxStatusEffects {

    lateinit var ignition: ArxStatusEffect

    fun load() {
        ignition = ArxStatusEffect("ignition").apply {
            color = Color.valueOf("ff6a00")
        }
    }
}
