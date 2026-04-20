public class Square extends Rectangle {

    public Square(Point a, Point b, Point c, Point d) {
        super(a, b, c, d);
    }
    boolean isValid() {
        // checks if all 4 sides of the square are equal within a tolerance of 0.00001.
        double side1 = Point.distance(getA(), getB());
        double side2 = Point.distance(getB(), getC());
        double side3 = Point.distance(getC(), getD());
        double side4 = Point.distance(getD(), getA());
        return Math.abs(side1 - side2) < 0.00001 && Math.abs(side2 - side3) < 0.00001 && Math.abs(side3 - side4) < 0.00001;

    }
}