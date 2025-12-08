public class Basketball extends Game{
    public Basketball(){
        name = "Basketball";
        players = 6;
    }
    @Override
    public void play(){
        System.out.println(name + " needs " + players + " players.");
    }
}
