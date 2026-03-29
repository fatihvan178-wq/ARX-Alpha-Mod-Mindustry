package axilians.khimeris

import arc.graphics.Color
import mindustry.type.Item
import mindustry.world.meta.Stat
import mindustry.world.meta.StatCat
import mindustry.world.meta.StatUnit

class ArxItem(name: String, color: Color) : Item(name, color) {
    var toxicity = 0f

    // override fun setStats() {
    //     if (explosiveness > 0f) stats.addPercent(Stat.explosiveness, explosiveness)
    //     if (flammability  > 0f) stats.addPercent(Stat.flammability,  flammability)
    //     if (radioactivity > 0f) stats.addPercent(Stat.radioactivity, radioactivity)
    //     if (charge        > 0f) stats.addPercent(Stat.charge,        charge)
    //     if (toxicity      > 0f) stats.add(ArxItems.toxicityStat, toxicity, StatUnit.none)
    // }

    override fun setStats() {
        super.setStats()
        stats.addPercent(ArxItems.toxicityStat, toxicity)
    }
}

object ArxItems {
    val toxicityStat = Stat("arx-khimeris-toxicity", StatCat.general)

    lateinit var axinium: ArxItem

    fun load() {
        axinium = ArxItem("axinium", Color.valueOf("3a2613")).apply {
            cost = 1.3f
            hardness = 1
            charge = 1.3f
            radioactivity = 0.5f
            flammability = 0.0f
            explosiveness = 0.0f
            toxicity = 2.5f
            alwaysUnlocked = true
        }
    }
}
