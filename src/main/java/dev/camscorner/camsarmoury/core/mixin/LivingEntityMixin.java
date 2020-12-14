package dev.camscorner.camsarmoury.core.mixin;

import dev.camscorner.camsarmoury.common.items.SpearItem;
import dev.camscorner.camsarmoury.core.util.VelocityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity
{
	public LivingEntityMixin(EntityType<?> type, World world) { super(type, world); }

	@Inject(method = "applyDamage", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F", ordinal = 0))
	public void applyDamage(DamageSource source, float amount, CallbackInfo info)
	{
		if(!world.isClient())
		{
			if(source.getAttacker() instanceof PlayerEntity && source.getAttacker() instanceof VelocityUtils &&
					((PlayerEntity) source.getAttacker()).getStackInHand(Hand.MAIN_HAND).getItem() instanceof SpearItem)
			{
				PlayerEntity attacker = (PlayerEntity) source.getAttacker();
				float damangeMult = MathHelper.clamp(((VelocityUtils) attacker).getSqVelocity(), 1F, 10);

				System.out.println(((VelocityUtils) attacker).getSqVelocity());
				System.out.println(damangeMult);
				amount *= damangeMult;
				System.out.println(amount);
			}
		}
	}
}
