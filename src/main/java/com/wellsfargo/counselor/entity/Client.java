package main.java.com.wellsfargo.counselor.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clients")
public class Client extends Person{
    @Id
    @GeneratedValue()
    private long clientId;
    @ManyToOne
    @JoinColumn(name = "advisorId")
    private long advisor;

    protected Client(){
        super();
    }
    public Client(String firstName, String lastName, String address, String phone, String email){
        super(firstName, lastName, address, phone, email);
        }
    }
    @Override
    public String toString(){
        return(
                String.format(
                        "Client %s %s, ID: %d %n Address: %s  Phone: %s  Email: %s"
                        , getFirstName(), getLastName(), clientId, getAddress(), getPhone(), getEmail()));
    }

    public long getAdvisor(){return advisor;}
    public void setAdvisor(long advisorId){
       this.advisor = advisorId;
    }

    public long getClientId(){return clientId;}



}
