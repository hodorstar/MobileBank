package bank.controllers;

import bank.models.User;
import bank.models.Wallet;
import bank.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;
    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        walletService.createWallet(wallet);
        Wallet newWallet = walletService.getWalletById(wallet.getId());
        return ResponseEntity.ok(newWallet);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallet() {
        List<Wallet> wallet = walletService.getAllWallets();
        return ResponseEntity.ok(wallet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWalletBalance(@PathVariable Long id, @RequestBody Double newBalance) {
        walletService.updateWallet(id, newBalance);
        Wallet wallet = walletService.getWalletById(id);
        return ResponseEntity.ok(wallet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        walletService.deleteWallet(wallet);
        return ResponseEntity.ok().build();
    }
}
