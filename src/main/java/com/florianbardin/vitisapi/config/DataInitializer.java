package com.florianbardin.vitisapi.config;

import com.florianbardin.vitisapi.wine.Wine;
import com.florianbardin.vitisapi.wine.WineRepository;
import com.florianbardin.vitisapi.wine.WineType;
import com.florianbardin.vitisapi.winery.Winery;
import com.florianbardin.vitisapi.winery.WineryRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class DataInitializer implements CommandLineRunner {
    private final WineryRepository wineryRepository;
    private final WineRepository wineRepository;
    private final Faker faker;

    public DataInitializer(WineryRepository wineryRepository, WineRepository wineRepository) {
        this.wineryRepository = wineryRepository;
        this.wineRepository = wineRepository;
        this.faker = new Faker(Locale.of("fr"));
    }

    @Override
    public void run(String... args) throws Exception {

        String[] regions = {
                "Bordeaux", "Bourgogne", "Alsace", "Champagne", "Vallée de la Loire",
                "Vallée du Rhône", "Provence", "Napa Valley", "Toscana", "Rioja", "Piedmont"
        };

        if (wineryRepository.count() == 0) {
            List<Winery> wineries = new ArrayList<>();

            for (int i = 1; i <= 25; i++) {
                Winery newWinery = new Winery();

                newWinery.setName("Maison " + faker.name().lastName());

                String region = regions[faker.random().nextInt(0, regions.length - 1)];
                newWinery.setRegion(region);
                newWinery.setAddress(faker.address().streetAddress());

                wineries.add(newWinery);
            }

            wineryRepository.saveAll(wineries);

            List<Wine> wines = new ArrayList<>();

            for (int i = 1; i <= 100; i++) {
                Wine newWine = new Wine();

                newWine.setName("Cuvée " + faker.name().firstName());
                newWine.setVintage(faker.number().numberBetween(1990, 2026));

                WineType[] wineType = WineType.values();
                newWine.setType(wineType[faker.random().nextInt(wineType.length - 1)]);

                String[] colors = {"Red", "White", "Rosé"};
                newWine.setColor(colors[faker.random().nextInt(0, colors.length - 1)]);

                newWine.setPrice(faker.number().randomDouble(2, (long) 10.00, (long) 150.00));

                newWine.setWinery(wineries.get(faker.random().nextInt(0, wineries.size() - 1)));

                wines.add(newWine);
            }

            wineRepository.saveAll(wines);
        }
    }
}
