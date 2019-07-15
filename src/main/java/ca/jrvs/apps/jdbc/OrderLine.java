package ca.jrvs.apps.jdbc;

import java.math.BigDecimal;

public class OrderLine {


    private int quantity;
    private String productCode;
    private String productName;
    private int productSize;
    private String productVariety;
    private BigDecimal productPrice;

    public int getQuantity(int anInt) {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductCode(String string) {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName(String string) {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductSize(int anInt) {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    public String getProductVariety() {
        return productVariety;
    }

    public void setProductVariety(String productVariety) {
        this.productVariety = productVariety;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
