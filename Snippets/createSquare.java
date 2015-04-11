public void createSquare(Location loc, Material block, int radius){
  Location loc = e.getEntity().getLocation();
	World world = e.getEntity().getWorld();
	for (int x = -radius; x < radius; x++) {
		for (int y = -radius; y < radius; y++) {
			for (int z = -radius; z < radius; z++) {
				Block block = world.getBlockAt(loc.getBlockX()+x, loc.getBlockY()+y, loc.getBlockZ()+z);
				block.setType(block);
			}
	  }
	}
}
