package edu.utdallas.cometbooks.listing;

public class BookListingEntry {
    private String seller_contact_info;
    private BookCondition condition;
    private String description;
    private ListingStatus status;
    private double price;

    public void updateSuggestedPrice(double price)   {
        this.price = price;
    }

    public void updateListingInfo(BookCondition condition, double price, String description) {
        this.condition = condition;
        this.price = price;
        this.description = description;

    }

    public void updateStatus(ListingStatus status)   {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public ListingStatus getStatus() {
        return status;
    }
}
