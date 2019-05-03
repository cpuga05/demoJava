package shop.application.service.product.find;

import shop.domain.bus.query.Response;
import shop.domain.model.product.Product;

public final class ProductResponse implements Response {
    private String id;
    private String name;
    private String price;
    private int offerUnits;
    private String offerPrice;

    public ProductResponse(String id, String name, String price, int offerUnits, String offerPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.offerUnits = offerUnits;
        this.offerPrice = offerPrice;
    }

    public static ProductResponse fromProduct(Product product) {
        return new ProductResponse(product.id().id(), product.name(), product.price().toString(), product.offerUnits().amount(), product.offerPrice().toString());
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String price() {
        return this.price;
    }

    public int offerUnits() {
        return this.offerUnits;
    }

    public String offerPrice() {
        return this.offerPrice;
    }
}
