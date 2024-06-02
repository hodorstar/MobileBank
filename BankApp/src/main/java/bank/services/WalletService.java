package bank.services;

import bank.exceptions.WalletNotFoundException;
import bank.models.Wallet;
import bank.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }
    @Transactional(readOnly = true)
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() ->
                        new WalletNotFoundException("Could not find wallet with id: " + id)
                );
    }

    @Transactional(readOnly = true)
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Transactional
    public void updateWallet(Long id, Double newBalance) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() ->
                        new WalletNotFoundException("Could not find wallet with id: " + id)
                );
      wallet.setBalance(newBalance);
      walletRepository.save(wallet);
    }

    @Transactional
    public void deleteWallet(Wallet wallet ) {
        walletRepository.delete(wallet);
    }

}
