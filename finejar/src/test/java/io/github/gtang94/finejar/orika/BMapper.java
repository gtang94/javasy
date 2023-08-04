package io.github.gtang94.finejar.orika;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CMapper.class})
public interface BMapper {
    BMapper INSTANCE = Mappers.getMapper(BMapper.class);

    @Mapping(source = "CList", target = "ZList")
    Y bToY(B b);
}
