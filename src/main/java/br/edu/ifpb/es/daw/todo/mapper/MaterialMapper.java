package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Material;
import br.edu.ifpb.es.daw.todo.rest.dto.MaterialRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MaterialResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    public Material from(MaterialRequestDTO dto) {
        return Material.builder()
                .tipo(dto.tipo())
                .titulo(dto.titulo())
                .link(dto.link())
                .build();
    }

    public MaterialResponseDTO from(Material material) {
        return MaterialResponseDTO.builder()
                .id(material.getId())
                .tipo(material.getTipo())
                .titulo(material.getTitulo())
                .link(material.getLink())
                .aulaId(material.getAula() != null ? material.getAula().getId() : null)
                .build();
    }
}
