/*
 * In the class below, write two methods:
 * 
 * First, method resize() to expand the existing array data by doubling its size.
 *
 * Second, method contains(String string) that returns true if String string already
 * exists in array data and false otherwise.
 *
 * Your code must have comments explaining what is done and why it is done in the
 * way you do it.
 *
 * DO NOT USE any tools that require to be imported, ie, do not use the import command.
 *
 * To save this assignment, make sure you commit your changes to your GitHub repository,
 * following the instructions in Sakai. IF YOU DO NOT COMMIT THE CHANGES, IT IS POSSIBLE THAT
 * YOUR WORK MAY BE LOST AND YOU MAY HAVE TO START ALL OVER AGAIN.
 */
public class DynamicArray {

    /** The underlying array for this object */
    private String[] data;

    /** Currently available position in array data */
    private int position;

    /** Constant with default size */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Basic constructor for the object
     * @param size initial size of array data
     */
    public DynamicArray(int size) {
        this.data = new String[size];
        this.position = 0;
    } // basic constructor

    /** Default constructor */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Adds a new item to array data after ensurig there is 
     * sufficient room by resizing the array if necessary.
     * @param string new item to add to array
     */
    public void add(String string) {
        // Make sure there is room in array data
        if (this.position == this.data.length) {
            resize();
        }
        // Now array has room for more elements.
        this.data[this.position] = string;
        this.position++;
    } // method add
    
    public void resize() {
        // Create a new array with double the size
        String[] newData = new String[this.data.length * 2];
        // Copy all elements from the old array to the new array
        for (int i = 0; i < this.data.length; i++) {
            newData[i] = this.data[i];
        }
        // Update the reference to the new array
        this.data = newData;
    } // method resize

    public boolean contains(String string) {
        // Variable to keep track of whether the string was found
        boolean found = false;
        // Iterate through the array to check for the string
        // Loop continues as long as 'i' is less than 'position' and 'found' is false
        for (int i = 0; i < this.position && !found; i++) {
            // Check if the current element equals the target string
            if (this.data[i].equals(string)) {
                // If found, set 'found' to true
                found = true;
            }
        }
        // Return the result of whether the string was found or not
        return found;
    } // method contains

    public int countOf(String string) {
        // Initialize a counter to keep track of occurrences
        int count = 0;
        // Iterate through the array and count the occurrences of the string
        for (int i = 0; i < this.position; i++) {
            // If the current element equals the target string, increment the count
            if (this.data[i].equals(string)) {
                count++;
            }
        }
        // Return the total count of occurrences
        return count;
    } // method countOf
} // class DynamicArray
