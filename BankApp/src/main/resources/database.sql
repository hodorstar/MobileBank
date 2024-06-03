 drop table if exists  users CASCADE;
 drop table if exists wallets CASCADE;
 drop table if exists transactions CASCADE;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE wallets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id BIGINT NOT NULL,
    balance DOUBLE NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);


CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_wallet_id BIGINT NOT NULL,
    receiver_wallet_id BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    date TIMESTAMP,
    FOREIGN KEY (sender_wallet_id) REFERENCES wallets(id),
    FOREIGN KEY(receiver_wallet_id) REFERENCES wallets(id)
);

INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com');
INSERT INTO users (name, email) VALUES ('Bob', 'bob@example.com');
INSERT INTO users (name, email) VALUES ('Charlie', 'charlie@example.com');
INSERT INTO users (name, email) VALUES ('David', 'david@example.com');
INSERT INTO users (name, email) VALUES ('Eve', 'eve@example.com');

-- Вставка данных в таблицу кошельков
INSERT INTO wallets (owner_id, balance) VALUES (1, 1000.0);
INSERT INTO wallets (owner_id, balance) VALUES (2, 1500.0);
INSERT INTO wallets (owner_id, balance) VALUES (3, 2000.0);
INSERT INTO wallets (owner_id, balance) VALUES (4, 2500.0);
INSERT INTO wallets (owner_id, balance) VALUES (5, 3000.0);
INSERT INTO wallets (owner_id, balance) VALUES (5, 5555.0);

-- Вставка данных в таблицу транзакций
INSERT INTO transactions (sender_wallet_id, receiver_wallet_id, amount, date) VALUES (1, 2, 100.0, TIMESTAMP '2024-06-01 10:00:00');
INSERT INTO transactions (sender_wallet_id, receiver_wallet_id, amount, date) VALUES (2, 3, 150.0, TIMESTAMP '2024-06-02 10:00:00');
INSERT INTO transactions (sender_wallet_id, receiver_wallet_id, amount, date) VALUES (2, 3, 10.0, TIMESTAMP '2024-06-02 11:00:00');
INSERT INTO transactions (sender_wallet_id, receiver_wallet_id, amount, date) VALUES (5, 1, 150.0, TIMESTAMP '2024-06-02 10:00:00');
INSERT INTO transactions (sender_wallet_id, receiver_wallet_id, amount, date) VALUES (2, 1, 5.0, TIMESTAMP '2024-06-03 10:00:00');