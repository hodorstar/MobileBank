package bank.exceptions;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String massage) {
        super(massage);
    }
}
