package edu.utdallas.cometbooks.listing;

public class BookListingEntry {
    private String seller_contact_info;
    private ConditionEnum condition;
    private String description;
    private StatusEnum status;
    private double price;

    public void updateSuggestedPrice(double price)   {
        this.price = price;
    }

    public void updateListingInfo(ConditionEnum condition, double price, String description) {
        this.condition = condition;
        this.price = price;
        this.description = description;

    }

    public void updateStatus(StatusEnum status)   {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public StatusEnum getStatus() {
        return status;
    }
}
