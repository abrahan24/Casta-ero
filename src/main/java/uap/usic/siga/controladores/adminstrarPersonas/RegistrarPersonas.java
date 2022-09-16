package uap.usic.siga.controladores.adminstrarPersonas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opencsv.CSVReader;

import uap.usic.siga.dto.PersonasResponse;
import uap.usic.siga.entidades.AuthorityType;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.MnuTiposFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.PnlItems;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PnlTiposAdministrativos;
import uap.usic.siga.entidades.PoaisSupervisores;
import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.entidades.SisNivelesAccesos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.CargosServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;
import uap.usic.siga.servicios.UserService;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 *
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/personas")
public class RegistrarPersonas {

	@Autowired
	private UsuariosServicios uServicio;

	@Autowired
	private PersonasServicios pServicio;

	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public CargosServicios cServicios;

	@Autowired
	public InstitucionesServicios insServicios;

	@Autowired
	public UsuariosServicios uServicios;

	@Autowired
	private UserService userService;

	@Autowired
	private MenuesServicios mServicios;
	
	@Autowired
	private PoaisServicios poaServicios;
	

	@RequestMapping(value = "/csv", method = RequestMethod.POST)
	public String subirCSV(Model model, RedirectAttributes flash, @RequestParam("archivo") MultipartFile archivo)
			throws IOException, ParseException {

		System.out.println("aquiii");

		File file = convertMultiPartToFile(archivo);

		/*
		 * try (Reader reader = new FileReader(file); CSVReader csvReader = new
		 * CSVReader( new InputStreamReader(new FileInputStream(file.getAbsolutePath()),
		 * "UTF-8"));) {
		 */

		try (Reader reader = new FileReader(file); CSVReader csvReader = new CSVReader(reader, ';')) {
			// Reading All Records at once into a List<String[]>
			List<String[]> records = csvReader.readAll();
			
			Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_USER));
			
			for (int i = 1; i < records.size(); i++) {
				String[] record = records.get(i);

				System.out.println(
						i + " " + record[0] + " " + record[1] + " " + record[2] + " " + record[3] + " " + record[4]);
				Date date = new Date();

				SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
				int year = Integer.parseInt(getYearFormat.format(date));

				Personas personas = pServicio.buscarPersonasPorCedulaIdentidad(record[3]);

				System.out.println("1");
				if (personas != null) {

					personas.setNombres(record[0]);
					if (!record[0].equals("")) {
						personas.setNombres(record[0]);
					} else {
						personas.setNombres(" ");
					}
					
					personas.setPaterno(record[1]);
					if (!record[1].equals("")) {
						personas.setPaterno(record[1]);
					} else {
						personas.setPaterno(" ");
					}
					
					personas.setMaterno(record[2]);
					if (!record[2].equals("")) {
						personas.setMaterno(record[2]);
					} else {
						personas.setMaterno(" ");
					}
					
					// =========================
					personas.setPrsCiExpedidos(pServicio.getCiExpedido(1l));
					// =========================
					personas.setPrsGradosAcademicos(pServicio.getGradoAcademico(1l));

					/*
					if (!record[6].equals("")) {
						personas.setEmail(record[6]);
					} else {
						personas.setEmail(personas.getCi() + "@gmail.com");
					}*/

					personas.setEmail("uap@gmail.com");
					
					System.out.println(personas.getEmail() + " 2");
					
					if (!record[7].equals("")) {
						personas.setFecNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(record[7]));
					}
					
					if (!record[9].equals("")) {
						personas.setTelefono(record[9]);
					}

					personas.setTipoSanguineo("ORH+");
					personas.setPrsPaises(pServicio.getPais(1l));
					personas.setPrsTiposSexos(pServicio.getSexo(1l));

					pServicio.registrarPersonas(personas);
					System.out.println("11");
				} else {
					personas = new Personas();

					personas.setNombres(record[0]);
					if (!record[0].equals("")) {
						personas.setNombres(record[0]);
					} else {
						personas.setNombres(" ");
					}
					
					personas.setPaterno(record[1]);
					if (!record[1].equals("")) {
						personas.setPaterno(record[1]);
					} else {
						personas.setPaterno(" ");
					}
					
					personas.setMaterno(record[2]);
					if (!record[2].equals("")) {
						personas.setMaterno(record[2]);
					} else {
						personas.setMaterno(" ");
					}
					personas.setCi(record[3]);
					// =========================
					personas.setPrsCiExpedidos(pServicio.getCiExpedido(1l));
					// =========================
					personas.setPrsGradosAcademicos(pServicio.getGradoAcademico(1l));

					/*
					if (!record[6].equals("")) {
						personas.setEmail(record[6]);
					} else {
						personas.setEmail(personas.getCi() + "@gmail.com");
					}*/

					personas.setEmail("uap@gmail.com");
					
					System.out.println(personas.getEmail() + " 2");
					
					if (!record[7].equals("")) {
						personas.setFecNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(record[7]));
					}
					
					if (!record[9].equals("")) {
						personas.setTelefono(record[9]);
					}

					personas.setTipoSanguineo("ORH+");
					personas.setPrsPaises(pServicio.getPais(1l));
					personas.setPrsTiposSexos(pServicio.getSexo(1l));

					pServicio.registrarPersonas(personas);
					System.out.println("111");
				}

				PnlCargos cargos = cServicios.buscarPnlCargosPorCargo(record[20]);

				if (cargos == null) {
					cargos = new PnlCargos();

					cargos.setCargo(record[20]);
					cargos.setIdEstado("A");
					cargos.setRegistro(new Date());

					cServicios.registrarPnlCargos(cargos);
				}
				System.out.println("12");
				PnlTiposAdministrativos tipoAdministrativo = aServicios.getTipoAdministrativo(record[12]);

				if (tipoAdministrativo == null) {
					tipoAdministrativo = new PnlTiposAdministrativos();

					tipoAdministrativo.setTipoAdministrativo(record[12]);
					tipoAdministrativo.setIdEstado("A");
					tipoAdministrativo.setRegistro(new Date());
					System.out.println("13 q");

					aServicios.guardarPnlTiposAdministrativos(tipoAdministrativo);
				}
				System.out.println("13");
				PnlItems items = aServicios.getItem(record[13]);
				if (items == null) {
					items = new PnlItems();

					items.setItem(record[13]);
					items.setIdEstado("A");
					items.setRegistro(new Date());

					aServicios.guardarPnlItems(items);
				}
				System.out.println("14");
				InsUnidadesFuncionales unidadesFuncionales = insServicios.buscarInsUnidadesFuncionalesPorUnidad(record[16]);
				System.out.println("14 a");
				
				if (unidadesFuncionales == null) {
					unidadesFuncionales = new InsUnidadesFuncionales();

					unidadesFuncionales.setUnidadFuncional(record[16]);
					unidadesFuncionales.setSigla(record[16].charAt(0) + "_" + record[16].charAt(1) + "_" + i);

					InsDireccionesFuncionales direccionesFuncionales = insServicios.getDireccionFuncional(record[15]);
					System.out.println("144");

					InsSedes sedes = insServicios.buscarInsSedes(16L);

					if (direccionesFuncionales == null) {
						direccionesFuncionales = new InsDireccionesFuncionales();

						direccionesFuncionales.setDireccionFuncional(record[15]);
						direccionesFuncionales.setSigla(record[15].charAt(0) + "_" + record[15].charAt(1) + "_" + i);
						direccionesFuncionales.setInsSedes(sedes);

						insServicios.registrarInsDireccionesFuncionales(direccionesFuncionales);
					}
					unidadesFuncionales.setInsDireccionesFuncionales(direccionesFuncionales);

					insServicios.guardarInsUnidadesFuncionales(unidadesFuncionales);
					System.out.println("155");
				}

				PnlPersonalAdministrativos administrativos = aServicios
						.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year,
								1);

				if (administrativos != null) {
					System.out.println("2");
					
					if (!record[17].equals("NULL")) {
						administrativos.setFecInicio(new SimpleDateFormat("dd/MM/yyyy").parse(record[17]));
					} else {
						administrativos.setFecInicio(new SimpleDateFormat("dd/MM/yyyy").parse("1/1/2022"));
					}
					
					if (!record[18].equals("NULL")) {
						administrativos.setFecFinal(new SimpleDateFormat("dd/MM/yyyy").parse(record[18]));
					} else {
						administrativos.setFecFinal(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022"));
					}				
										
					administrativos.setGestion(2022);
					administrativos.setPeriodo(1);

					administrativos.setPersonas(personas);

					administrativos.setPnlCargos(cargos);

					administrativos.setPnlTiposAdministrativos(tipoAdministrativo);

					administrativos.setPnlItems(items);

					administrativos.setInsUnidadesFuncionales(unidadesFuncionales);

					aServicios.guardarPersonalAdministrativo(administrativos);
				} else {
					System.out.println("22");
					administrativos = new PnlPersonalAdministrativos();

					if (!record[17].equals("NULL")) {
						administrativos.setFecInicio(new SimpleDateFormat("dd/MM/yyyy").parse(record[17]));
					} else {
						administrativos.setFecInicio(new SimpleDateFormat("dd/MM/yyyy").parse("1/1/2022"));
					}
					
					if (!record[18].equals("NULL")) {
						administrativos.setFecFinal(new SimpleDateFormat("dd/MM/yyyy").parse(record[18]));
					} else {
						administrativos.setFecFinal(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022"));
					}			
					
					administrativos.setGestion(2022);
					administrativos.setPeriodo(1);
					
					administrativos.setPersonas(personas);

					administrativos.setPnlCargos(cargos);

					administrativos.setPnlTiposAdministrativos(tipoAdministrativo);

					administrativos.setPnlItems(items);

					administrativos.setInsUnidadesFuncionales(unidadesFuncionales);

					aServicios.guardarPersonalAdministrativo(administrativos);

					System.out.println("222");
				}

				MnuTiposFunciones tiposFunciones = mServicios.getTipoFuncion(8l);

				SisAdministrador sisAdministrador = mServicios.getSisAdmin(7l);

				SisNivelesAccesos nivelesAccesos = mServicios.getNivelAcceso(3l);
				
				MnuFunciones funciones = mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(personas.getIdPersona(), tiposFunciones.getIdMnuTipoFuncion(),sisAdministrador.getIdSisAdministrador());

				if (funciones == null) {

					funciones = new MnuFunciones();

					funciones.setMnuTiposFunciones(tiposFunciones);
					funciones.setPersonas(personas);
					funciones.setSisAdministrador(sisAdministrador);
					funciones.setSisNivelesAccesos(nivelesAccesos);

					funciones.setValor(Double.parseDouble("2000"));
					funciones.setFecExpiracion(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022"));

					System.out.println("5");

					mServicios.registrarMnuFunciones(funciones);

					System.out.println("55");

				}

				System.out.println("6");

				Usuarios usuarios = pServicio.buscarUsuariosPorIdPersonaIdEstado(personas.getIdPersona(), "A");

				System.out.println("us 1");
				if (usuarios == null) {
					usuarios = new Usuarios();

					usuarios.setUsuario(personas.getCi());
					usuarios.setPersonas(personas);
					usuarios.setPassword(personas.getCi());

					usuarios.setDateCreated(new Date());
					
					//usuarios.setRoles(roles);
					
					userService.addUser(usuarios);

					System.out.println("us 11 ");
				}
				
				PoaisSupervisores supervisores = poaServicios.buscarSupervisorPersonaCargo(personas.getIdPersona(), cargos.getIdPnlCargos());
				
				if (supervisores == null) {
					supervisores = new PoaisSupervisores();
					
					supervisores.setPersonas(personas);
					supervisores.setPnlCargos(cargos);
					supervisores.setGestion(2022);
					supervisores.setDescripcion(cargos.getCargo());
					
					poaServicios.registrarPoaisSupervisores(supervisores);		
					System.out.println("us echoo ");
				}

			}
		}
		flash.addAttribute("success", "Datos Cargados con Exito!");

		return "redirect:/personas/formPersonas";
	}

	@GetMapping(value = "/formPersonas")
	public String listarPersonas(@ModelAttribute("personas") Personas personas, Model model) {
		getDesplegarListasComunes(model);
		model.addAttribute("busqueda", "find");

		return "RegPersonas";
	}

	@PostMapping(value = "/formPersonasNuevo")
	public String formRegistroPersona(@ModelAttribute("personas") Personas personas, Model model) {
		model.addAttribute("operation", "reg");
		getDesplegarListasComunes(model);
		return "RegPersonas";
	}

	@PostMapping(value = "/listarPersonas")
	public String registrarPersonas(@ModelAttribute("personas") @Valid Personas personas, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model);
			return "RegPersonas";
		}
		Personas bPersona = pServicio.buscarPersonasPorCedulaIdentidad(personas.getCi());
		if (bPersona == null) {
			pServicio.registrarPersonas(personas);
			getDesplegarListasComunes(model);
			model.addAttribute("busqueda", "find");
		} else {
			model.addAttribute("mensage", "err");
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model);
			return "RegPersonas";
		}
		return "RegPersonas";
	}

	@PostMapping(value = "/guardarPais", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PrsPaises> getListarPrsPaises(@ModelAttribute @Valid PrsPaises prsPaises,
			BindingResult result) {
		pServicio.registrarPrsPaises(prsPaises);
		return pServicio.listarPaises();
	}

	@PostMapping(value = "/guardarTipoSexo", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PrsTiposSexos> getListarPrsTiposSexo(@ModelAttribute @Valid PrsTiposSexos prsTiposSexos,
			BindingResult result) {
		pServicio.registrarPrsTiposSexos(prsTiposSexos);
		return pServicio.listarTiposSexos();
	}

	@PostMapping(value = "/inicioModificarPersonas")
	public String inicioModificarPersonas(@ModelAttribute("personas") Personas personas, BindingResult result,
			Model model, @RequestParam("idPersona") Long idPersona) {
		model.addAttribute("personas", pServicio.buscarPersonasPorIdPersona(idPersona));
		model.addAttribute("operation", "mod");
		getDesplegarListasComunes(model);

		return "RegPersonas";
	}

	@PostMapping("/confirmarModificacionPersonas")
	public String confirmarModificacionPersonas(@Valid @ModelAttribute("personas") Personas personas,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			getDesplegarListasComunes(model);
			model.addAttribute("operation", "mod");
			model.addAttribute("persona", personas);
			return "RegPersonas";
		}
		pServicio.modificarPersonas(personas);
		getDesplegarListasComunes(model);
		model.addAttribute("busqueda", "find");
		return "RegPersonas";

	}

	@PostMapping(value = "/inicioEliminarPersonas")
	public String inicioEliminarPersonas(@ModelAttribute("personas") @Valid Personas personas, BindingResult result,
			Model model, @RequestParam("idPersona") Long idPersona) {
		model.addAttribute("personas", pServicio.buscarPersonasPorIdPersona(idPersona));
		model.addAttribute("operation", "mod");
		model.addAttribute("operation", "elimP");
		getDesplegarListasComunes(model);

		Integer banderita = 0;

		PnlPersonalAdministrativos bPersonalAdministrativo = pServicio
				.buscarPnlPersonalAdministrativosPorIdPersonaIdEstado(idPersona, "A");
		CjaIngresos bCjaIngresos = pServicio.buscarCjaIngresosPoIdPersonaIdEstado(idPersona, "A");
		CjaGastosEjecutados cjaGastoEjecutados = pServicio.buscarCjaGastosEjecutadosPorIdPersonaIdEstado(idPersona,
				"A");
		Usuarios bUsuarios = pServicio.buscarUsuariosPorIdPersonaIdEstado(idPersona, "A");

		if (bPersonalAdministrativo != null) {
			banderita = 1;
			model.addAttribute("banderita", banderita);
			model.addAttribute("mensage", "err");
		}

		if (bCjaIngresos != null) {
			banderita = 1;
			model.addAttribute("banderita", banderita);
			model.addAttribute("mensage", "err");
		}

		if (cjaGastoEjecutados != null) {
			banderita = 1;
			model.addAttribute("banderita", banderita);
			model.addAttribute("mensage", "err");
		}

		if (bUsuarios != null) {
			banderita = 1;
			model.addAttribute("banderita", banderita);
			model.addAttribute("mensage", "err");
		}

		model.addAttribute("banderita", banderita);

		return "RegPersonas";
	}

	@PostMapping(value = "/confirmarEliminacionPersona")
	public String confirmarEliminarPersonas(@ModelAttribute("personas") @Valid Personas personas, BindingResult result,
			Model model, @RequestParam("idPersona") Long idPersona) {

		personas.setIdEstado("X");
		pServicio.eliminarPersonas(personas);
		model.addAttribute("personas", pServicio.buscarPersonasPorIdPersona(idPersona));
		getDesplegarListasComunes(model);
		model.addAttribute("busqueda", "find");

		return "RegPersonas";
	}

	public void getDesplegarListasComunes(Model model) {
		model.addAttribute("pers", "listarPersonas");
		model.addAttribute("lPaises", pServicio.listarPaises());
		model.addAttribute("lPersonas", pServicio.listarPersonasComprobantes());
		model.addAttribute("lExpedidos", pServicio.listarCedulaIdentidadexpedidos());
		model.addAttribute("lPrsTiposSexos", pServicio.listarTiposSexos());
		model.addAttribute("urlN", "formPersonasNuevo");
		model.addAttribute("urlModPer", "inicioModificarPersonas");
		model.addAttribute("urlModPerConf", "confirmarModificacionPersonas");
		model.addAttribute("urlIniE", "inicioEliminarPersonas");
		model.addAttribute("urlElimPerConf", "confirmarEliminacionPersona");
		model.addAttribute("lGradosAcademicos", pServicio.listarGradosAcademicos());
	}

	@PostMapping(value = "/guardarCiExpedido", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PrsCiExpedidos> getListarPrsCiExpedido(
			@ModelAttribute @Valid PrsCiExpedidos prsCiExpedidos, BindingResult result) {
		pServicio.registrarPrsCedulaIdentidadExpedidos(prsCiExpedidos);
		return pServicio.listarCedulaIdentidadexpedidos();
	}

	@GetMapping(value = "/listarPersonasDT", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PersonasResponse> listarPersonasGlobal() {
		return pServicio.listarPersonasResponsePersonalizado();
	}

	@PostMapping(value = "/guardarGradosAcademicos", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PrsGradosAcademicos> getListarPrsGradosAcademicos(
			@ModelAttribute @Valid PrsGradosAcademicos prsGradosAcademicos, BindingResult result) {
		pServicio.registrarPrsGradosAcademicos(prsGradosAcademicos);
		return pServicio.listarGradosAcademicos();
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
