package bank.controllers;

import bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
        private final TransactionService transactionService;

        @Autowired
        public TransactionController(TransactionService transactionService) {
            this.transactionService = transactionService;
        }

        @PostMapping("/transfer")
        public ResponseEntity<Void> executeTransaction(@RequestParam Long senderWalletId, @RequestParam Long receiverWalletId, @RequestParam Double amount) {
            transactionService.executeTransaction(senderWalletId, receiverWalletId, amount);
            return ResponseEntity.ok().build();
        }

}
