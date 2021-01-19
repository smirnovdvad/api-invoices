package sdv.spring.apiinvoices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sdv.spring.apiinvoices.domain.PaymentMean;
import sdv.spring.apiinvoices.model.PaymentMeanDTO;

@Mapper
public interface PaymentMeanMapper {
    PaymentMeanMapper INSTANCE = Mappers.getMapper(PaymentMeanMapper.class);


    PaymentMeanDTO paymentMeanToPaymentMeanDTO(PaymentMean paymentMean);

    PaymentMean paymentMeanDtoToPaymentMean (PaymentMeanDTO paymentMeanDTO);
}
