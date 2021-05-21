package pl.camp.it.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.camp.it.model.Item;
import pl.camp.it.model.User;

import java.util.ArrayList;
import java.util.List;


@Component
public class Database {
    private List<Item> items = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Database(){

        items.add(new Item("Smartfon APPLE iPhone 12 64GB Czarny MGJ53PM/A", 999.9, 2, "123-123", "smartfon"));
        items.add(new Item("Laptop HP Pavilion Gaming 15-ec0045nw FHD Ryzen5-3550H/16GB/512GB SSD/GTX1650 4GB/Win10H", 6999.9, 5, "222-222", "laptop"));
        items.add(new Item("Monitor XIAOMI Mi Curved Gaming 34", 1499.9, 3, "333-333", "monitor"));
        users.add(new User("Karol","Garlacz","admin", DigestUtils.md5Hex("admin"), User.Status.ADMIN));
        users.add(new User("Jan","Kowalczyk","user", DigestUtils.md5Hex("user"), User.Status.USER));
    }

    public List<Item> getAllItems() {

        return this.items;
    }
    public List<User> getAllUsers() {

        return this.users;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Item findItemsByCode(String code) {
        for(Item item : this.items) {
            if(item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
    public User authenticate(String login, String password) {
        for(User user : this.users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(DigestUtils.md5Hex(password))) {
                return user;
            }
        }

        return null;
    }
}
