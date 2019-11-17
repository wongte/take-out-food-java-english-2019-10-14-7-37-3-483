import java.util.ArrayList;
import java.util.List;

public class PromotionChecker {
    private ArrayList<Order> orders;
    private List<SalesPromotion> promotions;
    private SalesPromotion promotionInUsed = null;
    double saved = 0.0;
    ArrayList<String> affectedItems = new ArrayList<>();
    PromotionChecker(ArrayList<Order> orders, List<SalesPromotion> promotions) {
        this.orders = orders;
        this.promotions = promotions;
    }
    private double saveByPromotion1() {
       double total = 0;
       for (Order o : orders) {
           total += (o.getItem().getPrice()) * o.getQty();
       }
       return total >= 30 ? 6 : 0;
    }
    private double saveByPromotion2(List<String> relatedItems) {
        double saved = 0;
        for (Order o : orders) {
            for (String p : relatedItems) {
                if (p.equals(o.getItemId())) {
                    saved += (o.getItem().getPrice() * o.getQty()) / 2.0;
                    affectedItems.add(o.getItem().getName());
                    break;
                }
            }
        }
        return saved;
    }

    boolean check() {
        for (SalesPromotion prom : promotions) {
            double tmp = 0.0;
            if (prom.getType().equals("BUY_30_SAVE_6_YUAN")) {
                tmp = saveByPromotion1();
            } else if (prom.getType().equals("50%_DISCOUNT_ON_SPECIFIED_ITEMS")) {
                tmp = saveByPromotion2(prom.getRelatedItems());
            }
            if (saved < tmp) {
                promotionInUsed = prom;
                saved = tmp;
            }
        }
        return promotionInUsed != null;
    }

    String getConsoleOutput() {
        if (promotionInUsed.getType().equals("BUY_30_SAVE_6_YUAN"))
            return promotionInUsed.getDisplayName() + ", saving " + String.format("%.0f", saved) + " yuan";
        else if (promotionInUsed.getType().equals("50%_DISCOUNT_ON_SPECIFIED_ITEMS")) {
            return promotionInUsed.getDisplayName() + " ("+ String.join(", ", affectedItems) +"), saving " + String.format("%.0f", saved) + " yuan";
        } else {
            return "";
        }
    }

    public double getSaved() {
        return saved;
    }
}
