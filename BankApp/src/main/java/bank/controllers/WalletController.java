package bank.controllers;

import bank.models.Wallet;
import bank.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Wallet> getWalletById()

}
