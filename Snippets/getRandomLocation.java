Location getRandomLocation(Location origin, double radius, boolean _3D){
        Random r = new Random();
        randomRadius = r.nextDouble() * radius;
        theta =  Math.toRadians(r.nextDouble() * 360);
        phi = Math.toRadians(r.nextDouble() * 180 - 90);
 
        double x = randomRadius * Math.cos(theta) * Math.sin(phi);
        double y = randomRadius * Math.sin(theta) * Math.cos(phi);
        double z = randomRadius * Math.cos(phi)
        Location newLoc = origin.add(x, origin.getY(), z)
        if (_3D)
        {
                newLoc.add(0, y, 0);
        }
        return newLoc;
}
