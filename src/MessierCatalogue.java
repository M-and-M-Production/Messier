import java.util.ArrayList;
//import org.json.JSONObject;
public class MessierCatalogue {
    // create an arrayList of MessierObjects
    ArrayList<MessierObject> collection = new ArrayList<MessierObject>();

    // constructor
    MessierCatalogue(ArrayList<MessierObject> collection) {
        this.collection = collection;
    }
    
    
    /**
     * Add an object to the catalogue
     * Example:
     * <blockquote><pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * </pre></blockquote>
     * @param object the object to add to the catalogue
     * @return void
     * @see MessierObject
     */
    public void addObject(MessierObject object) {
        collection.add(object);
    }

    /**
     * Remove an object from the catalogue
     * Example:
     * <blockquote><pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * catalogue.removeObject(object);
     * </pre></blockquote>
     * @param object the object to remove from the catalogue
     * @return void
     * @see MessierObject
     */
    public void removeObject(MessierObject object) {
        collection.remove(object);
    }
    /**
     * Print all objects in the catalogue
     * Example:
     * <blockquote><pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * catalogue.printAll();
     * </pre></blockquote>
     * @return void
     * @see MessierObject
     * @see MessierObject#toString()
     */
    public void printAll() {
        for (MessierObject object : collection) {
            System.out.println(object.toString());
        }
    };
    /**
     * Sort the catalogue by the magnitude of the objects
     * Example:
     * <blockquote><pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * catalogue.sortByMagnitude();
     * </pre></blockquote>
     * @return void
     * @see MessierObject
     */
    public void sortByMagnitude() {
        collection.sort(null);
    }
    /**
     * Filter the catalogue by a query
     * Example:
     * <blockquote><pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * catalogue.match("{\"type\": \"GALAXY\"}");
     * </pre></blockquote>
     * @param json the query to filter the catalogue by
     * @return MessierCatalogue
     * @see MessierObject
     */
    // public void match(String json) {
    //     // convert the json string to a JSONObject
    //     JSONObject query = new JSONObject(json);
    //     // get the field and value from the JSONObject
    //     String[] fields = query.keys();
    //     String value;
    //     // filter the collection for matching fields in the query
    //     ArrayList<MessierObject> result = collection.stream()
    //         .filter(object -> {
    //             for (String field : fields) {
    //                 value = query.getString(field);
    //                 if (!object.getField(field).equal(value)) {
    //                     return false;
    //                 }
    //             }
    //             return true;
    //         })
    //         .collect(Collectors.toCollection(ArrayList::new));
    //     return MessierCatalogue(result);
    // }

}
