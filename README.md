# CometBooks

This codebase hosts the back-end and mock-front-end for the CometBooks project. Only UC1 was to be implemented in this 
project. 

## Setup
To run the project, use the `edu.utdallas.cometbooks.frontend.CometBooksFrontEnd` class to run the project. This will
create an instance of the backend that will be used by the front-end, communicating only through the 
`edu.utdallas.cometbooks.backend.Controller` class.

Two dummy users exist:
> - username: ajs180009, password: password
> - username: jjp180000, password: password

Users are defined in the `edu.utdallas.cometbooks.backend.CometBooksBackEnd` file as:
```java
    private static final StudentDatabase STUDENTS_DATABASE = StudentDatabase.createWith(
            UTDStudent.builder()
                    .netId("ajs180009")
                    .password("password")
                    .name("Arham J. Siddiqui")
                    .courseBook("978-0-321-87758-1")
                    .courseBook("978-0-321-87759-8")
                    .build(),
            UTDStudent.builder()
                    .netId("jjp000000")
                    .password("password")
                    .name("Jonathan J. Perry")
                    .courseBook("978-0-321-87758-1")
                    .courseBook("978-0-321-87759-8")
                    .build()
    );
```

Two dummy books exist as well:
> - UML in Practice The Art of Modeling Software Systems Demonstrated through Worked Examples and Solutions (2006) by Pascal Roques 
> - Computer Architecture: A Quantitative Approach, 6th Edition (2019) by John L. Hennessy & David A. Patterson 

Books are also defined in the `CometBooksBackEnd` file as:
```java
    private static final BookDatabase BOOK_DATABASE = BookDatabase.createWith(
            Book.builder()
                    .isbn("978-0-321-87758-1")
                    .title("UML in Practice The Art of Modeling Software Systems Demonstrated through Worked Examples and Solutions")
                    .author("Pascal Roques")
                    .year(2006)
                    .build(),
            Book.builder()
                    .isbn("978-0-321-87759-8")
                    .title("Computer Architecture: A Quantitative Approach, 6th Edition")
                    .author("John L. Hennessy & David A. Patterson")
                    .year(2019)
                    .build()
    );
```

Both dummy users have both books as their course books.

Alternatively, listings and transactions may also be pre-populated in the `CometBooksBackEnd` class as well.

## UC1 - Sell Book

The main use case is defined as follows:

> **Brief description**: The seller lists a book to sell, messages with the buyer, and finishes when the book has been sold.
> 
> **Primary actors**: Seller
> 
> **Secondary actors**: Buyer
> 
> **Preconditions**: The seller is logged in to the system.
> 
> **Main flow**:
> 1. The seller clicks My Books For Sale button. 
> 2. The system displays the books listed for sale by the seller. 
> 3. The seller clicks the “+” icon to add a new book listing. 
> 4. The system shows a page to enter the new book listing details. 
> 5. The seller selects a book. 
> 6. The seller selects the condition for the book. 
> 7. The seller sets the price for the book. 
> 8. The seller enters the description for the book. 
> 9. The seller clicks to list the book for sale. 
> 10. The system shows a success message. 
>
> *extension point: Update Listing*
> 
> *extension point: Remove Listing*
> 
> 11. The buyer puts the book on hold. 
> 
> *extension point: Cancel Hold*
> 
> *include(Exchange Message)*
> 
> 12. The seller clicks the “shopping cart” icon to go to transactions. 
> 13. The seller selects the transaction. 
> 14. The system views the transaction details. 
> 15. The buyer clicks the “complete” button to complete the buying of the book. 
> 16. The seller clicks the “complete” button to complete the selling of the book. 
> 17. The system adds the transaction to the buyer’s and the seller’s transaction history. 
> 
> **Postconditions**: The book has been sold to the buyer. 
> 
> **Alternative Flow**: None

#### UC3 - Exchange Messages

> **Brief description**: The user sends messages to or receives messages from others.
>
> **Primary actors**: Buyer or Seller
>
> **Secondary actors**: User
>
> **Preconditions**: The buyer or seller is logged in.
>
> **Main flow**:
> 1. While the buyer or seller needs to communicate with the other user then 
>    1. The buyer or seller sends a message to the other user 
>    2. The buyer or seller receives a message from the other user
>
> **Postconditions**: The buyer or seller agrees with another user to buy or sell a book.

#### UC4 - Update Listing

> **Brief description**: The seller updates the information such as condition or price of the book listing.
>
> **Primary actors**: Seller
>
> **Secondary actors**: None
>
> **Segment 1 preconditions**: The system has already added a book listing.
>
> **Segment 1 flow**:
> 1. The seller clicks My Books For Sale button. 
> 2. The system displays the books listed for sale by the seller. 
> 3. The seller clicks a book listing. 
> 4. The system shows the details of the book listing. 
> 5. The seller updates the book listing details. 
> 6. The system shows the updated listing.
>
> **Segment 1 postconditions**: The listing is updated by the seller.

#### UC5 - Remove Listing

> **Brief description**: The seller removes the book listing.
>
> **Primary actors**: Seller
>
> **Secondary actors**: None
>
> **Segment 1 preconditions**: The system has already added a book listing.
>
> **Segment 1 flow**:
> 1. The seller clicks My Books For Sale button. 
> 2. The system displays the books listed for sale by the seller. 
> 3. The seller clicks a book listing. 
> 4. The system shows the details of the book listing. 
> 5. The seller clicks the “trash” button in the book listing details page. 
> 6. The system removes the listing.
>
> **Segment 1 postconditions**: The listing is removed by the seller.

#### UC6 - Cancel Hold

> **Brief description**: The user (buyer or seller) removes the hold on the book.
>
> **Primary actors**: User (buyer or seller)
>
> **Secondary actors**: None
>
> **Segment 1 preconditions**: The user has an ongoing book transaction.
>
> **Segment 1 flow**:
> 1. The user clicks the My Transactions button. 
> 2. The system displays the book transactions of the user. 
> 3. The user clicks a book transaction. 
> 4. The system shows the details of the book transaction and the parties involved. 
> 5. The user clicks the “cancel transaction” button in the book listing details page. 
> 6. The system removes the transaction and removes the hold from the book listing.
>
> **Segment 1 postconditions**: The hold is removed.

This was made for SE 6329 (Object-Oriented Software Engineering) at the University of Texas at Dallas, taught by Dr. Rym Zalila-Wenkstern