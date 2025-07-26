
package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import javax.naming.ldap.Rdn;
import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task2 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int TILE_SIZE = 15;
//    private static final Random RANDOM = new Random();
    private static void fillWithTrees(TETile[][] world) {
        for(int x = 0; x < world.length; x++){
            for(int y = 0; y < world[0].length; y++){
                world[x][y] = Tileset.TREE;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][TILE_SIZE];
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < TILE_SIZE; y++){
                world[x][y] = Tileset.NOTHING;
            }
        }
        fillWithTrees(world);
//        drawSquare(world, 10, 7, 5, Tileset.FLOWER);
        for(int i = 0; i < 5; i++){
            Random rand = new Random();
            addRandomSquare(world, rand);
        }

        ter.renderFrame(world);
    }
    private static void drawSquare(TETile[][] world, int startX, int startY, int size, TETile tile) {
        int endX = Math.min((startX + size), world.length);
        int endY = Math.min((startY + size), world[0].length);
        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                world[x][y] = tile;
            }
        }
    }
    private static void addRandomSquare(TETile[][] world, Random rand) {
        int startX = rand.nextInt(WIDTH);
        int startY = rand.nextInt(HEIGHT);
        int size = RandomUtils.uniform(rand, 3, 8);
        TETile tile = randomTile(rand);
        drawSquare(world, startX, startY, size, tile);
    }

    private static TETile randomTile(Random rand) {
        int tileNum = rand.nextInt(3);
        return switch (tileNum) {
             case 0 -> Tileset.WALL;
             case 1 -> Tileset.FLOWER;
             default -> Tileset.WATER;
        };
    }


}