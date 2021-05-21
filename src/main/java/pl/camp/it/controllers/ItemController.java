package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Item;
import pl.camp.it.session.SessionObject;
import pl.camp.it.validators.ItemValidator;

import javax.annotation.Resource;

@Controller
public class ItemController {


@Autowired
Database database;

@Resource
    SessionObject sessionObject;



@RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public String addItemForm(Model model){
    model.addAttribute("item", new Item());
    model.addAttribute("info", this.sessionObject.getInfo());
    model.addAttribute("logged", this.sessionObject.isLogged());
    model.addAttribute("role", this.sessionObject.getUser() !=null ? this.sessionObject.getUser().getStatus() : null);

    return "addItem";
}
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(@ModelAttribute Item item) {
        if(!ItemValidator.validateBasics(item)) {
            this.sessionObject.setInfo("Nieprawidłowy kod produktu lub ilość !!");
            return "redirect:/addItem";
        }
        Item itemFromDatabase = this.database.findItemsByCode(item.getCode());
        if(itemFromDatabase != null) {
            itemFromDatabase.setQuantity(itemFromDatabase.getQuantity() + item.getQuantity());
        } else {
            if(!ItemValidator.validateFull(item)) {
                this.sessionObject.setInfo("Produkt o podanym kodzie nie istnieje, pełne dane wymagane !!");
                return "redirect:/addItem";
            }
            this.database.addItem(item);
        }
        return "redirect:/index";
    }
    @RequestMapping(value = "/editItem/{code}", method = RequestMethod.GET)
    public String edititemForm(Model model, @PathVariable String code) {
        Item item = this.database.findItemsByCode(code);
        if(item == null) {
            return "redirect:/index";
        }
        model.addAttribute("item", item);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getStatus() : null);
        return "editItem";
    }
    @RequestMapping(value = "/editItem/{code}", method = RequestMethod.POST)
    public String editItem(@PathVariable String code, @ModelAttribute Item item) {
        Item itemFromDb = database.findItemsByCode(code);
        if(itemFromDb == null) {
            return "redirect:/editItem/" + code;
        }

        itemFromDb.setName(item.getName());
        itemFromDb.setCategory(item.getCategory());
        itemFromDb.setQuantity(item.getQuantity());
        itemFromDb.setPrice(item.getPrice());
        itemFromDb.setCode(item.getCode());

        return "redirect:/index";
    }
}