package axilians.khimeris

import arc.Core
import arc.graphics.Blending
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.geom.Vec2
import arc.util.Tmp
import mindustry.gen.TankUnit
import mindustry.gen.UnitEntity
import mindustry.gen.LegsUnit
import mindustry.gen.MechUnit

class ArxTankUnit : TankUnit() {
    override fun draw() {
        super.draw()
        GlowDraw.draw(this)
    }
}

class ArxFlyingUnit : UnitEntity() {
    override fun draw() {
        super.draw()
        GlowDraw.draw(this)
    }
}

class ArxLegsUnit : LegsUnit() {
    override fun draw() {
        super.draw()
        GlowDraw.draw(this)
    }
}

class ArxMechUnit : MechUnit() {
    override fun draw() {
        super.draw()
        GlowDraw.draw(this)
    }
}

object GlowDraw {
    fun draw(unit: mindustry.gen.Unit, warmup: Float = 1f) {
        if (!Core.atlas.has("${unit.type.name}-glow")) return
        val glow = Core.atlas.find("${unit.type.name}-glow")

        Draw.blend(Blending.additive)
        Draw.color(unit.team.color, warmup)
        Draw.rect(glow as TextureRegion, unit.x, unit.y, unit.rotation - 90f)
        Draw.blend()
        Draw.color()
    }
}
