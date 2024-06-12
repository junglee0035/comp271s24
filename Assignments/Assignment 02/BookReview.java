import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.io.InputStream;

/*
NO IMPORT STATEMENTS. NO CALLS TO SYSTEM.anything, except for 
System.out.println or print or printf as needed.
 */ 
public class BookReview {

    /**
     * Establishes a Scanner on a weblink. If the connection can not be made,
     * the method returns a null. That's how we can tell something went wrong
     * and decide what to do next.
     * @param link String with link to website with text to scan
     * @return Scanner connected to the website or null if connection cannot be made
     */
    public static Scanner connectToBook(String link) {
        Scanner bookScanner;
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            bookScanner = new Scanner(stream);
        } catch (Exception e) {
            bookScanner = null;
        }
        return bookScanner;
    } // method connecttoBook

    public static DynamicArray extractWords(Scanner bookScanner) {
        DynamicArray wordsArray = new DynamicArray();

        while (bookScanner.hasNext()) {
            // Read next word, convert to lowercase, and remove non-alphabetic characters
            String word = bookScanner.next().toLowerCase().replaceAll("[^a-z]", "");
            // Add word to the array if it's not empty
            if (!word.equals("")) {
                wordsArray.addUnique(word);
            }
        }

        return wordsArray;
    } // method extractWords

    public static int countUniqueWords(DynamicArray wordsArray) {
        return wordsArray.position;
    } // method countUniqueWords


    public static void main(String[] args) {
        // https://gutenberg.org/cache/epub/98/pg98.txt is a link
        // to the text of "Tale of Two Cities" from Project Gutenberg
        String book = "https://gutenberg.org/cache/epub/98/pg98.txt";
        // Connect to the book and get a Scanner
        Scanner bookScanner = connectToBook(book);

        if (bookScanner != null) {
            // Extracts words from the book
            DynamicArray wordsArray = extractWords(bookScanner);
            // Counts the number of unique words
            int uniqueWordCount = countUniqueWords(wordsArray);

            // Prints the number of unique words
            System.out.println("Number of unique words: " + uniqueWordCount);
        } else {
            // Handles the case where the book connection failed
            System.out.println("Failed to connect to the book.");
        }
    } // method main
} // class BookReview
