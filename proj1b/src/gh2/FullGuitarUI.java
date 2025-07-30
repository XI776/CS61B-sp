package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class FullGuitarUI {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int N = keyboard.length();
        VisualString[] strings = new VisualString[N];

        // 图形区域
        StdDraw.setCanvasSize(1024, 300);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 1);

        // 初始化每根弦的位置和频率
        for (int i = 0; i < N; i++) {
            double freq = 440.0 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new VisualString(freq, i + 0.5);  // x坐标从0.5开始间隔
        }

        while (true) {
            // 键盘按键检测
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            // 音频合成
            double sample = 0;
            for (VisualString s : strings) {
                sample += s.sample();
            }
            StdAudio.play(sample);

            // 推进状态
            for (VisualString s : strings) {
                s.tic();
            }

            // 绘图
            StdDraw.clear();
            for (int i = 0; i < N; i++) {
                strings[i].draw(0.2, 0.8);
                // 显示键位字符
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(i + 0.5, 0.1, "" + keyboard.charAt(i));
            }
            StdDraw.show();
            StdDraw.pause(10); // 控制刷新频率
        }
    }
}
