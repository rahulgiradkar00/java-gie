package javagie.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javagie.arquitectura.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * The persistent class for the proyecto database table.
 *
 */
@Entity
@Table(name = "proyecto")
@NamedQueries({
    @NamedQuery(name="Proyecto.traerProyectosActivos", 
        query="select p from Proyecto p "
        + "where p.tipoEstadoProyecto.idTipoEstadoProyecto not in (6,7) "
        + "order by p.nombre asc "),
    @NamedQuery(name = "Proyecto.traerProyectosPorEmailUsuario",
    query = "SELECT DISTINCT p FROM Proyecto p "
    + "JOIN p.participantes pa "
    + "WHERE pa.usuario.email = :email "
    + "ORDER BY p.nombre ASC")
})
public class Proyecto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "PROYECTO_IDPROYECTO_GENERATOR", sequenceName = "PROYECTO_ID_PROYECTO_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROYECTO_IDPROYECTO_GENERATOR")
    @Column(name = "id_proyecto", unique = true, nullable = false)
    private Long idProyecto;
    @Size(max = 100)
    @Column(length = 500)
    private String descripcion;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Size(max = 100)
    @Column(length = 100)
    private String nombre;
    //bi-directional many-to-one association to HorasSoporte
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.REMOVE)
    private Set<HorasSoporte> horasSoportes = new HashSet<HorasSoporte>();
    //bi-directional many-to-one association to Participante
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.REMOVE)
    private Set<Participante> participantes = new HashSet<Participante>();
    //bi-directional many-to-one association to TipoEstadoProyecto
    @ManyToOne
    @JoinColumn(name = "id_tipo_estado_proyecto")
    private TipoEstadoProyecto tipoEstadoProyecto;
    //bi-directional many-to-one association to TipoProyecto
    @ManyToOne
    @JoinColumn(name = "id_tipo_proyecto", nullable = false)
    private TipoProyecto tipoProyecto;
    
    @OneToMany(mappedBy="proyecto", cascade= CascadeType.REMOVE)
    private Set<ReservaRecurso> reservasDeRecursos;

    public Proyecto() {
    }

    public Long getIdProyecto() {
        return this.idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<HorasSoporte> getHorasSoportes() {
        return this.horasSoportes;
    }

    public void setHorasSoportes(Set<HorasSoporte> horasSoportes) {
        this.horasSoportes = horasSoportes;
    }

    public Set<Participante> getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(Set<Participante> participantes) {
        this.participantes = participantes;
    }

    public TipoEstadoProyecto getTipoEstadoProyecto() {
        return this.tipoEstadoProyecto;
    }

    public void setTipoEstadoProyecto(TipoEstadoProyecto tipoEstadoProyecto) {
        this.tipoEstadoProyecto = tipoEstadoProyecto;
    }

    public TipoProyecto getTipoProyecto() {
        return this.tipoProyecto;
    }

    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public Set<ReservaRecurso> getReservasDeRecursos() {
        return reservasDeRecursos;
    }

    public void setReservasDeRecursos(Set<ReservaRecurso> reservasDeRecursos) {
        this.reservasDeRecursos = reservasDeRecursos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idProyecto == null) ? 0 : idProyecto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) obj;
        if (idProyecto == null) {
            if (other.idProyecto != null) {
                return false;
            }
        } else if (!idProyecto.equals(other.idProyecto)) {
            return false;
        }
        return true;
    }

    @Override
    @Transient
    public Object getPrimaryKey() {
        return this.idProyecto;
    }
}