package shop.domain.model.cart;

import shop.domain.model.product.ProductId;
import shop.domain.model.product.ProductNotExists;
import shared.domain.Collection;

import java.util.List;

public final class CartLines extends Collection<CartLine> {
    public CartLines() {
        super();
    }

    public CartLines(CartLine[] items) {
        super(items);
    }

    public CartLines(List<CartLine> items) {
        super(items);
    }

    public CartLine findLine(ProductId productId) {
        for (CartLine line : this.items) {
            if (productId.equals(line.productId())) {
                return line;
            }
        }

        return null;
    }

    public void removeLine(ProductId productId) throws ProductNotExists {
        for (CartLine line : this.items) {
            if (productId.equals(line.productId())) {
                this.items.remove(line);

                return;
            }
        }

        throw new ProductNotExists(productId);
    }
}
