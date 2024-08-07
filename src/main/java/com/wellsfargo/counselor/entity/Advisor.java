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
@table(name = "advisors")
public class Advisor extends Person {

    @Id
    @GeneratedValue()
    private long advisorId;
    @OneToMany(mappedBy = "advisors", cascade = CascadeType.REMOVE)
    private Set<Client> clients;


    protected Advisor() {
        super();
    }

    public Advisor(String firstName, String lastName, String address, String phone, String email, List<Client> clientsList) {
        super(firstName, lastName, address, phone, email);
        setClientsFromList(clientsList);
    }
    @Override
    public String toString(){
        String clientListString = String.format("Clients: %n %s",
                clients.stream()
                        .map(client -> (String.format("%d %s %s, Phone: %s", client.getClientId(), client.getFirstName(), client.getLastName(), client.getPhone())))
                        .collect(Collectors.joining(",%n")));
        return(
                String.format(
                        "Advisor %s %s, ID: %d %n Address: %s  Phone: %s  Email: %s %n %s"
                        , getFirstName(), getLastName(), advisorId, getAddress(), getPhone(), getEmail(), clientListString));
    }

    public Long getAdvisorId(){
        return advisorId;
    }
    public Set<Client> getClientsSet(){
        return clients;
    }

    public void setClientsFromSet(Set<Client> clientsSet){
        this.clients = clientsSet;
    }

    public void setClientsFromList(List<Client> clientsList){
        clients.clear();
        clients.addAll(clientsList);
    }

    public void addClient(Client client){
        clients.add(client);
        client.setAdvisor(this);
    }
    public void addClientsFromList(List<Client> clientsList){
        clients.addAll(clientsList);
        for(Client client : clientsList){
            client.setAdvisor(this);
        }
    }
    public void deleteClient(Client client){
        clients.remove(client);
        client.setAdvisor(null);
    }

    public void deleteClientsFromList(List<Client> clientList){
        clientList.forEach(clients::remove);
    }
 }
