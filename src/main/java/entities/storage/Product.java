package entities.storage;

public class Product {
    private String name;
    private int price;
    private int amount;
    public Product(String name, int price, int amount){
        this.name=name;
        this.price = price;
        this.amount=amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "Product: " + name +
                " amount = " + amount +
                " price = "+price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass())
            return this.getName().equals(((Product)obj).getName());
        return false;
    }
}
