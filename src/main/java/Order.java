public class Order {
    private String itemId;
    private int qty;
    private Item item;

    public Order(String itemId, int qty) {
        this.itemId = itemId;
        this.qty = qty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
