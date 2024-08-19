package main.java.com.wellsfargo.counselor.entity;
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


    protected Advisor() {
        super();
    }

    public Advisor(String firstName, String lastName, String address, String phone, String email) {
        super(firstName, lastName, address, phone, email);
    }
    @Override
    public String toString(){
        return(
                String.format(
                        "Advisor %s %s, ID: %d %n Address: %s  Phone: %s  Email: %s"
                        , getFirstName(), getLastName(), advisorId, getAddress(), getPhone(), getEmail()));
    }

    public Long getAdvisorId(){
        return advisorId;
    }
 }
