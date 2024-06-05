# MobileBank
В проекте реализованы CRUD методы с использованием фраемворка Spring Boot,
которые выполняют операции create, read, update и delete над объектом  User(пользователь) и  
Wallet(кошелек). Для сетевого взаимодействия используется REST.

Используется система сборки Gradle.

Для запуска приложение необходимо ввести в терминале в корневой папке пректа:
```
./gradlew clean bootRun     
```
В проекте присутствуют тесты @DataJpaTest для проверки работоспособности CRUD операций. Для запуска тестов необходимо ввести:
```
 ./gradlew clean test     
```
   
### Примеры сетевого взаимодействия через curl

#### Users
Примеры для работы с пользователями:

Создание пользователя:
 ```
  curl -X POST -H "Content-Type: application/json" -d '{"name": "[user_name]", "email": "[user_email]"}' http://localhost:8080/users
 ```
Получение информации о пользователе по ID:
```
curl -X GET http://localhost:8080/users/{userId}
```

Получение информации о всех пользователях:
```
curl -X GET http://localhost:8080/users
```

Обновление информации о пользователе:
```
curl -X PUT -H "Content-Type: application/json" -d '{"name":"[user_name]","email":"[user_email]"}' http://localhost:8080/users/{user_id}
```

Удаление пользователя по ID
```
curl -X DELETE http://localhost:8080/users/{user_id}
```

#### Wallet

Создание кошелька:
```
curl -X POST -H "Content-Type: application/json" -d '{"ownerId": 1, "balance": 1000.0}' http://localhost:8080/wallets
```

Получение информации о кошельке по ID:

```
curl -X GET http://localhost:8080/wallets/{walletId}
```
Получение информации о всех кошельках:
```
curl -X GET http://localhost:8080/wallets
```

обновление кошелька
```
curl -X PUT -H "Content-Type: application/json" -d '1500.0' http://localhost:8080/wallets/1
```

Удаление кошелька по ID
```
curl -X DELETE http://localhost:8080/wallets/1
```

#### Transaction

Примеры для работы с транзакциями:

Выполнение транзакции (перевод средств между кошельками):
```
curl -X POST -d "senderWalletId=1&receiverWalletId=2&amount=100.0" http://localhost:8080/transactions/transfer
```
