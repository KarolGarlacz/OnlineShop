package pl.camp.it.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Item;
import pl.camp.it.model.User;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonController {

@Autowired
    Database database;

@Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String main(Model model) {
        List<Item> items = this.database.getAllItems();
        List<User> users = this.database.getAllUsers();

        model.addAttribute("user" , this.sessionObject.getUser());
        model.addAttribute("items", items);
        model.addAttribute("name", users);
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getStatus() : null);
        return "index";


    }
}
