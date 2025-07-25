import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import main.AutograderBuddy;
import main.WordNet;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static utils.Utils.*;


public class TestWordNet {
    @Test
    public void testSimple() {
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String tmp = "act";
        Set<String> wordSet = wordNet.getHyponyms(tmp);
        List<String> list = new ArrayList<>(wordSet);
        Collections.sort(list);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(list.toString()).isEqualTo(expected);
    }

    @Test
    public void testSimple2() {
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String tmp = "alteration";
        Set<String> wordSet = wordNet.getHyponyms(tmp);
        List<String> list = new ArrayList<>(wordSet);
        Collections.sort(list);
        String expected = "[adjustment, alteration, change, conversion, increase, jump, leap, modification, mutation, saltation, transition]";

        assertThat(list.toString()).isEqualTo(expected);
    }
    @Test
    public void testSimple3() {
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String tmp = "action";
        Set<String> wordSet = wordNet.getHyponyms(tmp);
        List<String> list = new ArrayList<>(wordSet);
        Collections.sort(list);
        String expected = "[action, change, demotion, variation]";
        assertThat(list.toString()).isEqualTo(expected);
    }
    @Test
    public void testSimple4() {
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String tmp = "adjustment";
        Set<String> wordSet = wordNet.getHyponyms(tmp);
        List<String> list = new ArrayList<>(wordSet);
        Collections.sort(list);
        String expected = "[adjustment, alteration, conversion, modification, mutation]";
        assertThat(list.toString()).isEqualTo(expected);
    }
    @Test
    public void testMultiple() {
        WordNet wordNet = new WordNet(LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        String[] tmp = {"video", "recording"};
        Set<String> set = new HashSet<>();
        for(String word : tmp) {
            if(set.isEmpty()) {
                set.addAll(wordNet.getHyponyms(word));
            } else {
                set.retainAll(wordNet.getHyponyms(word));
            }
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        String expected = "[video, video_recording, videocassette, videotape]";
        assertThat(list.toString()).isEqualTo(expected);
    }
    @Test
    public void testMultiple2() {
        WordNet wordNet = new WordNet(LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        String[] tmp = {"pastry", "tart"};
        Set<String> set = new HashSet<>();
        for(String word : tmp) {
            if(set.isEmpty()) {
                set.addAll(wordNet.getHyponyms(word));
            } else {
                set.retainAll(wordNet.getHyponyms(word));
            }
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        String expected = "[apple_tart, lobster_tart, quiche, quiche_Lorraine, tart, tartlet]";
        assertThat(list.toString()).isEqualTo(expected);
    }
    @Test
    public void testMulitple3() {
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String[] tmp = {"event", "action"};
        Set<String> set = new HashSet<>();
        for(String word : tmp) {
            if(set.isEmpty()) {
                set.addAll(wordNet.getHyponyms(word));
            } else {
                set.retainAll(wordNet.getHyponyms(word));
            }
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        String expected = "[action, change, demotion, variation]";
        assertThat(list.toString()).isEqualTo(expected);
    }
    @Test
    public void testMulitple4() {
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String[] tmp = {"human_action", "change"};
        Set<String> set = new HashSet<>();
        for(String word : tmp) {
            if(set.isEmpty()) {
                set.addAll(wordNet.getHyponyms(word));
            } else {
                set.retainAll(wordNet.getHyponyms(word));
            }
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        String expected = "[change, demotion, variation]";
        assertThat(list.toString()).isEqualTo(expected);
    }

}
