package ru.nadin.cleaningService.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nadin.cleaningService.data.model.OrderModel;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
  Optional<OrderModel> findByPhoneNumberIsLikeIgnoreCase(String phoneNumber);

  void deleteByPhoneNumberIsLikeIgnoreCase(String phoneNumber);
}
