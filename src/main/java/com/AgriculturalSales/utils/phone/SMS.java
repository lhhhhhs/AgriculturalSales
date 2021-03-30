package com.AgriculturalSales.utils.phone;

public class SMS {

    private String code;
    private String Message;
    private int SuccessCounts;


    public SMS() {
    }

    public SMS(String code, String message, int successCounts) {
        this.code = code;
        Message = message;
        SuccessCounts = successCounts;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }


    public int getSuccessCounts() {
        return SuccessCounts;
    }

    public void setSuccessCounts(int successCounts) {
        SuccessCounts = successCounts;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
