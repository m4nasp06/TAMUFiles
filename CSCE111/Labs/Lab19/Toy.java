public class Toy {
    private String name;
    private double price;
    private int lowerLimit;
    private int upperLimit;

    public Toy(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getLowerLimit() { return lowerLimit; }
    public int getUpperLimit() { return upperLimit; }

    public void setRecommendedAge(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public boolean isRecommended(int age) {
        return age >= lowerLimit && age <= upperLimit;
    }

    public boolean isInBudget(double budget) {
        return price < budget;
    }

    public String toString() {
        return String.format("%s is recommended for children %d to %d years old and costs $%.2f",
            name, lowerLimit, upperLimit, price);
    }
}