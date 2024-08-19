package main.java.com.wellsfargo.counselor.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.table;
@Entity
@table(name = "securities")
public class Security {
    @Id
    @GeneratedValue
    private long securityId;

    @ManyToOne
    @JoinColumn(name = "portfolioId")
    private Long portfolio;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private String purchaseDate;

    @Column
    private double purchasePrice;

    @Column
    private int quantity;

    protected Security(){}
    public Security(Long portfolio, String name, String category, String purchaseDate, double purchasePrice, int quantity){
        this.portfolio = portfolio;
        this.name = name;
        this.category = category;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return(String.format("Security ID: %d Security Name: %s Security Category: %s %n Purchase Date: %s Purchase Price: $%.2f Quantity: %d %n Containing Portfolio: %d %s, %s, %s", securityId, name, category, purchaseDate, purchasePrice, quantity, portfolio.getPortfolioId(), portfolio.getClient().getLastName(), portfolio.getClient().getFirstName(), portfolio.getCreationDate()));
    }
    public long getSecurityId(){return securityId;}

    public Portfolio getPortfolio(){return portfolio;}

    public void setPortfolio(Long newPortfolio){
        this.portfolio = newPortfolio;
    }

    public String getName(){return name;}
    public void setName(String newName){name = newName;}
    public String getCategory(){return category;}
    public void setCategory(String newCategory){category = newCategory;}
    public String getPurchaseDate(){return purchaseDate;}
    public void setPurchaseDate(String newPurchaseDate){purchaseDate = newPurchaseDate;}
    public double getPurchasePrice(){return purchasePrice;}
    public void setPurchasePrice(double newPurchasePrice){purchasePrice = newPurchasePrice;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int newQuantity){quantity = newQuantity;}

}
