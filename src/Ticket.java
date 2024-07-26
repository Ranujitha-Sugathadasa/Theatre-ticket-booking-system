public class Ticket {  //creating ticket class file with attributes
    int row;
    int seat;
    int price;
    Person person;
    public Ticket(int row,int seat,Person person){ //attributes constructor
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
        switch(row){
            case 1:
                price =10;
                break;
            case 2:
                price=20;
                break;
            case 3:
                price=30;
        }
    }

    public Ticket() {  //default constructor
    }

    public void print(){
        System.out.println("Name: "+this.person.name+" "+this.person.surname);
        System.out.println("Email: "+this.person.email);
        System.out.println("Row number: "+this.row+"\tSeat number: "+this.seat);
        System.out.println("Ticket price: "+this.price);
    }


}
