package dev.camscorner.camsarmoury.core.util;

public class RayTracer
{
	/*public static void rayTraceEntity(Beam beam, Function<Entity, Boolean> consumer)
	{
		Vec3d start = beam.getStart();
		Vec3d lookVec = beam.getLookVec();
		Vec3d end = beam.getEnd();
		double dist = beam.getDist();
		World world = beam.getWorld();
		PlayerEntity player = beam.getPlayer();
		List<Entity> targets = world.getOtherEntities(player, player.getBoundingBox().expand(lookVec.x * dist, lookVec.y * dist, lookVec.z * dist).expand(1.0D, 1.0D, 1.0D),
				Predicates.and(EntityPredicates.EXCEPT_SPECTATOR, ent -> ent != null && ent.collides()));
		List<Pair<Entity, Double>> hitTargets = new ArrayList<>();

		for(Entity target : targets)
		{
			Box targetBB = target.getBoundingBox().expand(target.getTargetingMargin());

			if(targetBB.contains(start))
			{
				hitTargets.add(Pair.of(target, 0.0));
			}
			else
			{
				HitResult targetResult = targetBB.calculateIntercept(start, end);

				if(targetResult != null)
				{
					double d3 = start.distanceTo(targetResult.getPos());

					if(d3 < dist)
					{
						hitTargets.add(Pair.of(target, d3));
					}
				}
			}
		}

		hitTargets.sort(Comparator.comparing(Pair::getRight));
		hitTargets.stream().filter(pair -> consumer.apply(pair.getLeft())).findFirst();
	}

	//Credits for this go to Zabi
	public static void drawLine(Vec3d start, Vec3d end, World world, Beam beam, double density, ParticleEffect particle)
	{
		double dist = beam.getDist();

		for(double done = 0; done < dist; done += density)
		{
			double alpha = done / dist;
			double x = interpolate(start.x, end.x, alpha);
			double y = interpolate(start.y, end.y, alpha);
			double z = interpolate(start.z, end.z, alpha);

			if(world.isClient())
			{
				world.addParticle(particle, x, y, z, 0, 0, 0);
			}
		}
	}

	private static double interpolate(double start, double end, double alpha)
	{
		return start + (end - start) * alpha;
	}

	public static class Beam
	{
		private World world;
		private PlayerEntity player;
		private double maxDist;
		private Vec3d start;
		private Vec3d lookVec;
		private Vec3d end;
		private double dist;
		private boolean canBreak;

		public Beam(World world, PlayerEntity player, double maxDist, boolean canBreak)
		{
			this.world = world;
			this.player = player;
			this.maxDist = maxDist;
			this.canBreak = canBreak;

			calculate();
		}

		private void calculate()
		{
			start = this.player.getPos().add(0, player.getEyeY(), 0);
			lookVec = this.player.getRotationVector();
			end = start.add(lookVec.x * this.maxDist, lookVec.y * this.maxDist, lookVec.z * this.maxDist);
			HitResult result = this.world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
			dist = this.maxDist;

			if(result != null && result.getType() == HitResult.Type.BLOCK)
			{
				BlockPos pos = new BlockPos(result.getPos());

				if(world.getBlockState(pos).getCollisionShape(world, pos) != VoxelShapes.empty())
				{
					if(canBreak && !world.isClient())
					{
						if(player.canModifyBlocks())
						{
							if(world.getBlockState(pos).getMaterial() == Material.GLASS || world.getBlockState(pos).getMaterial() == Material.ICE)
							{
								world.breakBlock(pos, false);
							}
						}
					}

					dist = result.getPos().distanceTo(start);
					end = start.add(lookVec.x * dist, lookVec.y * dist, lookVec.z * dist);
				}
			}
		}

		public Vec3d getStart()
		{
			return start;
		}

		public Vec3d getLookVec()
		{
			return lookVec;
		}

		public Vec3d getEnd()
		{
			return end;
		}

		public double getDist()
		{
			return dist;
		}

		public World getWorld()
		{
			return world;
		}

		public PlayerEntity getPlayer()
		{
			return player;
		}

		public double getMaxDist()
		{
			return maxDist;
		}
	}*/
}
