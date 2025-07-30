package core;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;
import org.apache.hc.client5.http.impl.Wire;
import org.w3c.dom.html.HTMLDOMImplementation;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class World {
//    public static final Integer WIDTH = 60;
//    public static final Integer HEIGHT = 60;
//    public static final Integer TILE_SIZE = 45;
//    public static TETile[][] world;
//    public static Long SEED;
////    private static Long COUNT;
//    public static Random rand;
    public static TERenderer renderer;
    public StringBuilder operations;
    public static final String FILE = "data.txt";
    public static int cnt = 0;
    WorldCreator map;
    // build your own world!
    public World() {
//        world = new TETile[10][10];
        map = new WorldCreator();
        renderer = new TERenderer();
        renderer.initialize(WorldCreator.WIDTH, WorldCreator.HEIGHT);
    }
    public World(Long SEED) {
//        world = new TETile[10][10];
        map = new WorldCreator(SEED);
        renderer = new TERenderer();
        renderer.initialize(WorldCreator.WIDTH, WorldCreator.HEIGHT);
    }



//    public void initWorld() {
//        world = new TETile[WIDTH][HEIGHT];
//        for(int i = 0; i < WIDTH; i++) {
//            for(int j = 0; j < HEIGHT; j++) {
//                world[i][j] = Tileset.NOTHING;
//            }
//        }
//    }

    public void setSeedAndRand(String line){
        map.SEED = Long.parseLong(line);
        map.rand = new Random(map.SEED);
    }
//    public void drawDiffBlocks() {
//        char c;
//        while(StdDraw.hasNextKeyTyped()) {
//            c = StdDraw.nextKeyTyped();
//            c = Character.toLowerCase(c);
//            if(c == 'n' || c == 's' || c == 'l') {
//                operations.append(c);
//            }
//            System.out.println(c);
////            choose(c);
//            displayGame();
//        }
//
//    }
    public void displayGame() {
        renderer.renderFrame(map.world);
        StdDraw.setPenColor(Color.pink);
        StdDraw.textLeft(0, WorldCreator.TILE_SIZE + 2, "Current state");
        StdDraw.show();
    }
    public void recover() {
        return;

    }
//    public static void choose(char c) {
//        switch (c) {
//            case 'n':
//                addRandomSquare();
//                cnt++;
//                break;
//            case 's':
//                saveToFile();
//                break;
//            case 'l':
//                loadFromFile();
//                break;
//            case 'd':
//
//                break;
//            case 'q':
//                System.exit(0); // Closes the game window and quits the game.
//                return;
//            default:
//                break;
//        }
//    }

//    public static void loadFromFile() {
//        boolean firstLine = true;
//        try {
//            In in = new In(FILE);
//            while(in.hasNextLine()) {
//                if(firstLine) {
//                    String line = in.readLine();
//                    SEED = Long.valueOf(line);
//                    rand = new Random(SEED);
//                    firstLine = false;
//                } else {
//                    operations.delete(0, operations.length());
//                    operations.append(in.readLine());
////                    operations = ;
//                }
//            }
//            cnt = 0;
//            initWorld();
////            operations.delete(0, operations.length());
//////            operations.append(operations);
////            historyStack.clear();
//
//            for (int i = 0; i < operations.length(); i++) {
//                choose(operations.charAt(i));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void initWorld() {
//        for(int x = 0; x < WIDTH; x++){
//            for(int y = 0; y < TILE_SIZE; y++){
//                world[x][y] = Tileset.NOTHING;
//            }
//        }
////        fillWithTrees();
//    }
//    public static void deleteFromWorld() {
//        if(historyStack.empty()) {
//            return;
//        }
//        world = historyStack.pop();
//
//    }


//    public static void saveWorldState() {
//        // Create a new 2D array with the same dimensions
//        TETile[][] currentWorldCopy = new TETile[world.length][world[0].length];
//
//        // Iterate through the existing 'world' and copy each element
//        for (int i = 0; i < world.length; i++) {
//            for (int j = 0; j < world[0].length; j++) {
//                currentWorldCopy[i][j] = world[i][j]; // Assuming TETile is immutable or you need to copy its contents too
//                // If TETile is a custom object and mutable, you might need a deep copy of TETile as well!
//            }
//        }
//        historyStack.push(currentWorldCopy); // Push the *copy* onto the stack
//    }
//    public static void saveToFile()  {
//        try {
//            Out out1 = new Out(FILE);
//            out1.println(SEED);
//            out1.println(operations);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}
