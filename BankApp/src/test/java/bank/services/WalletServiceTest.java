package bank.services;

import bank.DTO.WalletDTO;
import bank.exceptions.WalletNotFoundException;
import bank.models.User;
import bank.models.Wallet;
import bank.repository.UserRepository;
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
class WalletServiceTest {
    @Autowired
    private WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("classpath:test_database.sql")
    void testAllReadWallet() {
        List<WalletDTO> wallets = walletService.getAllWallets();
        long iter = 1;
        for (WalletDTO wallet : wallets) {
            assertEquals(iter, wallet.getId());
            iter++;
        }
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testGetWalletBiId() {
        WalletDTO wallet = walletService.getWalletById(3L);
        assertEquals(3L, wallet.getId());
        assertEquals(3L, wallet.getOwnerId());
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
        WalletDTO walletDTO = new WalletDTO(null, 1L, 7777.0);
        walletService.createWallet(walletDTO);
        WalletDTO findWallet = walletService.getWalletById(7L);
        assertEquals(7L, findWallet.getId());
        assertEquals(1L, findWallet.getOwnerId());
        assertEquals(7777.0, findWallet.getBalance());
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testUpdateWallet() {
        walletService.updateWallet(2L, 9.0);
        WalletDTO findWallet = walletService.getWalletById(2L);
        assertEquals(2L, findWallet.getId());
        assertEquals(2L, findWallet.getOwnerId());
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
        walletService.deleteWallet(4L);
        assertThrows(WalletNotFoundException.class, () -> walletService.getWalletById(4L));
    }
}
