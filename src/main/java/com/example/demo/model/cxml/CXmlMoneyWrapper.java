package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/** Wraps a single &lt;Money&gt; element — used for both &lt;Total&gt; and &lt;UnitPrice&gt;. */
public class CXmlMoneyWrapper {

    @JacksonXmlProperty(localName = "Money")
    private CXmlMoney money;

    public CXmlMoney getMoney() { return money; }
    public void setMoney(CXmlMoney money) { this.money = money; }
}
