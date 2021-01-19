package sdv.spring.apiinvoices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;
import sdv.spring.apiinvoices.model.CompanyDTO;
import sdv.spring.apiinvoices.model.UnitOfMeasureDTO;

@Mapper
public interface UnitOfMeasureMapper {
    UnitOfMeasureMapper INSTANCE = Mappers.getMapper(UnitOfMeasureMapper.class);


    UnitOfMeasureDTO unitOfMeasureToUnitOfMeasureDTO(UnitOfMeasure unitOfMeasure);

    UnitOfMeasure unitOfMeasureDtoToUnitOfMeasure (UnitOfMeasureDTO unitOfMeasureDTO);
}
