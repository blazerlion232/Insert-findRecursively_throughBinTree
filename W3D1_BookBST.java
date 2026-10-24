
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class W3D1_BookBST {

    private Node root;

    // Inner class for the tree node
    private class Node {
        Book element;
        Node left;
        Node right;

        Node(Book element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(Book book) {
        root = insertRecursive(root, book);
    }

    private Node insertRecursive(Node current, Book book) {
        // TODO: Implement the insert recursive logic.
        return null; // Placeholder - replace with your implementation
    }

    public boolean find(Book book) {
        return findRecursive(root, book) != null;
    }

    private Node findRecursive(Node current, Book book) {
        // TODO: Implement the find recursive logic.
        return null; // Placeholder - replace with your implementation
    }

    // Helper method to get the size of the tree
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node current) {
        if (current == null) {
            return 0;
        }
        return 1 + sizeRecursive(current.left) + sizeRecursive(current.right);
    }

    // Helper method for in-order traversal (for debugging)
    public void inOrderTraversal() {
        System.out.println("In-order traversal:");
        inOrderTraversalRecursive(root);
        System.out.println();
    }

    private void inOrderTraversalRecursive(Node current) {
        if (current != null) {
            inOrderTraversalRecursive(current.left);
            System.out.print(current.element.getTitle() + " | ");
            inOrderTraversalRecursive(current.right);
        }
    }

    // Main method to test the BST
    public static void main(String[] args) {
        W3D1_BookBST bst = new W3D1_BookBST();
        List<Book> books = loadBooksFromCSV("books.csv");
        
        System.out.println("Loading books into BST...");
        // Insert first 100 books to make testing faster
        int count = 0;
        for (Book book : books) {
            bst.insert(book);
            count++;
            if (count >= 100) break; // Limit for testing purposes
        }
        
        System.out.println("Loaded " + bst.size() + " books into BST.");
        
        System.out.println("\nTesting find operation:");
        
        // Test cases using books that actually exist in the CSV
        Book found1 = new Book("I, Robot", "Isaac Asimov", 1950);
        Book found2 = new Book("The Dark Is Rising", "Susan Cooper", 1973);
        Book found3 = new Book("the lightning thief", "Rick Riordan", 2005); // Test case sensitivity
        
        // Books that don't exist in the dataset
        Book notFound1 = new Book("Non-existent Book", "Unknown Author", 2023);
        Book notFound2 = new Book("1984", "George Orwell", 1949); // This book is not in the dataset

        // Test existing books
        System.out.println("=== Testing books that SHOULD be found ===");
        System.out.println("Found 'I, Robot'? " + bst.find(found1));
        System.out.println("Found 'The Dark Is Rising'? " + bst.find(found2));
        System.out.println("Found 'the lightning thief' (lowercase test)? " + bst.find(found3));
        
        // Test non-existing books
        System.out.println("\n=== Testing books that should NOT be found ===");
        System.out.println("Found 'Non-existent Book'? " + bst.find(notFound1));
        System.out.println("Found '1984'? " + bst.find(notFound2));
        
        // Uncomment to see the sorted order of books
        // bst.inOrderTraversal();
    }
    
    private static List<Book> loadBooksFromCSV(String filePath) {
        List<Book> books = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                // Use a regex to split by comma, but ignore commas inside quotes
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (values.length >= 4) {
                    try {
                        // Remove quotes from fields that have them
                        String title = values[1].replaceAll("^\"|\"$", "");
                        String author = values[2].replaceAll("^\"|\"$", "");
                        int year = Integer.parseInt(values[3].trim());
                        books.add(new Book(title, author, year));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line (year parse error): " + line);
                    }
                } else {
                     System.err.println("Skipping malformed line (not enough columns): " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}