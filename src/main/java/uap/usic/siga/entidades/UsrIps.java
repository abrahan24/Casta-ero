package uap.usic.siga.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "USR_IPS")
@SequenceGenerator(name = "SEQ_USR_IPS", allocationSize = 1)
public class UsrIps {
    
     @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuarios;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usr_ip")   
    private Long idUsrIp;
  
   
    @Column(name = "id_estado", length = 60, nullable = false)
    private String idEstado;

   
    @Column(name = "ip", length = 60, nullable = false)
    private String ip;

}
