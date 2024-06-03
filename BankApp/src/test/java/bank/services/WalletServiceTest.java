package bank.services;

import bank.exceptions.WalletNotFoundException;
import bank.models.User;
import bank.models.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceTest {
    @Autowired
    private WalletService walletService;


    @Test
    @Sql("classpath:test_database.sql")
    void testAllReadWallet() {
        List<Wallet> wallets = walletService.getAllWallets();
        long iter = 1;
        for (Wallet p : wallets) {
            assertEquals(iter, p.getId());
            iter++;
        }
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testGetWalletBiId() {
        Wallet wallet = walletService.getWalletById(3L);
        assertEquals(3L, wallet.getId());
        assertEquals(3L, wallet.getOwner().getId());
        assertEquals(2000.0, wallet.getBalance());
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testGetWalletBiIdThrow() {
        assertThrows(WalletNotFoundException.class, () -> walletService.getWalletById(30L));
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testCreateWallet() {
        Wallet wallet = new Wallet(null, new User(1l, "Alice", "alice@example.com", null), 7777.0, null, null);
        walletService.createWallet(wallet);
        Wallet findWallet = walletService.getWalletById(7L);
        assertEquals(7L, findWallet.getId());
        assertEquals(1L, findWallet.getOwner().getId());
        assertEquals(7777.0, findWallet.getBalance());
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testUpdateWallet() {
        walletService.updateWallet(2L, 9.0);
        Wallet findWallet = walletService.getWalletById(2L);
        assertEquals(2L, findWallet.getId());
        assertEquals(2L, findWallet.getOwner().getId());
        assertEquals(9.0, findWallet.getBalance());
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testUpdateWalletThrow() {
        assertThrows(WalletNotFoundException.class, () -> walletService.updateWallet(20L, 9.0));
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testDeleteWallet() {
        Wallet findWallet = walletService.getWalletById(4L);
        walletService.deleteWallet(findWallet);
        assertThrows(WalletNotFoundException.class, () -> walletService.getWalletById(4L));
    }
}
