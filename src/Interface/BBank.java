package Interface;

public class BBank implements IBank{
    private String bankName;
    private String terminalId;
    private String password;

    public BBank(String bankName, String terminalId, String password) {
        this.bankName = bankName;
        this.terminalId = terminalId;
        this.password = password;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean connect(String ipAdress) {
        System.out.println("User Ip= " + ipAdress );
        System.out.println("Machine ip: " + this.hostIpAdress);
        System.out.println(this.bankName + " Connected");
        return true;
    }

    @Override
    public boolean payment(double amount, String cardNumber, String expiryDate, String cvc) {
        System.out.println("Waiting...!");
        System.out.println("Procces sucseed!");
        return true;
    }
}
