package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.category.CategoryRepository;
import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Product> criteriaQuery;

    @Mock
    private Root<Product> root;

    @Mock
    private TypedQuery<Product> typedQuery;

    @InjectMocks
    private ProductService productService;

    private User testUser;
    private Category testCategory;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("Test User");

        testCategory = new Category();
        testCategory.setId(1L);
        testCategory.setName("Test Category");

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(50.0);
        testProduct.setAge(2);
        testProduct.setCondition(Condition.NUEVO);
        testProduct.setUser(testUser);
        testProduct.setCategory(testCategory);
    }

    @Test
    void findAllProducts_ShouldReturnAllProducts() {
        List<Product> products = Arrays.asList(testProduct);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(products);

        List<ProductDtoResponse> result = productService.findAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getName(), result.get(0).name());
    }

    @Test
    void findById_WhenProductExists_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        ProductDtoResponse result = productService.findById(1L);

        assertNotNull(result);
        assertEquals(testProduct.getName(), result.name());
        assertEquals(testProduct.getPrice(), result.price());
    }

    @Test
    void createProduct_ShouldCreateAndReturnProduct() {
        ProductDtoRequest request = new ProductDtoRequest(
                "Test Product",
                "Test Description",
                "test.jpg",
                50.0,
                2,
                Condition.NUEVO,
                1L,
                1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        ProductDtoResponse result = productService.createProduct(request);

        assertNotNull(result);
        assertEquals(testProduct.getName(), result.name());
        assertEquals(testProduct.getPrice(), result.price());
    }

    @Test
    void updateProduct_WhenProductExists_ShouldUpdateAndReturnProduct() {
        ProductDtoRequest request = new ProductDtoRequest(
                "Updated Product",
                "Updated Description",
                "updated.jpg",
                75.0,
                3,
                Condition.USADO,
                1L,
                1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        ProductDtoResponse result = productService.updateProduct(1L, request);

        assertNotNull(result);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void deleteProduct_WhenProductExists_ShouldDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void findProductsByFilters_WithNameFilter_ShouldReturnFilteredProducts() {
        List<Product> products = Arrays.asList(testProduct);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(products);

        List<ProductDtoResponse> result = productService.findProductsByFilters(
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getName(), result.get(0).name());
    }

    @Test
    void findProductsByFilters_WithConditionFilter_ShouldReturnFilteredProducts() {
        List<Product> products = Arrays.asList(testProduct);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(products);

        List<ProductDtoResponse> result = productService.findProductsByFilters(
                Optional.empty(),
                Optional.empty(),
                Optional.of("NUEVO"),
                Optional.empty(),
                Optional.empty());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getCondition(), result.get(0).condition());
    }

    @Test
    void findProductsByFilters_WithAgeGroupFilter_ShouldReturnFilteredProducts() {
        List<Product> products = Arrays.asList(testProduct);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(products);

        List<ProductDtoResponse> result = productService.findProductsByFilters(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Pequeños"),
                Optional.empty());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).age() >= 1 && result.get(0).age() < 3);
    }

    @Test
    void findProductsByFilters_WithPriceGroupFilter_ShouldReturnFilteredProducts() {
        List<Product> products = Arrays.asList(testProduct);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(products);

        List<ProductDtoResponse> result = productService.findProductsByFilters(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("20€ - 50€"));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).price() >= 20.0 && result.get(0).price() <= 50.0);
    }

    @Test
    void findById_WhenProductDoesNotExist_ShouldThrowException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.findById(1L));
    }

    @Test
    void createProduct_WhenUserDoesNotExist_ShouldThrowException() {
        ProductDtoRequest request = new ProductDtoRequest(
                "Test Product",
                "Test Description",
                "test.jpg",
                50.0,
                2,
                Condition.NUEVO,
                1L,
                1L);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.createProduct(request));
    }

    @Test
    void createProduct_WhenCategoryDoesNotExist_ShouldThrowException() {
        ProductDtoRequest request = new ProductDtoRequest(
                "Test Product",
                "Test Description",
                "test.jpg",
                50.0,
                2,
                Condition.NUEVO,
                1L,
                1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.createProduct(request));
    }

    @Test
    void updateProduct_WhenProductDoesNotExist_ShouldThrowException() {
        ProductDtoRequest request = new ProductDtoRequest(
                "Updated Product",
                "Updated Description",
                "updated.jpg",
                75.0,
                3,
                Condition.USADO,
                1L,
                1L);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.updateProduct(1L, request));
    }

    @Test
    void deleteProduct_WhenProductDoesNotExist_ShouldThrowException() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> productService.deleteProduct(1L));
    }

}