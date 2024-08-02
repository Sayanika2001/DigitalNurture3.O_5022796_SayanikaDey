public class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

class LibraryManagementSystem {
    private Book[] books;

    public LibraryManagementSystem(Book[] books) {
        this.books = books;
    }

    public Book linearSearchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Book binarySearchByTitle(String title) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (books[mid].getTitle().equals(title)) {
                return books[mid];
            } else if (books[mid].getTitle().compareTo(title) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}

 class output {
    public static void main(String[] args) {
        Book[] books = new Book[] {
                new Book(1, "Book A", "Author A"),
                new Book(2, "Book B", "Author B"),
                new Book(3, "Book C", "Author C"),
                new Book(4, "Book D", "Author D"),
                new Book(5, "Book E", "Author E")
        };

        LibraryManagementSystem library = new LibraryManagementSystem(books);

        System.out.println("Linear Search:");
        Book linearSearchResult = library.linearSearchByTitle("Book C");
        if (linearSearchResult != null) {
            System.out.println("Found: " + linearSearchResult);
        } else {
            System.out.println("Not found");
        }

        System.out.println("\nBinary Search:");
        Book binarySearchResult = library.binarySearchByTitle("Book C");
        if (binarySearchResult != null) {
            System.out.println("Found: " + binarySearchResult);
        } else {
            System.out.println("Not found");
        }
    }
}