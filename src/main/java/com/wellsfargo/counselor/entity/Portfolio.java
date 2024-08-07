package main.java.com.wellsfargo.counselor.entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.table;

@Entity
@table(name="portfolios")
public class Portfolio {
    @Id
    @GeneratedValue
    private long portfolioId;

    @Column
    private String creationDate;

    @ManyToOne
    @JoinColumn(name="clientId")
    private Client client;

    @OneToMany(mappedBy = "portfolios", cascade = cascadeType.REMOVE)
    private Set<Security> securities;

    protected Portfolio(){
    }
    public Portfolio(Client clientNew, String creationDate, List<Security> securitiesList){
        this.client = clientNew;
        this.creationDate = creationDate;


        securities = new HashSet<Security>();
        setSecuritiesFromList(securitiesList);
        for(Security security : securitiesList){
            security.setPortfolio(this);
        }
    }
    @Override
    public String toString(){
        String securityListString = String.format("Securities: %n %s",
                securities.stream()
                        .map(security -> String.format("#%d %s %s", security.getSecurityId(), security.getName(), security.getPurchaseDate()))
                        .collect(Collectors.joining(",%n")));
        return(
                String.format(
                        "Portfolio ID: %d, Owner: %s, %s, Creation Date: %s %n %s"
                        , portfolioId, client.getLastName(), client.getFirstName(), creationDate, securityListString));
    }
    public void setClient(Client newClient){
        if(client == newClient){
            return;
        }else {
            client.deletePortfolio(this);
            this.client = newClient;
            newClient.addPortfolio(this);
        }
    }
    public Client getClient(){return client;}

    public long getPortfolioId(){return portfolioId;}

    public String getCreationDate(){return creationDate;}
    public void setCreationDate(String newCreationDate){creationDate=newCreationDate;}
    public void setSecuritiesFromList(List<Security> securitiesList){
        securities.clear();
        securities.addAll(securitiesList);
        for(Security security : securitiesList){
            security.setPortfolio(this);
        }
    }
    public void addSecurity(Security security){
        securities.add(security);
        security.setPortfolio(this);
    }
    public void addPortfoliosFromList(List<Security> securitiesList){
        securities.addAll(securitiesList);
        for(Security security : securitiesList){
            security.setPortfolio(this);
        }
    }
    public void deleteSecurity(Security security){
        securities.delete(security);
    }
    public void deleteSecuritiesFromList(List<Security> securitiesList){
        securitiesList.forEach(securities::remove);
    }


}
