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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator for Courses in a priority queue, which returns the Courses in order from highest
 * priority to lowest.
 */
public class CourseIterator implements Iterator<Course> {

  // data field - you may NOT add any additional data fields to this class!
  private CourseQueue queue; // a DEEP COPY of the priority queue of courses to iterate over

  /**
   * Creates a new CourseIterator which iterates over the elements of the given CourseQueue in order
   * from the highest-priority course to the lowest-priority course
   * 
   * @param queue a DEEP COPY of the queue to iterate over
   */
  public CourseIterator(CourseQueue queue) {
    this.queue = queue;
  }

  /**
   * Returns true if the iteration has more elements.
   * 
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return this.queue.size() > 0;
  }

  /**
   * Returns the next element in the iteration. Consider how to use the priority queue's methods to
   * get the next course in descending order.
   * 
   * @return the next element in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public Course next() throws NoSuchElementException {
    if (queue.size() == 0) {
      throw new NoSuchElementException("The Iteration has no more elements.");

    }
    return this.queue.dequeue();
  }

}
