public class Rectangle extends Parallelogram {
    // constructor
    public Rectangle(Point a, Point b, Point c, Point d) {
        super(a, b, c, d);
    }

    // methods
    @Override
    public double area() {
        double lenAB = Point.distance(getA(), getB());
        double lenBC = Point.distance(getB(), getC());
        return lenAB * lenBC;
    }

    @Override
    public double perimeter() {
        double lenAB = Point.distance(getA(), getB());
        double lenBC = Point.distance(getB(), getC());
        return 2 * (lenAB + lenBC);
    }

}
