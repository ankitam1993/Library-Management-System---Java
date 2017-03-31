
/**
 * This is a library class
 * which displays the menu to user 
 * for performing a paricular function
 * and thereby,calls the functions
 * @vikas
 * @v.0
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Library
{
    
    static int counter;

    /**
     * Constructor to initialise the counter which counts the number
     * of borrowers present in the library database.
     * It also starts the library system
     */
  public Library()
  {
        // initialise instance variables
        counter = 0;
        start();
        
  }
  
  /**
   * It is a start function of library, which is called first
   * as its object is created.It shows menu to user for 
   * registering,managing, deleting and listing of borrowers.
   */
  public void start()
  {
      BorrowerList bl = new BorrowerList();
      ArrayList<Borrower>tempAr = bl.getList();
      BookCollection bookc = new BookCollection();
      System.out.println('\u000c');
      readFile(bl);
      readBookFile(bookc);
      String option = "";
      System.out.println("***************************************************************************************************");
      System.out.println(counter + " borrower records were successfully imported into the database from \"borrowers.text\" ");
      System.out.println("***************************************************************************************************");
      String menu = "\nWelcome to My Library!"
             +"\n--------------------------------"
             +"\n--------------------------------"
             +"\n(1) Register a new Borrower"
             +"\n(2) Manage Borrower"
             +"\n(3) List all Borrowers"
             +"\n(4) Delete a Borrower"
             +"\n(5) Display Help"
             +"\n(6) Exit Library"
             +"\nChose an option 1/2/3/4/5/6 : ";          
    do
    {
          System.out.println(menu);
          Scanner input = new Scanner(System.in);
          option = input.next();
          switch(option) {
                            case "1":
                                   registerBorrower(bl);
                                   break;
                            case "2":
                                   manageBorrower(bl,bookc);
                                   break;
                            case "3":
                                   bl.listBorrowers();
                                   break;
                            case "4":
                                   deleteBorrowers(bl,tempAr);
                                   break;
                            case "5":
                                   System.out.println("This is the library management system");
                                   break;
                            case "6":
                                   writeToFile(bl);
                                   System.out.println("Have a nice day!!");
                                   break;
                            default:
                                   System.out.println("Wrong option");
                                   break; 
                        }
    } while (!option.equals("6"));
  }
  
  /**
   * It is a readfile function of library, which loads the borrowers
   * from the library database to the internal data structure.
   * It catches the exception, if file is not found.
   */
  public void readFile(BorrowerList bl) 
  {
     String fileName = "../borrower.txt";
     try
     {
         int index;
         Borrower borrower = new Borrower();
         FileReader inputFile = new FileReader(fileName); 
         Scanner parser = new Scanner(inputFile);
         while (parser.hasNextLine())
         {
          index = 0;
          String name = parser.nextLine();
          counter++ ;
          String[] parts = name.split(",");
          if (parts.length>3)
          {
             for(int i = 0; i < 3;i++)
             {
                index = name.indexOf(",",index + 1 );
             }
             borrower=setBorrower(parts[0],Integer.valueOf(parts[1]),Integer.valueOf(parts[2]),name.substring(index+1));         
          }
          else
             borrower=setBorrower(parts[0],Integer.valueOf(parts[1]),Integer.valueOf(parts[2]),"null"); 
             
          bl.addBorrower(borrower); 
         }
         
     }
     catch(FileNotFoundException exception)
     {
         System.out.println(fileName + "not found");
     }  
  }
  
  /**
   * This setBorrower function make a new borrower with some initial values
   */
  public Borrower setBorrower(String name, int Id, int age, String books)
  {
    Borrower borrower = new Borrower(name, Id, age, books);
    return borrower;  
  }
  
  /**
   * This function reads the books from the library database
   * and loads them into the internal data structure
   */
  public void readBookFile(BookCollection bookc) 
  {
     String fileName = "../books.txt";
     try
     {
         //Book bk = new Book();
         FileReader inputFile = new FileReader(fileName); 
         Scanner parser = new Scanner(inputFile);
         while (parser.hasNextLine())
         {
          String books = parser.nextLine();
          String[] parts = books.split("-");
          Book bk = new Book(parts[0],parts[1]);
          bookc.addBooks(bk);
         }                           
     }
     catch(FileNotFoundException exception)
     {
         System.out.println(fileName + "not found");
     }   
  }
  
  /**
   * This function registers a new borrower.
   * It takes the name, age ,and ID of a borrower
   * and give 2 options to the user for deciding its Id i.e, whether
   * he wants to chose by yourself or want to have the system generated ID
   */
  public void registerBorrower(BorrowerList bl)
  {
        int lasId = 0;
        String Idoption,borrowerID,borrowerAge;
        Borrower bor1 = new Borrower();
        System.out.println(" Enter the name of the Borrower: ");
        Scanner input1 =new Scanner(System.in);
        String borrowerName = input1.nextLine();
        borrowerName = validateName(borrowerName);
        
        String Idmenu = "*****************************************"
                       +"\nThere are 2 options for entering the ID"
                       +"\n1.Enter ID by yourself" 
                       +"\n2.Want to chose from available ID's"
                       +"\n****************************************";
                      
         System.out.println(Idmenu);
        do
        {
          System.out.println("Enter option1/2 :");
          Scanner input2 = new Scanner(System.in);
          Idoption = input2.nextLine();
        }while( Idoption.trim().isEmpty() || !Idoption.matches("[0-9]*") || Integer.parseInt(Idoption) < 1 || Integer.parseInt(Idoption) > 2);
        
          if (Integer.parseInt(Idoption) == 1)
           {
             System.out.println(" Enter the ID of Borrower(should be unique): ");
             Scanner input3 =new Scanner(System.in);
             borrowerID = input3.nextLine();
             borrowerID = validateID(borrowerID,bl);
            } 
           else   
           {
               int Id = 0,flag,i;
               char choice;
               ArrayList tempAr = bl.getList();
               do
                {
                    flag=0;
                    Id++;
                    for (i = 0; i < tempAr.size(); i++) 
                    {
                         Borrower tempBr = (Borrower) tempAr.get(i);
                         if (tempBr.getId() == Id)
                         {
                            flag = 1;  
                            break;
                         }
                    }
                    
                    if ( (flag != 1) && (i == tempAr.size() ) )
                    {
                        System.out.println("\n If you dont want to chose the available Id : " + Id + " press N/n, else press any key :");
                        Scanner input4 =new Scanner(System.in);
                        choice = input4.next().charAt(0);
                        lasId = Id;
                    }
                    else
                    {
                      choice = 'N';
                    }
                    
                } while( (choice == 'N' || choice =='n' ) && Id<100); 
         
                  if(lasId != 0 && Id >=100)
                   {
                    borrowerID = Integer.toString(lasId);
                    System.out.println("\nYou have been given the last available Id: " + lasId);
                   }
                   else
                      borrowerID = Integer.toString(Id);
           }
    
        System.out.println(" Enter the Age of Borrower: ");
        Scanner input5 =new Scanner(System.in);
        borrowerAge = input5.nextLine();
        borrowerAge = validateAge(borrowerAge);
        
        bor1=setBorrower(borrowerName,Integer.parseInt(borrowerID),Integer.parseInt(borrowerAge),"null");
        bl.addBorrower(bor1);
  }
  
  /**
   * This function validate the Name entered by the user while 
   * registering.It only accepts alphatets.
   */
  public String validateName(String borrowerName) 
  {
    while( !(borrowerName.matches("[a-zA-Z]*")) || (borrowerName.trim().equals("")))
    {
        System.out.println("invalid name: try again");
        Scanner input =new Scanner(System.in);
        borrowerName = input.nextLine();
    }
        return borrowerName;
  }
  
  /**
   * This function validate the ID entered by the user while 
   * registering.It Checks the entered ID should not be present in database
   */
  public String validateID(String borrowerID,BorrowerList bl) 
  {
        int i;
        ArrayList tempAr = bl.getList();
        for (i = 0; i < tempAr.size(); i++) 
        {
            Borrower tempBr = (Borrower) tempAr.get(i);
            if (borrowerID.equals("") || !borrowerID.matches("[0-9]*") || Integer.parseInt(borrowerID) < 1 || Integer.parseInt(borrowerID) > 100 || tempBr.getId() == Integer.parseInt(borrowerID))
            {
             System.out.println("invalid ID: try again");
             break;
            }
        }
        if(i < tempAr.size())
        {
        Scanner input =new Scanner(System.in);
        borrowerID = input.nextLine();
        borrowerID = validateID(borrowerID,bl);
        }
        return borrowerID;       
  }
  
  /**
   * This function validate the age entered by the user while 
   * registering.It Checks the entered age should be in between 
   * 5 to 110 years.
   */
  public String validateAge(String borrowerAge)
  {
        while (borrowerAge.equals("") || !borrowerAge.matches("[0-9]*") || Integer.parseInt(borrowerAge) <= 5 || Integer.parseInt(borrowerAge) >= 110 )
        {
            System.out.println("invalid Age(enter >5 < 110): try again");
            Scanner input =new Scanner(System.in);
            borrowerAge = input.nextLine();
            borrowerAge = validateAge(borrowerAge);
        }
        return borrowerAge; 
  }
  
  /**
   * This function manage the borrowers by giving them various 
   * options to borrow,return, and listing of borrowed books
   * It uses the search function : whether it wants to search by ID
   * or by the name of the borrower
   */
  public void manageBorrower(BorrowerList bl,BookCollection bookc)
  {
      ArrayList<Borrower>tempAr = bl.getList();
      Book book = new Book();
      String action;
      int option1;
      option1 = choseBorrower(tempAr,"manage");
      if (option1 == 999)
      {
             System.out.println("Borrower not present");
      }
      else
      {
            String menu2 =  "\n1.Borrow a Book "
                           +"\n2.Return a Book "
                           +"\n3.List Borrowed Books"
                           +"\n4.Return to main Menu"
                           +"\nChoose an option: ";    
        do
        {
              System.out.println(menu2);
              Scanner input = new Scanner(System.in);
              action = input.next();
              switch(action) {
                            case "1":
                                   bookc.displayBooksCollection();
                                   borrowBook(bl,bookc,option1);
                                   break;
                            case "2":
                                   bl.booksBorrowed(option1);
                                   returnBook(bl,option1);
                                   break;
                            case "3":
                                   bl.booksBorrowed(option1);
                                   break;
                            case "4":
                                   break;
                            default:
                                   System.out.println("Wrong option");
                                   break; 
                            }
         } while (!action.equals("4")); 
      }
  }
  
  /**
   * This is a search function which provides two options
   * to user, whether it wants to search by ID or name.
   * Then it returns the index of that particular borrower
   */
  public int choseBorrower(ArrayList tempAr, String work )
  {
      System.out.println("\nDo you want to search by name/Id of Borrower (Select 1 for name and 2 for Id ) 1/2 : "); 
      int i = 0,flag = 0;
      String option = "";
      do{
          flag = 0;
          Scanner input = new Scanner(System.in);
          option = input.next();
          if (option.equals("1"))
           {
              System.out.println("\nEnter the name of the borrower you want to "+ work + " : ");   
              Scanner input2 = new Scanner(System.in);
              String name = input2.nextLine();
              for (i = 0; i < tempAr.size(); i++)
              {
                  Borrower tempBr = (Borrower) tempAr.get(i);
                  if (name.equals(tempBr.getName()))
                  {
                      flag = 1;
                      break;
                    
                  }   
              } 
           }
          else if (option.equals("2"))
          {
              System.out.println("\nEnter the ID of the borrower you want to " + work + " : ");   
              Scanner input2 = new Scanner(System.in);
              int ID = input2.nextInt();
              for (i = 0; i < tempAr.size(); i++)
              {
                  Borrower tempBr = (Borrower) tempAr.get(i);
                  if (ID == tempBr.getId())
                  {
                    flag = 1;
                    break;
                  }
              } 
          }
          else
          {
           System.out.println("\nEnter the correct option 1/2 : ");
          }
        }while( (!option.equals("1")) && (!option.equals("2")) );
        
        if (flag == 0)
        {
          i = 999;        
        }
        return i;
  }
  
  /**
   * This function returns the book issued by the borrower
   * by asking the number of book which it wants to return
   */
  public void returnBook(BorrowerList bl,int option1)
   {
       String booksIssued,books = "",numberReturn;
       int flag=0;
       ArrayList<Borrower>tempAr = bl.getList();
       Borrower tempBr = (Borrower) tempAr.get(option1);
       booksIssued = tempBr.getBooks();
       if (booksIssued != "null")
     {
         
         do
        {
          System.out.println("Choose a number to return the book : ");
          Scanner input = new Scanner(System.in);
          numberReturn = input.nextLine();
        }
         while(numberReturn.trim().isEmpty() || !numberReturn.matches("[0-9]*") || Integer.parseInt(numberReturn) < 1);
         
          String[] parts = booksIssued.split(",");
          if((parts.length == 2) && (Integer.parseInt(numberReturn) == 1))
          {
              books = "null";
          }
          else
          {
            if ((Integer.parseInt(numberReturn) == 1) && (parts.length > 2) )
            {
              books = parts[2] + ","+ parts[3];
            
            }
            else if ((Integer.parseInt(numberReturn) == 2) && (parts.length > 2) )
              books = parts[0] + "," + parts[1];
            else
            {
              System.out.println("Please try again");  
              flag = 1;
            }
          }
          
         if (flag == 0)
         {
         tempAr.set(option1,new Borrower(tempBr.getName(),tempBr.getId(),tempBr.getAge(),books));
         bl.updateList(tempAr);
         }
     }       
  }
  
  /**
   * This function displays various books present in the database of the library
   * and gets a book number from the borrower to issue it.
   */
  public void borrowBook(BorrowerList bl,BookCollection bookc, int option1)
  {
       int numbBorr = 0;
       String numberBorrow = "";
       String booksIssued,books;
       ArrayList<Borrower>tempAr = bl.getList();
       ArrayList <Book> tempBk = bookc.getList();
       Borrower tempBr = (Borrower) tempAr.get(option1);
       booksIssued = tempBr.getBooks();
       
       String[] parts = booksIssued.split(",");
     do
     {
       System.out.println("Choose a number which book to borrow : ");
       Scanner input = new Scanner(System.in);
       numberBorrow = input.nextLine();
     }
     while(numberBorrow.trim().isEmpty() || !numberBorrow.matches("[0-9]*") || Integer.parseInt(numberBorrow) < 1 || Integer.parseInt(numberBorrow) > tempBk.size() );
     
       numbBorr = Integer.parseInt(numberBorrow);
       if(parts.length == 4)
       {
          System.out.println("Sorry: only 2 books allowed per borrower\n"); 
        
       }
       else
       {
        String bookTitle = tempBk.get(numbBorr-1).getBookTitle();
        String bookAuthor = tempBk.get(numbBorr-1).getBookAuthor();
        if(booksIssued != "null")
        booksIssued = booksIssued + "," + bookTitle + "," + bookAuthor ;
        else
        booksIssued = bookTitle + "," + bookAuthor ;
        
        tempAr.set(option1,new Borrower(tempBr.getName(),tempBr.getId(),tempBr.getAge(),booksIssued));
        bl.updateList(tempAr);
       }   
     
  }
  
  /**
   * This function deletes the borrower from the library database
   */
  public void deleteBorrowers(BorrowerList bl,ArrayList tempAr)
  {
    int index = choseBorrower(tempAr,"delete");
    if(index!=999)
    bl.deleteBorrower(index);
    else
    System.out.println("Borrower not present"); 
  }
  
  /**
   * This function after performing all the functions, update the library 
   * database.
   */
  public void writeToFile(BorrowerList bl)
  {
   String fileName = "/Users/ankitamehta/Desktop/borrower.txt";
     try
     {
         PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
          ArrayList <Borrower> tempAr = bl.getList();
           
           int size = tempAr.size();
           for (int i = 0; i < tempAr.size(); i++)
           {
               Borrower tempBr = (Borrower) tempAr.get(i);
               if(tempBr.getBooks() == "null")
               pw.println(tempBr.getName() + ","+ tempBr.getId() + "," + tempBr.getAge());
               else
               pw.println(tempBr.getName() + ","+ tempBr.getId() + "," + tempBr.getAge() + "," + tempBr.getBooks());
           }
         
           pw.close();
     }
     catch(FileNotFoundException exception)
     {
         System.out.println(fileName + "not found");
     }
   
  }
}