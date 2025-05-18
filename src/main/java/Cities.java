import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Cities {

    // this hold a collection of cities
    private List<City> cities = new ArrayList<>();


    public void init(String fileName){

        try(Scanner scanner = new Scanner(new File(fileName))){

            scanner.useDelimiter("\\t/\\n");

            while (scanner.hasNext()){

                String name = scanner.next();
                String country = scanner.next();
                int population = Integer.parseInt(scanner.next());
                double area = Double.parseDouble(scanner.next());

                cities.add(new City(name, country, population, area));

            }

        }catch (FileNotFoundException e){
            System.out.println("File not found: " + fileName);
        }

    }

    public void  listAlphabetically(){
        System.out.println("\nCity list\n");

        List<City> sorted = new ArrayList<>(cities);
        Collections.sort(sorted);

        for(City city : sorted){
            System.out.println(city);

        }

    }

    public void listByCountry(String country){
        System.out.println("\nCities in " + country + ":\n");

        for(City city : cities){
            if(city.getCountry().equals(country)){
                System.out.println(city);

            }

        }
    }

    public void listByPopulation(){
        System.out.println("\nCity list by population\n");
        List<City> sorted = new ArrayList<>(cities);
        sorted.sort(new PopulationComparator());

        for(City city : sorted){
            System.out.println(city);

        }
    }

    public void listByDensity(){
        System.out.println("\nCity list by density\n");

        List<City> sorted = new ArrayList<>(cities);
        sorted.sort(new DensityComparator());

        for(City city : sorted){
            System.out.printf("%s density: %.0f\n", city, city.getDensity());

        }
    }


    // the -> arrow key signify a lambda expression
    // a lambda expression is a shorter of iterating over a large dataset
    public Map<String, List<City>> mapByCountry(){
        Map<String, List<City>> map = new HashMap<>();

        for(City city : cities){
            map.computeIfAbsent(city.getCountry(), k -> new ArrayList<>()).add(city);

        }

        return map;
    }

    public void printMapByCountry(){
        Map<String, List<City>> map = mapByCountry();

        for(String country : map.keySet()){
            System.out.println("\n" + country + ":");

            for (City city : map.get(country)){
                System.out.println(city);

            }

        }
    }

    private static class PopulationComparator implements Comparator<City>{

        @Override
        public int compare(City c1, City c2) {
            return Integer.compare(c2.getPopulation(),
                    c1.getPopulation());
        }
    }

    private static class DensityComparator implements Comparator<City>{

        @Override
        public int compare(City c1, City c2) {
            return Double.compare(c2.getDensity(),
                    c1.getDensity());
        }
    }
}
