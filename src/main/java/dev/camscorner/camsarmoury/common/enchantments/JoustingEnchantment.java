package dev.camscorner.camsarmoury.common.enchantments;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;

public class JoustingEnchantment extends Enchantment
{
	public JoustingEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes)
	{
		super(weight, type, slotTypes);
	}

	@Override
	public int getMinPower(int level)
	{
		return 5 + level * 8;
	}

	@Override
	public int getMaxPower(int level)
	{
		return getMinPower(level) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 3;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack)
	{
		return super.isAcceptableItem(stack) || stack.getItem() instanceof AxeItem || stack.getItem() instanceof TridentItem;
	}

	@Override
	protected boolean canAccept(Enchantment other)
	{
		return !(other instanceof DamageEnchantment);
	}
}
