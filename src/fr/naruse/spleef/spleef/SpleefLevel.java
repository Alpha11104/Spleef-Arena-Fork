package fr.naruse.spleef.spleef;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SpleefLevel {
    private Location center;
    private int radius;
    private Block[] levelBlocks;
    private Material material;
    public SpleefLevel(Location center, int radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
        this.levelBlocks = generate();
    }

    public Block[] generate() {
        List<Block> blocks = new ArrayList<Block>();

        int centerX = center.getBlockX();
        int centerY = center.getBlockY();
        int centerZ = center.getBlockZ();

        World world = center.getWorld();

        int radiusSquared = radius * radius;

        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                double distance = Math.pow(centerX - x, 2) + Math.pow(centerZ - z, 2);
                if (distance < radiusSquared) {
                    blocks.add(world.getBlockAt(x, centerY, z));
                }
            }
        }

        Block[] blockArray = new Block[blocks.size()];
        blocks.toArray(blockArray);
        return blockArray;
    }

    public void build() {
        for(Block block: this.levelBlocks) {
            block.setType(this.material);
        }
    }

    public void destroy() {
        for(Block block: this.levelBlocks) {
            block.setType(Material.AIR);
        }
    }
}
