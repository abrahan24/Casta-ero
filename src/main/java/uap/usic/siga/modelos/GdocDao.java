package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.entidades.CjaTiposGastos;
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


public interface GdocDao {

	 public List<GdocResoluciones> listarResolucionesJPQL(Long idGdocConsejo);
	 public List<GdocAutoridades> listarAutoridadesJPQL(Long idGdocConsejoa);
	 
	 public GdocArchivosAdjuntos registrarGdocArchivoAdjuntoSET(GdocArchivosAdjuntos gdocArchivoAdjunto);
	 public GdocResoluciones registrarGdocResolucionesSET(GdocResoluciones gdocResoluciones);
	 
	 public GdocConsejos buscarConsejoPorIdUsuarioIdMnuTipoFuncionGET(Long idUsuario, Long idMnuTipoFuncion);
	 
	 public GdocArchivosAdjuntos buscarGdocArchivosAdjuntosPorIdGdocResolucionGET(Long idGdocResolucion);
	 
	 // ============ Moulo Convenios =============
	 public List<GdocRepresentantes> listarGdocRepresentanteJPQL();
	 public List<GdocTiposConvenios> listarGdocTiposConveniosJPQL();
	 
	 public List<GdocConvenios> listarGdocConveniosPorIdGdocConsejoJQPL(Long idGdocConsejo);
	 public GdocConvenios guardarGdocConveniosSET(GdocConvenios gdocConvenios);
	 
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocConvenioGET(Long idGdocConvenio);
	 
	public GdocConvenios buscarGdocConveniosGET(Long idGdocConvenio);
	public GdocConvenios modificarGdocConveniosSET(GdocConvenios gdocConvenios);
	
	public GdocConvenios actualizarGdocConveniosSET(GdocConvenios gdocConvenios);
	public GdocArchivosAdjuntos actualizarGdocArchivosAdjuntosSET(GdocArchivosAdjuntos gdocArchivosAdjuntos);
	
	// ================== Resoluciones
	
	public GdocResoluciones buscarGdocResolucionesPorIdGdocResolucionGET(Long idGdocResolucion);
	public GdocResoluciones actualizarGdocResolucionesSET(GdocResoluciones gdocResoluciones);
	
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocArchivoAdjuntoGET(Long idGeodcArchivoAdjunto);
 
	public GdocGestionConsejos buscarGdocGestionConsejosPorIdGdocConsejoGestionGET(Long idGdocConsejo, Integer gestion);
	public List<GdocResolucionesDigitales> listarGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestionJPQL(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion);
	public GdocResolucionesDigitales registrarGdocResolucionesDigitalesSET(GdocResolucionesDigitales gdocResolucionesDigitales);
	
	public GdocResolucionesDigitales buscarGdocResolucionesDigitalesPorIdGdocResolucionesDigitalesGET(Long idGdocResolucionDigital);
	
	public GdocResolucionesDigitales buscarMaxGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestionGET(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion);
	
	public GdocConvenios buscarGdocConveniosPorIdGdocTipoConvenioGestionGET(Long idGdocTipoConvenio, Integer gestion);
	
	public List<GdocConvenios> listarGdocConveniosPorIdGdocTipoCovenioGestionJPQL(Long idGdocTipoConvenio, Integer gestion);
	
	// ============ Modulo Titulos =============
		public List<PrsGradosAcademicos> listarPrsGradosAcademicosJPQL();
		public List<GdocTiposTitulos> listarGdocTiposTitulosJPQL();
		public List<GdocTiposTitulosGrados> listarGdocTiposTitulosGradosJPQL();
		
		public List<GdocTitulos> listarGdocTitulosPorIdGdocConsejoJQPL(Long idGdocConsejo);
		
		public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTituloGET(Long idGdocTitulo);
		public GdocTitulos guardarGdocTitulosSET(GdocTitulos gdocTitulos);
		public GdocTitulos buscarGdocTitulosGET(Long idGdocTitulo);
		
		public GdocTitulos modificarGdocTitulosSET(GdocTitulos gdocTitulos);
		
		public GdocTitulos actualizarGdocTitulosSET(GdocTitulos gdocTitulos);
		
		
		// ============ Modulo Titulados =============
		
		public List<GdocTitulos> listarGdocTitulosJPQL(Long idGdocConsejo);
		
		public List<GdocTitulados> listarGdocTituladosPorIdGdocConsejoJQPL(Long idGdocConsejo);
		public GdocTitulados guardarGdocTituladosSET(GdocTitulados gdocTitulados);
		public GdocTitulados buscarGdocTituladosGET(Long idGdocTitulados);
		
	    public GdocTitulados modificarGdocTituladosSET(GdocTitulados gdocTitulados);
		public GdocTitulados actualizarGdocTituladosSET(GdocTitulados gdocTitulados);
		public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTituladoGET(Long idGdocTitulado);
		
		
		// ============ Modulo Consejos =============
		
		public GdocConsejos guardarGdocConsejosSET(GdocConsejos gdocConsejos);
		public List<GdocConsejos> listarGdocConsejosJPQL();
		
		// ============ Modulo Gestion Consejos =============
		
		public List<GdocGestionConsejos> listarGdocGestionConsejosPorIdGdocConsejoJQPL();
		public GdocGestionConsejos guardarGdocGestionConsejosSET(GdocGestionConsejos gdocGestionConsejos);
		
		// ============ Modulo Funciones de Usuarios =============
		public GdocUsrTiposFunciones guardarGdocUsrTiposFuncionesSET(GdocUsrTiposFunciones gdocUsrTiposFunciones);
		public List<GdocUsrTiposFunciones> listarGdocGdocUsrTiposFuncionesJPQL();
		public List<GdocUsrTiposFunciones> listarGdocUsrTiposFuncionesPorIdGdocConsejoJQPL();
	
 }
