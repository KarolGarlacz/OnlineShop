package pl.camp.it.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.services.IItemService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

 @Autowired
 IItemService itemService;

@Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String main(Model model) {

        model.addAttribute("items", this.itemService.getItemsWithFilter());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getStatus() : null);
        return "index";
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String find(@RequestParam String pattern){
        this.sessionObject.setFindPattern(pattern);
        return "redirect:/index";

    }
}
