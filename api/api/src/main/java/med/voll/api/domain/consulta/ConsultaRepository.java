package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    @Query("""
           SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END 
           FROM Consulta c
           WHERE c.id = :idPaciente AND c.data BETWEEN :dataInicio AND :dataFinal
           """)
    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime dataInicio, LocalDateTime dataFinal);
}
