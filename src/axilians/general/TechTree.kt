package axilians.khimeris

import arc.struct.Seq
import arc.util.*
import mindustry.content.Blocks
import mindustry.content.Items
import mindustry.game.Objectives.Research
import mindustry.type.Item
import mindustry.type.ItemStack
import mindustry.world.blocks.defense.turrets.Turret

import mindustry.Vars.*
import mindustry.content.TechTree.*

object ArxKhimerisTechTree {

    fun load() {
        // Создаём корень древа привязанный к паднете с именем "NAME", обьект, доступен ли сразу
        ArxPlanets.Khimeris.techTree = nodeRoot("arx-khimeris", ArxBlocks.aroxus, true) {

            // Порядок создания узлов Верхний левой -> Верхний правый -> Нижний левый -> ижний правый
            node(Blocks.mechanicalDrill, ItemStack.with(ArxItems.axinium, 500), Seq.with(Research(ArxItems.axinium))) {
                node(Blocks.impactDrill) {}
            }

            node(ArxItems.axinium) {}
            node(ArxSectors.hextox) {}
        }
    }
}
