package com.aspiresys;

public class MenuItem {
    private String name;
    private int price;
    private String quantity;
    private int total;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    
    public MenuItem(String name, int price, String quantity2, int total) {
        this.name = name;
        this.price = price;
        this.quantity = quantity2;
        this.total = total;
    }
    @Override
    public String toString() {
        return "MenuItem [name=" + name + ", price=" + price + ", quantity=" + quantity + ", total=" + total + "]";
    }

    
}
