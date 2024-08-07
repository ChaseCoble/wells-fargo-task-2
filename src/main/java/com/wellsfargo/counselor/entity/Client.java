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
@table(name="clients")
public class Client extends Person{
    @Id
    @GeneratedValue()
    private long clientId;

    @OneToMany(mappedBy= "clients", cascade = cascadeType.REMOVE)
    private Set<Portfolio> portfolios;
    @ManyToOne
    @JoinColumn(name = "advisorId")
    private Advisor advisor;

    protected Client(){
        super();
    }
    public Client(String firstName, String lastName, String address, String phone, String email, List<Portfolio> portfoliosList){
        super(firstName, lastName, address, phone, email);
        portfolios = new HashSet<Portfolio>();
        setPortfoliosFromList(portfoliosList);
        for(Portfolio portfolio : portfoliosList){
            portfolio.setClient(this);
        }
    }
    @Override
    public String toString(){
        String portfolioListString = String.format("Portfolios: %n %s",
                portfolios.stream()
                        .map(Portfolio :: toString)
                        .collect(Collectors.joining(",%n")));
        return(
                String.format(
                        "Client %s %s, ID: %d %n Address: %s  Phone: %s  Email: %s %n %s"
                        , getFirstName(), getLastName(), clientId, getAddress(), getPhone(), getEmail(), portfolioListString));
    }
    public void setAdvisor(Advisor newAdvisor){
        if(advisor == newAdvisor){
            return;
        }else {
            advisor.deleteClient(this);
            this.advisor = newAdvisor;
            newAdvisor.addClient(this);
        }
    }
    public Advisor getAdvisor(){return advisor;}

    public long getClientId(){return clientId;}

    public void setPortfoliosFromList(List<Portfolio> portfoliosList){
        portfolios.clear();
        portfolios.addAll(portfoliosList);
        for(Portfolio portfolio : portfoliosList){
            portfolio.setClient(this);
        }
    }
    public void addPortfolio(Portfolio portfolio){
        portfolios.add(portfolio);
        portfolio.setClient(this);
    }
    public void addPortfoliosFromList(List<Portfolio> portfoliosList){
        portfolios.addAll(portfoliosList);
        for(Portfolio portfolio : portfoliosList){
            portfolio.setClient(this);
        }
    }
    public void deletePortfolio(Portfolio portfolio){
        portfolio.setClient(null);
        portfolios.delete(portfolio);
    }
    public void deletePortfoliosFromList(List<Portfolio> portfolioList){
        portfolioList.forEach(portfolios::remove);
    }


}
