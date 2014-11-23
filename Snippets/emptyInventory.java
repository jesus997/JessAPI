 public static boolean emptyInventory(Player p){
    for(ItemStack item : p.getInventory().getContents()){
        if(item != null)
            if(item.getType() != Material.AIR)
                return false;
    }
    for(ItemStack item : p.getInventory().getArmorContents()){
        if(item != null)
              if(item.getType() != Material.AIR)
                  return false;
    }
    return true;
}
