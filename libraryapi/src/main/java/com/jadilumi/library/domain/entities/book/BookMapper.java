package com.jadilumi.library.domain.entities.book;

import com.jadilumi.library.domain.entities.book.dto.BookDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "availableStock", source = "availableStock")
    public abstract void mapDTOToEntity(BookDTO dto, @MappingTarget Book entity);
}
