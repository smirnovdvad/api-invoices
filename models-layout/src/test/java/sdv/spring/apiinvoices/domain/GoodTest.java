package sdv.spring.apiinvoices.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodTest {

    Good good;
    UnitOfMeasure unitOfMeasure;

    Long uomId = 3L;
    String uomName = "KG";
    String uomDescription = "Kilos";
    String goodName = "Televisor";
    Long goodId = 4L;

    @BeforeEach
    public void setUp(){

        unitOfMeasure = UnitOfMeasure.builder()
                .id(this.uomId)
                .description(this.uomDescription)
                .name(this.uomName)
                .build();

        good = Good.builder()
                .name(this.goodName)
                .id(this.goodId)
                .uom(unitOfMeasure)
                .build();
    }

    @Test
    void getId() {
        assertEquals(this.goodId, good.getId());
    }

    @Test
    void getName() {
        assertEquals(this.goodName, good.getName());
    }

    @Test
    void getUom() {
        assertEquals(this.unitOfMeasure, good.getUom());
    }
}