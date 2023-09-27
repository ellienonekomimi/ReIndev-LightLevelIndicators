package com.jellied.lightlevelindicator.entity;

import com.jellied.lightlevelindicator.LightLevelIndicatorHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.src.client.renderer.Vec3D;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.nbt.NBTTagCompound;

public class EntityLightLevelIndicator extends Entity {
    protected int lightLevel;
    protected int trueLightLevel;

    protected Vec3D vecPosition;
    protected int assignedX;
    protected int assignedY;
    protected int assignedZ;

    Minecraft minecraft = Minecraft.getInstance();

    public EntityLightLevelIndicator(World world) {
        super(world);
        setSize(1, 0.1F);
    }

    public void setAssignedPosition(int newAssignedX, int newAssignedY, int newAssignedZ) {
        assignedX = newAssignedX;
        assignedY = newAssignedY;
        assignedZ = newAssignedZ;
        vecPosition = new Vec3D(assignedX, assignedY, assignedZ);
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public int getTrueLightLevel() {
        return trueLightLevel;
    }

    public void onUpdate() {
        if (!LightLevelIndicatorHandler.getIsEnabled()) {
            setEntityDead();
            return;
        }

        if (worldObj.isAirBlock(assignedX, assignedY, assignedZ)) {
            setEntityDead();
            // System.out.println("Block destroyed.");
            return;
        }

        if (!worldObj.isAirBlock(assignedX, assignedY + 1, assignedZ)) {
            setEntityDead();
            // System.out.println("Block obstructed.");
            return;
        }

        EntityPlayer localPlr = minecraft.thePlayer;
        if (vecPosition.distanceTo(new Vec3D(localPlr.posX, localPlr.posY, localPlr.posZ)) >= 32) {
            setEntityDead();
            // System.out.println("Entity out of render distance.");
            return;
        }

        lightLevel = worldObj.getBlockLightValue(assignedX, assignedY + 1, assignedZ);
        trueLightLevel = worldObj.getFullBlockLightValue(assignedX, assignedY + 1, assignedZ);
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public boolean isBurning() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    protected void entityInit() {}
    @Override
    protected void readEntityFromNBT(NBTTagCompound var1) {}
    @Override
    protected void writeEntityToNBT(NBTTagCompound var1) {}
}
