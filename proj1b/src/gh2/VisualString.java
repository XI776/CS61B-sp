package gh2;
import edu.princeton.cs.algs4.StdDraw;
class VisualString {
    GuitarString string;
    double xPos;  // 横向位置
    boolean isActive;  // 是否在振动
    int activeTicks;   // 高亮帧数计时器

    public VisualString(double freq, double xPos) {
        this.string = new GuitarString(freq);
        this.xPos = xPos;
        this.isActive = false;
        this.activeTicks = 0;
    }

    public void pluck() {
        string.pluck();
        isActive = true;
        activeTicks = 8;  // 高亮显示几帧
    }

    public void tic() {
        string.tic();
        if (activeTicks > 0) {
            activeTicks--;
        } else {
            isActive = false;
        }
    }

    public double sample() {
        return string.sample();
    }

    public void draw(double yMin, double yMax) {
        StdDraw.setPenRadius(0.005);
        if (isActive) {
            StdDraw.setPenColor(StdDraw.RED);
        } else {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        }
        StdDraw.line(xPos, yMin, xPos, yMax);
    }
}
