package dev.camscorner.camsarmoury.core.mixin;

import dev.camscorner.camsarmoury.common.items.SpearItem;
import dev.camscorner.camsarmoury.core.network.client.SquaredVelocityMessage;
import dev.camscorner.camsarmoury.core.util.VelocityUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements VelocityUtils
{
	private float sqVelocity;

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) { super(entityType, world); }

	@Inject(method = "tick", at = @At("TAIL"))
	public void tick(CallbackInfo info)
	{
		if(getStackInHand(Hand.MAIN_HAND).getItem() instanceof SpearItem)
		{
			if(world.isClient())
			{
				if(hasVehicle())
					SquaredVelocityMessage.send((float) getVehicle().getVelocity().lengthSquared() * 80);
				else
					SquaredVelocityMessage.send((float) getVelocity().lengthSquared() * 80);
			}
		}
	}

	@Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
	public void readNbt(CompoundTag tag, CallbackInfo info)
	{
		this.sqVelocity = tag.getFloat("SqVelocity");
	}

	@Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
	public void writeNbt(CompoundTag tag, CallbackInfo info)
	{
		tag.putFloat("SqVelocity", this.sqVelocity);
	}

	@Override
	public float getSqVelocity()
	{
		return this.sqVelocity;
	}

	@Override
	public void setSqVelocity(float sqVelocity)
	{
		this.sqVelocity = sqVelocity;
	}
}
