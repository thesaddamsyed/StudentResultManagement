public class Result {
    private int rollNo;
    private String subject;
    private int marks;

    public Result(int rollNo, String subject, int marks){
        this.rollNo = rollNo;
        this.subject = subject;
        this.marks = marks;
    }

    //getters
    public int getRollNo(){return rollNo;}
    public String getSubject(){return subject;}
    public int getMarks() { return marks;}

    //setters
    public void setRollNo(int rollNo){this.rollNo = rollNo;}
    public void setSubject(String subject) { this.subject = subject;}
    public void setMarks(int marks) { this.marks = marks;}
}
