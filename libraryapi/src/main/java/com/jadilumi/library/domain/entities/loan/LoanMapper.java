package com.jadilumi.library.domain.entities.loan;

import com.jadilumi.library.domain.entities.loan.dto.LoanDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class LoanMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    public abstract void mapDTOToEntity(LoanDTO dto, @MappingTarget Loan entity);

}
