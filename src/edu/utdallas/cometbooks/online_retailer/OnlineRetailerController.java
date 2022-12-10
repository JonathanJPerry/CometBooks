package edu.utdallas.cometbooks.online_retailer;

public final class OnlineRetailerController {
    public static OnlineRetailerController withMockPrice(double price) {
        return new OnlineRetailerController(price);
    }

    private final double mockPrice;

    private OnlineRetailerController(double mockPrice) {
        this.mockPrice = mockPrice;
    }

    public double fetchSuggestedPrice(String isbn) {
        return mockPrice;
    }
}
