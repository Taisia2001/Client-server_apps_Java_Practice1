package entities.storage;

import java.util.ArrayList;
import java.util.List;

public class ProductGroup {
    private String name;
    private ArrayList<Product> products;
    public ProductGroup(String name){
        this.name=name;
        products = new ArrayList<>();
    }
    public void addProducts(Product p){
        if(products.contains(p))
            products.get(products.indexOf(p)).setAmount(products.get(products.indexOf(p)).getAmount()+p.getAmount());
        else
            products.add(p);
    }

    public void deleteProducts(Product p){
            if(!products.contains(p))
                throw  new IllegalArgumentException();
            if( products.get(products.indexOf(p)).getAmount() < p.getAmount())
                 throw new IllegalArgumentException();
        products.get(products.indexOf(p)).setAmount(products.get(products.indexOf(p)).getAmount()-p.getAmount());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

}
