package com.jadilumi.library.domain.entities.client;

import com.jadilumi.library.domain.entities.client.dto.ClientDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    public abstract void mapDTOToEntity(ClientDTO dto, @MappingTarget Client entity);
}
