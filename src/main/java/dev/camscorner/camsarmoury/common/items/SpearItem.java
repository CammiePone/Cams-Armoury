package dev.camscorner.camsarmoury.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpearItem extends Item
{
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

	public SpearItem(Settings settings)
	{
		super(settings);
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", 3.0D, EntityAttributeModifier.Operation.ADDITION));
		builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", -3.0D, EntityAttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}

	@Override
	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner)
	{
		return !miner.isCreative();
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot)
	{
		return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}
}
