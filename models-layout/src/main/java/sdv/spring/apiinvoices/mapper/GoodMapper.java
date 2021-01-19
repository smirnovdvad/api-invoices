package sdv.spring.apiinvoices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sdv.spring.apiinvoices.domain.Good;
import sdv.spring.apiinvoices.model.GoodDTO;

@Mapper
public interface GoodMapper {
    GoodMapper INSTANCE = Mappers.getMapper(GoodMapper.class);


    GoodDTO goodToGoodDTO(Good good);

    Good goodDtoToGood (GoodDTO goodDTO);
}
