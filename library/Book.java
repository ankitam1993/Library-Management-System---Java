/**
 * This is class book
 * It maintains the title and
 * author of a particular book.
 * @Vikas 
 * @v.0
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Book 
{
   private String bookTitle; 
   private String bookAuthor;
   
   /** 
    * constructor to initialise
    * the variables of book class
    *  with empty string
   */
   Book()
    {
        bookTitle = "";
        bookAuthor = "";
    }
    
   /**Parametrized constructor to initialise
    * the title and author of book with some 
    * values
    */
   Book(String title, String author)
    {
        
        bookTitle = title;
        bookAuthor = author;
    
    }
    
   /**
    * It is a method to get the title 
    * of book
    */ 
    public String getBookTitle()
   {
       return bookTitle;
   }
   
   /**
    * It is a method to get the author 
    * of book.
    */ 
   public String getBookAuthor()
   {
       return bookAuthor;
   }
   
   /**
    * It is a method to get the title 
    * of book
    */ 
   public void setBookTitle(String title)
   {
       bookTitle = title;
   }
   
   /**
    * It is a method to set the author
    * of book with some value.
    */ 
   public void setBookAuthor(String author)
   {
       bookAuthor = author;
   }
}
