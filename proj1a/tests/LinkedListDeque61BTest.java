import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** This test performs interspersed empty calls.*/
    public void isEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    /**This test performs interspersed size calls*/
    public void sizeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(-1);
        lld1.addLast(2);
        lld1.addFirst(4);
        lld1.addFirst(-4);
        assertThat(lld1.size()).isEqualTo(6);
    }
    @Test
    /**This test performs interspersed get calls*/
    public void getElemTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.addLast(-1);
        lld1.addLast(2);
        lld1.addLast(-2);
        lld1.addLast(4);
        int elem = lld1.get(3);
        assertThat(elem).isEqualTo(2);
    }

    @Test
    /**This test performs interspersed getRecursive calls*/
    public void getElemRecursiveTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.addLast(-1);
        lld1.addLast(2);
        lld1.addLast(-2);
        lld1.addLast(4);
        int elem = lld1.getRecursive(4);
        assertThat(elem).isEqualTo(-2);
    }
    @Test
    /** The test performs interspersed addAndRemove size calls*/
    public void addAndRemove() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addFirst(-1);
        lld1.addLast(2);
        var elem = lld1.removeLast();
        assertThat(elem).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(-1, 0, 1).inOrder();
        elem = lld1.removeFirst();
        assertThat(elem).isEqualTo(-1);
        assertThat(lld1.toList()).containsExactly(0, 1).inOrder();

    }


}