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

    public double avgMag(String Type){
        Double avg=0.0;
        Object[] filter = collection.stream().filter(type -> type.toString() == Type).toArray();
        System.out.println(collection.stream());
        System.out.println(filter[0]);
        return avg;

    }
}
