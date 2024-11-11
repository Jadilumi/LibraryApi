package com.jadilumi.library.domain.entities.books;

import com.jadilumi.library.domain.entities.books.dto.BookDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    public abstract void mapDTOToEntity(BookDTO dto, @MappingTarget Book entity);

}
