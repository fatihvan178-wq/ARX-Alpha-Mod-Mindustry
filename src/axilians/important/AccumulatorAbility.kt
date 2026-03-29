package axilians.khimeris

import arc.graphics.Color
import arc.scene.ui.layout.Table
import arc.util.Time
import arc.Core
import mindustry.entities.abilities.Ability
import mindustry.gen.Unit
import mindustry.ui.Bar

class ArxAccumulatorAbility : Ability() {

    var charge = 0f          // текущий заряд
    var maxCharge = 0f       // 20% от макс HP — задаётся в created()
    var regenPerTick = 0f    // базовый реген = 0, изменяется эффектом

    // created() вызывается когда юнит создаётся из пула
    override fun created(unit: Unit) {
        maxCharge = unit.type.health * 0.5f
        charge = 0f
    }

    override fun update(unit: Unit) {
        if (regenPerTick > 0f && charge < maxCharge) {
            charge = minOf(charge + regenPerTick * Time.delta, maxCharge)
        }
    }

    // Бар под полоской HP
    override fun displayBars(unit: Unit, bars: Table) {
        bars.add(Bar(
            //{ "Accumulator: ${charge.toInt()} / ${maxCharge.toInt()}" },
            { Core.bundle.get("ability.arxaccumulator.bar")},
            { Color.valueOf("4fc3f7") },  // голубой
            { if (maxCharge > 0f) charge / maxCharge else 0f }
        )).growX()
        bars.row()
    }

    override fun death(unit: Unit) {
        charge = 0f
        regenPerTick = 0f
    }

    companion object {
        // Безопасно достать AbilityInstance с юнита — null если нет
        fun getFrom(unit: Unit): ArxAccumulatorAbility? {
            val idx = unit.type.abilities.indexOfFirst { it is ArxAccumulatorAbility }
            if (idx < 0) return null
            return unit.abilities[idx] as? ArxAccumulatorAbility
        }
    }
}
