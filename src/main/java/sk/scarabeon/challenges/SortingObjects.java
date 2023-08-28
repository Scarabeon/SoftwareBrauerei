package sk.scarabeon.challenges;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Challenge 3 - Sorting objects
 * @author Michal Bielik
 */
public class SortingObjects {

    public String sort() {
        try {
            /* Read input from resource file as JSON array */
            JSONArray jsonArray = new JSONArray(IOUtils.resourceToString("inputs/sortingObjectsInput.json", Charset.defaultCharset(), ClassLoader.getSystemClassLoader()));
            /* Transfer JSON array items into Java JSON object list collection */
            List<JSONObject> items = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                items.add(jsonArray.getJSONObject(i));
            }
            /* Sorting function of that JSON object list */
            items.sort((jsonObject1, jsonObject2) -> {
                try {
                    /* First sorting by customer number */
                    Long customerNumber1 = jsonObject1.getLong("customer_number");
                    Long customerNumber2 = jsonObject2.getLong("customer_number");
                    /* The same customer number will be further distinguished by invoice and it's number */
                    if (customerNumber1.compareTo(customerNumber2) == 0) {
                        try {
                            Long invoiceNumber1 = jsonObject1.getJSONObject("invoice").getLong("number");
                            Long invoiceNumber2 = jsonObject2.getJSONObject("invoice").getLong("number");
                            /* Sorted by invoice number descending */
                            return invoiceNumber2.compareTo(invoiceNumber1);
                        } catch (JSONException ex) {
                            return 0;
                        }
                    } else {
                        /* Sorted by customer number ascending */
                        return customerNumber1.compareTo(customerNumber2);
                    }
                } catch (JSONException ex) {
                    return 0;
                }
            });
            /* Make new JSON array with sorted items */
            return new JSONArray(items).toString(3);
        } catch (IOException ex) {
            return "Resource not found";
        }
    }
}