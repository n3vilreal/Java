public class Main{
    public static void main(String[] args){
        IStudent iStudent = new Student();
        IStudent iGeneral = new General();
        IStudent iCollege = new College();
        iStudent.read();
        iCollege.read();
        iGeneral.read();
    }
}