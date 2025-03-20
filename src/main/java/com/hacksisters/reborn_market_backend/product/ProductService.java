package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.category.CategoryRepository;
import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    public ProductService(ProductRepository productRepository, UserRepository userRepository,
            CategoryRepository categoryRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    public List<ProductDtoResponse> findAllProducts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);

        cq.select(productRoot);

        List<Product> products = entityManager.createQuery(cq).getResultList();

        return products.stream().map(ProductDtoResponse::fromEntity).toList();
    }

    public List<ProductDtoResponse> findProductsByFilters(Optional<String> name,
            Optional<Long> categoryId,
            Optional<String> condition,
            Optional<String> ageGroup,
            Optional<String> priceGroup) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        addNameFilter(name, cb, productRoot, predicates);
        addCategoryFilter(categoryId, cb, productRoot, predicates);
        addConditionFilter(condition, cb, productRoot, predicates);
        addAgeFilter(ageGroup, cb, productRoot, predicates);
        addPriceFilter(priceGroup, cb, productRoot, predicates);

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        List<Product> products = entityManager.createQuery(cq).getResultList();

        return products.stream().map(ProductDtoResponse::fromEntity).toList();
    }

    private void addNameFilter(Optional<String> name, CriteriaBuilder cb, Root<Product> productRoot,
            List<Predicate> predicates) {
        name.filter(n -> !n.trim().isEmpty())
                .ifPresent(n -> {
                    String searchPattern = "%" + n.toLowerCase() + "%";
                    predicates.add(cb.like(cb.lower(productRoot.get("name")), searchPattern));
                });
    }

    private void addCategoryFilter(Optional<Long> categoryId, CriteriaBuilder cb, Root<Product> productRoot,
            List<Predicate> predicates) {
        categoryId.ifPresent(id -> predicates.add(cb.equal(productRoot.get("category").get("id"), id)));
    }

    private void addConditionFilter(Optional<String> condition, CriteriaBuilder cb, Root<Product> productRoot,
            List<Predicate> predicates) {
        condition.ifPresent(c -> predicates.add(cb.equal(productRoot.get("condition"), Condition.valueOf(c))));
    }

    private void addAgeFilter(Optional<String> ageGroup, CriteriaBuilder cb, Root<Product> productRoot,
            List<Predicate> predicates) {
        ageGroup.ifPresent(group -> predicates.add(createAgePredicate(cb, productRoot.get("age"), group)));
    }

    private void addPriceFilter(Optional<String> priceGroup, CriteriaBuilder cb, Root<Product> productRoot,
            List<Predicate> predicates) {
        priceGroup.ifPresent(group -> predicates.add(createPricePredicate(cb, productRoot.get("price"), group)));
    }

    private Predicate createAgePredicate(CriteriaBuilder cb, Path<Integer> agePath, String ageGroup) {
        switch (ageGroup) {
            case "Bebés":
                return cb.between(agePath, 0, 1);
            case "Pequeños":
                return cb.between(agePath, 1, 3);
            case "Preescolares":
                return cb.between(agePath, 3, 5);
            case "Niños":
                return cb.between(agePath, 5, 8);
            default:
                return cb.conjunction();
        }
    }

    private Predicate createPricePredicate(CriteriaBuilder cb, Path<Double> pricePath, String priceGroup) {
        switch (priceGroup) {
            case "Gratuito":
                return cb.equal(pricePath, 0.0);
            case "Menos de 20€":
                return cb.lessThan(pricePath, 20.0);
            case "20€ - 50€":
                return cb.between(pricePath, 20.0, 50.0);
            case "50€ - 100€":
                return cb.between(pricePath, 50.0, 100.0);
            case "Más de 100€":
                return cb.greaterThan(pricePath, 100.0);
            default:
                return cb.conjunction();
        }
    }

    public ProductDtoResponse createProduct(ProductDtoRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User with id " + request.userId() + " not found"));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category with id " + request.categoryId() + " not found"));

        Product saveProduct = productRepository.save(request.toEntity(user, category));
        return ProductDtoResponse.fromEntity(saveProduct);
    }

    public ProductDtoResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        return ProductDtoResponse.fromEntity(product);
    }

    @Transactional
    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));

        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.image() != null) {
            product.setImage(request.image());
        }
        if (request.description() != null) {
            product.setDescription(request.description());
        }
        if (request.price() != 0) {
            product.setPrice(request.price());
        }
        if (request.age() != 0) {
            product.setAge(request.age());
        }
        if (request.condition() != null) {
            product.setCondition(request.condition());
        }

        if (request.userId() != null) {
            User user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new RuntimeException("User with id " + request.userId() + " not found"));
            product.setUser(user);
        }

        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new RuntimeException("Category with id " + request.categoryId() + " not found"));
            product.setCategory(category);
        }

        return ProductDtoResponse.fromEntity(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

}
