package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/** Represents &lt;Money currency="USD"&gt;2029.97&lt;/Money&gt; */
public class CXmlMoney {

    @JacksonXmlProperty(isAttribute = true)
    private String currency;

    @JacksonXmlText
    private String value;

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
