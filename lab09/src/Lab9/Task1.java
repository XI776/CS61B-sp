package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world initially full of trees.
 */
public class Task1 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int TILE_SIZE = 15;
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
        ter.renderFrame(world);
    }
}