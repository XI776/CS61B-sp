package Lab9;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import javax.naming.ldap.Rdn;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;


/**
 * Draws a world initially full of trees.
 */
public class Task4 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int TILE_SIZE = 15;
    private static TERenderer renderer;
    private static Long SEED;
    private static Random rand;
    private static int cnt = 0;
    private static final String FILE = "data.txt";
    private static StringBuilder seriesOp;
    private static TETile[][] world;
    private static Stack<TETile[][]> historyStack = new Stack<>();  // 历史记录栈
    public Task4() {
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
        SEED = System.currentTimeMillis(); // 使用当前时间作为种子
        rand = new Random(SEED);
        seriesOp = new StringBuilder();
        renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][TILE_SIZE];
        initWorld();
//        fillWithTrees(world);

//        renderer.renderFrame(world);
//        StdDraw.textLeft(1, 17, "Number of squares: " + cnt);
//        StdDraw.show();
//        StdDraw.pause(200);
        show();
        while(true){

            drawDiffBlocks();

        }

    }
    private static void show() {
        renderer.renderFrame(world);
        // 设置文字颜色为白色
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, 17, "Number of squares: " + cnt);  // 绘制文字
        StdDraw.show();  // 更新画布
    }
    private static void drawSquare( int startX, int startY, int size, TETile tile) {
        int endX = Math.min((startX + size), world.length);
        int endY = Math.min((startY + size), world[0].length);
        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                world[x][y] = tile;
            }
        }
    }
    private static void addRandomSquare() {

        int startX = rand.nextInt(WIDTH);
        int startY = rand.nextInt(HEIGHT);
        int size = RandomUtils.uniform(rand, 3, 8);
        TETile tile = randomTile(rand);
        saveWorldState();
        drawSquare(startX, startY, size, tile);
    }

    private static TETile randomTile(Random rand) {
        int tileNum = rand.nextInt(3);
        int t1 = rand.nextInt(3);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            default -> Tileset.WATER;
        };
    }

    public static void drawDiffBlocks() {
        char c;
        while(StdDraw.hasNextKeyTyped()) {
            c = StdDraw.nextKeyTyped();
            c = Character.toLowerCase(c);
            if(c == 'n' || c == 's' || c == 'l') {
                seriesOp.append(c);
            }
            System.out.println(c);
            choose(c);
            show();
        }

    }
    public static void choose(char c) {
        switch (c) {
            case 'n':
                addRandomSquare();
                cnt++;
                break;
            case 's':
                saveToFile();
                break;
            case 'l':
                loadFromFile();
                break;
            case 'd':
                deleteFromWorld();
                break;
            case 'q':
                System.exit(0); // Closes the game window and quits the game.
                return;
            default:
                break;
        }
    }
    public static void loadFromFile() {
        boolean firstLine = true;
        try {
            In in = new In(FILE);
            String operations = "";
            while(in.hasNextLine()) {
                if(firstLine) {
                    String line = in.readLine();
                    SEED = Long.valueOf(line);
                    rand = new Random(SEED);
                    firstLine = false;
                } else {
                    operations = in.readLine();
                }
            }
            cnt = 0;
            initWorld();
            seriesOp.delete(0, seriesOp.length());
            seriesOp.append(operations);
            historyStack.clear();

            for(int i = 0; i < operations.length(); i++) {
                choose(operations.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void initWorld() {
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < TILE_SIZE; y++){
                world[x][y] = Tileset.NOTHING;
            }
        }
        fillWithTrees(world);
    }
    public static void deleteFromWorld() {
        if(historyStack.empty()) {
            return;
        }
        world = historyStack.pop();

    }


    public static void saveWorldState() {
        // Create a new 2D array with the same dimensions
        TETile[][] currentWorldCopy = new TETile[world.length][world[0].length];

        // Iterate through the existing 'world' and copy each element
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                currentWorldCopy[i][j] = world[i][j]; // Assuming TETile is immutable or you need to copy its contents too
                // If TETile is a custom object and mutable, you might need a deep copy of TETile as well!
            }
        }
        historyStack.push(currentWorldCopy); // Push the *copy* onto the stack
    }
    public static void saveToFile()  {
        try {
            Out out1 = new Out(FILE);
            out1.println(SEED);
            out1.println(seriesOp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}