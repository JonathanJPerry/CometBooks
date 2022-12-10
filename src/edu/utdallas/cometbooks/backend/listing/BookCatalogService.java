package edu.utdallas.cometbooks.backend.listing;

import edu.utdallas.cometbooks.data.listing.BookListingEntry;

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
}
