package axilians.khimeris

import arc.graphics.*
import arc.math.*
import arc.struct.*
import mindustry.*
import mindustry.entities.*
import mindustry.entities.abilities.*
import mindustry.entities.bullet.*
import mindustry.entities.effect.*
import mindustry.entities.part.DrawPart.*
import mindustry.entities.part.*
import mindustry.entities.pattern.*
import mindustry.game.*
import mindustry.gen.*
import mindustry.graphics.*
import mindustry.type.*
import mindustry.type.unit.*
import mindustry.world.*
import mindustry.world.blocks.*
import mindustry.world.blocks.campaign.*
import mindustry.world.blocks.defense.*
import mindustry.world.blocks.defense.turrets.*
import mindustry.world.blocks.distribution.*
import mindustry.world.blocks.environment.*
import mindustry.world.blocks.heat.*
import mindustry.world.blocks.legacy.*
import mindustry.world.blocks.liquid.*
import mindustry.world.blocks.logic.*
import mindustry.world.blocks.payloads.*
import mindustry.world.blocks.power.*
import mindustry.world.blocks.production.*
import mindustry.world.blocks.sandbox.*
import mindustry.world.blocks.storage.*
import mindustry.world.blocks.units.*
import mindustry.world.consumers.*
import mindustry.world.draw.*
import mindustry.world.meta.*

import mindustry.world.blocks.environment.OreBlock
import mindustry.world.blocks.defense.Wall

import mindustry.content.UnitTypes.*

import mindustry.Vars.*
import mindustry.type.ItemStack.*


object ArxBlocks {
    lateinit var aroxus: CoreBlock
    lateinit var OreAxinium: OreBlock
    lateinit var wallOreAxinium: OreBlock

    fun load() {
        OreAxinium = OreBlock("ore-axinium", ArxItems.axinium)

        wallOreAxinium = OreBlock("ore-wall-axinium", ArxItems.axinium).apply {
            wallOre = true
        }

        aroxus = CoreBlock("core-aroxus").apply {
            // Требования для постройки
            requirements(Category.effect, BuildVisibility.coreZoneOnly, with(ArxItems.axinium, 5000))

            // Тип ядра
            isFirstTier = true
            requiresCoreZone = true
            alwaysUnlocked = true

            // Юнит игрока
            unitType = ArxUnits.nivaladen

            // Характеристики
            health = 10000
            armor = 10f
            size = 5 // 1 = 32 px
            itemCapacity = 10000

            // Лимит юнитов и множители
            unitCapModifier = 20
            buildCostMultiplier = 1.0f
            researchCostMultiplier = 0.1f
            researchCostMultipliers.put(ArxItems.axinium, 0.5f)

            // Поведение
            incinerateNonBuildable = true

            // Визуал посадки
            thrusterLength = 48 / 4f
        }
    }
}
