package ru.nadin.cleaningService.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nadin.cleaningService.data.model.CleaningTypeModel;
import java.util.Optional;

@Repository
public interface CleaningTypeRepository extends JpaRepository<CleaningTypeModel, Long> {
  Optional<CleaningTypeModel> findByTitleIsLikeIgnoreCase(String title);

  void deleteByTitleIsLikeIgnoreCase(String title);
}
