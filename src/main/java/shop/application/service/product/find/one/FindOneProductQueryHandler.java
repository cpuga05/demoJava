package shop.application.service.product.find.one;

import shop.application.service.product.find.ProductResponse;
import shop.domain.bus.query.QueryHandler;
import shop.domain.model.product.ProductId;

public final class FindOneProductQueryHandler implements QueryHandler<ProductResponse, FindOneProductQuery> {
    private FindOneProductService findOneProductService;

    public FindOneProductQueryHandler(FindOneProductService findOneProductService) {
        this.findOneProductService = findOneProductService;
    }

    @Override
    public ProductResponse handle(FindOneProductQuery query) throws Exception {
        ProductId id = new ProductId(query.id());

        return ProductResponse.fromProduct(this.findOneProductService.execute(id));
    }
}
