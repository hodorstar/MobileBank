package bank.repository;

import bank.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletCrudRepository extends JpaRepository<Wallet, Long> {
}
