import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

public class TestCities {

    private Cities cities;
    private List<City> allCities;

    @Before
    public void setup() throws FileNotFoundException {
        cities = new Cities();
        cities.init("cities.txt");

        allCities = new ArrayList<>();
        for (List<City> group : cities.mapByCountry().values()) {
            allCities.addAll(group);
        }
    }

    @Test
    public void testCityFields() {
        City city = new City("TestCity", "Testland", 1000000, 500);
        assertEquals("TestCity", city.getName());
        assertEquals("Testland", city.getCountry());
        assertEquals(1000000, city.getPopulation());
        assertEquals(500, city.getArea());
        assertEquals(2000.0, city.getDensity(), 0.001);
    }

    @Test
    public void testMapByCountryIsBuiltCorrectly() {
        Map<String, List<City>> map = cities.mapByCountry();
        assertNotNull(map);
        assertFalse(map.isEmpty());

        for (String country : map.keySet()) {
            assertNotNull(country);
            List<City> cityList = map.get(country);
            assertNotNull(cityList);
            assertFalse(cityList.isEmpty());
            for (City c : cityList) {
                assertEquals(country, c.getCountry());
            }
        }
    }

    @Test
    public void testSortByPopulationDescending() {
        List<City> sorted = new ArrayList<>(allCities);
        Collections.sort(sorted, new Comparator<City>() {
            @Override
            public int compare(City c1, City c2) {
                return Integer.compare(c2.getPopulation(), c1.getPopulation());
            }
        });

        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(
                    sorted.get(i).getPopulation() >= sorted.get(i + 1).getPopulation()
            );
        }
    }

    @Test
    public void testSortByDensityDescending() {
        List<City> sorted = new ArrayList<>(allCities);
        Collections.sort(sorted, new Comparator<City>() {
            @Override
            public int compare(City c1, City c2) {
                return Double.compare(c2.getDensity(), c1.getDensity());
            }
        });

        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(
                    sorted.get(i).getDensity() >= sorted.get(i + 1).getDensity()
            );
        }
    }

    @Test
    public void testAlphabeticalOrderByName() {
        TreeSet<City> sorted = new TreeSet<>(allCities);
        List<String> names = new ArrayList<>();
        for (City c : sorted) {
            names.add(c.getName());
        }

        List<String> expected = new ArrayList<>(names);
        Collections.sort(expected);

        assertEquals(expected, names);
    }
}
