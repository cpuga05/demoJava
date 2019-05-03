package shop.infrastructure.ui.console;

import shop.application.service.cart.removeproduct.RemoveProductCartCommand;
import shop.application.service.cart.take.TakeCartCommand;
import shop.application.service.cart.takeproduct.TakeProductCartCommand;
import shop.application.service.cart.view.CartLineResponse;
import shop.application.service.cart.view.CartResponse;
import shop.application.service.cart.view.ViewCartQuery;
import shop.application.service.product.create.CreateProductCommand;
import shop.application.service.product.find.ProductResponse;
import shop.application.service.product.find.ProductsResponse;
import shop.application.service.product.find.all.FindAllProductsQuery;
import shop.application.service.product.find.one.FindOneProductQuery;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Init {
    private static Kernel kernel;
    private static boolean start;
    private static String cartId;

    public static void main(String[] args) {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();

            return;
        }

        int request;

        do {
            try {
                printBorder();
                showCart(kernel, cartId);
                printMenu();
                printLine();

                do {
                    try {
                        request = Integer.parseInt(waitToRequest("What do you want do? "));
                    } catch (Exception e) {
                        request = 0;
                    }
                } while (request < 0 || request > 9);

                printLine();

                switch (request) {
                    case 0:
                        start = false;
                        break;
                    case 1:
                        showProducts(kernel);
                        printLine();
                        waitToRequest("Press any key to continue...");
                        break;
                    case 2:
                        takeProduct(kernel, cartId);
                        break;
                    case 3:
                        removeProduct(kernel, cartId);
                        break;
                    case 4:
                        printLine();
                        waitToRequest("Press any key to continue...");
                        break;
                }

                printLine();
            } catch (Exception e) {
                printLine("[" + e.getClass().getName() + "] " + e.getMessage());
                printLine();
            }
        } while (start);

        printLine("Thanks for the visit :)");
    }

    private static void init() throws Exception {
        kernel = Kernel.init();
        start = true;
        cartId = UUID.randomUUID().toString();

//        kernel.dispatch(new CreateProductCommand(UUID.randomUUID(), "1", "Lagavulin 16 Years", "50EUR", 5, "48EUR"));
//        kernel.dispatch(new CreateProductCommand(UUID.randomUUID(), "2", "Ardbeg 10 Years", "40EUR", 10, "37EUR"));
//        kernel.dispatch(new CreateProductCommand(UUID.randomUUID(), "3", "Laphroaig 10 Years", "30EUR", 10, "28EUR"));
//        kernel.dispatch(new CreateProductCommand(UUID.randomUUID(), "4", "Talisker Storm", "35EUR", 12, "30EUR"));
//        kernel.dispatch(new CreateProductCommand(UUID.randomUUID(), "5", "Talisker 10 Years", "30EUR", 10, "27EUR"));

        kernel.dispatch(new TakeCartCommand(UUID.randomUUID(), cartId));
    }

    private static void removeProduct(Kernel kernel, String cartId) throws Exception {
        showCart(kernel, cartId);

        String productId = waitToRequest("What product do you want to remove? [Product Id] ");

        kernel.dispatch(new RemoveProductCartCommand(UUID.randomUUID(), cartId, productId));
    }

    private static void takeProduct(Kernel kernel, String cartId) throws Exception {
        showProducts(kernel);
        printLine();

        String productId = waitToRequest("What product do you want to take? [Product Id] ");
        int units = Integer.parseInt(waitToRequest("How many units? "));

        kernel.dispatch(new TakeProductCartCommand(UUID.randomUUID(), cartId, productId, units));
    }

    private static void showCart(Kernel kernel, String cartId) throws Exception {
        CartResponse cart = kernel.ask(new ViewCartQuery(UUID.randomUUID(), cartId));

        printLine("Your cart:");
        printLine(String.format("%-10s %-20s %-10s %-15s %-10s %-15s", "ProductId", "Name", "Units", "TotalPrice", "Offer", "TotalOfferPrice"));

        for (CartLineResponse line : cart.lines()) {
            ProductResponse product = kernel.ask(new FindOneProductQuery(UUID.randomUUID(), line.productId()));

            printLine(String.format("%-10s %-20s %-10s %-15s %-10s %-15s",
                    line.productId(),
                    product.name(),
                    line.units(),
                    line.totalPrice(),
                    line.offer() ? "true" : "false",
                    line.totalOfferPrice()
            ));
        }

        printLine(String.format("%25s: %-25s", "TotalPriceWithoutOffer", cart.totalPriceWithoutOffers()));
        printLine(String.format("%25s: %-25s", "TotalPriceWithOffer", cart.totalPriceWithOffers()));
        printLine();
    }

    private static void showProducts(Kernel kernel) throws Exception {
        ProductsResponse products = kernel.ask(new FindAllProductsQuery(UUID.randomUUID()));

        printLine(String.format("%-10s %-20s %-10s %-15s %-15s", "ProductId", "Name", "Price", "OfferPrice", "OfferUnits"));

        for (ProductResponse product : products) {
            printLine(String.format("%-10s %-20s %-10s %-15s %-15s",
                    product.id(),
                    product.name(),
                    product.price(),
                    product.offerUnits(),
                    product.offerPrice()
            ));
        }
    }

    private static String waitToRequest(String text) {
        System.out.print(text);

        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    private static void printMenu() {
        printLine("You can:");
        printLine("          [1] Show all products");
        printLine("          [2] Take product");
        printLine("          [3] Remove product");
        printLine("          [4] Change Currency");
        printLine("[Another key] Leave the store :(");
    }

    private static void printBorder() {
        printLine("==================== Uvinum Shop ====================");
    }

    private static void printLine(String text) {
        System.out.println(text);
    }

    private static void printLine() {
        System.out.println();
    }
}
