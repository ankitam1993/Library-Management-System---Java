/**
 * This is a borrowerList class
 * It maintains the list of
 * borrowers
 * @Vikas 
 * @v.0
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class BorrowerList
{

  private ArrayList <Borrower> list; 
  /**
   * Constructor to initialise
   * the list of borrowers with zero value
   */
  BorrowerList()

     {
         list = new ArrayList < Borrower> ();
     }
 
  /**
   * Method to return the list
   * of borrowers
   */
  public ArrayList<Borrower> getList() 
     {
         return list;
     }
  
  /**
   * Method to add a borrower
   * to the list
   */
  public void addBorrower(Borrower borrower)
    {
         
         list.add(borrower);
    }
     
  /**
   * Method to update the list
   * of borrowers
   */
  public void updateList(ArrayList<Borrower> tempAr )
    {
        list = tempAr;
    }
    
  /**
   * Method to display the list 
   * of borrower : In this method it gets the name,ID,age and books issued by the borrower
   */
  public void listBorrowers()
    {
        int i = 0, j ;
        for(i = 0 ; i < list.size(); i++)
        {
          j = 0;
          String booksIssued = list.get(i).getBooks(); 
          if(booksIssued == "null")
          {
            System.out.println("\n Name  : " + list.get(i).getName());  
            System.out.println("\n ID#   : " + list.get(i).getId()); 
            System.out.println("\n Age   : " + list.get(i).getAge());  
            System.out.println("\n Books :\n " ); 
          }
          else
          {
             System.out.println("\n Name  : " + list.get(i).getName());  
             System.out.println("\n ID#   : " + list.get(i).getId()); 
             System.out.println("\n Age   : " + list.get(i).getAge());  
             System.out.println("\n Books : " );
             String[] parts = booksIssued.split(",");
             while(j < parts.length)
              {
                 System.out.println("\n    " + parts[j] + " - " + parts[j+1]); 
                 j = j + 2;
              }
          }
    
        }
  
    }
    
 /**
  * Method to display the list 
  * of books borrowed by a particular borrower
  */   
 public void booksBorrowed(int option1)
  {
      int i = 1,j = 0,flag = 0;
      Book book = new Book();
      String booksIssued = list.get(option1).getBooks();
      if (booksIssued != "null")
      {
           String[] parts = booksIssued.split(",");
           System.out.println("\n These are your current borrowed books : "
                             +"\n-----------------------------------------"
                             +"\n-----------------------------------------\n");
           while(j<parts.length)
           {
              System.out.println(i++ + "." + parts[j] + " - " + parts[j+1]);
              j = j + 2;
           }
           flag = 1;
      }
      else
      System.out.println("\n You dont have any books issued");
  }
 
 /**
  * Method to delete a particluar  
  * borrower from the list according to its index 
  */
public void deleteBorrower(int index)
  {
   list.remove(index);

  }
}