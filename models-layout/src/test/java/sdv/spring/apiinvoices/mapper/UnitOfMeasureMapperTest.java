package sdv.spring.apiinvoices.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.model.UnitOfMeasureDTO;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureMapperTest extends TestInizializer {

    UnitOfMeasureMapper unitOfMeasureMapper = UnitOfMeasureMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        this.initUnitOfMeasure();
        this.initUnitOfMeasureDTO();
    }

    @Test
    void unitOfMeasureToUnitOfMeasureDTO() {
        UnitOfMeasureDTO unitOfMeasureDTO = unitOfMeasureMapper.unitOfMeasureToUnitOfMeasureDTO(this.unitOfMeasure);
        assertEquals(unitOfMeasureDTO.getName(),this.unitOfMeasure.getName());
    }

    @Test
    void unitOfMeasureDtoToUnitOfMeasure() {
        UnitOfMeasure unitOfMeasure = unitOfMeasureMapper.unitOfMeasureDtoToUnitOfMeasure(this.unitOfMeasureDTO);
        assertEquals(unitOfMeasure.getDescription(),this.unitOfMeasureDTO.getDescription());
    }
}