package bank.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@JsonIgnoreProperties({"owner"})
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private Double balance;


    @OneToMany(mappedBy = "sender")
    private Set<Transaction> sentTransactions = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<Transaction> receivedTransactions = new HashSet<>();

    public Wallet() {
    }

    public Wallet(Long id, User owner, Double balance, Set<Transaction> sentTransactions, Set<Transaction> receivedTransactions) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        this.sentTransactions = sentTransactions;
        this.receivedTransactions = receivedTransactions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public Double getBalance() {
        return balance;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", owner=" + owner +
                ", balance=" + balance +
                ", sentTransactions=" + sentTransactions +
                ", receivedTransactions=" + receivedTransactions +
                "}\n";
    }
}
