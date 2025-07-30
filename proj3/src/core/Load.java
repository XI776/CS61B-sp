package core;

import edu.princeton.cs.algs4.In;
import tileengine.TERenderer;
import tileengine.TETile;

import java.util.Random;

public class Load {
    public static void loadFromFile(World world) {
        boolean firstLine = true;
        try {
            In in = new In(World.FILE);
            while(in.hasNextLine()) {
                if(firstLine) {
                    String line = in.readLine();
                    world.setSeedAndRand(line);
                    world = new World();
                    firstLine = false;
                } else {
                    world.operations.delete(0, world.operations.length());
                    world.operations.append(in.readLine());
//                    operations = ;
                }
            }
//            World.cnt = 0;
//            World.initWorld();
            world.recover();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
