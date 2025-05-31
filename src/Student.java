public class Student {
    private int rollNo;
    private String name;
    private String department;

    public Student(int rollNo, String name, String department){
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
    }

    //getters
    public int getRollNo(){ return rollNo;}
    public String getName(){ return name;}
    public String getDepartment() { return department;}

    //setters
    public void setRollNo(int rollNo){ this.rollNo =rollNo; }
    public void setName(String name){ this.name =name; }
    public void setDepartment(String department){ this.department =Student.this.department; }
}
