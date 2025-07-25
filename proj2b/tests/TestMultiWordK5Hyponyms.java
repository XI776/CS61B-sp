import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import main.AutograderBuddy;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestMultiWordK5Hyponyms {
    @Test
    public void testActK4s() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                Utils.FREQUENCY_EECS_FILE, Utils.TOTAL_COUNTS_FILE, Utils.SYNSETS_EECS_FILE, Utils.HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS61A");
        words.add("CS170");
        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 4);
        String actual = studentHandler.handle(nq);
        String expected = "[CS170, CS172, CS174, CS176]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testActK4() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                Utils.FREQUENCY_EECS_FILE, Utils.TOTAL_COUNTS_FILE, Utils.SYNSETS_EECS_FILE, Utils.HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS61C");
        words.add("CS152");
        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 5);
        String actual = studentHandler.handle(nq);
        String expected = "[CS152]";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testActK100() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                Utils.FREQUENCY_EECS_FILE, Utils.TOTAL_COUNTS_FILE, Utils.SYNSETS_EECS_FILE, Utils.HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("EE105");
        words.add("EE113");
        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 100);
        String actual = studentHandler.handle(nq);
        String expected = "[EE113]";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testEmpty() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                Utils.FREQUENCY_EECS_FILE, Utils.TOTAL_COUNTS_FILE, Utils.SYNSETS_EECS_FILE, Utils.HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("EE105");
        words.add("EE147");
        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }
}
