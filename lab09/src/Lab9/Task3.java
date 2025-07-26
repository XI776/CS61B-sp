package Lab9;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import javax.naming.ldap.Rdn;
import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task3 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int TILE_SIZE = 15;
    private static TERenderer renderer;
    private static final Random rand = new Random();
    private static int cnt = 0;
    public Task3() {
        renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);

    }

    private static void fillWithTrees(TETile[][] world) {
        for(int x = 0; x < world.length; x++){
            for(int y = 0; y < world[0].length; y++){
                world[x][y] = Tileset.TREE;
            }
        }
    }

    public static void main(String[] args) {
        renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][TILE_SIZE];
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < TILE_SIZE; y++){
                world[x][y] = Tileset.NOTHING;
            }
        }
        fillWithTrees(world);

//        renderer.renderFrame(world);
//        StdDraw.textLeft(1, 17, "Number of squares: " + cnt);
//        StdDraw.show();
//        StdDraw.pause(200);
        while(true){
            drawDiffBlocks(world);
            renderer.renderFrame(world);
            // 设置文字颜色为白色
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(1, 17, "Number of squares: " + cnt);  // 绘制文字

            StdDraw.show();  // 更新画布
            StdDraw.pause(200); // 暂停200毫秒
        }

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
    private static void addRandomSquare(TETile[][] world) {
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

    public static void drawDiffBlocks(TETile[][] world) {
        char c;
        while(StdDraw.hasNextKeyTyped()) {
            c = StdDraw.nextKeyTyped();
            c = Character.toLowerCase(c);
            switch (c) {
                case 'n':
                    addRandomSquare(world);
                    cnt++;
                    break;
                case 'q':
//                    System.exit(0); // Closes the game window and quits the game.
                    return;
                default:
                    break;
            }
        }
    }



}