package items.beers;

import helpers.Country;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;



public class BeerParser {
    private static final String BEER_CONFIG_FILE = "/beers.yml";
    static final Logger logger = Logger.getLogger(BeerParser.class.getName());

    /**
     * Get beer info by country name
     * @param country
     * @return Beer Object with the given country name as origin
     */
    public static Beer load(Country country) {
        try {
            return load().stream()
                    .filter(beer -> beer.getOrigin().equals(country))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("country not found"));
        } catch (Exception e) {
            logger.log(Level.WARNING,"Error parsing beer" + e.getMessage());
            return null;
        }
    }

    /**
     * Load beers from yaml file
     * @return [Iterable] of Beer Object
     * @throws FileNotFoundException
     */
    private static Iterable<Object> loadBeersFromFile() throws FileNotFoundException {
        Constructor constructor = new Constructor(Beer.class);
        Yaml yaml = new Yaml(constructor);

        URL url = BeerParser.class.getResource(BEER_CONFIG_FILE);

        assert url != null;
        return yaml.loadAll(new FileInputStream(url.getFile()));
    }


    // iterable -> List
    public static List<Beer> load() throws FileNotFoundException {
        Iterable<Object> beerIterable = loadBeersFromFile();
        return StreamSupport
                .stream(beerIterable.spliterator(), false)
                .map(beer -> (Beer) beer)
                .collect(Collectors.toList());
    }
}
