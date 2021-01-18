package sdv.spring.apiinvoices.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureTest {

    UnitOfMeasure unitOfMeasure;
    String name = "KG";
    String description = "Kilos";
    Long id = 99L;

    @BeforeEach
    public void setUp(){
        unitOfMeasure = UnitOfMeasure.builder()
                .name(this.name)
                .id(this.id)
                .description(this.description).build();
    }

    @Test
    void getId() {
        assertEquals(this.id, unitOfMeasure.getId());
    }

    @Test
    void getName() {
        assertEquals(this.name, unitOfMeasure.getName());
    }

    @Test
    void getDescription() {
        assertEquals(this.description, unitOfMeasure.getDescription());
    }
}