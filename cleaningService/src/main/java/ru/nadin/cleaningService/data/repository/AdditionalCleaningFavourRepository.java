package ru.nadin.cleaningService.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nadin.cleaningService.data.model.AdditionalCleaningFavourModel;
import java.util.Optional;

@Repository
public interface AdditionalCleaningFavourRepository extends JpaRepository<AdditionalCleaningFavourModel, Long> {
  Optional<AdditionalCleaningFavourModel> findByTitleIsLikeIgnoreCase(String title);

  void deleteByTitleIsLikeIgnoreCase(String title);
}
