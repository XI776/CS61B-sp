package main;

import net.sf.saxon.functions.Sum;
import ngrams.TimeSeries;

import java.util.List;

public class wordAndRespectively implements Comparable<wordAndRespectively>{
    String word;
    TimeSeries tm;

    public wordAndRespectively(String word, TimeSeries t) {
        this.word = word;
        this.tm = t;
    }

    @Override
    public int compareTo(wordAndRespectively o) {
        List<Double> data1 = tm.data();
        List<Double> data2 = o.tm.data();
        return Double.compare(getTotal(data2), getTotal(data1));

    }
    public Double getTotal(List<Double> data) {
        Double sum = 0.0;
        for(Double d : data) {
            sum += d;
        }
        return sum;
    }
    @Override
    public String toString() {
        return word;
    }

}
