// TODO file header

/**
 * This class implements unit test methods to check the correctness of Course, CourseIterator,
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {

  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() {
    // Check constructor
    try {
      Course course = new Course("", 300, 3, 10);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      Course course = new Course(null, 300, 3, 10);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      Course course = new Course("CS", 0, 3, 10);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      Course course = new Course("CS", -1, 3, 10);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      Course course = new Course("CS", 300, 0, 10);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      Course course = new Course("CS", 300, 6, 10);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      Course course = new Course("CS", 300, 3, -1);
      return false;
    } catch (IllegalArgumentException e) {
    }

    Course course = new Course("CS", 300, 3, 10);

    // Check Accessor
    if (course.getNumCredits() != 3) {
      return false;
    }

    // Check Mutators
    try {
      course.setProfessor("Professor1", -1);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      course.setProfessor("Professor1", 6);
      return false;
    } catch (IllegalArgumentException e) {
    }

    course.setProfessor("Professor1", 5);

    try {
      course.setProfessor(null, 0);
    } catch (IllegalArgumentException e) {
      return false;
    }

    try {
      course.setSeatsAvailable(-1);
      return false;
    } catch (IllegalArgumentException e) {
    }

    // Check toString()
    if (!course.toString().equals("CS 300 (10 seats) with Professor1 (5.0)")) {
      return false;
    }

    course.setSeatsAvailable(0);

    if (!course.toString().equals("CS 300 (closed) with Professor1 (5.0)")) {
      return false;
    }


    return true; // No bug detected
  }

  /**
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels of
   * the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() {
    Course big = new Course("CS", 300, 3, 10);
    Course small = new Course("MATH", 222, 4, 10);

    // First level of the comparison
    if (big.compareTo(small) <= 0) {
      return false;
    }
    if (small.compareTo(big) >= 0) {
      return false;
    }
    if (big.compareTo(big) != 0) {
      return false;
    }
    if (small.compareTo(small) != 0) {
      return false;
    }

    // Second level of the comparison
    big = new Course("CS", 300, 3, 10);
    small = new Course("CS", 300, 3, 0);
    if (big.compareTo(small) <= 0) {
      return false;
    }
    if (small.compareTo(big) >= 0) {
      return false;
    }
    if (big.compareTo(big) != 0) {
      return false;
    }
    if (small.compareTo(small) != 0) {
      return false;
    }

    // Third level of the comparison
    big = new Course("CS", 300, 3, 10);
    small = new Course("CS", 300, 3, 10);
    big.setProfessor("Prof1", 5.0);

    if (big.compareTo(small) <= 0) {
      return false;
    }
    if (small.compareTo(big) >= 0) {
      return false;
    }
    if (big.compareTo(big) != 0) {
      return false;
    }
    if (small.compareTo(small) != 0) {
      return false;
    }

    // Fourth level of the comparison
    big = new Course("CS", 300, 3, 10);
    small = new Course("CS", 300, 3, 10);
    big.setProfessor("Prof1", 5.0);
    small.setProfessor("Prof2", 4.0);

    if (big.compareTo(small) <= 0) {
      return false;
    }
    if (small.compareTo(big) >= 0) {
      return false;
    }
    if (big.compareTo(big) != 0) {
      return false;
    }
    if (small.compareTo(small) != 0) {
      return false;
    }

    // Equal level of comparison
    big = new Course("CS", 300, 3, 10);
    small = new Course("CS", 300, 3, 10);
    big.setProfessor("Prof1", 3.0);
    small.setProfessor("Prof2", 3.0);

    if (big.compareTo(small) != 0) {
      return false;
    }

    return true; // No bug detected
  }

  /**
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() {
    return false; // TODO: complete this test
  }

  /**
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() {
    return false; // TODO: complete this test
  }

  /**
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() {
    return false; // TODO: complete this test
  }

  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() {
    return false; // TODO: complete this test
  }

  /**
   * This method calls all test methods defined by us; you may add additional methods to this if you
   * like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() {
    boolean testVal = true;

    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    return testVal;
  }

  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
