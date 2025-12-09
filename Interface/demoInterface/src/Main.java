public class Main{
    public static void main(String[] args){
        IStudent iStudent = new Student();
        IStudent iGeneral = new General();
        IStudent iCollege = new College();
        ICollege iCollege_1 = new College();
        iStudent.read();
        iCollege.read();
        iGeneral.read();
        iCollege_1.department();
    }
}