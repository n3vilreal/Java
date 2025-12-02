public class Main{
    public static void main()
    {
        Game game = new Game();
        Game football = new Football();
        Game cricket = new Cricket();
        Game basketball = new Basketball();
        game.play();
        football.play();
        cricket.play();
        basketball.play();
    }
}