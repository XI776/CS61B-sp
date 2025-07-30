package core;

import org.eclipse.jetty.webapp.WebDescriptor;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.awt.*;
import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.*;
import java.util.List;

public class WorldCreator {
    public static Integer WIDTH = 30;
    public static Integer HEIGHT = 30;
    public static Integer TILE_SIZE = 30;
    public TETile[][] world;
    public Long SEED;
    private static final int N = 1, S = 2, E = 4, W = 8;  // 方向常量
    // 迷宫的方向
    private static final Map<Integer, int[]> DIRECTIONS = Map.of(
            N, new int[]{0, 1},// 向北
            S, new int[]{0, -1}, // 向南
            E, new int[]{1, 0},   // 向东
            W, new int[]{-1, 0}   // 向西
    );
    private static final int[] DIRECTION_X = {0, 0, 1, -1};
    private static final int[] DIRECTION_Y = {1, -1, 0, 0};
    // 用于随机打乱方向
    private static final List<Integer> directions = new ArrayList<>();

    //    private static Long COUNT;
    public Random rand;
    public WorldCreator(Long SEED) {
        this.SEED = SEED;
        rand = new Random(SEED);
        world = new TETile[WIDTH][HEIGHT];
        SEED = System.currentTimeMillis();
//        SEED = 1222L;
//        rand = new Random(SEED);
        directions.add(0);
        directions.add(1);
        directions.add(2);
        directions.add(3);
        initWorld();
        generateMazeRecursiveDivision(WIDTH, HEIGHT);
    }
    public WorldCreator() {
        world = new TETile[WIDTH][HEIGHT];
        SEED = System.currentTimeMillis();
//        SEED = 1222L;
        rand = new Random(SEED);
        directions.add(0);
        directions.add(1);
        directions.add(2);
        directions.add(3);
        initWorld();
//        visited = new boolean[world.length][world[0].length];
//        int num = rand.nextInt(30);
//        for (int i = 0; i < num; i++) {
//            addRandomSquare();
//        }
//        generateRoomsAndPaths();
//        MazeGenerator maze = new MazeGenerator(WIDTH, HEIGHT, rand);
//        carve_passages_from(0, 0);
//        generateMaze(0, 0);
//        Maze maze = new Maze(WIDTH, HEIGHT);
//        maze.generateMaze(0, 0);
//        boolean[][] grid = maze.getMazeGrid();
//        for(int i = 0; i < WIDTH; i++){
//            for(int j = 0; j < HEIGHT; j++){
//                if(grid[i][j]){
//                    world[i][j] = Tileset.FLOOR;
//                }
//            }
//        }
        generateMazeRecursiveDivision(WIDTH, HEIGHT);
    }
    public void initWorld() {
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < HEIGHT; y++){
                world[x][y] = Tileset.NOTHING;
            }
        }
        fillWithWALL();
//
    }
    public boolean isValid(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && world[x][y] == Tileset.WALL;
    }


//    private void generateMaze(int x, int y) {
//        Collections.shuffle(directions);
//        for(int direction : directions){
//            int nx = x + DIRECTIONS.get(direction)[0];
//            int ny = y + DIRECTIONS.get(direction)[1];
//            if(isValid(nx, ny)){
//                world[x][y] = Tileset.NOTHING;
//                world[nx][ny] = Tileset.NOTHING;
//                generateMaze(nx, ny);
//            }
//        }
//    }

    public void fillWithWALL() {
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.WALL;
            }
        }
    }
    public void generateMaze1(int startX, int startY){
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(startX, startY));
        world[startX][startY] = Tileset.FLOOR;
        while(!stack.isEmpty()){
            Point curr = stack.pop();
            int x =  curr.x;
            int y = curr.y;
            Collections.shuffle(directions);
            boolean moved = false;
            for(Integer direction : directions){
                int nx = x + DIRECTION_X[direction] * 2;
                int ny = y + DIRECTION_Y[direction] * 2;
                if(isValid(nx, ny)){
                    world[nx][ny] = Tileset.FLOOR;
                    world[x + DIRECTION_X[direction]][y + DIRECTION_Y[direction]] = Tileset.FLOOR;
                    stack.push(new Point(nx, ny));
                    moved = true;
                    break;
                }
            }
            if(!moved && !stack.isEmpty()){
                stack.pop();
            }
        }

    }
    public void generateMaze(int startX, int startY) {
        List<Point> walls = new ArrayList<>();
        addWalls(startX, startY, walls);

        while(!walls.isEmpty()) {
            Point wall = walls.get(rand.nextInt(walls.size()));
            walls.remove(wall);

            int x = wall.x;
            int y = wall.y;

            if(canCarvePath(x, y)) {
                world[x][y] = Tileset.FLOOR;
                addWalls(x, y, walls);
            }
        }

    }
    public boolean canCarvePath(int x, int y) {
        int count = 0;
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i == 0 || j == 0) {
                    int nx = x + i;
                    int ny = y + j;
                    if(nx >= 0 && nx < WIDTH && ny >= 0 && ny < HEIGHT && world[nx][ny] == Tileset.FLOOR) {
                        count++;
                    }
                }
            }
        }
        return count >= 2;
    }

    public void addWalls(int startX, int startY, List<Point> walls) {
        for(int dx = -1;  dx <= 1; dx++) {
            for(int dy = -1; dy <= 1; dy++) {
                if( (dx == 0 || dx == 0) && (dx != dy)) {
                   int nx = startX + dx;
                   int ny = startY + dy;
                   if(isValid(nx, ny)) {
                       walls.add(new Point(nx, ny));
                   }
                }
            }
        }
    }
    private class Point {
        private int x;
        private int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 假设 world 是你的迷宫数组，例如 char[][] 或 TileType[][]
// 假设 Tileset.WALL 和 Tileset.FLOOR 已经定义

    public void generateMazeRecursiveDivision(int width, int height) {
        // 1. 初始化整个世界为 FLOOR，或者 WALL，这里假设我们先填充好边界墙
        // 你的世界可能有一个边框，这里我们只关注内部区域
        // 假设 world 是 (width + 2) x (height + 2) 大小，外面有一圈墙
        // 或简单起见，我们处理的范围是 0 到 width-1, 0 到 height-1

        // 通常，迷宫会在一个全空的网格上绘制墙壁。
        // 如果你的迷宫开始是全墙，那么就相反，绘制通路。
        // 为了符合你“墙壁一块块”的说法，我们假设初始是全空或全墙都可以。
        // 这里我们先将整个区域填充为地板（即一开始是空的），然后绘制墙壁
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.FLOOR; // 初始全通路
            }
        }

        // 调用递归函数开始分割
        // 注意：这里的坐标和尺寸需要根据你的实际世界数组索引进行调整
        // 比如，如果你的世界有外部边界，那么你的起始点和结束点应该排除边界
        // 这里我们假设 (0,0) 到 (width-1, height-1) 是可操作区域
        divide(0, 0, width - 1, height - 1);
    }

    // 递归分割函数
// x1, y1 是当前区域的左上角坐标
// x2, y2 是当前区域的右下角坐标
    private void divide(int x1, int y1, int x2, int y2) {
        int currentWidth = x2 - x1 + 1;
        int currentHeight = y2 - y1 + 1;

        // 终止条件：如果区域太小，或者已经是单行/单列，则停止分割
        if (currentWidth < 3 || currentHeight < 3) {
            return;
        }

        // 随机选择是水平分割还是垂直分割
        // 为了避免生成只有一种方向的墙壁，可以交替或根据长宽比决定
        boolean horizontal = (currentWidth < currentHeight); // 如果高比宽大，更倾向于水平分割
        if (currentWidth == currentHeight) { // 如果是正方形，则随机选择
            horizontal = RandomUtils.bernoulli(rand, 0.5); // 需要一个随机布尔值的工具函数
        }

        if (horizontal) {
            // 水平分割
            // 选择一个奇数行作为墙壁（确保两边留有空间开门）
            // 墙壁的 Y 坐标必须是偶数，且在区域内部（奇数行表示地板，偶数行表示墙壁，如果墙是偶数索引）
            // 或者说，墙壁 Y 必须在 y1 和 y2 之间，且不能是边缘
            // 这里需要注意，如果你希望墙壁和通路交替，那么墙壁的坐标应该固定奇偶性
            // 比如，墙壁都在偶数行/列，通路都在奇数行/列
            // 假设我们使用“奇偶格”系统，墙壁的坐标是偶数

            // 确保墙壁在可分割的区域内，且是“偶数”行 (如果你的坐标体系是这样)
            int wallY = y1 + 1;
            // 找到第一个奇数行或偶数行作为墙壁，这取决于你的网格定义
            // 如果墙壁是偶数坐标，通路是奇数坐标，那么墙壁的y坐标需要是偶数
            // 我们可以选择一个 Y 坐标，确保它是偶数，并且在有效范围内

            // 简化方法：直接在 y1 和 y2 之间选择一个随机的墙壁位置
            // 为了确保有足够的空间开门，墙壁必须位于至少有两格宽的区域内
            // 例如，如果区域是 (y1, y2)，墙壁不能在 y1 或 y2

            // 找到一个可以放置墙壁的Y坐标（需要跳过已有的墙壁，如果你先画墙）
            // 简单起见：选择一个随机的 Y 坐标来画墙，这个 Y 坐标必须是 y1+1 到 y2-1 之间
            // 并且为了能开门，墙壁应该在奇数格（如果通路在偶数格）或偶数格（如果通路在奇数格）
            // 为了避免生成 1 格宽的死路，墙的 Y 值通常是 y1 + 1 到 y2 - 1 之间的一个随机奇数或偶数

            // 通常做法是确保墙壁和门的位置是交错的，例如墙壁在偶数行/列，通路在奇数行/列
            // 这里我们选择一个随机的 Y，确保它在区域内部，并且是“可以画墙”的位置

            // 随机选择一个 Y 坐标来画墙 (必须是偶数，因为迷宫通路是奇数)
            // 从 y1+1 到 y2-1 之间选择，且 Y % 2 == 0 (假设墙在偶数行)
            int randomWallY = getRandomEven(y1 + 1, y2 - 1);
            if (randomWallY == -1) return; // 如果找不到合适的偶数墙壁，就返回

            // 画水平墙
            for (int x = x1; x <= x2; x++) {
                world[x][randomWallY] = Tileset.WALL;
            }

            // 随机开一个门 (在墙上选择一个奇数 X 作为门)
            // 确保门在墙的范围内，且 X 坐标是奇数
            int randomDoorX = getRandomOdd(x1, x2);
            if (randomDoorX != -1) {
                world[randomDoorX][randomWallY] = Tileset.FLOOR;
            }

            // 递归分割上下两个子区域
            divide(x1, y1, x2, randomWallY - 1); // 上半部分
            divide(x1, randomWallY + 1, x2, y2); // 下半部分

        } else {
            // 垂直分割 (与水平分割类似，只是 X 和 Y 互换)
            int randomWallX = getRandomEven(x1 + 1, x2 - 1);
            if (randomWallX == -1) return;

            // 画垂直墙
            for (int y = y1; y <= y2; y++) {
                world[randomWallX][y] = Tileset.WALL;
            }

            // 随机开一个门
            int randomDoorY = getRandomOdd(y1, y2);
            if (randomDoorY != -1) {
                world[randomWallX][randomDoorY] = Tileset.FLOOR;
            }

            // 递归分割左右两个子区域
            divide(x1, y1, randomWallX - 1, y2); // 左半部分
            divide(randomWallX + 1, y1, x2, y2); // 右半部分
        }
    }

    // 辅助函数：生成范围内的随机偶数
    private int getRandomEven(int min, int max) {
        List<Integer> evens = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (i % 2 == 0) { // 假设墙壁在偶数行/列
                evens.add(i);
            }
        }
        if (evens.isEmpty()) return -1;
        return evens.get(rand.nextInt(0, evens.size()));
    }

    // 辅助函数：生成范围内的随机奇数
    private int getRandomOdd(int min, int max) {
        List<Integer> odds = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (i % 2 != 0) { // 假设通路在奇数行/列
                odds.add(i);
            }
        }
        if (odds.isEmpty()) return -1;
        return odds.get(rand.nextInt(0, odds.size()));
    }

// 需要一个 RandomUtils 类或者直接使用 java.util.Random
// 例如：Random random = new Random();
// RandomUtils.bernoulli() -> random.nextBoolean()
// RandomUtils.nextInt(min, max) -> min + random.nextInt(max - min + 1)
//    public void createRoom(Rectangle r) {
//        for(int i = r.x; i < r.x + r.width; i++) {
//            for(int j = r.y; j < r.y + r.height; j++) {
//                world[i][j] = Tileset.FLOOR;
//                if(i == r.x || j == r.y || i ==  r.x + r.width - 1 || j == r.y + r.height - 1) {
//                    world[i][j] = Tileset.WALL;
//                }
//            }
//        }
//    }
//    public void generateRoomsAndPaths() {
//        int num = rand.nextInt(9) + 4;
//        List<Rectangle> rooms = new ArrayList<>();
//        for(int i = 0; i < num; i++) {
//            int width = rand.nextInt(7) + 5;  // 房间宽度5-12
//            int height = rand.nextInt(7) + 5; // 房间高度5-12
//            int x = rand.nextInt(WIDTH - width - 1);
//            int y = rand.nextInt(HEIGHT - height - 1);
//            Rectangle room = new Rectangle(x, y, width, height);
//            createRoom(room);
//            rooms.add(room);
//        }
//        for(int i = 0; i < num - 1; i++) {
//            Rectangle room1 = rooms.get(i);
//            Rectangle room2 = rooms.get(i+1);
//            createPath(room1, room2);
//        }
//    }
//
//    // 创建两房间之间的路径
//    public void createPath(Rectangle room1, Rectangle room2) {
//        int startX = room1.x + room1.width / 2;
//        int startY = room1.y + room1.height / 2;
//        int endX = room2.x + room2.width / 2;
//        int endY = room2.y + room2.height / 2;
//
//        // 画一条路径（横向或纵向）
//        while (startX != endX) {
//            if (startX < endX) startX++;
//            else startX--;
//            world[startX][startY] = Tileset.FLOOR;
//        }
//        while (startY != endY) {
//            if (startY < endY) startY++;
//            else startY--;
//            world[startX][startY] = Tileset.FLOOR;
//        }
//    }

    public void addRandomSquare() {
        int startX = rand.nextInt(WIDTH);
        int startY = rand.nextInt(TILE_SIZE);
        int sizeX = RandomUtils.uniform(rand, 4, 20);
        int sizeY = RandomUtils.uniform(rand, 4, 20);
        TETile tile = getRandomTile();
//        saveWorldState();
        drawSquare(startX, startY, sizeX, sizeY, tile);
    }

    private void drawSquare( int startX, int startY, int sizeX, int sizeY, TETile tile) {
        int endX = Math.min((startX + sizeX), world.length);
        int endY = Math.min((startY + sizeY), TILE_SIZE);
        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                world[x][y] = tile;
            }
        }
    }

    public void createAsSeries(String line) {
        for(int x = 0; x < line.length(); x++){
            addRandomSquare();
        }
    }
    public TETile getRandomTile() {
        int size = rand.nextInt(9);//这个是以后随机生成的一些墙这些，数字还不固定
        return switch(size) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOOR;
            case 2 -> Tileset.GRASS;
            case 3 -> Tileset.WATER;
            case 4 -> Tileset.FLOWER;
            case 5 -> Tileset.LOCKED_DOOR;
            case 6 -> Tileset.UNLOCKED_DOOR;
            case 7 -> Tileset.SAND;
            case 8 -> Tileset.TREE;
            default -> Tileset.NOTHING;
        };
    }

    public boolean canWalk(TETile tile) {
        return tile == Tileset.LOCKED_DOOR;
    }
    private boolean firstWalk = true;
    private boolean[][] visited ;  // 标记每个位置是否已访问
    private static List<Character> passages = new ArrayList<>();
    public void carve_passages_from(int x, int y) {
//        if(x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT || visited[x][y]) {
//            return;
//        }
//        if(!canWalk(world[x][y])) {
//            return;
//        }
//        boolean canContinue = true;
//        visited[x][y] = true;
//        Collections.shuffle(passages, rand);
        world[x][y] = Tileset.NOTHING;
        boolean canContinue = false;
        Collections.shuffle(directions, rand);
        for(int direction : directions){
            int nx = x + DIRECTIONS.get(direction)[0];
            int ny = y + DIRECTIONS.get(direction)[1];
            if(isValid(nx, ny)) {
                carve_passages_from(nx, ny);
                canContinue = true;
            }
        }
        if(!canContinue) {
            world[x][y] = Tileset.LOCKED_DOOR;
        }
//        for(Character passage : passages) {
//            switch (passage) {
//                case 'N' : if(y + 1 < HEIGHT && canWalk(world[x][y + 1])) {
//                    carve_passages_from(x, y + 1);
//                    canContinue = canWalk(world[x][y + 1]);
//                }
//                    break;
//                case 'S' :
//                    if(y - 1 >= 0 && canWalk(world[x][y - 1])) {
//                        carve_passages_from(x, y - 1);
////                        canContinue = true;
//                        canContinue = !canWalk(world[x][y - 1]);
//                    }
//                    break;
//                case 'W' :
//                    if(x - 1 >= 0 && canWalk(world[x - 1][y])) {
//                        carve_passages_from(x - 1, y);
//                        canContinue = !canWalk(world[x - 1][y]);
//                    }
//                    break;
//                case 'E' :
//                    if(x + 1 < WIDTH && canWalk(world[x + 1][y])) {
//                        carve_passages_from(x + 1, y);
//                        canContinue = !canWalk(world[x + 1][y]);
//                    }
//                    break;
//            }
//        }
//        // 如果四个方向都无法继续，回溯，将当前位置设置为空
//        if (!canContinue) {
//            world[x][y] = Tileset.WALL;  // 将当前位置设置为空
//            return ;
//        }

        }
    }
