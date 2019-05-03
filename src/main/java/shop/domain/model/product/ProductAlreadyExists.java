package shop.domain.model.product;

public final class ProductAlreadyExists extends Exception {
    public ProductAlreadyExists(ProductId id) {
        super("The product " + id.id() + " already exists.");
    }
}
