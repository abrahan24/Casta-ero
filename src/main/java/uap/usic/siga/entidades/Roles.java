
package uap.usic.siga.entidades;


import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@SequenceGenerator(name = "SEQ_ROLES", allocationSize = 1)
public class Roles {
  
	@ManyToMany(mappedBy = "roles")
	private Set<Usuarios> usuarios;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol")   
    private Long idRol;
  
    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    public Roles() {}

    public Roles(AuthorityType name) {
        this.name = name;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

   
    public AuthorityType getName() {
        return name;
    }

    public void setName(AuthorityType name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "idRol=" + idRol +
                ", name='" + name + '\'' +
                '}';
    }
}

