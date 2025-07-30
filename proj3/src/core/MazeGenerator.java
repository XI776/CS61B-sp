package core;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MazeGenerator extends JPanel {

    private static final int TILE_SIZE = 40; // 每个方块的大小
    private static final int WIDTH = 15;     // 迷宫宽度
    private static final int HEIGHT = 15;    // 迷宫高度
    private static final int N = 1, S = 2, E = 4, W = 8;  // 方向常量
    private static final int[][] maze = new int[HEIGHT][WIDTH];  // 迷宫存储

    // 迷宫的方向
    private static final Map<Integer, int[]> DIRECTIONS = Map.of(
            N, new int[]{0, -1},  // 向北
            S, new int[]{0, 1},   // 向南
            E, new int[]{1, 0},   // 向东
            W, new int[]{-1, 0}   // 向西
    );

    // 用于随机打乱方向
    private static final List<Integer> directions = new ArrayList<>(Arrays.asList(N, S, E, W));

    public MazeGenerator() {
        setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
        setBackground(Color.WHITE);
        generateMaze(0, 0); // 从(0,0)开始生成迷宫
    }

    // 递归回溯算法生成迷宫
    private void generateMaze(int x, int y) {
        Collections.shuffle(directions); // 随机打乱方向顺序

        for (int direction : directions) {
            int nx = x + DIRECTIONS.get(direction)[0];
            int ny = y + DIRECTIONS.get(direction)[1];

            // 如果新位置在迷宫范围内，且未被访问过
            if (isValid(nx, ny)) {
                // 打开当前方块与相邻方块之间的墙
                maze[y][x] |= direction;
                maze[ny][nx] |= oppositeDirection(direction);
                generateMaze(nx, ny);  // 递归调用，继续生成
            }
        }
    }

    // 判断当前位置是否有效（未越界且未访问）
    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && maze[y][x] == 0;
    }

    // 获取与当前方向相对的方向
    private int oppositeDirection(int direction) {
        switch (direction) {
            case N: return S;
            case S: return N;
            case E: return W;
            case W: return E;
            default: return 0;
        }
    }

    // 绘制迷宫
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                // 绘制墙壁
                if ((maze[y][x] & N) == 0) {  // 如果北方没有通道
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, 1);  // 绘制上墙
                }
                if ((maze[y][x] & S) == 0) {  // 如果南方没有通道
                    g.fillRect(x * TILE_SIZE, (y + 1) * TILE_SIZE - 1, TILE_SIZE, 1);  // 绘制下墙
                }
                if ((maze[y][x] & E) == 0) {  // 如果东方没有通道
                    g.fillRect((x + 1) * TILE_SIZE - 1, y * TILE_SIZE, 1, TILE_SIZE);  // 绘制右墙
                }
                if ((maze[y][x] & W) == 0) {  // 如果西方没有通道
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, 1, TILE_SIZE);  // 绘制左墙
                }
            }
        }
    }

    public static void main(String[] args) {
        // 创建并显示迷宫界面
        JFrame frame = new JFrame("Maze Generator");
        MazeGenerator mazePanel = new MazeGenerator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mazePanel);
        frame.pack();
        frame.setVisible(true);
//        Graphics g;
//
//        g.drawImage("data/Walls/Bluerock_Texture.jpg", );
    }
}
