//
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Theatre{
    public static void main(String[] args) {
            int [][] rowInfo = new int[3][];  //adding ragged arrays
            rowInfo[0]=new int[12];
            rowInfo[1]=new int[16];
            rowInfo[2]=new int[20];

            ArrayList<Ticket> ticketsList = new ArrayList<>(); //creating the new arraylist

            System.out.println("Welcome to the New Theatre");

            String menu= """
                    (-------------------------------------------------)
                    \tPlease select an option:
                    \s
                    1) Buy a ticket\s
                    2) Seating area\s
                    3) Cancel ticket
                    4) List available seats\s
                    5) Save to file\s
                    6) Load from file\s
                    7) Print ticket information and total price\s
                    8) Sort tickets by price\s
                    \t0) Quit\s
                    -------------------------------------------------\s""";
            System.out.println(menu);

            Scanner input = new Scanner(System.in);
            int num;
            int Integer=0;        //validation about options
            do {
                num = integerValidation("Enter option: ",0,8,input);
                if(num != Integer){
                    System.out.println();
                }
                else{
                    System.out.println();
                }

                switch (num){
                    case 0:
                        break;
                    case 1:
                        buy_Ticket(rowInfo, input,ticketsList);
                        break;
                    case 2:
                        print_seating_area(rowInfo);
                        break;
                    case 3:
                        cancel_ticket(rowInfo,ticketsList);
                        break;
                    case 4:
                        show_available(rowInfo);
                        break;
                    case 5:
                        save(rowInfo);
                        break;
                    case 6:
                        load(rowInfo);
                        break;
                    case 7:
                        showTicketsInfo(ticketsList);
                        break;
                    case 8:
                        sortTickets(ticketsList);
                        break;
                }
            }while (num != 0);
    }
    static void buy_Ticket(int[][] array, Scanner scanner, ArrayList<Ticket>seatingList){
        int rowNum,seatNum=0;
        System.out.print("Enter first name: ");  //adding details about the ticket you purchased
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        String email = emailValidation(scanner,"Enter email:");
        Person personInfo = new Person(name,surname,email);
        while(true) {                                        //checking input are valid or invalid in rows and seats
            rowNum = integerValidation("Enter row: ",1,3,scanner);
            if(rowNum==1)
                seatNum=integerValidation("Enter seat: ", 1,12,scanner);
            else if (rowNum==2) {
                seatNum=integerValidation("Enter seat: ",1,16,scanner);
            }
            else if(rowNum==3) {
                seatNum=integerValidation("Enter seat: ",1,20,scanner);}
            if(array[rowNum-1][seatNum-1]==1) {
                System.out.println("This seat is unavailable");
            }
        else {
            array[rowNum-1][seatNum-1]=1;
                Ticket ticketInfo = new Ticket(rowNum,seatNum,personInfo);
                seatingList.add(ticketInfo); // adding details of the tickets to the list
            break;
        }
     }
    }    
        
    static void print_seating_area(int [][] array){
        int middleindex;
        int count=0;
        System.out.println("      *************");
        System.out.println("      *   STAGE   *");
        System.out.println("      *************\n");

        for (int i = 0; i < array.length; i++) {//print the taken seats as "x" and available seats in "0".
            middleindex =  array[i].length/2;
            for (int j = 0; j < array[i].length; j++){
               if (i==0 && j ==0)
                   System.out.print("    ");
               else if (i==1 && j == 0)
                   System.out.print("  ");
               else if (count == middleindex)
                   System.out.print("    ");
              if (array[i][j] == 0)
                  System.out.print("O");
              else System.out.print("X");
              count ++;
            }
            System.out.println();
            count = 0;
        }
    }
    static void cancel_ticket(int [][] array, ArrayList<Ticket>list){
        Scanner myScanner = new Scanner(System.in);  //Entering the row number and seat number to cancel
        System.out.print("Enter row: ");
        int row = myScanner.nextInt();
        System.out.print("Enter seat: ");
        int seat = myScanner.nextInt();
        System.out.print("Are you sure you want to cancel seat number "+seat+" on row "+row+"(y/n): ");
        if(myScanner.next().equalsIgnoreCase("y")) { //cancelling the tickets
            if(array[row-1][seat-1]==1) {
                array[row-1][seat-1]=0;
                System.out.println("Your ticket has been cancelled successfully");//removing the cancel ticket from tickets list.
                for(int i=0; i<list.size(); i++) {
                    if(list.get(i).row==row && list.get(i).seat==seat)
                        list.remove(i);
                }
            }
            else
                System.out.println("This ticket is not reserved");
        }
    }
    static void show_available(int [][] seating_array){
        // showing the available seats after you purchased
        for(int i=0;i<seating_array.length;i++){
            System.out.println("Seats available in row "+(i+1)+" are: ");
            for (int j=0;j<seating_array[i].length;j++){
                if (seating_array[i][j]==0)
                    System.out.print((j+1)+", ");
                }
            System.out.println();
            }
        }

    static void save(int [][] seating_array){
        //save the tickets for the file
        try {
            File file = new File("Seating Arrangement.txt");
            FileWriter writeToFile = new FileWriter(file.getName());
            for(int i=0; i<seating_array.length; i++) {
                for(int j=0; j<seating_array[i].length; j++) {
                    writeToFile.write(seating_array[i][j]+"\n");
                }
            }
            writeToFile.close();
        }
        catch (IOException e) {
            System.out.println("There was an error in creating the file");
        }


    }
    static void load(int [][] seating_array){
        //load the file to the programme from saved place
        try {
            File file = new File("Seating Arrangement.txt");
            Scanner fileReader = new Scanner(file);
            for(int i=0; i< seating_array.length; i++) {
                for(int j=0; j<seating_array[i].length; j++) {
                    int seat = Integer.parseInt(fileReader.nextLine());
                    seating_array[i][j] = seat;
                }
            }
        }
        catch (IOException e) {
            System.out.println("An error has occurred");
        }

    }
    public static void showTicketsInfo(ArrayList<Ticket>seatingList)
    {
        int sumOfTickets = 0;                         //showing the ticket details u purchased.
        for(int i=0; i< seatingList.size(); i++) {
            System.out.println("-------------------------");
            seatingList.get(i).print();
            System.out.println("-------------------------");
            sumOfTickets+=seatingList.get(i).price;
        }
        System.out.println("The total price of the tickets are: $"+sumOfTickets);
    }
    public static void sortTickets(ArrayList<Ticket>seatingList) {
        int limit = seatingList.size()-2;
        boolean exchanged = true;
        new Ticket();
        Ticket temp;
        while(exchanged) {
            exchanged = false;
            for(int i=0; i<=limit; i++) {    //sorting the seating list by ticket price in ascending order
                if(seatingList.get(i).price>seatingList.get(i+1).price) {
                    temp = seatingList.get(i);
                    seatingList.set(i,seatingList.get(i+1));
                    seatingList.set(i+1,temp);
                    exchanged = true;
                }
            }
            limit--;
        }
        showTicketsInfo(seatingList);
    }
    static int integerValidation(String message,int min, int max, Scanner scanner) {

        int var=0;
        boolean responseValidity = true;  //integer validation to validate the inputs
        while(responseValidity) {
            System.out.print(message);
            try {
                var = Integer.parseInt(scanner.next());
                if(var<min || var>max)
                    System.out.println("Invalid row number. Please enter between 1-3");
                else {
                    responseValidity=false;
                }

            }
            catch (NumberFormatException e1) {
                System.out.println("Please enter valid option");
            }
        }
        return var;
    }
    public static String emailValidation(Scanner myScanner, String message) {
        //Checking the emails are valid or not by using "@" and ".com" signs.
        String mail;
        while(true) {
            System.out.print(message);
            mail = myScanner.next();
            Pattern pattern = Pattern.compile("@");
            Pattern pattern1 = Pattern.compile(".com");
            Matcher matcher = pattern.matcher(mail);
            Matcher matcher1 = pattern1.matcher(mail);
            boolean matchFound = (matcher).find();
            boolean isThere = (matcher1).find();
            if(matchFound && isThere) {
                break;
            }
            else {
                System.out.println("This email does not exist. Please enter valid email");
            }
        }
        return mail;
    }
}