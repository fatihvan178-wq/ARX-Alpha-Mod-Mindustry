package axilians.khimeris

import arc.*
import arc.util.*
import mindustry.mod.*
import mindustry.content.StatusEffects

// Наследование от Mod - это основной класс. Mindustry автоматически создаёт его экземпляр при загрузке мода.
class Main : Mod(){

    init{Log.info("Loaded MainKotlin constructor.")
    }
    override fun init() {
        ArxUtilities.registerIcons()
        ArxUtilities.modinfo()
        ArxUtilities.settings()
        ArxUtilities.translate()
        ArxUtilities.teamSlot()
        ArxUtilities.teamIcons()
    }

    override fun loadContent() {
        ArxPlanets.load()
        ArxSectors.load()
        ArxOthers.load()
        ArxUtilities.load()
        ArxStatusEffects.load()
        ArxItems.load()
        ArxUnits.load()
        ArxBlocks.load()
        ArxKhimerisTechTree.load()
        StatusEffects.burning.damage = 10f / 60f
    }

}
