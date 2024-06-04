package bank.services;

import bank.DTO.WalletDTO;
import bank.exceptions.WalletNotFoundException;
import bank.models.User;
import bank.models.Wallet;
import bank.repository.UserRepository;
import bank.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    private UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createWallet(WalletDTO walletDTO) {
        Wallet wallet = new Wallet();
        User user = userRepository.findById(walletDTO.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + walletDTO.getOwnerId()));
        wallet.setId(walletDTO.getId());
        wallet.setOwner(user);
        wallet.setBalance(walletDTO.getBalance());
        walletRepository.save(wallet);
    }

    @Transactional(readOnly = true)
    public WalletDTO getWalletById(Long id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() ->
                        new WalletNotFoundException("Could not find wallet with id: " + id)
                );
        return new WalletDTO(wallet.getId(), wallet.getOwner().getId(), wallet.getBalance());
    }

    @Transactional(readOnly = true)
    public List<WalletDTO> getAllWallets() {
        List<Wallet> wallets = walletRepository.findAll();
        List<WalletDTO> walletDTOs = new ArrayList<>();
        for (Wallet wallet : wallets) {
            walletDTOs.add(new WalletDTO(wallet.getId(), wallet.getOwner().getId(), wallet.getBalance()));
        }
        return walletDTOs;
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
    public void deleteWallet(Long id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() ->
                        new WalletNotFoundException("Could not find wallet with id: " + id)
                );
        walletRepository.delete(wallet);
    }
}
