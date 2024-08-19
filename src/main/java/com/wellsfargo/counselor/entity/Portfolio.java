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
    private Long client;
    protected Portfolio(){
    }
    public Portfolio(Client clientNew, String creationDate){

    }
    @Override
    public String toString(){
        return(
                String.format(
                        "Portfolio ID: %d, Owner: %s, %s, Creation Date: %s"
                        , portfolioId, client.getLastName(), client.getFirstName(), creationDate));
    }
    public void setClient(Long newClient){
        if(client == newClient){
            return;
        }else {
            this.client = newClient;
        }
    }
    public Long getClient(){return client;}
    public long getPortfolioId(){return portfolioId;}

    public String getCreationDate(){return creationDate;}
    public void setCreationDate(String newCreationDate){creationDate=newCreationDate;}




}
