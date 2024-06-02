package bank.services;

import bank.exceptions.TransactionNotFoundException;
import bank.models.Transaction;
import bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }



    @Transactional
            public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionNotFoundException("Could not find transaction with id: " + id)
                );
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
}
