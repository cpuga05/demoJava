package shop.domain.model.product;

public final class ProductNotExists extends Exception {
    public ProductNotExists(ProductId id) {
        super("The product " + id.id() + " not exists.");
    }
}
