package uap.usic.siga.servicios.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import uap.usic.siga.modelos.GdocDao;
import uap.usic.siga.servicios.GdocServicios;

@Service("gdocServicio")
@Transactional
public class GdocServiciosImpl implements GdocServicios {

	 @Autowired
	 private GdocDao dao;

	@Override
	public List<GdocResoluciones> listarResoluciones(Long idGdocConsejo) {
		return dao.listarResolucionesJPQL(idGdocConsejo);
	}

	@Override
	public List<GdocAutoridades> listarAutoridades(Long idGdocConsejo) {
		return dao.listarAutoridadesJPQL(idGdocConsejo);
	}

	@Override
	public GdocArchivosAdjuntos registrarGdocArchivoAdjunto(GdocArchivosAdjuntos gdocArchivoAdjunto) {
		return dao.registrarGdocArchivoAdjuntoSET(gdocArchivoAdjunto);
	}

	@Override
	public GdocResoluciones registrarGdocResoluciones(GdocResoluciones gdocResoluciones) {
		return dao.registrarGdocResolucionesSET(gdocResoluciones);
	}

	@Override
	public GdocConsejos buscarConsejoPorIdUsuarioIdMnuTipoFuncion(Long idUsuario, Long idMnuTipoFuncion) {
		return dao.buscarConsejoPorIdUsuarioIdMnuTipoFuncionGET(idUsuario, idMnuTipoFuncion);
	}

	@Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntosPorIdGdocResolucion(Long idGdocResolucion) {
	     return dao.buscarGdocArchivosAdjuntosPorIdGdocResolucionGET(idGdocResolucion);
	}

	@Override
	public List<GdocRepresentantes> listarGdocRepresentante() {
		return dao.listarGdocRepresentanteJPQL();
	}

	@Override
	public List<GdocTiposConvenios> listarGdocTiposConvenios() {
		return dao.listarGdocTiposConveniosJPQL();
	}

	@Override
	public List<GdocConvenios> listarGdocConveniosPorIdGdocConsejo(Long idGdocConsejo) {
		return dao.listarGdocConveniosPorIdGdocConsejoJQPL(idGdocConsejo);
	}

	@Override
	public GdocConvenios guardarGdocConvenios(GdocConvenios gdocConvenios) {
		return dao.guardarGdocConveniosSET(gdocConvenios);
	}

	@Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocConvenio(Long idGdocConvenio) {
		return dao.buscarGdocArchivosAdjuntsPorIdGdocConvenioGET(idGdocConvenio);
	}

	@Override
	public GdocConvenios buscarGdocConvenios(Long idGdocConvenio) {
		return dao.buscarGdocConveniosGET(idGdocConvenio);
	}

	@Override
	public GdocConvenios modificarGdocConvenios(GdocConvenios gdocConvenios) {
		return dao.modificarGdocConveniosSET(gdocConvenios);
	}

	@Override
	public GdocConvenios actualizarGdocConvenios(GdocConvenios gdocConvenios) {
		return dao.actualizarGdocConveniosSET(gdocConvenios);
	}

	@Override
	public GdocArchivosAdjuntos actualizarGdocArchivosAdjuntos(GdocArchivosAdjuntos gdocArchivosAdjuntos) {
		return dao.actualizarGdocArchivosAdjuntosSET(gdocArchivosAdjuntos);
	}

	@Override
	public GdocResoluciones buscarGdocResolucionesPorIdGdocResolucion(Long idGdocResolucion) {
		return dao.buscarGdocResolucionesPorIdGdocResolucionGET(idGdocResolucion);
	}

	@Override
	public GdocResoluciones actualizarGdocResoluciones(GdocResoluciones gdocResoluciones) {
		return dao.actualizarGdocResolucionesSET(gdocResoluciones);
	}

	@Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocArchivoAdjunto(Long idGeodcArchivoAdjunto) {
		return dao.buscarGdocArchivosAdjuntsPorIdGdocArchivoAdjuntoGET(idGeodcArchivoAdjunto);
	}

	@Override
	public GdocGestionConsejos buscarGdocGestionConsejosPorIdGdocConsejoGestion(Long idGdocConsejo, Integer gestion) {
		return dao.buscarGdocGestionConsejosPorIdGdocConsejoGestionGET(idGdocConsejo, gestion);
	}

	@Override
	public List<GdocResolucionesDigitales> listarGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestion(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion) {
		return dao.listarGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestionJPQL(idGdocConsejo, idGdocGestionConsejo, gestion);
	}

	@Override
	public GdocResolucionesDigitales registrarGdocResolucionesDigitales(GdocResolucionesDigitales gdocResolucionesDigitales) {
		return dao.registrarGdocResolucionesDigitalesSET(gdocResolucionesDigitales);
	}

	@Override
	public GdocResolucionesDigitales buscarGdocResolucionesDigitalesPorIdGdocResolucionesDigitales(Long idGdocResolucionDigital) {
		return dao.buscarGdocResolucionesDigitalesPorIdGdocResolucionesDigitalesGET(idGdocResolucionDigital);
	}

	@Override
	public GdocResolucionesDigitales buscarMaxGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestion(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion) {
		return dao.buscarMaxGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestionGET(idGdocConsejo, idGdocGestionConsejo, gestion);
	}

	@Override
	public GdocConvenios buscarGdocConveniosPorIdGdocTipoConvenioGestion(Long idGdocTipoConvenio, Integer gestion) {
		return dao.buscarGdocConveniosPorIdGdocTipoConvenioGestionGET(idGdocTipoConvenio, gestion);
	}

	@Override
	public List<GdocConvenios> listarGdocConveniosPorIdGdocTipoCovenioGestion(Long idGdocTipoConvenio, Integer gestion) {
		return dao.listarGdocConveniosPorIdGdocTipoCovenioGestionJPQL(idGdocTipoConvenio, gestion);
	}

	@Override
	public List<PrsGradosAcademicos> listarPrsGradosAcademicos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GdocTiposTitulos> listarGdocTiposTitulos() {
		// TODO Auto-generated method stub
		return dao.listarGdocTiposTitulosJPQL();
	}

	@Override
	public List<GdocTiposTitulosGrados> listarGdocTiposTitulosGrados() {
		// TODO Auto-generated method stub
		return dao.listarGdocTiposTitulosGradosJPQL();
	}

	

	@Override
	public GdocTitulos guardarGdocTitulosSET(GdocTitulos gdocTitulos) {
		// TODO Auto-generated method stub
		return dao.guardarGdocTitulosSET(gdocTitulos);
	}

	@Override
	public GdocTitulos buscarGdocTitulosGET(Long idGdocTitulo) {
		// TODO Auto-generated method stub
		return dao.buscarGdocTitulosGET(idGdocTitulo);
	}

	@Override
	public List<GdocTitulos> listarGdocTitulosPorIdGdocConsejo(Long idGdocConsejo) {
		// TODO Auto-generated method stub
		return dao.listarGdocTitulosPorIdGdocConsejoJQPL(idGdocConsejo);
	}

	

	@Override
	public List<GdocTitulos> listarGdocTitulos(Long idGdocConsejo) {
		// TODO Auto-generated method stub
		return dao.listarGdocTitulosJPQL(idGdocConsejo);
	}

	@Override
	public List<GdocTitulados> listarGdocTituladosPorIdGdocConsejo(Long idGdocConsejo) {
		// TODO Auto-generated method stub
		return dao.listarGdocTituladosPorIdGdocConsejoJQPL(idGdocConsejo);
	}

	@Override
	public GdocTitulados guardarGdocTituladosSET(GdocTitulados gdocTitulados) {
		// TODO Auto-generated method stub
		return dao.guardarGdocTituladosSET(gdocTitulados);
	}

	@Override
	public GdocTitulados buscarGdocTituladosGET(Long idGdocTitulados) {
		// TODO Auto-generated method stub
		return dao.buscarGdocTituladosGET(idGdocTitulados);
	}

	@Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTitulo(Long idGdocTitulo) {
		// TODO Auto-generated method stub
		return dao.buscarGdocArchivosAdjuntsPorIdGdocTituloGET(idGdocTitulo);
	}

	@Override
	public GdocTitulos guardarGdocTitulos(GdocTitulos gdocTitulos) {
		// TODO Auto-generated method stub
		return dao.guardarGdocTitulosSET(gdocTitulos);
	}

	@Override
	public GdocTitulados guardarGdocTitulados(GdocTitulados gdocTitulados) {
		// TODO Auto-generated method stub
		return dao.guardarGdocTituladosSET(gdocTitulados);
	}

	@Override
	public GdocArchivosAdjuntos buscarGdocArchivosAdjuntsPorIdGdocTitulado(Long idGdocTitulado) {
		// TODO Auto-generated method stub
		return dao.buscarGdocArchivosAdjuntsPorIdGdocTituladoGET(idGdocTitulado);
	}

	@Override
	public GdocTitulos modificarGdocTitulos(GdocTitulos gdocTitulos) {
		// TODO Auto-generated method stub
		return dao.modificarGdocTitulosSET(gdocTitulos);
	}

	@Override
	public GdocTitulos actualizarGdocTitulos(GdocTitulos gdocTitulos) {
		// TODO Auto-generated method stub
		return dao.actualizarGdocTitulosSET(gdocTitulos);
	}


	@Override
	public GdocTitulados modificarGdocTitulados(GdocTitulados gdocTitulados) {
		// TODO Auto-generated method stub
		return dao.modificarGdocTituladosSET(gdocTitulados);
	}

	@Override
	public GdocTitulados actualizarGdocTitulados(GdocTitulados gdocTitulados) {
		// TODO Auto-generated method stub
		return dao.actualizarGdocTituladosSET(gdocTitulados);
	}
	
	
	
	@Override
	public GdocConsejos guardarGdocConsejos(GdocConsejos gdocConsejos) {
		// TODO Auto-generated method stub
		return dao.guardarGdocConsejosSET(gdocConsejos);
	}

	@Override
	public List<GdocConsejos> listarGdocConsejos() {
		// TODO Auto-generated method stub
		return dao.listarGdocConsejosJPQL();
	}

	@Override
	public GdocUsrTiposFunciones guardarGdocUsrTiposFunciones(GdocUsrTiposFunciones gdocUsrTiposFunciones) {
		// TODO Auto-generated method stub
		return dao.guardarGdocUsrTiposFuncionesSET(gdocUsrTiposFunciones);
	}

	@Override
	public List<GdocUsrTiposFunciones> listarGdocUsrTiposFunciones() {
		// TODO Auto-generated method stub
		return dao.listarGdocGdocUsrTiposFuncionesJPQL();
	}

	@Override
	public List<GdocUsrTiposFunciones> listarGdocUsrTiposFuncionesPorIdGdocConsejo() {
		// TODO Auto-generated method stub
		return dao.listarGdocGdocUsrTiposFuncionesJPQL();
	}

	

	@Override
	public GdocGestionConsejos guardarGdocGestionConsejosSET(GdocGestionConsejos gdocGestionConsejos) {
		// TODO Auto-generated method stub
		return dao.guardarGdocGestionConsejosSET(gdocGestionConsejos);
	}

	@Override
	public List<GdocGestionConsejos> listarGdocGestionConsejosPorIdGdocConsejo() {
		// TODO Auto-generated method stub
		return dao.listarGdocGestionConsejosPorIdGdocConsejoJQPL();
	} 
}
