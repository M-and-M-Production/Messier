import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * <blockquote>
     * 
     * <pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * </pre>
     * 
     * </blockquote>
     * 
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
     * <blockquote>
     * 
     * <pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * catalogue.removeObject(object);
     * </pre>
     * 
     * </blockquote>
     * 
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
     * <blockquote>
     * 
     * <pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * catalogue.printAll();
     * </pre>
     * 
     * </blockquote>
     * 
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
     * <blockquote>
     * 
     * <pre>
     * MessierCatalogue catalogue = new MessierCatalogue();
     * MessierObject object = new MessierObject();
     * catalogue.addObject(object);
     * catalogue.sortByMagnitude();
     * </pre>
     * 
     * </blockquote>
     * 
     * @return void
     * @see MessierObject
     */
    public void sortByMagnitude() {
        collection.sort(null);
    }

    public double avgMagOfType(String Type) {
        Double avg = 0.0;
        avg = collection.stream().filter(obj -> obj.type.equals(Type))
                .collect(Collectors.averagingDouble(obj -> obj.magnitude));
        return avg;
    }

    public String mostDistantOfType(String Type) {
        ArrayList<MessierObject> filter = collection.stream().filter(obj -> obj.type.equals(Type))
                .collect(Collectors.toCollection(ArrayList<MessierObject>::new));
        filter.sort((o1, o2) -> Double.compare(o2.distance.value(), o1.distance.value()));
        MessierObject result = filter.get(0);
        return result.toString();
    }

    public String lowestDecOfConstel(String Type) {
        ArrayList<MessierObject> filter = collection.stream().filter(obj -> obj.constellation.equals(Type))
                .collect(Collectors.toCollection(ArrayList<MessierObject>::new));
        filter.sort((o1, o2) -> Double.compare(o2.declination, o1.declination));
        MessierObject result = filter.get(0);
        return result.toString();
    }

    public String nearestStar(int star) {
        collection.sort((o1, o2) -> Integer.compare(o1.messierNumber, o2.messierNumber));
        MessierObject staticStar = collection.get(star-1);
        Double lowestAngleDistance= 100.0;
        Double currentAngleDistance = 0.0;
        MessierObject result = collection.get(0);
        for (MessierObject observer : collection) {
            if (observer.messierNumber != star) {
                currentAngleDistance = Math.acos((Math.sin(observer.declination) * Math.sin(staticStar.declination)) + 
                                                  Math.cos(observer.declination) * Math.cos(staticStar.declination) * Math.cos(staticStar.ascension - observer.ascension));
                if (lowestAngleDistance > currentAngleDistance) {
                    lowestAngleDistance = currentAngleDistance;
                    result = collection.get(observer.messierNumber);
                }
            }
        }
        System.out.println(staticStar.toString());
        return result.toString();

    }
}
