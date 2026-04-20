public class Parallelogram implements Shape {
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    // constructor
    public Parallelogram(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    @Override
    public double area() {
        return 0;
    }
    @Override
    public double perimeter() {
        return 0;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Point getD() {
        return d;
    }
    
}