package bank.models;

import jakarta.persistence.Entity;

@Entity
public class Transaction {
    private Long id;
    private  Long senderId;
    private  Long recipientId;
    private Double amount;

}
