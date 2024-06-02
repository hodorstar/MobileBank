package bank.models;

import jakarta.persistence.Entity;

import java.util.List;
import java.util.Set;

@Entity
public class User {
    private Long id;
    private String name;
    private String email;
    private Set<Wallet> wallets;
    private Set<Transaction> receivedTransactions;
    private Set<Transaction> sentTransactions;
}
