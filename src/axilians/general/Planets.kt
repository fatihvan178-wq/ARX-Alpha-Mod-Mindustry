package axilians.khimeris

import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;

import mindustry.content.Planets

import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.*;// если нужен PlanetGenerator
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.environment.*

object ArxPlanets {     // Список планет

    // Ниже присваиваем ссылке параметры
    lateinit var Khimeris: Planet

    // Загружаем те самые параметры
    fun load() {
        Khimeris = Planet("khimeris", Planets.sun, 1f, 3).apply {
            iconColor = Color.valueOf("2e1f1a");
            loadPlanetData = true
            generator = ArxAlterraGenerator()

            // Основной меш планеты
            meshLoader = Prov<GenericMesh> { HexMesh(this@apply, 6) }

            // Облака и небо (MultiMesh из HexSkyMesh)
            cloudMeshLoader = Prov<GenericMesh> {
                MultiMesh(
                    HexSkyMesh(
                        this@apply, 5, 0.15f, 0.13f, 5,
                        Color.valueOf("ffffff").a(0.5f), 2, 0.45f, 0.9f, 0.38f
                    ),
                    HexSkyMesh(
                        this@apply, 2, 0.6f, 0.16f, 5,
                        Color.white.cpy().a(0.75f), 2, 0.45f, 1f, 0.41f
                    )
                )
            }

            // Атмосфера
            hasAtmosphere = true
            atmosphereColor = Color.valueOf("2e1f1a").a(0.5f)

            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.25f;

            // Цвет облаков на поверхности
            landCloudColor = Color.valueOf("ffffff").a(0.5f)

            // Стартовый сектор
            startSector = 1
            alwaysUnlocked = true

            // Разные флаги как у Serpulo
            allowSelfSectorLaunch = true
            allowLaunchSchematics = true
            allowWaves = true
            allowLaunchLoadout = true
        }
    }
}
