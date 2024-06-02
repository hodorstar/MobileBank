package bank.services;

import bank.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
     private WalletRepository walletRepository;

    public WalletService(WalletRepository walletCrud) {
        this.walletRepository = walletCrud;
    }
}
