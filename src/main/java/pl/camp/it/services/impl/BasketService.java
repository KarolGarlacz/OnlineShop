package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import pl.camp.it.dao.IItemDAO;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.dao.IOrderPositionDAO;
import pl.camp.it.dao.impl.ItemDAO;
import pl.camp.it.dao.impl.OrderDAO;
import pl.camp.it.dao.impl.OrderPositionDAO;
import pl.camp.it.model.BasketPosition;
import pl.camp.it.model.Item;
import pl.camp.it.model.Order;
import pl.camp.it.services.IBasketService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Component
public class BasketService implements IBasketService {

    @Autowired
    IItemDAO itemDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Resource
    SessionObject sessionObject;

    public void addItemToBasket(String code){
        Item item = this.itemDAO.findItemsByCode(code);
        if(item != null){
            this.sessionObject.getBasket().addItem(item);
        }
    }

    public double calculateBasketSum(){
        double sum = 0;
        for(BasketPosition basketPosition : this.sessionObject.getBasket().getBasketPositions()){
            sum = sum + (basketPosition.getItem().getPrice() * basketPosition.getQuantity());
        }
        return sum;
    }
    public void removeItemFromBasket(String code){
        Iterator<BasketPosition> iterator = this.sessionObject.getBasket().getBasketPositions().iterator();

        while(iterator.hasNext()){
            if(iterator.next().getItem().getCode().equals(code)){
                iterator.remove();
                break;
            }
        }
    }

    public void confirmOrder(){
        List<Item> itemsFromDb = this.itemDAO.getAllItems();

        for(Item item : itemsFromDb){
            Iterator<BasketPosition> iterator = this.sessionObject.getBasket().getBasketPositions().iterator();
            while(iterator.hasNext()){
                BasketPosition actualBasketPosition = iterator.next();
                if(item.getCode().equals(actualBasketPosition.getItem().getCode()) && item.getQuantity() <actualBasketPosition.getQuantity()){
                    iterator.remove();
                    return;
                }
            }
        }
        Order order = new Order(this.sessionObject.getUser(), this.sessionObject.getBasket().getBasketPositions());
        int orderId = this.orderDAO.addOrder(order);

        for(BasketPosition basketPosition : order.getPositions()){
            this.orderPositionDAO.addOrderPosition(basketPosition, orderId);
        }
        for(Item item : itemsFromDb){
            for(BasketPosition position : this.sessionObject.getBasket().getBasketPositions()){
                if(item.getCode().equals(position.getItem().getCode())){
                    item.setQuantity(item.getQuantity() - position.getQuantity());
                    this.itemDAO.updateItem(item);
                }
            }
        }
        this.sessionObject.createNewBasket();
    }
}
