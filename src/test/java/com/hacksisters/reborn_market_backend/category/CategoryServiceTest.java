package com.hacksisters.reborn_market_backend.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void should_findAllCategories() {
        // GIVEN
        Category category1 = new Category(1L, "Electronics");
        Category category2 = new Category(2L, "Clothing");
        List<Category> categories = List.of(category1, category2);
        List<CategoryDtoResponse> expectedResponse = categories.stream().map(CategoryDtoResponse::fromEntity).toList();

        when(categoryRepository.findAll()).thenReturn(categories);

        // WHEN
        List<CategoryDtoResponse> response = categoryService.findAllCategories();

        // THEN
        verify(categoryRepository).findAll();
        assertEquals(expectedResponse, response);
    }

    @Test
    void should_findCategoryById() {
        // GIVEN
        Category category = new Category(1L, "Electronics");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // WHEN
        CategoryDtoResponse response = categoryService.findCategoryById(1L);

        // THEN
        verify(categoryRepository).findById(1L);
        assertEquals("Electronics", response.name());
    }

    @Test
    void should_throwException_when_categoryNotFound() {
        // GIVEN
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // WHEN & THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.findCategoryById(1L));
        assertEquals("Category with id 1 not found.", exception.getMessage());
        verify(categoryRepository).findById(1L);
    }

    @Test
    void should_createCategory() {
        // GIVEN
        CategoryDtoRequest request = new CategoryDtoRequest("Electronics");
        Category category = new Category(1L, "Electronics");
        when(categoryRepository.findByName(request.name())).thenReturn(Optional.empty());
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        // WHEN
        CategoryDtoResponse response = categoryService.createCategory(request);

        // THEN
        verify(categoryRepository).findByName(request.name());
        verify(categoryRepository).save(Mockito.any(Category.class));
        assertEquals("Electronics", response.name());
    }

    @Test
    void should_throwException_when_categoryNameExists() {
        // GIVEN
        CategoryDtoRequest request = new CategoryDtoRequest("Electronics");
        Category existingCategory = new Category(1L, "Electronics");
        when(categoryRepository.findByName(request.name())).thenReturn(Optional.of(existingCategory));

        // WHEN & THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.createCategory(request));
        assertEquals("Category with the name Electronics already exist", exception.getMessage());
        verify(categoryRepository).findByName(request.name());
    }

    @Test
    void should_updateCategory() {
        // GIVEN
        Category existingCategory = new Category(1L, "Electronics");
        CategoryDtoRequest request = new CategoryDtoRequest("Home Appliances");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(new Category(1L, "Home Appliances"));

        // WHEN
        CategoryDtoResponse response = categoryService.updateCategory(1L, request);

        // THEN
        verify(categoryRepository).findById(1L);
        verify(categoryRepository).save(Mockito.any(Category.class));
        assertEquals("Home Appliances", response.name());
    }

    @Test
    void should_throwException_when_updatingNonExistentCategory() {
        // GIVEN
        CategoryDtoRequest request = new CategoryDtoRequest("Home Appliances");
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // WHEN & THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.updateCategory(1L, request));
        assertEquals("Category with id1 not found.", exception.getMessage());
        verify(categoryRepository).findById(1L);
    }

    @Test
    void should_deleteCategoryById() {
        // GIVEN
        when(categoryRepository.existsById(1L)).thenReturn(true);

        // WHEN
        categoryService.deleteCategoryById(1L);

        // THEN
        verify(categoryRepository).existsById(1L);
        verify(categoryRepository).deleteById(1L);
    }

    @Test
    void should_throwException_when_deletingNonExistentCategory() {
        // GIVEN
        when(categoryRepository.existsById(1L)).thenReturn(false);

        // WHEN & THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.deleteCategoryById(1L));
        assertEquals("Category with id 1 not found.", exception.getMessage());
        verify(categoryRepository).existsById(1L);
    }
}
