// Servidor

package models;

public enum PaymentWayEnum {
    CREDITO("Credito"),
    DEBITO("Debito");
    
    private String paymentWay;

    private PaymentWayEnum(String paymentWay) {
        this.paymentWay = paymentWay;
    }
    
    public String getPaymentWay() {
        return paymentWay;
    }
}
