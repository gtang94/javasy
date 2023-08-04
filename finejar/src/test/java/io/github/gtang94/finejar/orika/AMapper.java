package io.github.gtang94.finejar.orika;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BMapper.class})
public interface AMapper {
    AMapper INSTANCE = Mappers.getMapper(AMapper.class);

    @Mapping(source = "BList", target = "YList")
    X aToX(A a);
}
