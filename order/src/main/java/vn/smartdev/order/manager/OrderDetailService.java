package vn.smartdev.order.manager;import vn.smartdev.order.dao.entity.OrderDetail;import java.util.List;/** * Created by Nguyen on 30/11/2016. */public interface OrderDetailService {    List<OrderDetail> getAll();    OrderDetail findById(String id);    void save(OrderDetail orderDetail);    void delete(OrderDetail orderDetail);}