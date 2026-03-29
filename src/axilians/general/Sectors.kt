package axilians.khimeris

import mindustry.content.*
import mindustry.type.*
import mindustry.game.*
import mindustry.content.TechTree.*
import arc.struct.Seq
import mindustry.maps.planet.SerpuloPlanetGenerator

object ArxSectors {

    lateinit var hextox: SectorPreset

    fun load() {
        //Ссылка // internal name // твоя планета // номер сектора
            hextox = SectorPreset("hextox", ArxPlanets.Khimeris, 1).apply {
            alwaysUnlocked = true
            addStartingItems = true
            captureWave = 3
            difficulty = 4f
            overrideLaunchDefaults = true
            noLighting = false
            }
    }
}
