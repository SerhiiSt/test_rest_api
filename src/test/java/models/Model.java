package models;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergio on 12/10/18.
 */

public class Model {


    List<String> filters;
    public  HashMap<String, HashMap<String, Double>> boundingBox;

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public HashMap<String, HashMap<String, Double>> getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(HashMap<String, HashMap<String, Double>> boundingBox) {
        this.boundingBox = boundingBox;
    }
}
