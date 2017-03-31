/**
 * This is a borrower class
 * It maintains the name,age
 * ID and books borrowed by him/her.
 * @Vikas 
 * @v.0
 */
import java.util.Scanner;
public class Borrower
{
    private String name;
    private int ID;
    private int age;
    private String books;
   
  /**
   * Constructor to initialise
   * the fields of borrower with zero value
   */
   Borrower()
    {
    name = "";
    ID = 0;
    age = 0;
    books = "";
    } 
    
   /**
    * Constructor to initialise
    * the fields of borrower with some value
    */
   Borrower(String a, int b, int c, String d)
    {
    name = a;
    ID = b;
    age = c;
    books = d;
    }
    
   /**
    * Method to return the name 
    * of borrower
    */
     public String getName()
    {
        return name;
    }
    
    /**
    * Method to set the name 
    * of borrower
    */

    public void setName(String borrowerName)
    {
        name = borrowerName;
            
    }
    
    /**
    * Method to return the ID 
    * of borrower
    */

    public int getId()
    {
        return ID;
        
    }
    
    /**
    * Method to set the ID
    * of borrower
    */

    public void setId(int borrowerID)
    {
        ID = borrowerID;
    }
    
    /**
    * Method to return the age
    * of borrower
    */

    public int getAge()
    {
        return age;
    }
    
    /**
    * Method to set the age 
    * of borrower
    */

    public void setAge(int borrowerAge)
    {
        age= borrowerAge;
        
    }
    
    /**
    * Method to set the books issued 
    * by borrower
    */

    public void setBooks(String borrowerbooks)
    {
        
        books = borrowerbooks;
    }
    
    /**
    * Method to get the books
    * issued by borrower
    */

    public String getBooks()
    {
        return books;
    }
    
    /**
    * Method to display the information
    * of borrower
    */

    public void display()
    {
        System.out.println("Name of the borrower is "+name);
        System.out.println("Age of the borrower is "+age);
        System.out.println("ID of the borrower is "+ID);
                         
    }
}

    
        
    
            
            
        