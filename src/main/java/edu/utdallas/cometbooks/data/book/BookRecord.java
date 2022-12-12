package edu.utdallas.cometbooks.data.book;

public class BookRecord {
    public static class BookRecordBuilder {
        private String title;
        private String isbn;
        private int year;
        private String author;
        private double suggestedRetailPrice;

        public BookRecordBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookRecordBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookRecordBuilder year(int year) {
            this.year = year;
            return this;
        }

        public BookRecordBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookRecordBuilder suggestedRetailPrice(double suggestedRetailPrice) {
            this.suggestedRetailPrice = suggestedRetailPrice;
            return this;
        }

        public BookRecord build() {
            return new BookRecord(title, isbn, year, author, suggestedRetailPrice);
        }
    }

    public static BookRecordBuilder builder() {
        return new BookRecordBuilder();
    }

    private final String title;
    private final String isbn;
    private final int year;
    private final String author;
    private final double suggestedRetailPrice;

    BookRecord(String title, String isbn, int year, String author, double suggestedRetailPrice) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.author = author;
        this.suggestedRetailPrice = suggestedRetailPrice;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public double getSuggestedRetailPrice() {
        return suggestedRetailPrice;
    }
}
