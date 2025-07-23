import deque.Maximizer61B;
import deque.ArrayDeque61B;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class Maximizer61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("");
        ad.addFirst("2");
        ad.addFirst("fury road");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("fury road");
    }
    @Test
    public void singleElementTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("onlyOne");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("onlyOne");
    }

    @Test
    public void multipleSameLengthTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("aaa");
        ad.addLast("bbb");
        ad.addLast("ccc");
        // 所有字符串长度都是3，返回任意一个都行，这里期望返回第一个加入的
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("aaa");
    }



    @Test
    public void maxWithSpacesTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("hi");
        ad.addLast("hello world");
        ad.addLast("  lots of spaces   ");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("  lots of spaces   ");
    }

    @Test
    public void maxWithSpecialCharactersTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("!@#");
        ad.addLast("alpha");
        ad.addLast("beta12345");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("beta12345");
    }

}
