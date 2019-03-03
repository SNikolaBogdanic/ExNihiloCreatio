package exnihilocreatio.config

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import com.electronwill.nightconfig.core.io.WritingMode
import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.MODID
import net.minecraftforge.common.ForgeConfigSpec
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.Logging
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import java.nio.file.Path

@Mod.EventBusSubscriber
object Config {
    val SERVER_BUILDER = ForgeConfigSpec.Builder()
    val CLIENT_BUILDER = ForgeConfigSpec.Builder()

    val SERVER_CONFIG: ForgeConfigSpec
    val CLIENT_CONFIG: ForgeConfigSpec

    val SIEVE_CONFIG: SieveConfig
    val BARREL_CONFIG: BarrelConfig
    val CRUCIBLE_CONFIG : CrucibleConfig
    val TOOL_CONFIG : ToolConfig
    val SILK_CONFIG: SilkConfig

    init {
        SIEVE_CONFIG = SieveConfig(SERVER_BUILDER, CLIENT_BUILDER)
        BARREL_CONFIG = BarrelConfig(SERVER_BUILDER, CLIENT_BUILDER)
        CRUCIBLE_CONFIG = CrucibleConfig(SERVER_BUILDER, CLIENT_BUILDER)
        TOOL_CONFIG = ToolConfig(SERVER_BUILDER, CLIENT_BUILDER)
        SILK_CONFIG = SilkConfig(SERVER_BUILDER, CLIENT_BUILDER)


        SERVER_CONFIG = SERVER_BUILDER.build()
        CLIENT_CONFIG = CLIENT_BUILDER.build()
    }

    fun loadConfig(spec: ForgeConfigSpec, path: Path) {
        ExNihiloCreatio.logger.debug("Loading config file $path")
        val configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build()

        ExNihiloCreatio.logger.debug("Built TOML config for $path")
        configData.load()
        ExNihiloCreatio.logger.debug("Loaded TOML config for $path")
        spec.setConfig(configData)
    }

    @SubscribeEvent
    fun onLoad(event: ModConfig.Loading) {
        ExNihiloCreatio.logger.debug("Loaded $MODID config file ${event.config.fileName}")
    }

    @SubscribeEvent
    fun onFileChange(event: ModConfig.Loading) {
        ExNihiloCreatio.logger.fatal(Logging.CORE, "$MODID config just got changes on the file system!")
    }
}