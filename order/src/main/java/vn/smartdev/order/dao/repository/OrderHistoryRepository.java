package vn.smartdev.order.dao.repository;import org.springframework.data.jpa.repository.JpaRepository;import vn.smartdev.order.dao.entity.OrderHistory;/** * Created by Nguyen on 30/11/2016. */public interface OrderHistoryRepository extends JpaRepository<OrderHistory, String> {}