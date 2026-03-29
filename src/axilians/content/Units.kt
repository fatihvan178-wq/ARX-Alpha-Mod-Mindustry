package axilians.khimeris

import arc.graphics.*
import arc.graphics.g2d.*
import arc.math.*
import arc.math.geom.*
import arc.struct.*
import arc.util.*
import mindustry.ai.*
import mindustry.ai.types.*
import mindustry.entities.*
import mindustry.entities.abilities.*
import mindustry.entities.bullet.*
import mindustry.entities.effect.*
import mindustry.entities.part.*
import mindustry.entities.pattern.*
import mindustry.gen.*
import mindustry.graphics.*
import mindustry.type.*
import mindustry.type.ammo.*
import mindustry.type.unit.*
import mindustry.type.weapons.*
import mindustry.world.meta.*
import arc.graphics.g2d.Draw.*
import arc.graphics.g2d.Lines.*
import arc.math.Angles.*
import mindustry.Vars.*
import arc.func.Prov

import arc.graphics.Color
import mindustry.content.Fx

import arc.math.Interp;
import arc.math.Mathf;
import arc.Core
import mindustry.ai.UnitCommand;
import mindustry.ai.types.*;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.SeqEffect;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.DrawPart.PartProgress
import mindustry.entities.pattern.ShootHelix;
import mindustry.entities.pattern.ShootSpread;
import mindustry.entities.units.WeaponMount
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;

object ArxUnits {


    lateinit var arxor: UnitType
    lateinit var arsi: UnitType
    lateinit var adrager : UnitType
    lateinit var aruxTail: UnitType
    lateinit var arux: UnitType
    lateinit var nivaladen : UnitType

    fun load() {

        arxor = UnitType("arxor").apply {
            constructor = Prov { LegsUnit.create() }

            outlines = true
            outlineColor = Color.valueOf("201e1eff")
            outlineRadius = 3

            speed = 1.2f;
            drag = 0.09f;
            hitSize = 8f;
            rotateSpeed = 2.0f;
            health = 1920f;
            armor = 1f;
            legStraightness = 0.2f;
            baseLegStraightness = 1f
            stepShake = 0.3f;
            stepSoundVolume = 0.1f

            legSpeed = 0.2f
            legCount = 6;
            legLength = 10f;
            legGroupSize = 3
            lockLegBase = true; // Поворачивается ли базовый слой корпуса если да то и ноги поворачиваются вслед корпуса
            legContinuousMove = true; // Ноги выравниваются при остановке корпуса

            legExtension = 1f; // Как далеко ступня заезжает на ноги
            legBaseOffset = 0f; // Как далкео нога отьезжает от тела

            legMaxLength = 1f;
            legMinLength = 1f;

            legLengthScl = 1f;          // чем ниже тем сильнее основание стремится к точке назначения 0-1F
            legForwardScl = 1f; // Насколько далеко нога наступает вперёд когда делает шаг

            rippleScale = 0.5f;
            legPairOffset = 0.0f

            legMoveSpace = 1.2f; // Каждая нога сместившись от своей базовой точки делает новый шаг, чем ето значение выше тем нога дольше ждёт перед шагом
            allowLegStep = true;
            legPhysicsLayer = false;

            shadowElevation = 0.1f;
            groundLayer = Layer.legUnit - 1f;
            targetAir = false;
            researchCostMultiplier = 0f;

            mineTier = 3
            mineFloor = true
            mineWalls = true
        }

//        adrager  = UnitType("adrager").apply {
//            constructor = Prov { MechUnit.create() }
//
//            hideDatabase = true
//
//            flying = true
//            canBoost = true
//            outlines = true
//            outlineColor = Color.valueOf("201e1eff")
//            outlineRadius = 3
//
//            speed = 0.5f;
//            hitSize = 8f;
//            health = 1920f;
//
//            mineTier = 3
//            mineFloor = true
//            mineWalls = true
//
//            abilities.add(ArxAccumulatorAbility())
//        }.also {
//            // при создании юнита сразу вешаем эффект
//            it.constructor = Prov {
//                val unit = MechUnit.create()
//                unit.apply(ArxStatusEffects.ignition, 9999f)
//                unit.apply(StatusEffects.tarred, 9999f)
//                unit
//            }}


        arsi = UnitType("arsi").apply {
            constructor = Prov { ArxFlyingUnit() }

            outlines = true
            outlineColor = Color.valueOf("201e1eff")
            outlineRadius = 3

            flying       = true
            lowAltitude  = false
            health       = 320f
            armor        = 4f
            hitSize      = 8f

            // --- Движение ---
            speed        = 2.2f
            rotateSpeed  = 7f
            drag         = 0.08f
            accel        = 0.6f
            wobble       = true
            omniMovement = true

            // --- Визуал ---
            engineOffset  = 6.5f
            engineSize    = 2.8f
            trailLength   = 14
            lightRadius   = 60f
            lightOpacity  = 0.55f

            // --- Бой ---
            targetAir    = true
            targetGround = true
            faceTarget   = true

            weapons.add(object : Weapon("arx-khimeris-arsi-weapon") {
                init {
                    x         = -4.75f
                    y         = -4.75f
                    showStatSprite = false
                    mirror    = true
                    alternate = true
                    reload    = 7f
                    recoil    = 2f
                    recoilTime = 10f
                    recoilPow  = 1f
                    shake      = 1f
                    inaccuracy = 3f
                    rotate        = false
                    rotateSpeed   = 9f
                    rotationLimit = 45f
                    shootSound = Sounds.shoot

                    bullet = object : BasicBulletType(4.5f, 28f) {
                        override fun draw(b: Bullet) {
                            // подменяем цвета на team color перед стандартным draw
                            backColor  = b.team.color
                            trailColor = b.team.color.cpy().mul(0.7f)
                            super.draw(b)
                        }}.apply {
                        width         = 7f
                        height        = 12f
                        lifetime      = 42f
                        homingPower   = 0.04f
                        homingRange   = 80f
                        trailLength   = 6
                        trailWidth    = 2.2f
                        // начальные значения — перезапишутся в draw
                        trailColor    = Color.valueOf("a9d8ff")
                        backColor     = Color.valueOf("5599ff")
                        frontColor    = Color.white
                        hitEffect     = Fx.hitBulletSmall
                        despawnEffect = Fx.hitBulletSmall
                    }
                }
                   // parts.add(RegionPart("-blade").apply {
                   //     // суффикс: загрузит спрайт "arsi-weapon-blade"
                   //     progress  = PartProgress.recoil          // зависит от отдачи
                   //     moveY     = 2f                          // сдвигается на -2 по Y при recoil=1
                   //     moveX     = -6f
                   //     x         = 0f
                   //     y         = 0f                           // Базовая позиция отностильно оружия
                   //     mirror    = false                         // Зеркалить ли, если оружие уже зеркальное то не надо
                   //     outline   = true                         // Обводка
                   //     under     = true                        // Если да то отрисовывать в самом низу
                   // })

                override fun shoot(unit: mindustry.gen.Unit, mount: WeaponMount, shootX: Float, shootY: Float, rotation: Float) {
                    super.shoot(unit, mount, shootX, shootY, rotation)
                    if (otherSide != -1 && otherSide < unit.mounts.size) {
                        unit.mounts[otherSide].recoil = 0f
                    }
                }
            })

            parts.add(RegionPart("-glow").apply {
                // color = Color.white.cpy().a(0f)  // альфа 0.5 = 50% интенсивность
                color   = Color(1f, 1f, 1f, 1f)
                colorTo = Color(0f, 0f, 0f, 0f)  // от белого к прозрачному
                progress = PartProgress.warmup    // меняется по прогрессу разогрева
                blending = Blending.additive
                outline = false
                mirror = false
            })
        }

        nivaladen = object : UnitType("nivaladen") {
            override fun setStats() {
                super.setStats()
                stats.remove(Stat.canBoost)
                stats.remove(Stat.flying)
                stats.remove(Stat.abilities)
                stats.remove(Stat.boostingSpeed)
             }

            override fun createIcons(packer: mindustry.graphics.MultiPacker) {  // Переопределяем метод генерации спрайтов юнита. Вызывается один раз при загрузке игры.
                super.createIcons(packer)                                       // Сначала вызываем оригинальный метод — он генерирует однопиксельные кадры. Мы их потом перезапишем.

                val pix = Core.atlas.getPixmap(treadRegion)                     // Берём полный спрайт гусеницы my-tank-treads.png как массив пикселей.
                for (r in treadRects.indices) {                                 //Перебираем каждый прямоугольник гусеницы из treadRects.
                    val rect = treadRects[r]
                    // Переводим координаты из системы "от центра спрайта" в систему "от левого верхнего угла" — так работает getPixmap.
                    val rx = (rect.x + pix.width / 2f).toInt()
                    val ry = (rect.y + pix.height / 2f).toInt()
                    // Ширина и высота прямоугольника гусеницы в пикселях.
                    val rw = rect.width.toInt()
                    val rh = rect.height.toInt()

                    for (i in 0 until treadFrames) {                    // Перебираем каждый кадр анимации — от 0 до treadFrames - 1.
                        val frame = arc.graphics.Pixmap(rw, rh)         // Создаём новый пустой холст размером с прямоугольник гусеницы — сюда запишем один кадр.

                        for (fy in 0 until rh) {                        // Перебираем каждый пиксель кадра по Y и X.
                            for (fx in 0 until rw) {
                                // Считаем откуда взять пиксель из оригинального спрайта:
                                    //fy — текущая строка пикселей
                                    //i * rh / treadFrames — сдвиг для этого кадра (чем больше i, тем больше сдвиг)
                                    //+ treadPullOffset — коррекция шва
                                    //% rh — зацикливаем чтобы не выйти за пределы спрайта
                                    // val srcY = (fy + i * rh / treadFrames + treadPullOffset) % rh // чем длинее гусеница тем быстрее её анимация
                                    //val srcY = (fy + i + treadPullOffset) % rh  // 1px за кадр для любой высоты - одинаковая скоровть любой гусеницы
                                    val srcY = (fy % 18 + i + treadPullOffset) % 18 // 18рх - патерн

                                // Копируем пиксель из оригинального спрайта в новый кадр. rx + fx и ry + srcY — абсолютные координаты в исходном спрайте.
                                frame.set(fx, fy, pix.get(rx + fx, ry + srcY))
                            }
                        }
                        // Сохраняем кадр в атлас под именем например my-tank-treads0-5. Перезаписывает однопиксельный кадр который сгенерировал super.
                        packer.add(mindustry.graphics.MultiPacker.PageType.main, "$name-treads$r-$i", frame)

                        // Освобождаем память — кадр уже сохранён в атласе, объект больше не нужен.
                        frame.dispose()
                    }
                }
             }

            override fun <T> drawTank(unit: T) where T : mindustry.gen.Unit, T : mindustry.gen.Tankc {
                applyColor(unit)                                                    // Применяет цвет команды к юниту — после этого все Draw.rect будут окрашены в цвет команды.
                Draw.rect(treadRegion, unit.x, unit.y, unit.rotation - 90f)         // Рисует статичный спрайт my-tank-treads.png целиком — это подложка под анимированные гусеницы.

                if (treadRegion.found()) {                                          // Проверяем что спрайт гусениц вообще существует.
                    val frame = (unit.treadTime() % treadFrames).toInt()            // Вычисляем текущий кадр анимации. treadTime растёт пока танк движется, % treadFrames зацикливает от 0 до 35.
                    for (i in treadRects.indices) {                                 // Перебираем каждый прямоугольник гусеницы из массива treadRects.
                        val region = treadRegions[i][frame]                         // Берём нужный кадр для этого ректа — спрайт my-tank-treads{i}-{frame}.
                        val treadRect = treadRects[i]                               //
                        val xOffset = (treadRect.x + treadRect.width / 2f)          // Вычисляем центр ректа относительно центра спрайта. Y инвертирован потому что в системе изображения Y растёт вниз, а в мире — вверх.
                        val yOffset = -(treadRect.y + treadRect.height / 2f)        //

                        Tmp.v1.set(xOffset, yOffset).rotate(unit.rotation - 90f)    // Помещаем смещение в вектор и поворачиваем его вместе с юнитом — так гусеница всегда находится на правильном месте независимо от направления танка.
                        Draw.rect(                                                  // Рисуем кадр гусеницы. Делим на 4 потому что спрайты хранятся в 4x масштабе относительно мировых координат. Последний параметр — поворот совпадает с поворотом танка.
                            region,                                                     // Какой спрайт рисовать
                            unit.x + Tmp.v1.x / 4f,                                     // X позиция в мире
                            unit.y + Tmp.v1.y / 4f,                                     // Y позиция в мире
                            treadRect.width / 4f,                                       // Ширина в мировых единицах
                            region.height * region.scale / 4f,                          // Высота в мировых единицах — реальная высота спрайта с учётом масштаба атласа.
                            unit.rotation - 90f                                         // Угол поворота — поворот на -90° потому что спрайты нарисованы смотрящими вверх (90°), а юнит по умолчанию смотрит вправо (0°).
                        )
                    }
                }
             }

         }.apply {
            // Тип юнита — обязательно для TankUnit
            constructor = Prov { ArxTankUnit() }

            outlines = true
            outlineColor = Color.valueOf("201e1eff")
            outlineRadius = 3

            envDisabled = Env.none  // ← вот это! убирает запрет на scorching
            envEnabled  = Env.any   // ← и это, чтобы работал в любой среде

            canBoost = true
            omniMovement = false
            rotateMoveFirst = true

            shadowElevation = 0.1f;
            engineSize = 0f
            treadPullOffset = 0; // сдвигает точку сшва, для спрайтов с неровными гусеницами
            treadFrames = 18 //18 дефолт - скорость анимации гусеницы, при длине 36 делим на 18, получаем сдвиг 2px за кадр

            health = 5000f;
            armor = 11f;
            hitSize = 16f;

            speed = 1f;
            rotateSpeed = 2f;

            floorMultiplier = 0.65f;
            drownTimeMultiplier = 1.3f // Множитель времени до утопания в глубокой жидкости. 1.2f (у тебя) — тонет на 20% медленнее

            itemCapacity = 0;
            immunities.addAll(StatusEffects.burning, StatusEffects.melting);
            crushFragile = true;
            researchCostMultiplier = 0f;

            // Rect [Отступ px. от края] - [Половина px. от спрайта] (Сначала для X потом для Y)
            // Это мы задали точку откуда далее указуется Ширина и Длина трака
            treadRects = arrayOf(
                Rect(12 - 48f,  8 - 48f, 24f, 36f),
                Rect(60 - 48f, 8 - 48f, 24f, 36f),
                Rect(12 - 48f, 65 - 48f, 24f, 27f),
                Rect(60 - 48f, 65 - 48f, 24f, 27f)
            )


            weapons.add(Weapon("arx-khimeris-tank-weapon").apply {
                rotate = true
                rotateSpeed = 3f
                x = 0f     //-5f   // влево от центра
                y = 3.4f     //-8f   // назад (вниз в координатах юнита)
                reload = 60f
                recoil = 2f
                mirror    = false
                alternate = true
                bullet = BasicBulletType(3f, 20f).apply {
                    width = 8f
                    height = 11f
                    lifetime = 60f
                }
            })

            abilities.add(object : Ability() {
                init {
                    display = false  // скрывает абилку из UI
                }

                override fun update(unit: mindustry.gen.Unit) {
                    unit.elevation = 0f
                }
            })
        }
    }
}
