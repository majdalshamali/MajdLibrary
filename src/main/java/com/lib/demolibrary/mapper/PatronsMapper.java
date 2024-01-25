package com.lib.demolibrary.mapper;

import com.lib.demolibrary.dto.PatronsDto;
import com.lib.demolibrary.dto.createDto.PatronsCreateDto;
import com.lib.demolibrary.entity.Patrons;

public class PatronsMapper {

    public static PatronsDto mapToPatronDto(Patrons patrons,PatronsDto patronsDto)
    {
        patronsDto.setId(patrons.getId());
        patronsDto.setAddress(patrons.getAddress());
        patronsDto.setPatronID(patrons.getPatronID());
        patronsDto.setEmail(patrons.getEmail());
        patronsDto.setMobile(patrons.getMobile());
        patronsDto.setFirstName(patrons.getFirstName());
        patronsDto.setLastName(patrons.getLastName());
        return patronsDto;
    }

    public static Patrons mapToPatron(PatronsDto patronsDto,Patrons patrons)
    {
        patrons.setAddress(patronsDto.getAddress());
        patrons.setEmail(patronsDto.getEmail());
        patrons.setMobile(patronsDto.getMobile());
        patrons.setFirstName(patronsDto.getFirstName());
        patrons.setLastName(patronsDto.getLastName());
        patrons.setPatronID(patronsDto.getPatronID());
        return patrons;
    }

    public static PatronsDto mapFromPatronCreateDto(PatronsCreateDto patronsCreateDto,PatronsDto patronsDto)
    {
        patronsDto.setLastName(patronsCreateDto.getLastName());
        patronsDto.setEmail(patronsCreateDto.getEmail());
        patronsDto.setMobile(patronsCreateDto.getMobile());
        patronsDto.setAddress(patronsCreateDto.getAddress());
        patronsDto.setPatronID(patronsCreateDto.getPatronID());
        patronsDto.setFirstName(patronsCreateDto.getFirstName());
        return patronsDto;
    }
}
