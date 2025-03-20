package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDtoResponse productDtoResponse;
    private ProductDtoRequest productDtoRequest;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        user.setUsername("username");
        user.setPassword("password");

        Category category = new Category();
        category.setId(1L);
        category.setName("category");

        productDtoResponse = new ProductDtoResponse(1L, "Test Product", "image.jpg", "Test Description", 100.0, 18,
                Condition.NUEVO, user, category);
        productDtoRequest = new ProductDtoRequest("Test Product", "image.jpg", "Test Description", 100.0, 18,
                Condition.NUEVO, 1L, 1L);
    }

    @Test
    void testGetProducts_NoFilters_ShouldReturnProducts() {

        when(productService.findAllProducts()).thenReturn(List.of(productDtoResponse));

        ResponseEntity<?> response = productController.getProducts(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(((List<?>) response.getBody()).size() > 0);
    }

    @Test
    void testCreateProduct_ShouldReturnCreatedProduct() {

        when(productService.createProduct(any(ProductDtoRequest.class))).thenReturn(productDtoResponse);

        ResponseEntity<?> response = productController.createProduct(productDtoRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", ((ProductDtoResponse) response.getBody()).name());
    }

    @Test
    void testGetProductById_ShouldReturnProduct() {

        when(productService.findById(1L)).thenReturn(productDtoResponse);

        ResponseEntity<ProductDtoResponse> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().id());
    }

    @Test
    void testUpdateProduct_ShouldReturnUpdatedProduct() {

        when(productService.updateProduct(eq(1L), any(ProductDtoRequest.class))).thenReturn(productDtoResponse);

        ResponseEntity<ProductDtoResponse> response = productController.updateProduct(1L, productDtoRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().id());
    }

    @Test
    void testDeleteProduct_ShouldReturnSuccessMessage() {

        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<Map<String, String>> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody().get("message"));
    }

    @Test
    void testGetProducts_WithFilters_ShouldReturnFilteredProducts() {
        when(productService.findProductsByFilters(
                Optional.of("Test"),
                Optional.of(1L),
                Optional.of("NUEVO"),
                Optional.of("Pequeños"),
                Optional.of("20€ - 50€")))
                .thenReturn(List.of(productDtoResponse));

        ResponseEntity<?> response = productController.getProducts(
                Optional.of("Test"),
                Optional.of(1L),
                Optional.of("NUEVO"),
                Optional.of("Pequeños"),
                Optional.of("20€ - 50€"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(((List<?>) response.getBody()).size() > 0);
    }

    @Test
    void testGetProducts_NoProductsFound_ShouldReturnBadRequest() {
        when(productService.findProductsByFilters(
                Optional.of("NonExistent"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()))
                .thenReturn(List.of());

        ResponseEntity<?> response = productController.getProducts(
                Optional.of("NonExistent"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("No products found", ((Map<?, ?>) response.getBody()).get("message"));
    }

    @Test
    void testGetProductById_ProductNotFound_ShouldThrowException() {
        when(productService.findById(999L)).thenThrow(new RuntimeException("Product with id 999 not found"));

        assertThrows(RuntimeException.class, () -> productController.getProductById(999L));
    }

    @Test
    void testUpdateProduct_ProductNotFound_ShouldThrowException() {
        when(productService.updateProduct(eq(999L), any(ProductDtoRequest.class)))
                .thenThrow(new RuntimeException("Product with id 999 not found"));

        assertThrows(RuntimeException.class, () -> productController.updateProduct(999L, productDtoRequest));
    }

    @Test
    void testDeleteProduct_ProductNotFound_ShouldThrowException() {
        doThrow(new RuntimeException("Product with id 999 not found")).when(productService).deleteProduct(999L);

        assertThrows(RuntimeException.class, () -> productController.deleteProduct(999L));
    }

    @Test
    void testGetProducts_WithSingleFilter_ShouldReturnFilteredProducts() {
        when(productService.findProductsByFilters(
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()))
                .thenReturn(List.of(productDtoResponse));

        ResponseEntity<?> response = productController.getProducts(
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(((List<?>) response.getBody()).size() > 0);
    }

    @Test
    void testGetProducts_WithPriceGroupFilter_ShouldReturnFilteredProducts() {
        when(productService.findProductsByFilters(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("20€ - 50€")))
                .thenReturn(List.of(productDtoResponse));

        ResponseEntity<?> response = productController.getProducts(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("20€ - 50€"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(((List<?>) response.getBody()).size() > 0);
    }

    @Test
    void testGetProducts_WithAgeGroupFilter_ShouldReturnFilteredProducts() {
        when(productService.findProductsByFilters(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Pequeños"),
                Optional.empty()))
                .thenReturn(List.of(productDtoResponse));

        ResponseEntity<?> response = productController.getProducts(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Pequeños"),
                Optional.empty());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(((List<?>) response.getBody()).size() > 0);
    }
}
