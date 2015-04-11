public void createSphere(Location center, Material block, int radius, boolean hollow){
			List<Location> blocks = new ArrayList<>();
			int bX = center.getBlockX();
			int bY = center.getBlockY();
			int bZ = center.getBlockZ();
			
			for(int x = bX - radius; x <= bX + radius; x++){
				for(int y = bY - radius; y <= bY + radius; y++){
					for(int z = bZ - radius; z <= bZ + radius; z++){
						double distance = ((bX-x)*(bX-x)+((bZ-z)*(bZ-z)) + ((bY-y)*(bY-y)));
						
						if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))){
							Location l = new Location(center.getWorld(), x, y, z);
							blocks.add(l);
						}
					}
				}
			}
			
			for(Location loc : blocks){
				loc.getBlock().setType(block);
			}
		}
