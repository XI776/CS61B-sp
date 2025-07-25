package main;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.util.*;

public class sortEECS {
    public static void main(String[] args) throws Exception {
        String inputFile = "data/ngrams/frequency-EECS.csv";
        String outputFile = "output.csv";
        Map<String, Long> tm = new HashMap<>();
        try {
            In fileIn = new In(inputFile);
            while(fileIn.hasNextLine()) {
                String line = fileIn.readLine();
                String[] fields = line.split("\t");
                tm.put(fields[0], Long.parseLong(fields[2]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 2: 将 entrySet 转换为 List，并根据 value（Long）排序
        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(tm.entrySet());
        sortedEntries.sort(Comparator.comparingLong(Map.Entry::getValue)); // 从小到大排序
        // 如果想从大到小排序：加个 `reversed()`：
        // sortedEntries.sort(Comparator.comparingLong(Map.Entry::getValue).reversed());

        // Step 3: 写入输出文件
        try {
            Out fileOut = new Out(outputFile);
            for (Map.Entry<String, Long> entry : sortedEntries) {
                fileOut.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
