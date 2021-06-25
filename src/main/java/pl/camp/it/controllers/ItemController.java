package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.model.Item;
import pl.camp.it.services.IItemService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class ItemController {


@Autowired
    IItemService itemService;

@Resource
    SessionObject sessionObject;



@RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public String addItemForm(Model model){
    model.addAttribute("item", new Item());
    model.addAttribute("info", this.sessionObject.getInfo());
    model.addAttribute("logged", this.sessionObject.isLogged());
    model.addAttribute("status", this.sessionObject.getUser() !=null ? this.sessionObject.getUser().getStatus() : null);

    return "addItem";
}
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(@ModelAttribute Item item) {
        if (this.itemService.addItem(item)) {

            return "redirect:/index";
        }
        return "redirect:/addItem";
    }

    @RequestMapping(value = "/editItem/{code}", method = RequestMethod.GET)
    public String editItemForm(Model model, @PathVariable String code) {
        Item item = this.itemService.findItemByCode(code);
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

        if(this.itemService.updateItem(code, item)) {
            return "redirect:/index";
        }
        return "redirect:/editItem/" + code;
    }
}