package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.camp.it.database.Database;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;
}
