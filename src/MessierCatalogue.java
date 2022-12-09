import java.util.ArrayList;
import org.json.JSONObject;
public class MessierCatalogue {
    // create an arrayList of MessierObjects
    ArrayList<MessierObject> collection = new ArrayList<MessierObject>();

    // constructor
    MessierCatalogue(ArrayList<MessierObject> collection) {
        this.collection = collection;
    }
    
    }
    // basic functions to add and remove objects
    public void addObject(MessierObject object) {
        collection.add(object);
    }
    public void removeObject(MessierObject object) {
        collection.remove(object);
    }
    // print all objects in the catalogue
    public void printAll() {
        for (MessierObject object : collection) {
            System.out.println(object);
        }
    };
    // sort the objects by their magnitude with compareTo
    public void sortByMagnitude() {
        collection.sort(null);
    }
    // query the collection for matching fields
    public void match(String json) {
        // convert the json string to a JSONObject
        JSONObject query = new JSONObject(json);
        // get the field and value from the JSONObject
        String[] fields = query.keys();
        String value;
        // filter the collection for matching fields in the query
        ArrayList<MessierObject> result = collection.stream()
            .filter(object -> {
                for (String field : fields) {
                    value = query.getString(field);
                    if (!object.getField(field).equal(value)) {
                        return false;
                    }
                }
                return true;
            })
            .collect(Collectors.toCollection(ArrayList::new));
        return MessierCatalogue(result);
    }

}
