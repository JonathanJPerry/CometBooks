package edu.utdallas.cometbooks.backend.listing;

import edu.utdallas.cometbooks.data.listing.BookListingEntry;
import edu.utdallas.cometbooks.online_retailer.OnlineRetailerController;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public final class BookCatalogService {

    public static BookCatalogService createWith(BookCatalog catalog) {
        return new BookCatalogService(catalog);
    }

    private final BookCatalog catalog;

    private BookCatalogService(BookCatalog catalog) {
        this.catalog = catalog;
    }

    public void addListing(BookListingEntry listing) {
        catalog.addListing(listing);
    }

    public List<BookListingEntry> fetchActiveListingsBy(String netId) {
        return catalog.fetchActiveListingsBy(netId);
    }

    public double fetchSuggestedPrice(String isbn, List<OnlineRetailerController> onlineRetailers) {
        List<BookListingEntry> soldListings = catalog.fetchSoldListingsFor(isbn);

        DoubleStream onlineRetailersStream = onlineRetailers.stream()
                .mapToDouble(controller -> controller.fetchSuggestedPrice(isbn));
        DoubleStream soldListingsStream = soldListings.stream()
                .mapToDouble(BookListingEntry::getPrice);

        return DoubleStream.concat(onlineRetailersStream, soldListingsStream)
                .average()
                .orElse(0.0);
    }
}
