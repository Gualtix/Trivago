package EDD;


public class Employee {

    private int ID;
    private static Employee Inst = null;

    private Employee(){
        ID = 200;
    }

    public static Employee getInstance(){
        if(Inst == null){
            Inst = new Employee();
        }
        return Inst;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void Increment(){
        this.ID++;
    }


   /*

    String Name;
    int Age;
    String Mail;

    public Employee(String Name,int Age,String Mail){
        this.Namec    0e;
        this.Age = Age;
        this.Mail = Mail;
    }
    */
}
