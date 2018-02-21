package com.infosupport.machinelearning.modelmanagement.storage;

public class RegExMatcher {

    public boolean isValidName(String s) {
        String pattern = "^[a-z0-9](-?[a-z0-9])*$";
        return s.matches(pattern);
    }
}
