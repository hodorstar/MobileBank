package bank.repository;

import bank.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPepository extends JpaRepository<User, Long> {
}
