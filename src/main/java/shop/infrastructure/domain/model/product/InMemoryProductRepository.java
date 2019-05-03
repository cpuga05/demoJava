package shop.infrastructure.domain.model.product;

import shop.domain.model.product.Product;
import shop.domain.model.product.ProductId;
import shop.domain.model.product.ProductRepository;
import shop.domain.model.product.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {
    private final HashMap<String, Product> products;

    public InMemoryProductRepository() {
        this.products = new HashMap<String, Product>();
    }

    @Override
    public void save(Product product) {
        this.products.put(product.id().id(), product);
    }

    @Override
    public Product ofId(ProductId id) {
        if (!this.products.containsKey(id.id())) {
            return null;
        }

        return this.products.get(id.id());
    }

    @Override
    public Products all() {
        return new Products((List<Product>) new ArrayList<Product>(this.products.values()));
    }
}
