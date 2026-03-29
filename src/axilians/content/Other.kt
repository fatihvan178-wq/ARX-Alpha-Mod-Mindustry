package axilians.khimeris

import mindustry.type.StatusEffect
import mindustry.game.Team
import mindustry.ctype.UnlockableContent
import mindustry.ctype.ContentType
import mindustry.world.meta.Stat
import mindustry.world.meta.StatCat
import mindustry.graphics.Pal
import mindustry.gen.Iconc
import arc.graphics.Color
import arc.Core
import arc.scene.ui.layout.Table
import axilians.khimeris.ArxOthers

open class ArxOther(name: String) : UnlockableContent(name) {
    open var statCat_for_text = ""

    override fun getContentType() = ContentType.typeid_UNUSED

    companion object {
        val statCat_record = StatCat("arx-khimeris-statCat.record")
        val stat_record_1  = Stat("arx-khimeris-stat.record_1", statCat_record)
        val stat_record_2  = Stat("arx-khimeris-stat.record_2", statCat_record)

        val statsCat_overview   = StatCat("arx-khimeris-statCat.overview")
        val stat_overview_1    = Stat("arx-khimeris-stat.overview_1", statsCat_overview)
        val stat_overview_2    = Stat("arx-khimeris-stat.overview_2", statsCat_overview)
    }

    override fun setStats() {
        stats.useCategories = true
    }

    override fun displayExtra(table: Table) {
        val text = Core.bundle.getOrNull("$name.text")
        val extraDetails = Core.bundle.getOrNull("$name.extra-details")

        if (!statCat_for_text.isNullOrEmpty() && !text.isNullOrEmpty()) {
            table.add(statCat_for_text).color(Pal.accent).fillX()
            table.row()
        }

        if (!text.isNullOrEmpty()) {
            table.add("[lightgray]$text").wrap().fillX().padLeft(10f).width(500f).left()
            table.row()
        }

        if (minfo.mod != null && description == null) {
            table.add("[gray]" + Core.bundle.format("mod.display", minfo.mod.meta.displayName)).fillX().padLeft(10f)
            table.row()
        }

        if (extraDetails != null) {
            table.add("[gray]" + if (unlocked() || !hideDetails) "$extraDetails" else "${Iconc.lock} ${Core.bundle.get("unlock.incampaign")}").pad(6f).padTop(20f).width(400f).wrap().fillX()
            table.row()
        }
    }
}

object ArxOthers {

    lateinit var AnkerInfo: ArxOther
    lateinit var AnkerInfo1: ArxOther

    fun load() {

        AnkerInfo = object : ArxOther("anker-info") {
            init {
                alwaysUnlocked = true
                hideDatabase = false
                databaseCategory = "info"
                // databaseTag = "NAME"
                databaseTabs.add(ArxPlanets.Khimeris)
                // Сюда ключь целиком ето для названия блока текста, для самого текста в bundles задаётся (arx-khimeris-anker-info.text) а точнее $name.text
                statCat_for_text = Core.bundle.get("arx-khimeris-key.history")
            }

            override fun setStats() {
                super.setStats()
                // stats.add(stat_record_1, Core.bundle.get("$name.record_1"))
                // stats.add(stat_record_2, Core.bundle.get("$name.record_2"))
            }
        }
    }
}
