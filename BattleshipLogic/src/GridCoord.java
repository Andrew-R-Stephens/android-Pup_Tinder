public class GridCoord {

    private int status = Grid.Status.EMPTY.ordinal();

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String toString() {
        return " " + Grid.Status.values()[status];
    }

}
