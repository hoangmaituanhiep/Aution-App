package app.functions;
public abstract class Item extends Entity {
    private String name;
    private final double StartingPrice;
    private double current_Price;
    private String detail = "Seller is too lazy to write anything here.";
    Item(double StartingPrice, String name) { //Giá khởi điểm
        this.StartingPrice = StartingPrice;
        this.name = name;
    }
    public void setNewPrice(double NewPrice){ //Giá hiện tại
        if (NewPrice > current_Price) {
            this.current_Price = NewPrice;
        } else {
            System.out.println("Hahaha Sucker.");
        }
    }
    public void writeDetail (String detail) {this.detail = detail;}

    public double getStartingPrice() {return StartingPrice;}
    public double getCurrent_Price() {return current_Price;}
    public String getDetail() {return detail;}
}
class 
