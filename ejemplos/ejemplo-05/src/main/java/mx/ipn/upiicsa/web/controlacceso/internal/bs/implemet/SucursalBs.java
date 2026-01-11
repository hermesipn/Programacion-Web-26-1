package mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.SucursalJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.DisponibilidadDto;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKBReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SucursalBs {

    @Inject
    SucursalJpaRepository sucursalRepository;

    public List<DisponibilidadDto> obtenerDisponibilidadCitas() {
        WKBReader reader = new WKBReader();

        return sucursalRepository.findAll().stream().map(jpa -> {
            // Usamos el Builder de tu DisponibilidadDto
            DisponibilidadDto dto = DisponibilidadDto.builder()
                    .idSucursal(jpa.getId())
                    .nombreSucursal(jpa.getNombre())
                    .horariosDisponibles(new ArrayList<>()) // Inicialmente vacío
                    .build();

            // Procesamos la ubicación (WKB -> Lat/Lon)
            try {
                if (jpa.getUbicacion() != null) {
                    Point p = (Point) reader.read(jpa.getUbicacion());
                    dto.setLatitud(p.getY());
                    dto.setLongitud(p.getX());
                }
            } catch (Exception e) {
                dto.setLatitud(0.0);
                dto.setLongitud(0.0);
            }

            // Simulación de horarios (Aquí luego conectarás con tu lógica de citas)
            dto.setHorariosDisponibles(List.of("09:00", "12:00", "16:00"));

            return dto;
        }).collect(Collectors.toList());
    }
}