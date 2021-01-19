package sdv.spring.apiinvoices.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdv.spring.apiinvoices.domain.Good;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.model.GoodDTO;

import static org.junit.jupiter.api.Assertions.*;

class GoodMapperTest extends TestInizializer {

    GoodMapper goodMapper = GoodMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        this.initUnitOfMeasure();
        this.initUnitOfMeasureDTO();
        this.initGood();
        this.initGoodDTO();
    }

    @Test
    void goodToGoodDTO() {
        GoodDTO goodDTO = goodMapper.goodToGoodDTO(this.good);
        assertEquals(this.good.getName(),goodDTO.getName());
    }

    @Test
    void goodDtoToGood() {
        Good good = goodMapper.goodDtoToGood(this.goodDTO);
        assertEquals(this.goodDTO.getName(),good.getName());
    }
}