package dev.camscorner.camsarmoury.core.network.client;

import dev.camscorner.camsarmoury.CamsArmoury;
import dev.camscorner.camsarmoury.core.util.VelocityUtils;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class SquaredVelocityMessage
{
	public static final Identifier ID = new Identifier(CamsArmoury.MOD_ID, "squared_velocity");

	public static void send(float sqVelocity)
	{
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeFloat(sqVelocity);
		ClientSidePacketRegistry.INSTANCE.sendToServer(ID, buf);
	}

	public static void handle(PacketContext context, PacketByteBuf buf)
	{
		float sqVelocity = buf.readFloat();

		context.getTaskQueue().submit(new Runnable()
		{
			@Override
			public void run()
			{
				((VelocityUtils) context.getPlayer()).setSqVelocity(sqVelocity);
			}
		});
	}
}
