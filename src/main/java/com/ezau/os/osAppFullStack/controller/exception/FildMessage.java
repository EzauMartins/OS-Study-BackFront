package com.ezau.os.osAppFullStack.controller.exception;

import java.io.Serializable;

public class FildMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldname;
    private String message;

    public FildMessage() {
    }

    public FildMessage(String fieldname, String message) {
        this.fieldname = fieldname;
        this.message = message;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
