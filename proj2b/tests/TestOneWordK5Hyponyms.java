import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import main.AutograderBuddy;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static utils.Utils.WORDS_FILE;

public class TestOneWordK5Hyponyms {
    @Test
    public void testActK5() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                Utils.FREQUENCY_EECS_FILE, Utils.TOTAL_COUNTS_FILE, Utils.SYNSETS_EECS_FILE, Utils.HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS61A");

        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 4);
        String actual = studentHandler.handle(nq);
        String expected = "[CS170, CS61A, CS61B, CS61C]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testActK4() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                Utils.FREQUENCY_EECS_FILE, Utils.TOTAL_COUNTS_FILE, Utils.SYNSETS_EECS_FILE, Utils.HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS61C");
        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 5);
        String actual = studentHandler.handle(nq);
        String expected = "[CS161, CS162, CS169, CS186, CS61C]";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testActK100() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                Utils.FREQUENCY_EECS_FILE, Utils.TOTAL_COUNTS_FILE, Utils.SYNSETS_EECS_FILE, Utils.HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("EE120");
        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 100);
        String actual = studentHandler.handle(nq);
        String expected = "[EE120, EE123, EE192, EEC106A, EEC106B]";
        assertThat(actual).isEqualTo(expected);
    }
    
}
