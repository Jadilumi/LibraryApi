package com.jadilumi.library.domain.entities.books;

import com.jadilumi.library.domain.entities.books.dto.BookDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "availableStock", source = "availableStock")
    public abstract void mapDTOToEntity(BookDTO dto, @MappingTarget Book entity);
}
