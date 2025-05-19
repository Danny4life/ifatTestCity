public class City implements Comparable<City> {

    private String name;
    private String country;
    private int population;
    private double area;

    public City(String name, String country, int population, double area) {
        this.name = name;
        this.country = country;
        this.population = population;
        this.area = area;
    }


    public String getName() { return name; }
    public String getCountry() { return country; }
    public int getPopulation() { return population; }
    public double getArea() { return area; }

    public double getDensity() {
        return population / area;
    }

    @Override
    public int compareTo(City other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("%s(%s) population: %d area: %.0f", name, country, population, area);
    }
}
