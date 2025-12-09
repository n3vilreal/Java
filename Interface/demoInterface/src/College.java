public class College implements ICollege, IStudent{
    @Override
    public void department(){
        System.out.println("College Department");
    }
    @Override
    public void read(){
        System.out.println("College Laptop");
    }
}
