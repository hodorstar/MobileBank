package bank.services;

import bank.repository.WalletCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletCrudService {
    @Autowired
     private WalletCrudRepository walletCrud;

    public WalletCrudService(WalletCrudRepository walletCrud) {
        this.walletCrud = walletCrud;
    }
}
