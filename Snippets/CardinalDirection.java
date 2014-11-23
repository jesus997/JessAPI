public String getCardinalDirection(Player player) throws NullPointerException  {
    double rotation = (player.getLocation().getYaw() - 90) % 360;
    if (rotation < 0)
        rotation += 360.0;
    if (0 <= rotation && rotation < 67.5)
        return "NW"; //NorthWest
    else if (67.5 <= rotation && rotation < 112.5)
        return "N"; //North
    else if (112.5 <= rotation && rotation < 157.5)
        return "NE"; //NorthEeast
    else if (157.5 <= rotation && rotation < 202.5)
        return "E"; //East
    else if (202.5 <= rotation && rotation < 247.5)
            return "SE"; //SouthEast
    else if (247.5 <= rotation && rotation < 292.5)
        return "S"; //South
    else if (292.5 <= rotation && rotation < 337.5)
        return "SW"; //SouthWest
    else if (337.5 <= rotation && rotation < 360.0)
       return "W"; //West
    else
       return null;
}
