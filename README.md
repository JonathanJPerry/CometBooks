# CometBooks

This codebase hosts the back-end and mock-front-end for the CometBooks project. Only UC1 was to be implemented in this 
project. 

Two dummy users exist:
> username: ajs180009, password: password
> 
> username: jjp180000, password: password

The Use Case is defined as follows:

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

This was made for SE 6329 (Object-Oriented Software Engineering) at the University of Texas at Dallas, taught by Dr. Rym Zalila-Wenkstern