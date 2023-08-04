package io.github.gtang94.finejar.orika;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CMapper {
    CMapper INSTANCE = Mappers.getMapper(CMapper.class);

    Z cToZ(C c);
}
