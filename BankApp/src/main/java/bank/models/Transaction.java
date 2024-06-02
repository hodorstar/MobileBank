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
    @JoinColumn(name = "sender_id", nullable = false)
    private  User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private  User receiver;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "date")
    private Timestamp date;

    public Transaction() {
    }
}
