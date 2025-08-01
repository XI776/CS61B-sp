/**
 * Exercise sourced from Practice-It by the University of Washington.
 * Original problems available at: https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 */

// TODO: What is the output of the following program?
public class Confusing {
    public static void method1() {
        System.out.println("I am method 1.");
    }
    public static void method2() {
        method1();
        System.out.println("I am method 2.");
    }
    public static void method3() {
        method2();
        System.out.println("I am method 3.");
        method1();
    }

    public static void main(String[] args) {
        method1();
        method3();
        method2();
        method3();
    }
}

/* Before running the code, type your answer below.

TODO: Write output here
method1:
I am method 1.
method2:
I am method 1.
I am method 2.
method3:
I am method 1.
I am method 2.
I am method 3.
I am method 1.

I am method 1.
I am method 1.
I am method 2.
I am method 3.
I am method 1.
I am method 1.
I am method 2.
I am method 1.
I am method 2.
I am method 3.
I am method 1.
Then, click the green play button to check your work. */