package core;

import edu.princeton.cs.algs4.StdDraw;
// 假设这里是你的其他类或方法，例如你的主游戏逻辑类
// import your.game.MainGameLogic;

public class GameStartMenu {

    private static final int SMALLWIDTH = 80;
    private static final int SMALLHEIGHT = 60;

    // 定义一个枚举或常量来表示不同的游戏状态
    public enum GameState {
        MENU,
        NEW_GAME,
        LOAD_GAME,
        RESUME_GAME,
        SAVE_GAME,
        QUIT,

    }

    public static GameState currentState = GameState.MENU; // 初始状态为菜单

    public static GameState drawStartScreen() {
        StdDraw.setCanvasSize(SMALLWIDTH * 10, SMALLHEIGHT * 10);
        StdDraw.setXscale(0, SMALLWIDTH);
        StdDraw.setYscale(0, SMALLHEIGHT);
        StdDraw.enableDoubleBuffering(); // 启用双缓冲，让动画更流畅

        // --- 按钮属性 ---
        double buttonWidth = 28;
        double buttonHeight = 6;
        double halfButtonWidth = buttonWidth / 2;
        double halfButtonHeight = buttonHeight / 2;
        double buttonSpacing = 2;

        double centerX = SMALLWIDTH / 2;
        double newGameButtonY = SMALLHEIGHT * 0.55;
        double loadGameButtonY = newGameButtonY - (buttonHeight + buttonSpacing);
        double quitGameButtonY = loadGameButtonY - (buttonHeight + buttonSpacing);


        // --- 游戏主循环 (在菜单界面) ---
        while (currentState == GameState.MENU) {
            StdDraw.clear(StdDraw.BLACK); // 每帧都清除并重绘

            // --- 菜单标题 ---
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 36));
            StdDraw.text(centerX, SMALLHEIGHT * 0.8, "MAZE GAME");

            // --- 绘制按钮 ---
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledRectangle(centerX, newGameButtonY, halfButtonWidth, halfButtonHeight);
            StdDraw.filledRectangle(centerX, loadGameButtonY, halfButtonWidth, halfButtonHeight);
            StdDraw.filledRectangle(centerX, quitGameButtonY, halfButtonWidth, halfButtonHeight);

            // --- 绘制文字 ---
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 18));
            StdDraw.text(centerX, newGameButtonY, "NEW GAME");
            StdDraw.text(centerX, loadGameButtonY, "LOAD GAME");
            StdDraw.text(centerX, quitGameButtonY, "QUIT GAME");

            // --- 鼠标点击检测 ---
            if (StdDraw.isMousePressed()) {
                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();

                // 判断点击是否在 "NEW GAME" 按钮区域内
                if (isInside(mouseX, mouseY, centerX, newGameButtonY, halfButtonWidth, halfButtonHeight)) {
                    currentState = GameState.NEW_GAME;
                }
                // 判断点击是否在 "LOAD GAME" 按钮区域内
                else if (isInside(mouseX, mouseY, centerX, loadGameButtonY, halfButtonWidth, halfButtonHeight)) {
                    currentState = GameState.LOAD_GAME;
                }
                // 判断点击是否在 "QUIT GAME" 按钮区域内
                else if (isInside(mouseX, mouseY, centerX, quitGameButtonY, halfButtonWidth, halfButtonHeight)) {
                    currentState = GameState.QUIT;
                }
            }

            StdDraw.show(); // 刷新屏幕
            StdDraw.pause(20); // 短暂暂停，避免CPU占用过高和检测过于频繁
        }


        return currentState;
    }

    // 辅助方法：判断鼠标坐标是否在给定矩形区域内
    private static boolean isInside(double mouseX, double mouseY, double rectX, double rectY, double halfRectWidth, double halfRectHeight) {
        return mouseX >= rectX - halfRectWidth && mouseX <= rectX + halfRectWidth &&
                mouseY >= rectY - halfRectHeight && mouseY <= rectY + halfRectHeight;
    }



    public static void main(String[] args) {
//        drawStartScreen();
        win();
//        drawPauseMenu();
//        StdDraw.show();
    }
    public static void win() {
        StdDraw.setCanvasSize(SMALLWIDTH * 10, SMALLHEIGHT * 10);
        StdDraw.setXscale(0, SMALLWIDTH);
        StdDraw.setYscale(0, SMALLHEIGHT);
        StdDraw.enableDoubleBuffering();
//        StdDraw.enableDoubleBuffering(); // 启用双缓冲，让动画更流畅

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.5);
        StdDraw.text(SMALLHEIGHT/2, SMALLHEIGHT/2, "WIN GAME!");
        StdDraw.text(SMALLHEIGHT/2, SMALLHEIGHT/2 + 10, "RESTART GAME...");
        StdDraw.show();
    }

    public static void drawPauseMenu() {
        StdDraw.setCanvasSize(SMALLWIDTH * 10, SMALLHEIGHT * 10);
        StdDraw.setXscale(0, SMALLWIDTH);
        StdDraw.setYscale(0, SMALLHEIGHT);
        StdDraw.enableDoubleBuffering(); // 启用双缓冲，让动画更流畅
        // 暂停菜单的背景 (半透明或纯色)
        StdDraw.setPenColor(new java.awt.Color(0, 0, 0, 150)); // 半透明黑色
        StdDraw.filledRectangle(SMALLWIDTH / 2, SMALLHEIGHT / 2, SMALLWIDTH / 2, SMALLHEIGHT / 2); // 覆盖整个屏幕

        // 按钮属性与主菜单类似
        double buttonWidth = 28;
        double buttonHeight = 6;
        double halfButtonWidth = buttonWidth / 2;
        double halfButtonHeight = buttonHeight / 2;
        double buttonSpacing = 2;

        double centerX = SMALLWIDTH / 2;
        // 调整 Y 坐标，确保暂停菜单在屏幕中央
        double resumeButtonY = SMALLHEIGHT / 2 + buttonHeight + buttonSpacing;
        double saveGameButtonY = SMALLHEIGHT / 2;
        double mainMenuButtonY = SMALLHEIGHT / 2 - buttonHeight - buttonSpacing;

        // 绘制按钮
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY); // 按钮颜色可以不同
        StdDraw.filledRectangle(centerX, resumeButtonY, halfButtonWidth, halfButtonHeight);
        StdDraw.filledRectangle(centerX, saveGameButtonY, halfButtonWidth, halfButtonHeight);
        StdDraw.filledRectangle(centerX, mainMenuButtonY, halfButtonWidth, halfButtonHeight);

        // 绘制文字
        StdDraw.setPenColor(StdDraw.BLACK); // 暂停菜单文字颜色
        StdDraw.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 20)); // 稍大一点的字体
        StdDraw.text(centerX, resumeButtonY, "RESUME");
        StdDraw.text(centerX, saveGameButtonY, "SAVE GAME");
        StdDraw.text(centerX, mainMenuButtonY, "MAIN MENU");

        StdDraw.show();
    }
}