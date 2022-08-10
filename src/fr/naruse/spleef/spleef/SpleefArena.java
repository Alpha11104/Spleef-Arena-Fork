package fr.naruse.spleef.spleef;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Stack;

public class SpleefArena {
    private int numLevels;
    private int levelSpacing;
    private int maxSize;
    private int maxLevelSize;
    private int minLevelSize;
    private Location location;
    private Stack<SpleefLevel> levels;

    public SpleefArena(Location loc, int numLevels, int levelSpacing, int maxSize, int maxLevelSize, int minLevelSize) {
        this.levels = new Stack<SpleefLevel>();
        this.levelSpacing = levelSpacing;
        this.maxSize = maxSize;
        this.maxLevelSize = maxLevelSize;
        this.minLevelSize = minLevelSize;
        this.numLevels = numLevels;
        this.location = loc;

        generateLevels();

    }
    public void generateLevels() {
        Location currentLocation = this.location;
        levels.push(new SpleefLevel(currentLocation, this.maxSize, Material.PACKED_ICE));
        currentLocation.setY(currentLocation.getBlockY() + 25);
        for(int i = 0; i < this.numLevels; i++) {
            int currentLevelSize = (int) (Math.random() * (maxLevelSize - minLevelSize + 1) + minLevelSize);
            currentLocation.setY(currentLocation.getBlockY() + (this.levelSpacing + 1));
            levels.push(new SpleefLevel(currentLocation, currentLevelSize, Material.SNOW_BLOCK));
        }
    }
    public void buildLevels() {
        for(int i = 0; i < levels.size() - 1; i++) {
            levels.get(i).build();
        }
    }
}
