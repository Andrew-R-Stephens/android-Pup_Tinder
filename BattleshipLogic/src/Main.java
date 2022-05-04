public class Main {

    public static void main(String[] args) {

        TurnHandler handler = new TurnHandler(new Player(), new Player());

        System.out.println(handler);
        handler.swapTurns();
        System.out.println(handler);
        handler.swapTurns();
        System.out.println(handler);
        handler.swapTurns();
        System.out.println(handler);

        Grid grid = new Grid();
        grid.setStatus(3, 1, Grid.Status.HIT);

        System.out.println(grid);

    }

}
