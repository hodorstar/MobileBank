package bank.services;

import bank.models.Transaction;
import bank.models.User;
import bank.models.Wallet;
import bank.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
 class TransactionServiceTest {

    @Mock
    private WalletService walletService;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Wallet senderWallet;
    private Wallet receiverWallet;

    @BeforeEach
    void setUp() {
        senderWallet = new Wallet(1L, new User(1L, "Sender", "sender@example.com", null), 1000.0, null, null);
        receiverWallet = new Wallet(2L, new User(2L, "Receiver", "receiver@example.com", null), 500.0, null, null);
    }

    @Test
    void testExecuteTransaction_success() {
        when(walletService.getWalletById(1L)).thenReturn(senderWallet);
        when(walletService.getWalletById(2L)).thenReturn(receiverWallet);

        transactionService.executeTransaction(1L, 2L, 200.0);

        assertEquals(800.0, senderWallet.getBalance());
        assertEquals(700.0, receiverWallet.getBalance());

        verify(walletService).createWallet(senderWallet);
        verify(walletService).createWallet(receiverWallet);
        verify(transactionRepository).save(any(Transaction.class));
    }

}
