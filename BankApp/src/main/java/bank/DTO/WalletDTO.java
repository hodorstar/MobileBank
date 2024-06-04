package bank.DTO;

public class WalletDTO {
    private Long id;
    private Long ownerId;
    private Double balance;

    public WalletDTO() {
    }

    public WalletDTO(Long id, Long ownerId, Double balance) {
        this.id = id;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
