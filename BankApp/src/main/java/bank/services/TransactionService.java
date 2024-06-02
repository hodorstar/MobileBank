package bank.services;

import bank.models.Transaction;
import bank.models.Wallet;
import bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletService walletService;


    public void executeTransaction(Long senderWalletId, Long receiverWalletId, Double amount){
        Wallet senderWallet = walletService.getWalletById(senderWalletId);
        Wallet receiverWallet = walletService.getWalletById(receiverWalletId);

        if (senderWallet.getBalance() < amount) {
            throw new IllegalStateException("Execution of transaction is not possible.");
        }
        senderWallet.setBalance(senderWallet.getBalance() - amount);
        walletService.createWallet(senderWallet);

        receiverWallet.setBalance(receiverWallet.getBalance() + amount);
        walletService.createWallet(receiverWallet);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSender(senderWallet);
        transaction.setReceiver(receiverWallet);
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        transactionRepository.save(transaction);
    }
}
