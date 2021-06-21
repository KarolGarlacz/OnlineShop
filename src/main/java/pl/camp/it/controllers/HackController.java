package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.dao.impl.ItemDAO;
import pl.camp.it.model.Item;

@Controller
public class HackController {

    @Autowired
    ItemDAO itemDAO;

    @RequestMapping(value = "/hack/addItems", method = RequestMethod.GET)
    public String addItems(){
        itemDAO.addItem(new Item(1,"Smartfon APPLE iPhone 12 64GB Czarny MGJ53PM/A", 999.9, 2, "123-123", "smartfon"));
        itemDAO.addItem(new Item(2,"Laptop HP Pavilion Gaming 15-ec0045nw FHD Ryzen5-3550H/16GB/512GB SSD/GTX1650 4GB/Win10H", 6999.9, 5, "222-222", "laptop"));
        itemDAO.addItem(new Item(3,"Monitor XIAOMI Mi Curved Gaming 34", 1499.9, 3, "333-333", "monitor"));

        return "redirect/index";
    }
}
