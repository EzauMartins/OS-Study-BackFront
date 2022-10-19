package com.ezau.os.osAppFullStack.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
        private static final long serialVersionUID = 1L;

        private List<FildMessage> errors = new ArrayList<>();

        public ValidationError() {
        }

        public ValidationError(Long timestamp, Integer status, String error) {
            super(timestamp, status, error);
        }


        public List<FildMessage> getErrors() {
            return errors;
        }

        public void addError(String fieldName, String message) {
            this.errors.add(new FildMessage(fieldName,message));
        }
}
