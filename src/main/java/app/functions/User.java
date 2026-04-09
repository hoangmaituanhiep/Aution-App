package app.functions;

import java.util.HashMap;
import java.util.Map;

abstract class User extends Entity {
    protected String userName;
    protected String password;

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getPassword() {return password;}
    public void setPassword(String password) { this.password = password;}
}

class Bidder extends User {

    public <T extends Item> String getItem_Info(T item) {
        return item.toString();
    }
    public <T extends Item> void Autioned(T item, double newPrice) {
        if (newPrice > item.getCurrent_Price()) {
            item.setNewPrice(newPrice);
            System.out.println("Done!!!");
        } else { System.out.println("Absolutely failure.");}
    }
}
class Admin extends User {

}
class Seller<T extends Item> extends User {
    Map<String, Item> list_item = new HashMap<>();
    public void setStartingPrice(String id,double StartingPrice) {
        list_item.get(id).setStartingPrice(StartingPrice);
    }

    public void setMaxPrice(String id, double MaxPrice) {
        list_item.get(id).setMaxPrice(MaxPrice);
    }

    public void addSellingItem(String id, Item item) {
        list_item.put(id, item);
    }

    public void deleteSellingItem(String id) {
        if (list_item.containsKey(id)) {
            list_item.remove(id);
        }
    }
}
