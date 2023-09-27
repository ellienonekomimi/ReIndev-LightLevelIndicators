package com.jellied.lightlevelindicator.renderer.entity;

import com.jellied.lightlevelindicator.entity.EntityLightLevelIndicator;
import net.minecraft.src.client.renderer.Tessellator;
import net.minecraft.src.client.renderer.entity.Render;
import net.minecraft.src.game.entity.Entity;
import org.lwjgl.opengl.GL11;

public class RenderLightLevelIndicator extends Render {
    private Tessellator tessellator = Tessellator.instance;
    public void renderIndicator(EntityLightLevelIndicator indicator, double x, double y, double z, float yaw, float pitch) {
        int lightLevel = indicator.getLightLevel();

        int r = 0;
        int g = 0;
        int b = 255;

        if (lightLevel <= 7) {
            r = 255;
            g = 0;
            b = 0;
        }

        loadTexture("/misc/lightindicator/lightlevel" + lightLevel + ".png");

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x - 0.5F, (float) y + 0.0625F, (float) z - 0.5F);

        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(r, g, b, 255);
        tessellator.addVertexWithUV(0, 0, 1, 0, 1);
        tessellator.addVertexWithUV(1, 0, 1, 1, 1);
        tessellator.addVertexWithUV(1, 0, 0, 1, 0);
        tessellator.addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.draw();

        GL11.glPopMatrix();
    }
    @Override
    public void doRender(Entity indicator, double x, double y, double z, float yaw, float pitch) {
        renderIndicator((EntityLightLevelIndicator) indicator, x, y, z, yaw, pitch);
    }
}
