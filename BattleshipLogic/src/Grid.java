public class Grid {

    public enum Status {
        EMPTY,
        SHIP,
        MISS,
        HIT
    }

    private final GridCoord[][] grid = new GridCoord[7][7];

    public Grid() {
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                grid[i][j] = new GridCoord();
            }
        }
    }

    public void setStatus(int coordX, int coordY, Status status) {
        grid[coordY][coordX].setStatus(status.ordinal());
    }

    public int getStatusIndex(int coordX, int coordY) {
        return grid[coordY][coordX].getStatus();
    }

    public Status getStatus(int coordX, int coordY) {
        return Status.values()[getStatusIndex(coordX, coordY)];
    }

    public GridCoord[][] getGrid() {
        return grid;
    }

    public String toString() {
        String t = "Current grid\n";

        for(GridCoord[] go : grid) {
            t += "| ";
            for(GridCoord g: go) {
                t += String.format("%6s", g) + " | ";
            }
            t += "\n";
        }

        return t;
    }

}
