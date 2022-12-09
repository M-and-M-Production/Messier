import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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

    public double avgMagOfType(String Type){
        Double avg=0.0;
        avg = collection.stream().filter(obj -> obj.type.equals(Type)).collect(Collectors.averagingDouble(obj -> obj.magnitude));
        System.out.println(collection.stream().filter(obj -> obj.type.equals(Type)).collect(Collectors.averagingDouble(obj -> obj.magnitude)));
        return avg;
    }
    public String mostDistant(String Type){
        MessierObject result;
        
        collection.sort((o1,o2)-> Double.compare(o2.distance.value(),o1.distance.value()));
        result = collection.get(0);
        System.out.println(result.messierNumber);

        return result.toString();

    }
}
