
package Interface;
public interface IBank {
    String hostIpAdress = "127.0.0.1";
    boolean connect(String ipAdress);
    boolean payment(double amount, String cardNumber, String expiryDate, String cvc);

}
