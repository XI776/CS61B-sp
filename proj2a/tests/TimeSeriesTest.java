import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(1991);
        expectedYears.add(1992);
        expectedYears.add(1994);
        expectedYears.add(1995);

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>();
        expectedTotal.add(0.0);
        expectedTotal.add(100.0);
        expectedTotal.add(600.0);
        expectedTotal.add(500.0);

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }
    @Test
    public void testCopyConstructor() {
        TimeSeries original = new TimeSeries();
        original.put(1980, 1.0);
        original.put(1990, 2.0);
        original.put(2000, 3.0);
        original.put(2010, 4.0);

        TimeSeries copy = new TimeSeries(original, 1990, 2005);

        assertThat(copy.years()).isEqualTo(Arrays.asList(1990, 2000));
        assertThat(copy.data()).isEqualTo(Arrays.asList(2.0, 3.0));
    }

    @Test
    public void testDividedByNormal() {
        TimeSeries t1 = new TimeSeries();
        t1.put(2000, 100.0);
        t1.put(2001, 200.0);

        TimeSeries t2 = new TimeSeries();
        t2.put(2000, 10.0);
        t2.put(2001, 20.0);

        TimeSeries result = t1.dividedBy(t2);
        assertThat(result.years()).isEqualTo(Arrays.asList(2000, 2001));
        assertThat(result.data()).isEqualTo(Arrays.asList(10.0, 10.0));
    }

    @Test
    public void testDividedByThrowsException() {
        TimeSeries t1 = new TimeSeries();
        t1.put(2000, 100.0);
        t1.put(2001, 200.0);

        TimeSeries t2 = new TimeSeries();
        t2.put(2000, 10.0); // t2 不包含 2001

        try {
            t1.dividedBy(t2);
            throw new AssertionError("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Test passes
        }
    }

    @Test
    public void testPlusWithOverlap() {
        TimeSeries t1 = new TimeSeries();
        t1.put(2000, 1.0);
        t1.put(2001, 2.0);

        TimeSeries t2 = new TimeSeries();
        t2.put(2001, 3.0);
        t2.put(2002, 4.0);

        TimeSeries sum = t1.plus(t2);

        assertThat(sum.years()).isEqualTo(Arrays.asList(2000, 2001, 2002));
        assertThat(sum.data()).isEqualTo(Arrays.asList(1.0, 5.0, 4.0));
    }

    @Test
    public void testBoundaryYears() {
        TimeSeries ts = new TimeSeries();
        ts.put(TimeSeries.MIN_YEAR, 1.0);
        ts.put(TimeSeries.MAX_YEAR, 2.0);
        ts.put(1800, 3.0);

        TimeSeries copy = new TimeSeries(ts, TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
        assertThat(copy.years()).isEqualTo(Arrays.asList(TimeSeries.MIN_YEAR, 1800, TimeSeries.MAX_YEAR));
        assertThat(copy.data()).isEqualTo(Arrays.asList(1.0, 3.0, 2.0));
    }

} 