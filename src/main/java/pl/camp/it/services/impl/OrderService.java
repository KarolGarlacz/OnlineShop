package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import pl.camp.it.dao.IItemDAO;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.dao.IOrderPositionDAO;
import pl.camp.it.model.BasketPosition;
import pl.camp.it.model.Item;
import pl.camp.it.model.Order;
import pl.camp.it.services.IOrderService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OrderService implements IOrderService {

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Autowired
    IItemDAO itemDAO;

    @Resource
    SessionObject sessionObject;

    public List<Order> getOrderForUser(){
        List<Order> orders = this.orderDAO.getOrdersForUser(this.sessionObject.getUser().getId());

        for(Order order : orders){
            List<BasketPosition> basketPositions = this.orderPositionDAO.getOrderPositionsForOrder(order.getId());
            for(BasketPosition position : basketPositions) {
                Item item = this.itemDAO.getItemById(position.getItemId());
                position.setItem(item);
            }
            order.setPositions(basketPositions);
        }
        return orders;

    }
}
