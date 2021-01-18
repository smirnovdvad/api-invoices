package sdv.spring.apiinvoices.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sdv.spring.apiinvoices.domain.Good;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;
import sdv.spring.apiinvoices.repository.GoodRepository;
import sdv.spring.apiinvoices.services.GoodService;
import sdv.spring.apiinvoices.services.UnitOfMeasureService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoodJPAServiceIT {

    @Mock
    GoodRepository goodRepository;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    UnitOfMeasure unitOfMeasure;

    Good good1;
    Good good2;

    GoodService goodService;

    Set<Good> goods = new HashSet<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goodService = new GoodJPAService(goodRepository,unitOfMeasureService);

        unitOfMeasure = UnitOfMeasure.builder()
                .name("KG")
                .description("Kilos").build();
        good1 = Good.builder()
                .id(1L)
                .name("Patotes")
                .uom(unitOfMeasure)
                .build();

        good2 = Good.builder()
                .id(2L)
                .name("Tomatoes")
                .uom(unitOfMeasure)
                .build();

        goods.add(good1);
        goods.add(good2);

    }

    @Test
    void findAll() {
        when(goodRepository.findAll()).thenReturn(goods);
        Set<Good> resGoods = goodService.findAll();
        assertEquals(2,resGoods.size());
        verify(goodRepository,times(1)).findAll();
        verify(goodRepository,never()).findById(anyLong());
    }

    @Test
    void findById() {
        Optional<Good> optGood = Optional.of(good1);
        when(goodRepository.findById(anyLong())).thenReturn(optGood);
        assertEquals(1L,goodService.findById(2L).getId());
        verify(goodRepository,never()).findAll();
    }

    @Test
    void save() {
        when(goodRepository.save(any())).thenReturn(good2);
        assertEquals(2L,goodService.save(good1).getId());
        verify(unitOfMeasureService,times(1)).save(any());
    }

    @Test
    void delete() {
        goodService.delete(good2);
        verify(goodRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        goodService.deleteById(2L);
        verify(goodRepository,times(1)).deleteById(2L);
        verify(goodRepository,never()).delete(any());
    }

    @Test
    void findByName() {
        Optional<Good> optGood = Optional.of(good2);
        when(goodRepository.findByName(anyString())).thenReturn(optGood);
        assertEquals(2L,goodService.findByName("KG").getId());
    }
}