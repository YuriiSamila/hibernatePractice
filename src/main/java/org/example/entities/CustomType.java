package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Struct;

@Embeddable
@Struct(name = "custom_type")
public class CustomType {

    @Column(name = "num_value")
    private int numValue;

    @Column(name = "var_value", length = 10)
    private String varValue;

    public CustomType() {
    }

    public CustomType(int numValue, String varValue) {
        this.numValue = numValue;
        this.varValue = varValue;
    }

    public int getNumValue() {
        return numValue;
    }

    public void setNumValue(int numValue) {
        this.numValue = numValue;
    }

    public String getVarValue() {
        return varValue;
    }

    public void setVarValue(String varValue) {
        this.varValue = varValue;
    }

    @Override
    public String toString() {
        return "CustomType{" +
                "numValue=" + numValue +
                ", varValue='" + varValue + '\'' +
                '}';
    }
}
