package shop.application.service.product.find;

import shop.domain.bus.query.ResponseCollection;
import shop.domain.model.product.Product;
import shop.domain.model.product.Products;

import java.util.ArrayList;
import java.util.List;

public final class ProductsResponse extends ResponseCollection<ProductResponse> {
    public ProductsResponse() {
        super();
    }

    public ProductsResponse(ProductResponse[] items) {
        super(items);
    }

    public ProductsResponse(List<ProductResponse> items) {
        super(items);
    }

    public static ProductsResponse fromProducts(Products products) {
        List<ProductResponse> items = new ArrayList<>();

        for (Product product : products) {
            items.add(ProductResponse.fromProduct(product));
        }

        return new ProductsResponse(items);
    }
}
