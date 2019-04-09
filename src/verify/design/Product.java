package verify.design;

//不变模式
public class Product {

    private final String no;
    private  final String name;
    private  final String price;
    public Product(String no,String name,String price) {
        // TODO Auto-generated constructor stub
        super();
        this.no=no;
        this.name=name;
        this.price=price;
    }
    public String getNo() {
        return no;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }

}
