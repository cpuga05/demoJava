package shop.domain.model.product;

public interface ProductRepository {
    public void save(Product product);

    public Product ofId(ProductId id);

    public Products all();
}
