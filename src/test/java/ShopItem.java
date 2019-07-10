

public class ShopItem implements Comparable {
    String name;
    int price;

    public ShopItem(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Item name: ").append(this.name).append(", ").append("Item price: ").append(this.price);
        return sb.toString();
    }

    @Override
    public int compareTo(Object item){
        //return this.getName().compareTo(((ShopItem) item).getName());
        //return (this.getPrice() < ((ShopItem)item).getPrice() ? -1 : (this.getPrice() == ((ShopItem)item).getPrice() ? 0 : 1));
        return (Integer.compare(this.getPrice(), ((ShopItem) item).getPrice()));
    }
}
