package ru.nadin.cleaningService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nadin.cleaningService.data.model.AdditionalCleaningFavourModel;
import ru.nadin.cleaningService.data.repository.AdditionalCleaningFavourRepository;
import ru.nadin.cleaningService.service.dto.AdditionalFavourDto;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalCleaningFavourService {
  private final AdditionalCleaningFavourRepository additionalCleaningFavourRepository;

  public List<AdditionalCleaningFavourModel> findAdditionalFavours() {
    return additionalCleaningFavourRepository.findAll();
  }

  public AdditionalCleaningFavourModel searchAdditionalFavourById(final Long id) {
    return additionalCleaningFavourRepository.findById(id)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с ID - " + id + " не существует!")
      );
  }

  public AdditionalCleaningFavourModel searchAdditionalFavourByTitle(final String title) {
    return additionalCleaningFavourRepository.findByTitleIsLikeIgnoreCase(title)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с названием - " + title + " не существует!")
      );
  }

  public AdditionalCleaningFavourModel createAdditionalFavour(final AdditionalFavourDto additionalFavourDto) {
    AdditionalCleaningFavourModel additionalCleaningFavourModel = new AdditionalCleaningFavourModel();
    additionalCleaningFavourModel.setPrice(additionalFavourDto.getPrice());
    additionalCleaningFavourModel.setTitle(additionalFavourDto.getTitle());
    return additionalCleaningFavourRepository.save(additionalCleaningFavourModel);
  }

  public AdditionalCleaningFavourModel updateAdditionalFavourById(final AdditionalFavourDto additionalFavourDto,
                                                                  final Long id) {
    AdditionalCleaningFavourModel cleaningTypeModel = additionalCleaningFavourRepository.findById(id)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с ID - " + id + " не существует!")
      );
    if (additionalFavourDto.getTitle() != null && !additionalFavourDto.getTitle().equalsIgnoreCase("")) {
      cleaningTypeModel.setTitle(additionalFavourDto.getTitle());
    }
    if (additionalFavourDto.getPrice() != 0) {
      cleaningTypeModel.setPrice(additionalFavourDto.getPrice());
    }
    return additionalCleaningFavourRepository.save(cleaningTypeModel);
  }

  public AdditionalCleaningFavourModel updateAdditionalFavourByTitle(final AdditionalFavourDto additionalFavourDto,
                                                                     final String title) {
    AdditionalCleaningFavourModel cleaningTypeModel = additionalCleaningFavourRepository
      .findByTitleIsLikeIgnoreCase(title)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с названием - " + title + " не существует!")
      );
    if (additionalFavourDto.getTitle() != null && !additionalFavourDto.getTitle().equalsIgnoreCase("")) {
      cleaningTypeModel.setTitle(additionalFavourDto.getTitle());
    }
    if (additionalFavourDto.getPrice() != 0) {
      cleaningTypeModel.setPrice(additionalFavourDto.getPrice());
    }
    return additionalCleaningFavourRepository.save(cleaningTypeModel);
  }

  public void deleteAdditionalFavourById(final Long id) {
    additionalCleaningFavourRepository.deleteById(id);
  }

  public void deleteAdditionalFavourByTitle(final String title) {
    additionalCleaningFavourRepository.deleteByTitleIsLikeIgnoreCase(title);
  }
}
