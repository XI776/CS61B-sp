package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.lang.classfile.TypeAnnotation;

public class User {
    private int posX;
    private int posY;
    private TETile[][] world;
    TETile currTile;
    private boolean end = false;

    public User(TETile[][] world1, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        world = world1;
        currTile = world[posX][posY];

    }
    public User() {
        posX = 0;
        posY = 0;
    }
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public boolean isWall(TETile tile) {
        return tile == Tileset.WALL;
    }
    public void drawUser(int endX, int endY) {

        if(endX >= 0 && endX < world.length && endY >= 0 && endY < world[0].length ) {
            if(endX == WorldCreator.WIDTH - 1 && endY == WorldCreator.HEIGHT - 1) {
                end = true;
                return;
            }
            if(isWall(world[endX][endY])) {
                return;
            }
            world[posX][posY] = currTile;
            posX = endX;
            posY = endY;
            currTile = world[endX][endY];
            world[endX][endY] = Tileset.PERSON;
        }

    }
    public void chooseDir(char dir) {
        switch (dir) {
            case 'w' -> drawUser(posX, posY + 1);
            case 's' -> drawUser(posX, posY - 1);
            case 'a' -> drawUser(posX - 1, posY);
            case 'd' -> drawUser(posX + 1, posY);
            case 'q' -> System.exit(0);
        }
    }

    public boolean move(char dir) {
        chooseDir(dir);
        return end;
    }
}
