package dev.camscorner.camsarmoury.core.mixin;

import dev.camscorner.camsarmoury.common.enchantments.JoustingEnchantment;
import dev.camscorner.camsarmoury.core.util.VelocityUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity
{
	public LivingEntityMixin(EntityType<?> type, World world) { super(type, world); }

	@ModifyVariable(method = "applyDamage", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F", ordinal = 0))
	public float setAmount(float amount, DamageSource source)
	{
		if(!world.isClient())
		{
			if(source.getAttacker() instanceof PlayerEntity && source.getAttacker() instanceof VelocityUtils)
			{
				PlayerEntity attacker = (PlayerEntity) source.getAttacker();

				for(Enchantment enchant : EnchantmentHelper.get(attacker.getStackInHand(Hand.MAIN_HAND)).keySet())
				{
					float level = EnchantmentHelper.get(attacker.getStackInHand(Hand.MAIN_HAND)).get(enchant);

					if(enchant instanceof JoustingEnchantment)
					{
						float damangeMult = MathHelper.clamp(((VelocityUtils) attacker).getSqVelocity(), 1F, 1F + (level / 2F));

						System.out.println(amount * damangeMult);

						return amount * damangeMult;
					}
				}
			}
		}

		return amount;
	}
}
