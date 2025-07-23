
package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class FullGuitar {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int N = keyboard.length();
        GuitarString[] strings = new GuitarString[N];

        // 初始化每个琴键对应的频率和弦
        for (int i = 0; i < N; i++) {
            double frequency = 440.0 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        // 显示提示信息
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.clear();
        StdDraw.text(0.5, 0.6, "Guitar Hero!");
        StdDraw.text(0.5, 0.4, "Type keys: " + keyboard);
        StdDraw.show();

        // 主循环
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                    StdDraw.clear();
                    StdDraw.text(0.5, 0.5, "Key: " + key);
                    StdDraw.show();
                }
            }

            // 合成所有弦的声音
            double sample = 0;
            for (GuitarString s : strings) {
                sample += s.sample();
            }

            // 播放声音
            StdAudio.play(sample);

            // 更新每根弦
            for (GuitarString s : strings) {
                s.tic();
            }
        }
    }
}
