package com.example.walletapi;

import com.example.walletapi.entity.Wallet;
import com.example.walletapi.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WalletRepository walletRepository;

    @BeforeEach
    public void setup() {
        walletRepository.deleteAll();
        walletRepository.save(new Wallet(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), BigDecimal.ZERO));
    }

    @Test
    public void testDepositFlow() throws Exception {
        String jsonRequest = """
            {
              "walletId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
              "operationType": "DEPOSIT",
              "amount": 2000
            }
            """;

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }
}
