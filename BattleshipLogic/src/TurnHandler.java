public class TurnHandler {

    int current = 1;
    private Player[] players;

    public TurnHandler(Player p1, Player p2) {
        this.players = new Player[]{p1, p2};
    }

    public Player swapTurns() {
        indexTurnover();

        return players[current];
    }

    private int indexTurnover() {
        return current = ((current+1) % 2);
    }

    public String toString() {
        return "Current Player: [" + current + "] " + players[current];
    }

}
