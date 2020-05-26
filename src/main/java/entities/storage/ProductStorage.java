package entities.storage;

import java.util.ArrayList;

public class ProductStorage {
    private ArrayList<ProductGroup> groups;
    public ProductStorage(){
        groups=new ArrayList<>();
    }

    public ArrayList<ProductGroup> getGroups() {
        return groups;
    }
}
