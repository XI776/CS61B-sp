package ngrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;
    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        var keys = ts.keySet();
        for(int key:  keys) {
            if(key >= startYear && key <= endYear) {
                this.put(key, ts.get(key));
            }
        }
        // TODO: Fill in this constructor.
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        var keys = keySet();
        List<Integer> years = new ArrayList<>(keys);
        Collections.sort(years);
        return years;
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Integer> years = years();
        List<Double> values = new ArrayList<>();
        for(var year: years) {
            values.add(get(year));
        }
        return values;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries sum =  new TimeSeries();
        List<Integer> years1 = years();
        List<Integer> years2 = ts.years();
        for(int year: years1) {
            if(years2.contains(year)) {
                sum.put(year, ts.get(year) + get(year));
            } else {
                sum.put(year, get(year));
            }
        }
        for(int year: years2) {
            if(!years1.contains(year)) {
                sum.put(year, ts.get(year));
            }
        }
        return sum;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries div =  new TimeSeries();
        List<Integer> years1 = years();
        List<Integer> years2 = ts.years();
        for(int year: years1) {
            if(!years2.contains(year)) {
                throw new IllegalArgumentException();
            } else {
                div.put(year, get(year) / ts.get(year));
            }
        }
        return div;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
