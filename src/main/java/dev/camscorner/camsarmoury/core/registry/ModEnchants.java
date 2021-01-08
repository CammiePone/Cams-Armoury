package dev.camscorner.camsarmoury.core.registry;

import dev.camscorner.camsarmoury.CamsArmoury;
import dev.camscorner.camsarmoury.common.enchantments.JoustingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;

public class ModEnchants
{
	//----Enchantment Map----//
	public static final LinkedHashMap<Enchantment, Identifier> ENCHANTS = new LinkedHashMap<>();

	public static final Enchantment JOUSTING = create("jousting", new JoustingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

	//-----Registry-----//
	public static void register()
	{
		ENCHANTS.keySet().forEach(enchant -> Registry.register(Registry.ENCHANTMENT, ENCHANTS.get(enchant), enchant));
	}

	private static <T extends Enchantment> T create(String name, T enchant)
	{
		ENCHANTS.put(enchant, new Identifier(CamsArmoury.MOD_ID, name));
		return enchant;
	}
}
