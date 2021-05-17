
package com.paypal.json.comparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility class that contains Json manipulation methods.
 */
public final class JSONCompareUtil {
    private static Integer INTEGER_ONE = new Integer(1);

    private JSONCompareUtil() {
    }

    /**
     * Converts the provided {@link JSONArray} to a Map of {@link JSONObject}s where the key of each object
     * is the value at {@code uniqueKey} in each object.
     *
     * @param array     the JSON array to convert
     * @param uniqueKey the key to map the JSON objects to
     * @return the map of {@link JSONObject}s from {@code array}
     * @throws JSONException JSON parsing error
     */
    public static Map<Object, JSONObject> arrayOfJsonObjectToMap(JSONArray array, String uniqueKey) throws JSONException {
        Map<Object, JSONObject> valueMap = new HashMap<Object, JSONObject>();
        for (int i = 0; i < array.length(); ++i) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            Object id = jsonObject.get(uniqueKey);
            valueMap.put(id, jsonObject);
        }
        return valueMap;
    }

    /**
     * Searches for the unique key of the {@code expected} JSON array.
     *
     * @param expected the array to find the unique key of
     * @return the unique key if there's any, otherwise null
     * @throws JSONException JSON parsing error
     */
    public static String findUniqueKey(JSONArray expected) throws JSONException {
        // Find a unique key for the object (id, name, whatever)
        JSONObject o = (JSONObject) expected.get(0); // There's at least one at this point
        for (String candidate : getKeys(o)) {
            if (isUsableAsUniqueKey(candidate, expected)) return candidate;
        }
        // No usable unique key :-(
        return null;
    }

    /**
     * <p>Looks to see if candidate field is a possible unique key across a array of objects.
     * Returns true IFF:</p>
     * <ol>
     *   <li>array is an array of JSONObject
     *   <li>candidate is a top-level field in each of of the objects in the array
     *   <li>candidate is a simple value (not JSONObject or JSONArray)
     *   <li>candidate is unique across all elements in the array
     * </ol>
     *
     * @param candidate is usable as a unique key if every element in the
     * @param array is a JSONObject having that key, and no two values are the same.
     * @return true if the candidate can work as a unique id across array
     * @throws JSONException JSON parsing error
     */
    public static boolean isUsableAsUniqueKey(String candidate, JSONArray array) throws JSONException {
        Set<Object> seenValues = new HashSet<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object item = array.get(i);
            if (item instanceof JSONObject) {
                JSONObject o = (JSONObject) item;
                if (o.has(candidate)) {
                    Object value = o.get(candidate);
                    if (isSimpleValue(value) && !seenValues.contains(value)) {
                        seenValues.add(value);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts the given {@link JSONArray} to a list of {@link Object}s.
     *
     * @param expected the JSON array to convert
     * @return the list of objects from the {@code expected} array
     * @throws JSONException JSON parsing error
     */
    public static List<Object> jsonArrayToList(JSONArray expected) throws JSONException {
        List<Object> jsonObjects = new ArrayList<Object>(expected.length());
        for (int i = 0; i < expected.length(); ++i) {
            jsonObjects.add(expected.get(i));
        }
        return jsonObjects;
    }

    /**
     * Returns whether all of the elements in the given array are simple values.
     *
     * @param array the JSON array to iterate through on
     * @return true if all the elements in {@code array} are simple values
     * @throws JSONException JSON parsing error
     * @see #isSimpleValue(Object)
     */
    public static boolean allSimpleValues(JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); ++i) {
            if (!isSimpleValue(array.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether the given object is a simple value: not {@link JSONObject} and not {@link JSONArray}.
     *
     * @param o the object to inspect
     * @return true if {@code o} is a simple value
     */
    public static boolean isSimpleValue(Object o) {
        return !(o instanceof JSONObject) && !(o instanceof JSONArray);
    }

    /**
     * Returns whether all elements in {@code array} are {@link JSONObject} instances.
     *
     * @param array the array to inspect
     * @return true if all the elements in the given array are JSONObjects
     * @throws JSONException JSON parsing error
     */
    public static boolean allJSONObjects(JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); ++i) {
            if (!(array.get(i) instanceof JSONObject)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether all elements in {@code array} are {@link JSONArray} instances.
     *
     * @param array the array to inspect
     * @return true if all the elements in the given array are JSONArrays
     * @throws JSONException JSON parsing error
     */
    public static boolean allJSONArrays(JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); ++i) {
            if (!(array.get(i) instanceof JSONArray)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Collects all keys in {@code jsonObject}.
     *
     * @param jsonObject the {@link JSONObject} to get the keys of
     * @return the set of keys
     */
    public static Set<String> getKeys(JSONObject jsonObject) {
        Set<String> keys = new TreeSet<String>();
        Iterator<?> iter = jsonObject.keys();
        while (iter.hasNext()) {
            keys.add((String) iter.next());
        }
        return keys;
    }

    public static String qualify(String prefix, String key) {
        return "".equals(prefix) ? key : prefix + "." + key;
    }

    public static String formatUniqueKey(String key, String uniqueKey, Object value) {
        return key + "[" + uniqueKey + "=" + value + "]";
    }

    /**
     * Creates a cardinality map from {@code coll}.
     *
     * @param coll the collection of items to convert
     * @param <T>  the type of elements in the input collection
     * @return the cardinality map
     */
    public static <T> Map<T, Integer> getCardinalityMap(final Collection<T> coll) {
        Map count = new HashMap<T, Integer>();
        for (T item : coll) {
            Integer c = (Integer) (count.get(item));
            if (c == null) {
                count.put(item, INTEGER_ONE);
            } else {
                count.put(item, new Integer(c.intValue() + 1));
            }
        }
        return count;
    }
    
    public static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
    
    HttpURLConnection conn = (HttpURLConnection) (new URL("https://www.quora.com")).openConnection(); 
  
conn.setRequestProperty("Content-Type", "application/json"); 
conn.setDoOutput(true); 
conn.setDoInput(true); 
 
conn.setRequestMethod("PUT"); 
JSONObject data = new JSONObject(); 
//Now add all the data to this object using accumulate. 
 
OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream()); 
out.write(data); 
 
Bufferedreader reader = new BufferedReader ( new InputStreamReader(conn.getInputStream()); 
for (String line; (line = reader.readLine()) != null;) { 
        System.out.println(line); 
} 
reader.close(); 
out.close(); 
    
}
