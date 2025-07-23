import deque.ArrayDeque61B;

import deque.Deque61B;
import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    @DisplayName("Only add first")
    void addFirst() {
        ArrayDeque61B<String> arrayDeque61B = new ArrayDeque61B<>();
        arrayDeque61B.addFirst("a");
        arrayDeque61B.addFirst("b");
        arrayDeque61B.addFirst("c");
        arrayDeque61B.addFirst("d");

        assertThat(arrayDeque61B.toList())
                .containsExactly("d", "c", "b", "a")
                .inOrder();
    }

        @Test
        @DisplayName("Only add first need resize")
        void addFirstNeedResize() {
            ArrayDeque61B<String> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addFirst("a");
            arrayDeque61B.addFirst("b");
            arrayDeque61B.addFirst("c");
            arrayDeque61B.addFirst("d");
            arrayDeque61B.addFirst("e");
            arrayDeque61B.addFirst("f");
            arrayDeque61B.addFirst("g");
            arrayDeque61B.addFirst("h");
            arrayDeque61B.addFirst("i");
            arrayDeque61B.addFirst("j");
            arrayDeque61B.addFirst("k");
            arrayDeque61B.addFirst("l");
            assertThat(arrayDeque61B.toList())
                    .containsExactly("l", "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a")
                    .inOrder();
        }

        @Test
        @DisplayName("Only add last")
        void addLast() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addLast(1);
            arrayDeque61B.addLast(2);
            arrayDeque61B.addLast(9);
            assertThat(arrayDeque61B.toList()).containsExactly(1, 2, 9).inOrder();
        }
        @Test
        @DisplayName("Only add last need resize")
        void addLastNeedResize() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addLast(1);
            arrayDeque61B.addLast(2);
            arrayDeque61B.addLast(3);
            arrayDeque61B.addLast(4);
            arrayDeque61B.addLast(5);
            arrayDeque61B.addLast(6);
            arrayDeque61B.addLast(7);
            arrayDeque61B.addLast(8);
            arrayDeque61B.addLast(9);
            arrayDeque61B.addLast(10);
            arrayDeque61B.addLast(11);
            arrayDeque61B.addLast(12);
            arrayDeque61B.addLast(13);
            assertThat(arrayDeque61B.toList())
                    .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
                    .inOrder();
        }

        @Test
        @DisplayName("test isempty")
        void isEmpty() {
             ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
             assertThat(arrayDeque61B.isEmpty()).isTrue();
        }

        @Test
        @DisplayName("test for size")
        void size() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addFirst(1);
            arrayDeque61B.addLast(2);
            arrayDeque61B.addLast(9);
            arrayDeque61B.addFirst(8);
            assertThat(arrayDeque61B.size()).isEqualTo(4);
            assertThat(arrayDeque61B.toList()).containsExactly(8, 1, 2, 9).inOrder();
        }

        @Test
        @DisplayName("test Remove First")
        void testRemoveFirst() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addFirst(1);
            arrayDeque61B.addLast(2);
            arrayDeque61B.addLast(9);
            arrayDeque61B.addFirst(8);
            arrayDeque61B.addFirst(7);
            arrayDeque61B.removeFirst();
            arrayDeque61B.removeFirst();
            assertThat(arrayDeque61B.toList()).containsExactly(1, 2, 9).inOrder();
        }
        @Test
        @DisplayName("test Remove First Need Resize")
        void testRemoveFirstNeedResize() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addFirst(1);
            arrayDeque61B.addFirst(2);
            arrayDeque61B.addFirst(9);
            arrayDeque61B.addFirst(8);
            arrayDeque61B.addFirst(7);
            arrayDeque61B.addFirst(6);
            arrayDeque61B.addFirst(5);
            arrayDeque61B.addFirst(4);
            arrayDeque61B.addFirst(3);
            arrayDeque61B.addFirst(10);
            arrayDeque61B.addFirst(11);
            arrayDeque61B.addFirst(12);
            arrayDeque61B.addFirst(13);
            arrayDeque61B.addFirst(14);
            for(int i = 0; i < 11; i++) {
                arrayDeque61B.removeFirst();
            }
            assertThat(arrayDeque61B.toList()).containsExactly(9, 2, 1).inOrder();
        }

        @Test
        @DisplayName("test Remove Last")
        void testRemoveLast() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addFirst(1);
            arrayDeque61B.addLast(2);
            arrayDeque61B.addLast(9);
            arrayDeque61B.addLast(8);
            arrayDeque61B.addLast(7);
            arrayDeque61B.removeLast();
            arrayDeque61B.removeLast();
            assertThat(arrayDeque61B.toList()).containsExactly(1, 2, 9).inOrder();
        }
        @Test
        @DisplayName("test Remove Last Need Resize")
        void testRemoveLastNeedResize() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addFirst(1);
            arrayDeque61B.addLast(2);
            arrayDeque61B.addLast(9);
            arrayDeque61B.addFirst(8);
            arrayDeque61B.addFirst(7);
            arrayDeque61B.addFirst(6);
            arrayDeque61B.addFirst(5);
            arrayDeque61B.addFirst(4);
            arrayDeque61B.addFirst(3);
            arrayDeque61B.addFirst(10);
            arrayDeque61B.addFirst(11);
            arrayDeque61B.addFirst(12);
            arrayDeque61B.addFirst(13);
            arrayDeque61B.addFirst(14);
            for(int i = 0; i < 11; i++) {
                arrayDeque61B.removeLast();
            }
            assertThat(arrayDeque61B.toList()).containsExactly(14, 13, 12).inOrder();
        }

        @Test
        @DisplayName("test get elem")
        void testGetElem() {
            ArrayDeque61B<Integer> arrayDeque61B = new ArrayDeque61B<>();
            arrayDeque61B.addFirst(1);
            arrayDeque61B.addLast(2);
            arrayDeque61B.addLast(9);
            arrayDeque61B.addLast(8);
            arrayDeque61B.addFirst(7);
            var elem = arrayDeque61B.get(3);
            assertThat(elem).isEqualTo(9);
        }
        @Test
        @DisplayName("test addLastTestBasic")
        public void addLastTestBasic() {
            ArrayDeque61B<String> lld1 = new ArrayDeque61B<>();

            lld1.addLast("front"); // after this call we expect: ["front"]
            lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
            lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
            assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
        }

        @Test
        @DisplayName("This test performs interspersed addFirst and addLast calls.")
        /** This test performs interspersed addFirst and addLast calls. */
        public void addFirstAndAddLastTest() {
            ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();

             /* I've decided to add in comments the state after each call for the convenience of the
                person reading this test. Some programmers might consider this excessively verbose. */
            lld1.addLast(0);   // [0]
            lld1.addLast(1);   // [0, 1]
            lld1.addFirst(-1); // [-1, 0, 1]
            lld1.addLast(2);   // [-1, 0, 1, 2]
            lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

            assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
        }




        @Test
        /**This test performs interspersed size calls*/
        public void sizeTest() {
            ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
            lld1.addLast(0);
            lld1.addLast(1);
            lld1.addLast(-1);
            lld1.addLast(2);
            lld1.addFirst(4);
            lld1.addFirst(-4);
            assertThat(lld1.size()).isEqualTo(6);
        }
        @Test
        @DisplayName("This test performs interspersed get calls")
        /**This test performs interspersed get calls*/
        public void getElemTest() {
            ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
            lld1.addFirst(0);
            lld1.addFirst(1);
            lld1.addLast(-1);
            lld1.addLast(2);
            lld1.addLast(-2);
            lld1.addLast(4);
            int elem = lld1.get(3);
            assertThat(elem).isEqualTo(2);
        }

//        @Test
//        @DisplayName("This test performs interspersed getRecursive calls")
//        /**This test performs interspersed getRecursive calls*/
//        public void getElemRecursiveTest() {
//            ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
//            lld1.addFirst(0);
//            lld1.addFirst(1);
//            lld1.addLast(-1);
//            lld1.addLast(2);
//            lld1.addLast(-2);
//            lld1.addLast(4);
//            int elem = lld1.getRecursive(4);
//            assertThat(elem).isEqualTo(-2);
//        }
        @Test
        @DisplayName("The test performs interspersed addAndRemove size calls")
        /** The test performs interspersed addAndRemove size calls*/
        public void addAndRemove() {
            ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
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

        @Test
        @DisplayName("This test is for iterator")
        public void addLastTestBasicWithoutToList() {
            ArrayDeque61B<String> lld1 = new ArrayDeque61B<>();

            lld1.addLast("front"); // after this call we expect: ["front"]
            lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
            lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
            assertThat(lld1).containsExactly("front", "middle", "back");
        }

        @Test
        @DisplayName("This test is for equals")
        public void testEqualDeques61B() {
            ArrayDeque61B<String> lld1 = new ArrayDeque61B<>();
            ArrayDeque61B<String> lld2 = new ArrayDeque61B<>();

            lld1.addLast("front");
            lld1.addLast("middle");
            lld1.addLast("back");

            lld2.addLast("front");
            lld2.addLast("middle");
            lld2.addLast("back");

            assertThat(lld1).isEqualTo(lld2);
        }
        @Test
        @DisplayName("This test is for toString")
        public void testToStringDeques61B() {
            ArrayDeque61B<String> lld1 = new ArrayDeque61B<>();

            lld1.addLast("front");
            lld1.addLast("middle");
            lld1.addLast("back");

            assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
        }

}
