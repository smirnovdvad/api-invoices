package sdv.spring.apiinvoices.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;
import sdv.spring.apiinvoices.serializer.BigDecimalDeSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class CurrencyCBRFNode implements Serializable {
    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    private String ID;


    @JacksonXmlProperty(localName = "NumCode")
    private String NumCode;

    @JacksonXmlProperty(localName = "CharCode")
    private String CharCode;

    @JacksonXmlProperty(localName = "Nominal")
    private Integer Nominal;

    @JacksonXmlProperty(localName = "Name")
    private String Name;

    @JsonDeserialize(using= BigDecimalDeSerializer.class)
    @JsonProperty("Value")
    private BigDecimal Value;

}
