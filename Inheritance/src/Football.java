public class Football extends Game{
    public Football(){
        name = "Football";
        players = 10;
    }
    @Override
    public void play(){
        System.out.println(name + " needs " + players + " players.");
    }
}
