import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10
// Course: CS 300 Fall 2022
//
// Author: Kenneth Oh
// Email: oh87@wisc.edu
// Lecturer: Jeff Nyhoff
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

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
      Course course = new Course(" ", 300, 3, 10);
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

    course.setProfessor(null, 5.0);
    if (!course.toString().equals("CS 300 (10 seats)")) {
      return false;
    }

    course.setProfessor("Professor1", 5);

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
    big = new Course("MATH", 300, 3, 10);
    small = new Course("MATH", 300, 3, 0);
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
    try {
      CourseQueue queue = new CourseQueue(-1);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      CourseQueue queue = new CourseQueue(0);
      return false;
    } catch (IllegalArgumentException e) {
    }

    CourseQueue queue = new CourseQueue(5);

    if (!queue.isEmpty()) {
      return false;
    }

    if (queue.size() != 0) {
      return false;
    }

    Course course1 = new Course("CS", 300, 3, 10);
    Course course2 = new Course("MATH", 221, 5, 0);
    Course course3 = new Course("MATH", 222, 4, 0);
    Course course4 = new Course("CHEM", 103, 5, 10);
    Course course5 = new Course("ECON", 101, 4, 10);
    course4.setProfessor("Professor1", 5.0);

    queue.enqueue(course1);
    queue.enqueue(course2);
    queue.enqueue(course3);
    queue.enqueue(course4);
    queue.enqueue(course5);

    if (!queue.peek().equals(course1) || queue.isEmpty() || queue.size() != 5) {
      return false;
    }

    return true; // No bug detected
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
    CourseQueue queue = new CourseQueue(4);

    Course course1 = new Course("CS", 300, 3, 10);
    Course course2 = new Course("CS", 221, 5, 0);
    Course course3 = new Course("MATH", 222, 4, 0);
    Course course4 = new Course("MATH", 223, 4, 10);

    try {
      queue.enqueue(null);
      return false;
    } catch (NullPointerException e) {
    } catch (Exception e) {
      return false;
    }

    try {
      queue.enqueue(course1);
      if (!queue.peek().equals(course1)) {
        return false;
      }
      queue.enqueue(course2);
      if (!queue.peek().equals(course1)) {
        return false;
      }
      queue.enqueue(course3);
      if (!queue.peek().equals(course1)) {
        return false;
      }
      queue.enqueue(course4);
      if (!queue.peek().equals(course1)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      queue.enqueue(course1);
      return false;
    } catch (IllegalStateException e) {
    } catch (Exception e) {
      return false;
    }


    if (queue.size() != 4) {
      return false;
    }

    try {
      if (!queue.dequeue().equals(course1) || queue.size() != 3 || queue.isEmpty()) {
        System.out.println(1);
        return false;
      }

      if (!queue.dequeue().equals(course2) || queue.size() != 2 || queue.isEmpty()) {
        System.out.println(2);
        return false;
      }

      if (!queue.dequeue().equals(course3) || queue.size() != 1 || queue.isEmpty()) {
        return false;
      }

      if (!queue.dequeue().equals(course2) || queue.size() != 0 || !queue.isEmpty()) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      queue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      return false;
    }

    return true; // No bug detected
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
    CourseQueue queue = new CourseQueue(3);
    var itr = queue.iterator();

    if (itr.hasNext()) {
      return false;
    }

    Course course1 = new Course("CS", 300, 3, 10);
    Course course2 = new Course("MATH", 221, 5, 10);
    Course course3 = new Course("MATH", 222, 4, 0);

    queue.enqueue(course1);
    queue.enqueue(course2);
    queue.enqueue(course3);

    itr = queue.iterator();
    if (!itr.hasNext()) {
      return false;
    }

    if (!itr.next().equals(course1)) {
      return false;
    }

    if (!itr.next().equals(course2)) {
      return false;
    }

    if (!itr.next().equals(course3)) {
      return false;
    }

    try {
      itr.next();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      return false;
    }

    return true; // No bug detected
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
    try {
      CourseReg cr = new CourseReg(-1, 0);
      System.out.println(1);
      return false;
    } catch (IllegalArgumentException e) {
    }

    CourseReg cr = new CourseReg(5, 16);

    Course course1 = new Course("CS", 300, 3, 10);
    Course course2 = new Course("MATH", 221, 5, 0);
    Course course3 = new Course("MATH", 222, 4, 0);
    Course course4 = new Course("CHEM", 103, 4, 10);
    Course course5 = new Course("ECON", 101, 4, 10);
    Course course6 = new Course("ECE", 252, 3, 10);
    course4.setProfessor("Professor1", 5.0);

    if (!cr.add(course1)) {
      System.out.println(2);
      return false;
    }
    if (!cr.add(course2)) {
      System.out.println(3);
      return false;
    }
    if (!cr.add(course3)) {
      System.out.println(4);
      return false;
    }
    if (!cr.add(course4)) {
      System.out.println(5);
      return false;
    }
    if (!cr.add(course5)) {
      System.out.println(6);
      return false;
    }

    if (cr.add(course6)) {
      return false;
    }

    if (!cr.getRecommendedCourses().equals(
        "CS 300 (10 seats)\nCHEM 103 (10 seats) with Professor1 (5.0)\nECON 101 (10 seats)\nMATH 222 (closed)\n")) {
      return false;
    }

    try {
      cr.setCreditLoad(0);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      cr.setCreditLoad(-1);
      return false;
    } catch (IllegalArgumentException e) {
    }

    cr.setCreditLoad(21);

    if (!cr.getRecommendedCourses().equals(
        "CS 300 (10 seats)\nCHEM 103 (10 seats) with Professor1 (5.0)\nECON 101 (10 seats)\nMATH 222 (closed)\nMATH 221 (closed)\n")) {
      return false;
    }

    return true; // No bug detected
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
