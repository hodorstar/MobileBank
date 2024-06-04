package bank.controllers;
import bank.DTO.WalletDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity<Void> createWallet(@RequestBody WalletDTO walletDTO) {
        walletService.createWallet(walletDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> getWalletById(@PathVariable Long id) {
        WalletDTO walletDTO = walletService.getWalletById(id);
        return ResponseEntity.ok(walletDTO);
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets() {
        List<WalletDTO> walletDTOs = walletService.getAllWallets();
        return ResponseEntity.ok(walletDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWalletBalance(@PathVariable Long id, @RequestBody Double newBalance) {
        walletService.updateWallet(id, newBalance);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.ok().build();
    }
}
