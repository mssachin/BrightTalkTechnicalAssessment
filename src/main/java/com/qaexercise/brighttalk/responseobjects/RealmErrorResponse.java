package com.qaexercise.brighttalk.responseobjects;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Sachin on 21/05/2017.
 */

@XmlRootElement(name = "error")
@XmlType(propOrder = {"code", "message"})
public class RealmErrorResponse {

    private String code;
    private String message;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
