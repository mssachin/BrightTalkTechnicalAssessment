package com.qaexercise.brighttalk.requestobjects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Sachin on 20/05/2017.
 */
@XmlRootElement(name = "realm")
@XmlType(propOrder = {"description"})
public class RealmCreate {



    private String name;
    private String description;


    @XmlAttribute
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}
