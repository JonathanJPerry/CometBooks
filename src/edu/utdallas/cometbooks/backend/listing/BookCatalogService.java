package edu.utdallas.cometbooks.backend.listing;

import edu.utdallas.cometbooks.data.listing.BookListingEntry;
import edu.utdallas.cometbooks.data.listing.ListingStatus;
import edu.utdallas.cometbooks.online_retailer.OnlineRetailerController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

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

    public void editListing(BookListingEntry original, BookListingEntry updated) {
        catalog.replaceListing(original, updated);
    }

    public void removeListing(BookListingEntry listing) {
        catalog.remove(listing);
    }

    public List<BookListingEntry> fetchActiveListingsBy(String netId) {
        return catalog.fetchActiveListingsBy(netId);
    }

    public List<BookListingEntry> fetchActiveListingsFor(String buyerNetId, List<String> isbns) {
        return isbns.stream()
                .flatMap(isbn -> catalog.fetchActiveListingsFor(isbn).stream())
                .filter(listing -> !listing.getSellerNetId().equals(buyerNetId))
                .collect(Collectors.toList());
    }

    public List<BookListingEntry> fetchActiveTransactionsBy(String netId) {
        return catalog.fetchActiveListingsBy(netId).stream()
                .filter(listing -> listing.getStatus() == ListingStatus.ON_HOLD)
                .collect(Collectors.toList());
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

    public void putOnHold(BookListingEntry entry) {
        catalog.updateStatus(entry, ListingStatus.ON_HOLD);
    }
}
