package bank.controllers;

import bank.DTO.WalletDTO;
import bank.models.Wallet;
import bank.services.UserService;
import bank.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {
    private MockMvc mockMvc;

    @Mock
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

    @Test
    void createWalletTest() throws Exception {
        WalletDTO walletDTO = new WalletDTO(6L, 2L, 50000.0);
        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ownerId\":2}")
                        .content("{\"balance\":50000.0}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getWalletByIdTest() throws Exception {
        WalletDTO walletDTO = new WalletDTO(1L, null, 1000.0);
        when(walletService.getWalletById(1L)).thenReturn(walletDTO);
        mockMvc.perform(get("/wallets/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.balance").value(1000.0));
        verify(walletService).getWalletById(1L);
    }

    @Test
    void getAllWalletsTest() throws Exception {
        WalletDTO walletDTO1 = new WalletDTO(1L, null, 1000.0);
        WalletDTO walletDTO2 = new WalletDTO(2L, null, 2000.0);
        when(walletService.getAllWallets()).thenReturn(Arrays.asList(walletDTO1, walletDTO2));
        mockMvc.perform(get("/wallets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$.[0].balance").value(1000.0))
                .andExpect(jsonPath("$.[1].balance").value(2000.0));
        verify(walletService).getAllWallets();
    }

    @Test
    void updateWalletTest() throws Exception {
        mockMvc.perform(put("/wallets/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1000000.0"))
                .andExpect(status().isOk());
        verify(walletService, times(1)).updateWallet(1L, 1000000.0);
    }

    @Test
    void deleteWalletTest() throws Exception {
        mockMvc.perform(delete("/wallets/{id}", 1))
                .andExpect(status().isOk());
        verify(walletService).deleteWallet(1L);
    }
}
