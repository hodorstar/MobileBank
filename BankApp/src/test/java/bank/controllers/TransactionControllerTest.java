package bank.controllers;


import bank.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class TransactionControllerTest {

 @Mock
 private TransactionService transactionService;

 @InjectMocks
 private TransactionController transactionController;

 @Test
  void testExecuteTransaction() {
  Long senderWalletId = 1L;
  Long receiverWalletId = 2L;
  Double amount = 100.0;
  doNothing().when(transactionService).executeTransaction(senderWalletId, receiverWalletId, amount);
  ResponseEntity<Void> responseEntity = transactionController.executeTransaction(senderWalletId, receiverWalletId, amount);
  assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  verify(transactionService, times(1)).executeTransaction(senderWalletId, receiverWalletId, amount);
 }
}
