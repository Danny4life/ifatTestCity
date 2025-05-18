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


    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDensity(){
        return population / area;
    }

    // the compareTo method will allow us to compare names of different cities
    @Override
    public int compareTo(City other) {
        return this.name.compareTo(other.name);
    }



    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                ", area=" + area +
                '}';
    }
}
