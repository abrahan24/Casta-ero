package uap.usic.siga.servicios;

import java.io.ByteArrayInputStream;
import java.util.List;

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

public interface GdocServicios {

	public List<GdocResoluciones> listarResoluciones(Long idGdocConsejo);
	 public List<GdocAutoridades> listarAutoridades(Long idGdocConsejo);
	 
	 public GdocArchivosAdjuntos registrarGdocArchivoAdjunto(GdocArchivosAdjuntos gdocArchivoAdjunto);
	 public GdocResoluciones registrarGdocResoluciones(GdocResoluciones gdocResoluciones);
	
	 public GdocConsejos buscarConsejoPorIdUsuarioIdMnuTipoFuncion(Long idUsuario, Long idMnuTipoFuncion);
	 
	 public GdocArchivosAdjuntos buscarGdocArchivosAdjuntosPorIdGdocResolucion(Long idGdocResolucion);
	 
	 // ==================== Modulo Convenios =======================
	 public List<GdocRepresentantes> listarGdocRepresentante();
	 public List<GdocTiposConvenios> listarGdocTiposConvenios();
	 
	 public List<GdocConvenios> listarGdocConveniosPorIdGdocConsejo(Long  idGdocConsejo);
	 public GdocConvenios guardarGdocConvenios(GdocConvenios gdocConvenios);
	
	 public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocConvenio(Long idGdocConvenio);
	 
	 public GdocConvenios buscarGdocConvenios(Long idGdocConvenio);
	 public GdocConvenios modificarGdocConvenios(GdocConvenios gdocConvenios);
		
	 public GdocConvenios actualizarGdocConvenios(GdocConvenios gdocConvenios);
	 public GdocArchivosAdjuntos actualizarGdocArchivosAdjuntos(GdocArchivosAdjuntos gdocArchivosAdjuntos);
	 
		public GdocResoluciones buscarGdocResolucionesPorIdGdocResolucion(Long idGdocResolucion);
		public GdocResoluciones actualizarGdocResoluciones(GdocResoluciones gdocResoluciones);
		
		public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocArchivoAdjunto(Long idGeodcArchivoAdjunto);

		public GdocGestionConsejos buscarGdocGestionConsejosPorIdGdocConsejoGestion(Long idGdocConsejo, Integer gestion);
		public List<GdocResolucionesDigitales> listarGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestion(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion);
		public GdocResolucionesDigitales registrarGdocResolucionesDigitales(GdocResolucionesDigitales gdocResolucionesDigitales);
		
		public GdocResolucionesDigitales buscarGdocResolucionesDigitalesPorIdGdocResolucionesDigitales(Long idGdocResolucionDigital);
		
		public GdocResolucionesDigitales buscarMaxGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestion(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion);
		
		public GdocConvenios buscarGdocConveniosPorIdGdocTipoConvenioGestion(Long idGdocTipoConvenio, Integer gestion);
		
		public List<GdocConvenios> listarGdocConveniosPorIdGdocTipoCovenioGestion(Long idGdocTipoConvenio, Integer gestion);
		
		// ==================== Modulo Titulos =======================
		public List<PrsGradosAcademicos> listarPrsGradosAcademicos();

		public List<GdocTiposTitulos> listarGdocTiposTitulos();

		public List<GdocTiposTitulosGrados> listarGdocTiposTitulosGrados();

		public GdocTitulos guardarGdocTitulos(GdocTitulos gdocTitulos);

		public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTitulo(Long idGdocTitulo);

		public List<GdocTitulos> listarGdocTitulosPorIdGdocConsejo(Long idGdocConsejo);

		public GdocTitulos guardarGdocTitulosSET(GdocTitulos gdocTitulos);

		public GdocTitulos buscarGdocTitulosGET(Long idGdocTitulo);
		
		public GdocTitulos modificarGdocTitulos(GdocTitulos gdocTitulos);
		
		 public GdocTitulos actualizarGdocTitulos(GdocTitulos gdocTitulos);
		 

		// ==================== Modulo Titulados =======================

		public List<GdocTitulos> listarGdocTitulos(Long idGdocConsejo);

		public GdocTitulados guardarGdocTitulados(GdocTitulados gdocTitulados);

		public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTitulado(Long idGdocTitulado);

		public List<GdocTitulados> listarGdocTituladosPorIdGdocConsejo(Long idGdocConsejo);

		public GdocTitulados guardarGdocTituladosSET(GdocTitulados gdocTitulados);

		public GdocTitulados buscarGdocTituladosGET(Long idGdocTitulados);
		
		public GdocTitulados modificarGdocTitulados(GdocTitulados gdocTitulados);
		
		public GdocTitulados actualizarGdocTitulados(GdocTitulados gdocTitulados);
		// ==================== Modulo Consejos =======================
		public GdocConsejos guardarGdocConsejos(GdocConsejos gdocConsejos);
		public List<GdocConsejos> listarGdocConsejos();
		
		// ==================== Modulo Gestion Consejos =======================
		public List<GdocGestionConsejos> listarGdocGestionConsejosPorIdGdocConsejo();

		public GdocGestionConsejos guardarGdocGestionConsejosSET(GdocGestionConsejos gdocGestionConsejos);
		
		// ==================== Modulo Funciones Usuarios =======================
		public GdocUsrTiposFunciones guardarGdocUsrTiposFunciones(GdocUsrTiposFunciones gdocUsrTiposFunciones);
		public List<GdocUsrTiposFunciones> listarGdocUsrTiposFunciones();
		public List<GdocUsrTiposFunciones> listarGdocUsrTiposFuncionesPorIdGdocConsejo();
}
