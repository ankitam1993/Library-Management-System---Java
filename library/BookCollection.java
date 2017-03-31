 
/**
 * This class maintains the list of books
 * 
 * @Vikas 
 * @v.0
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class BookCollection
{
    
    private ArrayList < Book> bookList;

    /**
     * Constructor to initialise the list of books
     */
    BookCollection()
    {

        bookList = new ArrayList <Book> ();
    }

    /**
     * This method returns the list of maintained books
     */
    public ArrayList<Book> getList() 
     {
         return bookList;
     }
     
    /**
     * This method add books to the books list
     */

    public void addBooks(Book book)
    {
        bookList.add(book);
    }
    
    /**
     * This method displays the list of maintained books
     */

    public void displayBooksCollection()
    {
        int i, j = 1;
        System.out.println("These are the available books\n");
        System.out.println("-------------------------------");
        System.out.println("-------------------------------\n");
        for(i = 0 ; i < bookList.size() ; i++)
        {
          String Title = bookList.get(i).getBookTitle();
          String Author = bookList.get(i).getBookAuthor();
          System.out.println(j++ + "." + Title + " - " + Author);   
          
        }
     }
}
