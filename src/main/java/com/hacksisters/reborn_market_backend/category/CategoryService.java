package com.hacksisters.reborn_market_backend.category;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDtoResponse> findAllCategories(){
        List<Category> categorytList = categoryRepository.findAll();
        return categorytList.stream().map(CategoryDtoResponse::fromEntity).toList();
    }

    public CategoryDtoResponse findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id " + id + " not found."));

        return CategoryDtoResponse.fromEntity(category);
    }

    public CategoryDtoResponse createCategory(CategoryDtoRequest request){
        Optional<Category> optionalAirport = categoryRepository.findByName(request.name());
        if(optionalAirport.isPresent()){
            throw new RuntimeException("Category with the name " + request.name() + " already exist");
        }
        Category newCategory = request.toEntity();
        Category saveCategory = categoryRepository.save(newCategory);

        return CategoryDtoResponse.fromEntity(saveCategory);
    }

    public CategoryDtoResponse updateCategory(Long id, CategoryDtoRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id" + id + " not found."));

        existingCategory.setName(request.name());

        return CategoryDtoResponse.fromEntity( categoryRepository.save(existingCategory));
    }

    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw  new RuntimeException("Category with id " + id + " not found.");
        }
        categoryRepository.deleteById(id);
    }
}
