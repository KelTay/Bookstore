# Bookstore

This is a command line-based program that represents an imaginary bookstore. It accepts command line arguments to print out certain reports, which can be sorted or filtered in different ways.

The program gets a list of books, customers, and purchase history by parsing .dat and .csv files.

List of arguments:

-help or -h        Display help.

-customers or -c        Print the customer report.

-books or -b        Print the books report.

-purchases or -p        Print the purchase report.

-total or -t        When combined with the 'purchases' option, also print the total value of the purchases.

-by_author or -A        Sorts the books report by author in ascending order. This option is ignored if 'books' isn't also specified.

-by_lastname or -L        Sorts the purchases report by customer last name in ascending order. This option is ignored if 'purchases' isn't also specified.

-by_title or -T        Sorts the purchases report by book title in ascending order. This option is ignored if 'purchases' isn't also specified.

-by_join_date or -J        Sorts the customers report by join date in ascending order. This option is ignored if 'customers' isn't also specified.

-customer_id or -C        Filters the purchases report, showing only customers that match the customer id.

-desc or -d        Any sorted report is sorted in descending order. Must be combined with 'by_lastname', 'by_title', or 'by_join_date'


If no command line parameters are specified, all three reports (customer, books, purchase) are printed.
