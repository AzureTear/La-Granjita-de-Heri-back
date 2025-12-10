package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.RegionControllerV2;
import com.example.La_Granjita_de_Heri.model.Region;

@Component
public class RegionModelAssembler implements RepresentationModelAssembler<Region, EntityModel<Region>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Region> toModel(Region region){
        return EntityModel.of(region,
                linkTo(methodOn(RegionControllerV2.class).getRegionById(Integer.valueOf(region.getId()))).withSelfRel(),
                linkTo(methodOn(RegionControllerV2.class).getAllRegiones()).withRel("Region")
        );
    }
}
