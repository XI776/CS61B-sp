package core;

import edu.princeton.cs.algs4.StdDraw;

public class Play {
    World world;
    User user;
    public Play() {
        world = new World();
        user = new User(world.map.world, 0, 0);
    }

    public void start() {
        char operation;
        while(StdDraw.hasNextKeyTyped()) {
            operation = StdDraw.nextKeyTyped();
            operation = Character.toLowerCase(operation);
            System.out.println(operation);
            user.move(operation);
            world.displayGame();
        }
    }
    public void options() {
        //wasd
    }
    public void disPlayMenu() {

    }


}
