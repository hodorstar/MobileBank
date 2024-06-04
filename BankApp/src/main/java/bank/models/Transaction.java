package bank.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_wallet_id", nullable = false)
    private  Wallet sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_wallet_id", nullable = false)
    private  Wallet receiver;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "date")
    private Timestamp date;

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public void setReceiver(Wallet receiver) {
        this.receiver = receiver;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", date=" + date +
                "}\n";
    }
}
