package rw.transax.hahiye.data.local.database;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import rw.transax.hahiye.model.Interest;

class DataGenerator {

    static List<Interest> SAMPLE_DATA() {
        return Arrays.asList(
                new Interest(UUID.randomUUID().toString(), "Hotel", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Pub", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Restaurant", "https://api.androidhive.info/images/food/5.jpg"),
                new Interest(UUID.randomUUID().toString(), "Cafe-Resto", "https://api.androidhive.info/images/food/5.jpg")
        );

    }
}
