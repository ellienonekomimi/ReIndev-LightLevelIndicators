package com.jellied.lightlevelindicator;

import com.jellied.lightlevelindicator.entity.EntityLightLevelIndicator;
import net.minecraft.client.Minecraft;
import net.minecraft.src.game.MathHelper;
import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.level.World;

import java.util.HashMap;
import java.util.Map;

public class LightLevelIndicatorHandler {
    private static Map<String, EntityLightLevelIndicator> indicators = new HashMap<>();
    private static boolean isEnabled = false;

    static Minecraft minecraft = Minecraft.getInstance();

    public static boolean getIsEnabled() {
        return isEnabled;
    }

    public static void setIsEnabled(boolean set) {
        isEnabled = set;
    }

    public static void toggleIsEnabled() {
        setIsEnabled(!isEnabled);
    }

    public static void update() {
        World world = minecraft.theWorld;
        EntityPlayer plr = minecraft.thePlayer;
        if (world == null || !isEnabled) {
            indicators.clear();

            return;
        }

        if (plr == null) {
            return;
        }

        for (int x = -16; x < 16; x++) {
            for (int y = -16; y < 16; y++) {
                for (int z = -16; z < 16; z++) {
                    int posX = MathHelper.floor_double(plr.posX) + x;
                    int posY = MathHelper.floor_double(plr.posY) + y;
                    int posZ = MathHelper.floor_double(plr.posZ) + z;

                    int blockIdAt = world.getBlockId(posX, posY, posZ);
                    Block blockAt = Block.blocksList[blockIdAt];
                    int blockIdAbove = world.getBlockId(posX, posY + 1, posZ);
                    Block blockAbove = Block.blocksList[blockIdAbove];

                    if (world.isAirBlock(posX, posY, posZ) || !blockAt.renderAsNormalBlock()) {
                        continue;
                    }

                    if (!world.isAirBlock(posX, posY + 1, posZ) && blockAbove.renderAsNormalBlock()) {
                        continue;
                    }

                    EntityLightLevelIndicator indicator = indicators.get(getMapString(posX, posY, posZ));
                    if (indicator == null || indicator.isDead) {
                        indicator = new EntityLightLevelIndicator(world);
                        indicator.setAssignedPosition(posX, posY, posZ);
                        indicator.setPosition(posX + 0.5, posY + 1, posZ + 0.5);
                        world.entityJoinedWorld(indicator);
                        indicators.put(getMapString(posX, posY, posZ), indicator);
                    }
                }
            }
        }
    }

    private static String getMapString(int x, int y, int z) {
        return x + ", " + y + ", " + z;
    }
}
