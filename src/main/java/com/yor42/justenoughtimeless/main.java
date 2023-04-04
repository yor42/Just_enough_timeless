package com.yor42.justenoughtimeless;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import static com.yor42.justenoughtimeless.constants.MODID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MODID)
public class main
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public main() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
