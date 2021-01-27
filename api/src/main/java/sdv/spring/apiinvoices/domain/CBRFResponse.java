package sdv.spring.apiinvoices.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdv.spring.apiinvoices.service.CBRFService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="ValCurs")
public class CBRFResponse implements Serializable {
    @JsonProperty("Date")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy")
    private Date date;
    @XmlElement(name="Name")
    private String Name;


    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    Set<CurrencyCBRFNode> currencyCBRFNodeSet = new HashSet<>();
}
