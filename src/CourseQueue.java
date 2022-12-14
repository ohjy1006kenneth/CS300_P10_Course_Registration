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
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the max-heap
 * invariant, so that the Course at the root should have the highest score, and all children always
 * have a score lower than or equal to their parent's.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>, PriorityQueueADT<Course> {

  // data fields
  private Course[] queue; // array max-heap of courses representing this priority queue
  private int size; // number of courses currently in this priority queue

  /**
   * Creates a new, empty CourseQueue with the given capacity
   * 
   * @param capacity the capacity of this CourseQueue
   * @throws IllegalArgumentException if the capacity is not a positive integer
   */
  public CourseQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("The capacity is not a positive integer.");
    }

    queue = new Course[capacity];
    size = 0;
  }

  /**
   * Returns a deep copy of this CourseQueue containing all of its elements in the same order. This
   * method does not return the deepest copy, meaning that you do not need to duplicate courses.
   * Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
   */
  public CourseQueue deepCopy() {
    CourseQueue copy = new CourseQueue(this.queue.length);
    copy.size = this.size;

    int i = 0;
    for (var v : this.queue) {
      copy.queue[i++] = v;
    }

    return copy;
  }

  /**
   * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
   * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY of
   * this queue.
   * 
   * @see CourseIterator
   * @return an Iterator for this CourseQueue
   */
  @Override
  public Iterator<Course> iterator() {
    return new CourseIterator(this);
  }

  ///////////////////////////// TODO: PRIORITY QUEUE METHODS //////////////////////////////////
  // Add the @Override annotation to these methods once this class implements PriorityQueueADT!

  /**
   * Checks whether this CourseQueue is empty
   * 
   * @return {@code true} if this CourseQueue is empty
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the size of this CourseQueue
   * 
   * @return the size of this CourseQueue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Adds the given Course to this CourseQueue and use the percolateUp() method to maintain max-heap
   * invariant of CourseQueue. Courses should be compared using the Course.compareTo() method.
   * 
   * 
   * @param toAdd Course to add to this CourseQueue
   * @throws NullPointerException  if the given Course is null
   * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
   */
  @Override
  public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
    if (toAdd == null) {
      throw new NullPointerException("The given Course is null.");
    }

    if (this.size == this.queue.length) {
      throw new IllegalStateException("This CourseQueue is full.");
    }

    this.queue[size++] = toAdd;
    percolateUp(size - 1);
  }

  /**
   * Removes and returns the Course at the root of this CourseQueue, i.e. the Course with the
   * highest priority. Use the percolateDown() method to maintain max-heap invariant of CourseQueue.
   * Courses should be compared using the Course.compareTo() method.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException with a descriptive error message if this CourseQueue is empty
   */
  @Override
  public Course dequeue() throws NoSuchElementException {
    if (this.size == 0) {
      throw new NoSuchElementException("This CourseQueue is empty.");
    }

    Course temp = queue[0];

    queue[0] = queue[size - 1];
    queue[size - 1] = null;
    size--;
    percolateDown(0);

    return temp;
  }

  /**
   * Returns the Course at the root of this CourseQueue, i.e. the Course with the highest priority.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException if this CourseQueue is empty
   */
  @Override
  public Course peek() throws NoSuchElementException {
    if (this.size == 0) {
      throw new NoSuchElementException("This CourseQueue is empty.");
    }
    return this.queue[0];
  }

  ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////

  /**
   * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If
   * the element at the given index does not violate the max-heap invariant (it is higher priority
   * than its children), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("The index is out of bounds.");
    }
    int childIndex = 2 * index + 1;
    Course value = queue[index];

    while (childIndex < this.size) {
      Course maxValue = value;
      int maxIndex = -1;
      for (int i = 0; i < 2 && i + childIndex < this.size; i++) {
        if (queue[i + childIndex].compareTo(maxValue) > 0) {
          maxValue = queue[i + childIndex];
          maxIndex = i + childIndex;
        }
      }

      if (maxValue == value) {
        return;
      } else {
        Course temp = queue[index];

        queue[index] = queue[maxIndex];
        queue[maxIndex] = temp;

        index = maxIndex;
        childIndex = 2 * index + 1;
      }
    }
  }

  /**
   * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element
   * at the given index does not violate the max-heap invariant (it is lower priority than its
   * parent), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("The index is out of bounds.");
    }
    while (index > 0) {
      int parentIndex = (index - 1) / 2;
      if (queue[index].compareTo(queue[parentIndex]) <= 0) {
        return;
      } else {
        Course temp = queue[index];

        queue[index] = queue[parentIndex];
        queue[parentIndex] = temp;
        index = parentIndex;
      }
    }
  }

  ////////////////////////////// PROVIDED: TO STRING ////////////////////////////////////

  /**
   * Returns a String representing this CourseQueue, where each element (course) of the queue is
   * listed on a separate line, in order from the highest priority to the lowest priority.
   * 
   * @author yiwei
   * @see Course#toString()
   * @see CourseIterator
   * @return a String representing this CourseQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Course c : this) {
      val.append(c).append("\n");
    }

    return val.toString().trim();
  }

}
