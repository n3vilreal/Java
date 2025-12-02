public class Cricket extends Game{
    public Cricket(){
        name = "Cricket";
        players = 11;
    }
    @Override
    public void play(){
        System.out.println("Cricket is being played");
    }
}
