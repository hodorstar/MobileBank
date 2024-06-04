package bank.services;

import bank.DTO.WalletDTO;
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

    private WalletDTO senderWallet;
    private WalletDTO receiverWallet;

    @BeforeEach
    void setUp() {
        senderWallet = new WalletDTO(1L, 1L, 1000.0);
        receiverWallet = new WalletDTO(2L, 2L, 500.0 );
    }

    @Test
    void testExecuteTransactionSuccess() {
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
