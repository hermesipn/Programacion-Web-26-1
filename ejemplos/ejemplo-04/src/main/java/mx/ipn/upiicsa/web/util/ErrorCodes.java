package mx.ipn.upiicsa.web.util;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    RN_001("Favor de proporcionar el campo"),
    RN_002("El dato ingresado no es valido, favor de proporcionar el tipo de dato correcto"),;

    String message;
    ErrorCodes(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
