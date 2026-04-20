public class Point {
    // attributes
    private double x;
    private double y;

    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance
    public static double distance(Point x, Point y) {
        double xDiff = x.x - y.x;
        double yDiff = x.y - y.y;
        xDiff = Math.pow(xDiff, 2);
        yDiff = Math.pow(yDiff, 2);
        return Math.sqrt(xDiff + yDiff);

    }
    @Override
    public String toString() {
    // format 2 decimals
    return String.format("(%.2f, %.2f)", x, y);
    
    }

}