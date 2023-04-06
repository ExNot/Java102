package CollectionMapInterface;

import java.util.HashMap;
import java.util.Map;

public class _Map {
    public static void main(String[] args) {

        Map<String,String> country = new HashMap<>();

        country.put("tr", "Türkiye");
        country.put("gr", "Almanya");
        country.put("en", "İngiltere");

        country.replace("gr", "alamanya");
        for (String key : country.keySet()){

            System.out.println(country.get(key));
        }

        for (String val: country.values()){
            System.out.println(val);
        }


    }
}
