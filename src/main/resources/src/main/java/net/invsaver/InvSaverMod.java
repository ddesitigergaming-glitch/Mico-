package net.invsaver;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvSaverMod implements ModInitializer {
    public static final String MOD_ID = "invsaver";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[InvSaver] Main Mod Initialized by AI Mind!");
    }
}
