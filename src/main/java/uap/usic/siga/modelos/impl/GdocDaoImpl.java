package uap.usic.siga.modelos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.GdocArchivosAdjuntos;
import uap.usic.siga.entidades.GdocAutoridades;
import uap.usic.siga.entidades.GdocConsejos;
import uap.usic.siga.entidades.GdocConvenios;
import uap.usic.siga.entidades.GdocGestionConsejos;
import uap.usic.siga.entidades.GdocRepresentantes;
import uap.usic.siga.entidades.GdocResoluciones;
import uap.usic.siga.entidades.GdocResolucionesDigitales;
import uap.usic.siga.entidades.GdocTiposConvenios;
import uap.usic.siga.entidades.GdocTiposTitulos;
import uap.usic.siga.entidades.GdocTiposTitulosGrados;
import uap.usic.siga.entidades.GdocTitulados;
import uap.usic.siga.entidades.GdocTitulos;
import uap.usic.siga.entidades.GdocUsrTiposFunciones;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.GdocDao;

@Repository("gdocDao")
public class GdocDaoImpl implements GdocDao{
	
	 @PersistenceContext
	   private EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
	@Override
	public List<GdocResoluciones> listarResolucionesJPQL(Long idGdocConsejo) {
    	 String sql = "SELECT res "
    	 		+ " FROM GdocResoluciones res LEFT JOIN res.gdocConsejos cjo  "
                + " WHERE cjo.idGdocConsejo =:idGdocConsejo"
                + " AND res.idEstado = 'A' "
                + " AND cjo.idEstado = 'A' ";
         Query q = em.createQuery(sql);
         q.setParameter("idGdocConsejo", idGdocConsejo);
         return q.getResultList();
	}

	@Override
	public List<GdocAutoridades> listarAutoridadesJPQL(Long idGdocConsejo) {
		 String sql = "SELECT aut "
	    	 		+ " FROM GdocAutoridades aut LEFT JOIN aut.gdocConsejos  cjo "
	                + " WHERE cjo.idGdocConsejo =:idGdocConsejo "
	                + " AND aut.idEstado = 'A' "
	                + " AND cjo.idEstado = 'A' ";
	         Query q = em.createQuery(sql);
	         q.setParameter("idGdocConsejo", idGdocConsejo);
	         return q.getResultList();
	}

	@Override
	public GdocArchivosAdjuntos registrarGdocArchivoAdjuntoSET(GdocArchivosAdjuntos gdocArchivoAdjunto) {
		em.persist(gdocArchivoAdjunto);
		return gdocArchivoAdjunto;
	}

	@Override
	public GdocResoluciones registrarGdocResolucionesSET(GdocResoluciones gdocResoluciones) {
		em.persist(gdocResoluciones);
		return gdocResoluciones;
	}

	@Override
	public GdocConsejos buscarConsejoPorIdUsuarioIdMnuTipoFuncionGET(Long idUsuario, Long idMnuTipoFuncion) {
		  String sql = "SELECT cjo "
	                + " FROM GdocUsrTiposFunciones gtf LEFT JOIN gtf.gdocConsejos cjo "
	                + " LEFT JOIN gtf.mnuTiposFunciones mtf LEFT JOIN gtf.usuarios usr"
	                + " WHERE mtf.idMnuTipoFuncion =:idMnuTipoFuncion "
	                + " AND usr.idUsuario =:idUsuario "
	                + " AND gtf.idEstado = 'A' "
	                + " AND cjo.idEstado =  'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idMnuTipoFuncion", idMnuTipoFuncion);
	        q.setParameter("idUsuario", idUsuario);
	    try {
	            return (GdocConsejos) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntosPorIdGdocResolucionGET(Long idGdocResolucion) {
		  String sql = "SELECT gaa  "
	                + " FROM GdocResoluciones res LEFT JOIN  res.gdocArchivosAdjuntos gaa "
	                + " WHERE res.idGdocResolucion =:idGdocResolucion "
	                + " AND res.idEstado = 'A' "
	                + " AND gaa.idEstado =  'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idGdocResolucion", idGdocResolucion);
	     try {
	            return (GdocArchivosAdjuntos) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public List<GdocRepresentantes> listarGdocRepresentanteJPQL() {
		 String sql = "SELECT rep "
	    	 		+ " FROM GdocRepresentantes rep  "
	                + " WHERE rep.idEstado = 'A' ";
	         Query q = em.createQuery(sql);
	         return q.getResultList();
	}

	@Override
	public List<GdocTiposConvenios> listarGdocTiposConveniosJPQL() {
		 String sql = "SELECT gtc "
	    	 		+ " FROM GdocTiposConvenios gtc  "
	                + " WHERE gtc.idEstado = 'A' ";
	         Query q = em.createQuery(sql);
	         return q.getResultList();
	}

	@Override
	public List<GdocConvenios> listarGdocConveniosPorIdGdocConsejoJQPL(Long idGdocConsejo) {
		 String sql = "SELECT cnv "
	    	 		+ " FROM GdocConvenios cnv LEFT JOIN cnv.gdocConsejos cjo  "
	                + " WHERE cjo.idGdocConsejo =:idGdocConsejo"
	                + " AND cnv.idEstado = 'A' "
	                + " AND cjo.idEstado = 'A' ";
	         Query q = em.createQuery(sql);
	         q.setParameter("idGdocConsejo", idGdocConsejo);
	         return q.getResultList();
	}

	@Override
	public GdocConvenios guardarGdocConveniosSET(GdocConvenios gdocConvenios) {
		em.persist(gdocConvenios);
		return gdocConvenios;
	}

    @Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocConvenioGET(Long idGdocConvenio) {
		  String sql = "SELECT gaa  "
	                + " FROM GdocConvenios cnv LEFT JOIN  cnv.gdocArchivosAdjuntos gaa "
	                + " WHERE cnv.idGdocConvenio =:idGdocConvenio "
	                + " AND cnv.idEstado = 'A' "
	                + " AND gaa.idEstado =  'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idGdocConvenio", idGdocConvenio);
	     try {
	            return (GdocArchivosAdjuntos) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public GdocConvenios buscarGdocConveniosGET(Long idGdocConvenio) {
		  String sql = "SELECT cnv "
	                + " FROM GdocConvenios cnv "
	                + " WHERE cnv.idGdocConvenio =:idGdocConvenio "
	                + " AND cnv.idEstado =  'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idGdocConvenio", idGdocConvenio);
	    try {
	            return (GdocConvenios) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public GdocConvenios modificarGdocConveniosSET(GdocConvenios gdocConvenios) {
		return em.merge(gdocConvenios);
	}

	@Override
	public GdocConvenios actualizarGdocConveniosSET(GdocConvenios gdocConvenios) {
		 return em.merge(gdocConvenios);  
	}

	@Override
	public GdocArchivosAdjuntos actualizarGdocArchivosAdjuntosSET(GdocArchivosAdjuntos gdocArchivosAdjuntos) {
		return em.merge(gdocArchivosAdjuntos);
	}

	@Override
	public GdocResoluciones buscarGdocResolucionesPorIdGdocResolucionGET(Long idGdocResolucion) {
		String sql = "SELECT res "
                + " FROM GdocResoluciones res "
                + " WHERE res.idGdocResolucion =:idGdocResolucion "
                + " AND res.idEstado =  'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idGdocResolucion", idGdocResolucion);
    try {
            return (GdocResoluciones) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public GdocResoluciones actualizarGdocResolucionesSET(GdocResoluciones gdocResoluciones) {
		return em.merge(gdocResoluciones);
	}

	@Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocArchivoAdjuntoGET(Long idGeodcArchivoAdjunto) {
		  String sql = "SELECT gaa  "
	                + " FROM GdocArchivosAdjuntos gaa "
	                + " WHERE gaa.idGeodcArchivoAdjunto =:idGeodcArchivoAdjunto "
	               + " AND gaa.idEstado =  'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idGeodcArchivoAdjunto", idGeodcArchivoAdjunto);
	     try {
	            return (GdocArchivosAdjuntos) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public GdocGestionConsejos buscarGdocGestionConsejosPorIdGdocConsejoGestionGET(Long idGdocConsejo, Integer gestion) {
		 String sql = "SELECT ggc "
	                + " FROM GdocGestionConsejos ggc  LEFT JOIN ggc.gdocConsejos cjo "
	                + " WHERE cjo.idGdocConsejo =:idGdocConsejo "
	                + " AND ggc.gestion   =:gestion "
	                + " AND ggc.idEstado = 'A' "
	                + " AND cjo.idEstado =  'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idGdocConsejo", idGdocConsejo);
	        q.setParameter("gestion", gestion);
	    try {
	            return (GdocGestionConsejos) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public List<GdocResolucionesDigitales> listarGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestionJPQL(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion) {
		 String sql = "SELECT res "
	    	 		+ " FROM GdocResolucionesDigitales res LEFT JOIN res.gdocConsejos cjo "
	    	 		+ " LEFT JOIN res.gdocGestionConsejos ggc "
	                + " WHERE cjo.idGdocConsejo =:idGdocConsejo "
	                + " AND  ggc.idGdocGestionConsejo =:idGdocGestionConsejo  "
	                + "  AND res.gestion =:gestion "
	                + " AND res.idEstado = 'A'  "
	                + " AND ggc.idEstado = 'A' "
	                + " AND cjo.idEstado = 'A' ";
	         Query q = em.createQuery(sql);
	         q.setParameter("idGdocConsejo", idGdocConsejo);
	         q.setParameter("idGdocGestionConsejo", idGdocGestionConsejo);
	         q.setParameter("gestion", gestion);
	         return q.getResultList();
	}

	@Override
	public GdocResolucionesDigitales registrarGdocResolucionesDigitalesSET(GdocResolucionesDigitales gdocResolucionesDigitales) {
		 em.persist(gdocResolucionesDigitales);
		 return gdocResolucionesDigitales;
	}

	@Override
	public GdocResolucionesDigitales buscarGdocResolucionesDigitalesPorIdGdocResolucionesDigitalesGET(Long idGdocResolucionDigital) {
		String sql = "SELECT grc "
                + " FROM GdocResolucionesDigitales grc "
                + " WHERE grc.idGdocResolucionDigital =:idGdocResolucionDigital "
                + " AND grc.idEstado =  'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idGdocResolucionDigital", idGdocResolucionDigital);
   try {
            return (GdocResolucionesDigitales) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public GdocResolucionesDigitales buscarMaxGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestionGET(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion) {
		 String sql = "SELECT MAX(res.idGdocResolucionDigital) "
	    	 		+ " FROM GdocResolucionesDigitales res LEFT JOIN res.gdocConsejos cjo "
	    	 		+ " LEFT JOIN res.gdocGestionConsejos ggc "
	                + " WHERE cjo.idGdocConsejo =:idGdocConsejo "
	                + " AND ggc.idGdocGestionConsejo =:idGdocGestionConsejo  "
	                + " AND res.gestion =:gestion "
	                + " AND res.idEstado = 'A' "
	                + " AND cjo.idEstado = 'A' ";
	         Query q = em.createQuery(sql);
	         q.setParameter("idGdocConsejo", idGdocConsejo);
	         q.setParameter("idGdocGestionConsejo", idGdocGestionConsejo);
	         q.setParameter("gestion", gestion);
	         Long idGdocResolucionDigital = (Long) q.getSingleResult();
	         
	         String sql2 = "SELECT grc "
	                 + " FROM GdocResolucionesDigitales grc "
	                 + " WHERE grc.idGdocResolucionDigital =:idGdocResolucionDigital ";
	        Query q2 = em.createQuery(sql2);
	         q2.setParameter("idGdocResolucionDigital", idGdocResolucionDigital);
	    try {
	             return (GdocResolucionesDigitales) q2.getSingleResult();
	         } catch (Exception e) {
	             return null;
	         }
	         
	         
	}

	@Override
	public GdocConvenios buscarGdocConveniosPorIdGdocTipoConvenioGestionGET(Long idGdocTipoConvenio, Integer gestion) {
		String sql = "SELECT cnv "
                + " FROM GdocConvenios cnv  LEFT JOIN cnv.gdocTiposConvenios gtc "
                + " WHERE gtc.idGdocTipoConvenio =:idGdocTipoConvenio "
                + " AND cnv.gestion =:gestion  "
                + " AND gtc.idEstado =  'A' "
                + " AND cnv.idEstado =  'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idGdocTipoConvenio", idGdocTipoConvenio);
        q.setParameter("gestion", gestion);
   try {
            return (GdocConvenios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public List<GdocConvenios> listarGdocConveniosPorIdGdocTipoCovenioGestionJPQL(Long idGdocTipoConvenio,	Integer gestion) {
		String sql = "SELECT cnv "
                + " FROM GdocConvenios cnv  LEFT JOIN cnv.gdocTiposConvenios gtc "
                + " WHERE gtc.idGdocTipoConvenio =:idGdocTipoConvenio "
                + " AND cnv.gestion =:gestion  "
                + " AND gtc.idEstado =  'A' "
                + " AND cnv.idEstado =  'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idGdocTipoConvenio", idGdocTipoConvenio);
        q.setParameter("gestion", gestion);
	         return q.getResultList();
	}

	//metodos para el modulo titulos
	
		@Override
		public List<PrsGradosAcademicos> listarPrsGradosAcademicosJPQL() {
			 String sql = "SELECT rep "
		    	 		+ " FROM PrsGradosAcademicos rep  "
		                + " WHERE rep.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         return q.getResultList();
		}
		
		@Override
		public List<GdocTiposTitulos> listarGdocTiposTitulosJPQL() {
			 String sql = "SELECT gtc "
		    	 		+ " FROM GdocTiposTitulos gtc  "
		                + " WHERE gtc.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         return q.getResultList();
		}
		
		@Override
		public List<GdocTiposTitulosGrados> listarGdocTiposTitulosGradosJPQL() {
			 String sql = "SELECT ttg "
		    	 		+ " FROM GdocTiposTitulosGrados ttg  "
		                + " WHERE ttg.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         return q.getResultList();
		}
		
		public List<GdocTitulos> listarGdocTitulosPorIdGdocConsejoJQPL(Long idGdocConsejo) {
			 String sql = "SELECT titu "
		    	 		+ " FROM GdocTitulos titu LEFT JOIN titu.gdocConsejos cjo  "
		                + " WHERE cjo.idGdocConsejo =:idGdocConsejo"
		                + " AND titu.idEstado = 'A' "
		                + " AND cjo.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         q.setParameter("idGdocConsejo", idGdocConsejo);
		         return q.getResultList();
		}

		@Override
		public GdocTitulos guardarGdocTitulosSET(GdocTitulos gdocTitulos) {
			em.persist(gdocTitulos);
			return gdocTitulos;
		}


		@Override
		public GdocTitulos buscarGdocTitulosGET(Long idGdocTitulo) {
			  String sql = "SELECT cnv "
		                + " FROM GdocTitulos cnv "
		                + " WHERE cnv.idGdocTitulo =:idGdocTitulo "
		                + " AND cnv.idEstado =  'A' ";
		        Query q = em.createQuery(sql);
		        q.setParameter("idGdocTitulo", idGdocTitulo);
		    try {
		            return (GdocTitulos) q.getSingleResult();
		        } catch (Exception e) {
		            return null;
		        }
		}
		
		@Override
		public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTituloGET(Long idGdocTitulo) {
			  String sql = "SELECT gaa  "
		                + " FROM GdocTitulos cnv LEFT JOIN  cnv.gdocArchivosAdjuntos gaa "
		                + " WHERE cnv.idGdocTitulo =:idGdocTitulo "
		                + " AND cnv.idEstado = 'A' "
		                + " AND gaa.idEstado =  'A' ";
		        Query q = em.createQuery(sql);
		        q.setParameter("idGdocTitulo", idGdocTitulo);
		     try {
		            return (GdocArchivosAdjuntos) q.getSingleResult();
		        } catch (Exception e) {
		            return null;
		        }
		}
		
		
		
		@Override
		public GdocTitulos modificarGdocTitulosSET(GdocTitulos gdocTitulos) {
			// TODO Auto-generated method stub
			return em.merge(gdocTitulos);
		}

		//metodos para el modulo titulados
		
		
		
		@Override
		public List<GdocTitulos> listarGdocTitulosJPQL(Long idGdocConsejo) {
			 String sql = "SELECT aut "
		    	 		+ " FROM GdocTitulos aut LEFT JOIN aut.gdocConsejos  cjo "
		                + " WHERE cjo.idGdocConsejo =:idGdocConsejo "
		                + " AND aut.idEstado = 'A' "
		                + " AND cjo.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         q.setParameter("idGdocConsejo", idGdocConsejo);
		         return q.getResultList();
		}
		

		public List<GdocTitulados> listarGdocTituladosPorIdGdocConsejoJQPL(Long idGdocConsejo) {
			 String sql = "SELECT cnv "
		    	 		+ " FROM GdocTitulados cnv LEFT JOIN cnv.gdocConsejos cjo  "
		                + " WHERE cjo.idGdocConsejo =:idGdocConsejo"
		                + " AND cnv.idEstado = 'A' "
		                + " AND cjo.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         q.setParameter("idGdocConsejo", idGdocConsejo);
		         return q.getResultList();
		}
		
		@Override
		public GdocTitulados guardarGdocTituladosSET(GdocTitulados gdocTitulados) {
			em.persist(gdocTitulados);
			return gdocTitulados;
		}


		@Override
		public GdocTitulados buscarGdocTituladosGET(Long idGdocTitulado) {
			  String sql = "SELECT cnv "
		                + " FROM GdocTitulados cnv "
		                + " WHERE cnv.idGdocTitulado =:idGdocTitulado "
		                + " AND cnv.idEstado =  'A' ";
		        Query q = em.createQuery(sql);
		        q.setParameter("idGdocTitulado", idGdocTitulado);
		    try {
		            return (GdocTitulados) q.getSingleResult();
		        } catch (Exception e) {
		            return null;
		        }
		}                       
	 
		    @Override
			public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTituladoGET(Long idGdocTitulado) {
				  String sql = "SELECT gaa  "
			                + " FROM GdocTitulados cnv LEFT JOIN  cnv.gdocArchivosAdjuntos gaa "
			                + " WHERE cnv.idGdocTitulado =:idGdocTitulado "
			                + " AND cnv.idEstado = 'A' "
			                + " AND gaa.idEstado =  'A' ";
			        Query q = em.createQuery(sql);
			        q.setParameter("idGdocTitulado", idGdocTitulado);
			     try {
			            return (GdocArchivosAdjuntos) q.getSingleResult();
			        } catch (Exception e) {
			            return null;
			        }
			
		}

		

		@Override
		public GdocTitulos actualizarGdocTitulosSET(GdocTitulos gdocTitulos) {
			// TODO Auto-generated method stub
			return em.merge(gdocTitulos);
		}

		
		
		@Override
		public GdocTitulados modificarGdocTituladosSET(GdocTitulados gdocTitulados) {
			// TODO Auto-generated method stub
			return em.merge(gdocTitulados);
		}

		@Override
		public GdocTitulados actualizarGdocTituladosSET(GdocTitulados gdocTitulados) {
			// TODO Auto-generated method stub
			return em.merge(gdocTitulados);
		}
		
		
		
		@Override
		public GdocConsejos guardarGdocConsejosSET(GdocConsejos gdocConsejos) {
			em.persist(gdocConsejos);
			return gdocConsejos;
		}
		
		@Override
		public List<GdocConsejos> listarGdocConsejosJPQL() {
			 String sql = "SELECT ttg "
		    	 		+ " FROM GdocConsejos ttg  "
		                + " WHERE ttg.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         return q.getResultList();
		}
		
		@Override
		public GdocUsrTiposFunciones guardarGdocUsrTiposFuncionesSET(GdocUsrTiposFunciones gdocUsrTiposFunciones) {
			em.persist(gdocUsrTiposFunciones);
			return gdocUsrTiposFunciones;
		}
		
		@Override
		public List<GdocUsrTiposFunciones> listarGdocGdocUsrTiposFuncionesJPQL() {
			 String sql = "SELECT ttg "
		    	 		+ " FROM GdocUsrTiposFunciones ttg  "
		                + " WHERE ttg.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         return q.getResultList();
		}
		
		public List<GdocUsrTiposFunciones> listarGdocUsrTiposFuncionesPorIdGdocConsejoJQPL() {
			String sql = "SELECT ttg "
	    	 		+ " FROM GdocUsrTiposFunciones ttg  "
	                + " WHERE ttg.idEstado = 'A' ";
	         Query q = em.createQuery(sql);
	         return q.getResultList();
		}
		
		public List<GdocGestionConsejos> listarGdocGestionConsejosPorIdGdocConsejoJQPL() {
			 String sql = "SELECT ttg "
		    	 		+ " FROM GdocGestionConsejos ttg  "
		                + " WHERE ttg.idEstado = 'A' ";
		         Query q = em.createQuery(sql);
		         return q.getResultList();
		}
		
		@Override
		public GdocGestionConsejos guardarGdocGestionConsejosSET(GdocGestionConsejos gdocGestionConsejos) {
			em.persist(gdocGestionConsejos);
			return gdocGestionConsejos;
		}

	


}
