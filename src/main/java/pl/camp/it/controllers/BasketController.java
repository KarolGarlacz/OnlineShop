package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Item;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addToBasket/{code}", method = RequestMethod.GET)
    public String addBookToBasket(@PathVariable String code) {
        Item item = database.findItemsByCode(code);
        if(item == null) {
            return "redirect:/index";
        }

        this.sessionObject.getBasket().addItem(item);

        return "redirect:/index";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getStatus() : null);
        model.addAttribute("basket", this.sessionObject.getBasket());

        return "basket";
    }
}
