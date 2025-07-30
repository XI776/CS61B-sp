package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Maze {

    private int logicalWidth;  // 逻辑迷宫宽度 (例如 10)
    private int logicalHeight; // 逻辑迷宫高度 (例如 10)
    private boolean[][] grid;  // 实际的网格，true表示墙壁，false表示通道
    private boolean[][] visited; // 跟踪逻辑单元格是否已被访问

    // 方向向量：右、下、左、上
    private static final int[][] DIRECTIONS = {
            {0, 1},  // 右 (deltaX, deltaY)
            {0, -1}, // 左
            {1, 0},  // 下
            {-1, 0}  // 上
    };

    private Random random = new Random();

    public Maze(int logicalWidth, int logicalHeight) {
        this.logicalWidth = logicalWidth;
        this.logicalHeight = logicalHeight;
        // 实际网格尺寸：(2*N+1) x (2*M+1)
//        this.grid = new boolean[logicalWidth * 2 + 1][logicalHeight * 2 + 1];
        this.grid = new boolean[logicalWidth][logicalHeight];
        this.visited = new boolean[logicalWidth][logicalHeight]; // 逻辑单元格的访问状态

        initializeMaze();
    }

    private void initializeMaze() {
        // 初始时，所有实际网格单元格都是墙壁
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = true; // true 表示墙壁
            }
        }
        // 逻辑单元格的访问状态全部初始化为false
        for (int i = 0; i < logicalWidth; i++) {
            for (int j = 0; j < logicalHeight; j++) {
                visited[i][j] = false;
            }
        }
    }

    // 检查逻辑单元格是否在边界内且未被访问
    private boolean isValidLogicalCell(int x, int y) {
        return x >= 0 && x < logicalWidth && y >= 0 && y < logicalHeight && !visited[x][y];
    }

    // DFS 迷宫生成主方法
    public void generateMaze(int currentLogicalX, int currentLogicalY) {
        // 1. 标记当前逻辑单元格为已访问
        visited[currentLogicalX][currentLogicalY] = true;

        // 2. 将当前逻辑单元格在实际网格中标记为通道
        grid[currentLogicalX][currentLogicalY] = false;

        // 3. 随机化方向
        List<Integer> directionsList = new ArrayList<>();
        for (int i = 0; i < DIRECTIONS.length; i++) {
            directionsList.add(i);
        }
        Collections.shuffle(directionsList, random); // 使用实例化的Random对象

        // 4. 遍历每个方向
        for (int directionIndex : directionsList) {
            int dx = DIRECTIONS[directionIndex][0];
            int dy = DIRECTIONS[directionIndex][1];

            // 计算下一个逻辑单元格的坐标
            int nextLogicalX = currentLogicalX + dx;
            int nextLogicalY = currentLogicalY + dy;

            if (isValidLogicalCell(nextLogicalX, nextLogicalY)) {
                // 如果下一个逻辑单元格有效且未被访问

                // 5. 打通当前单元格和下一个单元格之间的墙壁
                // 墙壁的实际坐标是两个逻辑单元格实际坐标的中点
//                int wallGridX = currentLogicalX * 2 + 1 + dx;
//                int wallGridY = currentLogicalY * 2 + 1 + dy;
                int wallGridX = currentLogicalX + dx;
                int wallGridY = currentLogicalY + dy;
                grid[wallGridX][wallGridY] = false; // 将墙壁方格设为通道

                // 6. 递归调用
                generateMaze(nextLogicalX, nextLogicalY);
            }
        }
    }

    // 获取生成的迷宫网格
    public boolean[][] getMazeGrid() {
        return grid;
    }

    // 简单打印迷宫 (用于测试)
    public void printMaze() {
        for (int j = 0; j < grid[0].length; j++) { // 遍历列 (Y轴)
            for (int i = 0; i < grid.length; i++) { // 遍历行 (X轴)
                System.out.print(grid[i][j] ? "██" : "  "); // 墙壁打印方块，通道打印空格
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int width = 20; // 逻辑宽度
        int height = 20; // 逻辑高度
        Maze maze = new Maze(width, height);

        // 从左上角的逻辑单元格 (0,0) 开始生成
        maze.generateMaze(0, 0);

        maze.printMaze();
    }
}