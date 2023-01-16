package ru.nadin.cleaningService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nadin.cleaningService.data.model.CleaningTypeModel;
import ru.nadin.cleaningService.data.repository.CleaningTypeRepository;
import ru.nadin.cleaningService.service.dto.CleaningTypeDto;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CleaningTypeService {
  private final CleaningTypeRepository cleaningTypeRepository;

  public CleaningTypeModel createCleaningType(final CleaningTypeDto cleaningTypeDto) {
    CleaningTypeModel cleaningTypeModel = new CleaningTypeModel();
    cleaningTypeModel.setDescription(cleaningTypeDto.getDescription());
    cleaningTypeModel.setPrice(cleaningTypeDto.getPrice());
    cleaningTypeModel.setTitle(cleaningTypeDto.getTitle());
    return cleaningTypeRepository.save(cleaningTypeModel);
  }

  public CleaningTypeModel updateCleaningTypeById(final CleaningTypeDto cleaningTypeDto, Long id) {
    CleaningTypeModel cleaningTypeModel = cleaningTypeRepository.findById(id)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с ID - " + id + " не существует!")
      );
    if (cleaningTypeDto.getTitle() != null && !cleaningTypeDto.getTitle().equalsIgnoreCase("")) {
      cleaningTypeModel.setTitle(cleaningTypeDto.getTitle());
    }
    if (cleaningTypeDto.getDescription() != null && !cleaningTypeDto.getDescription().equalsIgnoreCase("")) {
      cleaningTypeModel.setDescription(cleaningTypeDto.getDescription());
    }
    if (cleaningTypeDto.getPrice() != 0) {
      cleaningTypeModel.setPrice(cleaningTypeDto.getPrice());
    }
    return cleaningTypeRepository.save(cleaningTypeModel);
  }

  public CleaningTypeModel updateCleaningTypeByTitle(final CleaningTypeDto cleaningTypeDto, final String title) {
    CleaningTypeModel cleaningTypeModel = cleaningTypeRepository.findByTitleIsLikeIgnoreCase(title)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с названием - " + title + " не существует!")
      );
    if (cleaningTypeDto.getTitle() != null && !cleaningTypeDto.getTitle().equalsIgnoreCase("")) {
      cleaningTypeModel.setTitle(cleaningTypeDto.getTitle());
    }
    if (cleaningTypeDto.getDescription() != null && !cleaningTypeDto.getDescription().equalsIgnoreCase("")) {
      cleaningTypeModel.setDescription(cleaningTypeDto.getDescription());
    }
    if (cleaningTypeDto.getPrice() != 0) {
      cleaningTypeModel.setPrice(cleaningTypeDto.getPrice());
    }
    return cleaningTypeRepository.save(cleaningTypeModel);
  }

  public CleaningTypeModel searchCleaningTypeByTitle(final String title) {
    return cleaningTypeRepository.findByTitleIsLikeIgnoreCase(title)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с названием - " + title + " не существует!")
      );
  }

  public CleaningTypeModel searchCleaningTypeById(final Long id) {
    return cleaningTypeRepository.findById(id)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с ID - " + id + " не существует!")
      );
  }

  public void deleteCleaningTypeByTitle(final String title) {
    cleaningTypeRepository.deleteByTitleIsLikeIgnoreCase(title);
  }

  public void deleteCleaningTypeById(final Long id) {
    cleaningTypeRepository.deleteById(id);
  }

  public List<CleaningTypeModel> findAllCleaningTypes() {
    return cleaningTypeRepository.findAll();
  }
}
