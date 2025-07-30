package core;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;

import static core.GameStartMenu.drawStartScreen;

public class Game {
    static World world = new World();
    static User user = new User(world.map.world, 0, 0);
    private static boolean end = false;

    public static void main(String[] args) {
        GameStartMenu.GameState currentState = drawStartScreen();
        // 处理状态变化的逻辑
        if (currentState == GameStartMenu.GameState.NEW_GAME) {
            // 这里调用你的新游戏启动逻辑
            System.out.println("Starting New Game...");
            start();

        } else if (currentState == GameStartMenu.GameState.LOAD_GAME) {
            System.out.println("Loading Game...");
            load();
            // 加载游戏逻辑
        } else if (currentState == GameStartMenu.GameState.QUIT) {
            System.out.println("Quitting Game...");
            System.exit(0); // 退出程序
        }
    }

    public static void start() {
        StdDraw.clear();
        char operation;
        world.displayGame();
        while (!end) {
            while (StdDraw.hasNextKeyTyped()) {
                operation = StdDraw.nextKeyTyped();
                operation = Character.toLowerCase(operation);
                System.out.println(operation);
                end = user.move(operation);
                if (end) {
                    StdDraw.clear();
                    GameStartMenu.win();
                    return;
                }
                world.displayGame();
            }
        }

    }

    public static void load() {
        try {
            In in = new In(World.FILE);
            while (in.hasNextLine()) {
                String line = in.readLine();
                String[] tokens = line.split(",");
                world = new World(Long.parseLong(tokens[0]));
                user.setPosX(Integer.parseInt(tokens[1]));
                user.setPosY(Integer.parseInt(tokens[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        start();
    }
    public static void save() {
        try {
            Out out = new Out(World.FILE);
            out.println(world.map.SEED + "," + user.getPosX() + "," + user.getPosY());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Saving Game...");
    }
}
