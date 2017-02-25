package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.Marca;
import es.caib.gestoli.logic.model.TipusEnvas;
import es.caib.gestoli.logic.util.EtiquetatgeAux;

/**
 * <p>
 * Controlador para las acciones de dar de alta o editar un registro de tipos de
 * información.
 * </p>
 * <p>
 * Los parámetros de la petición HTTP relevantes para este controlador son:
 * <ul>
 * <li><code>id</code> - Identificador del registro. Este parámetro es el que
 * determina si se ha de mostrar la página de creación o la página de edición.</li>
 * </ul>
 * </p>
 * <p>
 * No hay información para la vista:
 * <p>
 * Este controlador distingue entre las peticiones del tipo GET y las de tipo
 * POST. Si la petición es de tipo POST se ejecuta la acción de creación o de
 * edición. Si la petición es de tipo GET solo se muestra la página.
 * 
 * 
 */

public class MarcaFormController extends AbstractWizardFormController {
	private static Logger logger = Logger.getLogger(MarcaFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String esDoGestorRequestKey;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private String campanyaSessionKey;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo se ejecuta
	 * esta función en el caso de que se haya ejecutado la validación sin
	 * producir ningún error.
	 * 
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */

	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException arg3)
			throws Exception {

		MarcaCommand marca = (MarcaCommand) command;

		// Para la marca

		Boolean activa = Boolean.valueOf(true);
		String activaParam = request.getParameter("actiu");
		if (activaParam == null) {
			activa = Boolean.valueOf(false);
		}

		if (isCreate(request)) {
			activa = Boolean.valueOf(true);
		}

		String nom = marca.getNom();

		Boolean denominacioOrigen = Boolean.valueOf(true);
		String denominacioOrigenParam = request
				.getParameter("denominacioOrigen");
		if (denominacioOrigenParam == null) {
			denominacioOrigen = Boolean.valueOf(false);
		}
		
		Boolean olivaTaula = Boolean.valueOf(true);
		String olivaTaulaParam = request
				.getParameter("olivaTaula");
		if (olivaTaulaParam == null) {
			olivaTaula = Boolean.valueOf(false);
		}

		List etiquetageList = marca.getEtiquetatgesList();

		String observaciones = marca.getObservacions();
		Long marcaId = marca.getId();

		Collection establecimientos = null;
		if (marcaId != null) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			establecimientos = oliInfraestructuraEjb
					.getEstablecimientosByMarca(marcaId);

		}

		Map myModel = new HashMap();

		// Pasamos la lista de EtiquetatgeCommand a EtiquetatgeAux para poder
		// leerlo en el ejb
		List etiquetageListAux = new ArrayList();
		for (int i = 0; i < etiquetageList.size(); i++) {
			EtiquetatgeCommand etiquetatgeCommand = (EtiquetatgeCommand) etiquetageList
					.get(i);
			EtiquetatgeAux etiquetatgeAux = new EtiquetatgeAux();
			etiquetatgeAux.setActiu(etiquetatgeCommand.getActiu());
			etiquetatgeAux.setEtiPosition(etiquetatgeCommand.getEtiPosition());
			etiquetatgeAux.setId(etiquetatgeCommand.getId());
			etiquetatgeAux.setArxiucImatgeFrontal(etiquetatgeCommand
					.getArxiucImatgeFrontal());
			etiquetatgeAux.setImatgeFrontal(etiquetatgeCommand
					.getImatgeFrontal());
			etiquetatgeAux.setImatgeImatgeFrontal(etiquetatgeCommand
					.getImatgeImatgeFrontal());
			etiquetatgeAux.setArxiucImatgePosterior(etiquetatgeCommand
					.getArxiucImatgePosterior());
			etiquetatgeAux.setImatgePosterior(etiquetatgeCommand
					.getImatgePosterior());
			etiquetatgeAux.setImatgeImatgePosterior(etiquetatgeCommand
					.getImatgeImatgePosterior());
			etiquetatgeAux.setMarca(etiquetatgeCommand.getMarca());
			etiquetatgeAux
					.setObservacions(etiquetatgeCommand.getObservacions());
			etiquetatgeAux.setTipusEnvas(etiquetatgeCommand.getTipusEnvas());
			etiquetatgeAux
					.setTipusEnvasId(etiquetatgeCommand.getTipusEnvasId());
			etiquetageListAux.add(etiquetatgeAux);
		}
		if (isCreate(request)) {

			try {
				logger.info("Creant el registre: " + marca);
				marcaId = oliInfraestructuraEjb.marcaCrear(nom, activa,
						denominacioOrigen, olivaTaula, etiquetageListAux, observaciones);
				marca.setId(marcaId);
				ControllerUtils.saveMessageInfo(request,
						missatge("marca.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant el registre", ex);
				ControllerUtils.saveMessageError(request,
						missatge("marca.missatge.crear.no"));
			}

		} else {

			try {
				logger.info("Modificant el registre: " + marca);
				oliInfraestructuraEjb
						.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.marcaModificar(marcaId, nom, activa,
						denominacioOrigen, olivaTaula, etiquetageListAux, establecimientos,
						observaciones);
				ControllerUtils.saveMessageInfo(request,
						missatge("marca.missatge.modificar.ok"));
			} catch (Exception ex) {
				logger.error("Error modificant el registre ", ex);
				ControllerUtils.saveMessageError(request,
						missatge("marca.missatge.modificar.no"));
			}

		}
		String modelAndView = "redirect:MarcaForm.html?id=" + marca.getId();

		return new ModelAndView(modelAndView, myModel);

	}

	/**
	 * Retorna todos los datos del modelo necesarios para mostrar el formulario
	 * de inserción (LOVs y cosas de esas) si no hay.
	 * 
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */

	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		Map myModel = new HashMap();

		MarcaCommand marca = (MarcaCommand) command;

		try {
			// Vemos primero si se trata del gestor
			if (request.getAttribute(esDoGestorRequestKey) != null) {

				List etiquetatgesList = new ArrayList();

				switch (page) {
				case 0:
					String idMarca = request.getParameter("id");
					String guardarEtiq = request.getParameter("guardarEtiq");
					String eliminarEtiq = request.getParameter("eliminarEtiq");
					// La primera vez que entramos en el formulario de marca
					// recuperamos el etiquetatgeList de BBDD si existiera
					if (idMarca != null && !idMarca.equals("")
							&& guardarEtiq == null && eliminarEtiq == null) {
						// Entramos cuando vamos a modificar una marca que ya
						// existe
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						etiquetatgesList = oliInfraestructuraEjb.etiquetatgeByMarca(Long.valueOf(idMarca));

						// Pasamos a un listado de tipo EtiquetatgeCommand
						List aux = new ArrayList();
						if (etiquetatgesList.size() > 0) {
							for (int i = 0; i < etiquetatgesList.size(); i++) {
								Etiquetatge etiquetatgeAux = (Etiquetatge) etiquetatgesList.get(i);
								EtiquetatgeCommand etiquetatgeCommandAux = new EtiquetatgeCommand();
								etiquetatgeCommandAux.fromEtiquetatge(etiquetatgeAux);
								aux.add(etiquetatgeCommandAux);
							}
							etiquetatgesList = aux;
						}

						myModel.put("idMarca", Long.valueOf(idMarca));
					}

					// Por si no estamos entrando por primera vez en el
					// formulario de marca recogemos el etiquetatgeList del
					// formulario
					if (marca.getEtiquetatgesList() != null) {
						etiquetatgesList = marca.getEtiquetatgesList();
					}

					// Si venimos de crear etiquetaje lo guardamos

					if (guardarEtiq != null && !guardarEtiq.equals("")) {

						EtiquetatgeCommand etiquetatge = new EtiquetatgeCommand();

						Boolean actiuEtiquetatge = Boolean.valueOf(true);
						String activoEtiquetajeParam = request
								.getParameter("actiuEtiquetatge");
						if (activoEtiquetajeParam == null) {
							actiuEtiquetatge = Boolean.valueOf(false);
						}
						etiquetatge.setActiu(actiuEtiquetatge);

						etiquetatge.setObservacions(marca
								.getObservacionsEtiquetatge());
						if (marca.getTipusEnvasIdEtiquetatge() != null) {
							oliInfraestructuraEjb
									.setHibernateTemplate(getHibernateTemplate());
							TipusEnvas tipusEnvas = oliInfraestructuraEjb
									.tipusEnvasAmbId(marca
											.getTipusEnvasIdEtiquetatge());
							etiquetatge.setTipusEnvas(tipusEnvas);
						}
						MultipartFile[] files = getFiles(request);
						if (files != null) {
							if (files.length >= 1) {
								MultipartFile file = files[0];
								if (file.getSize() > 0) {
									Arxiu arxiucImatgeFrontal = new Arxiu();
									arxiucImatgeFrontal.setBinari(file.getBytes());
									arxiucImatgeFrontal.setMime(file.getContentType());
									arxiucImatgeFrontal.setNom(file.getOriginalFilename());
									etiquetatge.setArxiucImatgeFrontal(arxiucImatgeFrontal);
								}

							}
							if (files.length >= 2) {
								MultipartFile file = files[1];
								if (file.getSize() > 0) {
									Arxiu arxiucImatgePosterior = new Arxiu();
									arxiucImatgePosterior.setBinari(file.getBytes());
									arxiucImatgePosterior.setMime(file.getContentType());
									arxiucImatgePosterior.setNom(file.getOriginalFilename());
									etiquetatge.setArxiucImatgePosterior(arxiucImatgePosterior);
								}
							}
						}

						if (marca.getArxiucImatgeFrontal() != null) {
							etiquetatge.setArxiucImatgeFrontal(marca.getArxiucImatgeFrontal());
						}
						if (marca.getArxiucImatgePosterior() != null) {
							etiquetatge.setArxiucImatgePosterior(marca.getArxiucImatgePosterior());
						}

						if (marca.getImatgeFrontal() != null) {
							etiquetatge.setImatgeFrontal(marca.getImatgeFrontal());
						}
						if (marca.getImatgePosterior() != null) {
							etiquetatge.setImatgePosterior(marca.getImatgePosterior());
						}

						// Distinguimos si es una etiqueta que ya esta en el
						// array o no (Si no tiene EtiPosition sera
						// completamente nueva y la añadimos directamente)
						if (marca.getEtiPosition() != null) {
							etiquetatge.setEtiPosition(marca.getEtiPosition());
							for (int i = 0; i < etiquetatgesList.size(); i++) {
								EtiquetatgeCommand etiquetatgeAux = (EtiquetatgeCommand) etiquetatgesList
										.get(i);
								if (etiquetatgeAux.getId() != null)
									etiquetatge.setId(etiquetatgeAux.getId());
								if (etiquetatgeAux.getEtiPosition() != null
										&& etiquetatgeAux.getEtiPosition().intValue() == marca.getEtiPosition().intValue()) {
									etiquetatgesList.remove(i);
									etiquetatgesList.add(etiquetatge);
									break;
								}
							}

						} else {
							etiquetatgesList.add(etiquetatge);
						}

					}

					// Si venimos de eliminar etiquetaje
					if (eliminarEtiq != null && !eliminarEtiq.equals("")) {
						if (marca.getEtiPosition() != null) {
							for (int i = 0; i < etiquetatgesList.size(); i++) {
								EtiquetatgeCommand etiquetatgeAux = (EtiquetatgeCommand) etiquetatgesList
										.get(i);
								if (etiquetatgeAux.getEtiPosition() != null
										&& etiquetatgeAux.getEtiPosition()
												.intValue() == marca
												.getEtiPosition().intValue()) {
									if (etiquetatgeAux.getId() != null) {
										if (oliInfraestructuraEjb.findLotesByEtiquetaje(etiquetatgeAux.getId()).size() > 0) {
											ControllerUtils.saveMessageError(request,missatge("etiquetatge.missatge.borrar.no"));
											break;
										}
									}
									etiquetatgesList.remove(i);
									break;
								}
							}
						}
					}

					// Recorremos el listado pasandole la posicion a cada
					// etiquetatge para poder editarlo
					List etiquetatgesListOrdenado = new ArrayList();
					for (int i = 0; i < etiquetatgesList.size(); i++) {
						EtiquetatgeCommand etiquetatgeAux = (EtiquetatgeCommand) etiquetatgesList
								.get(i);
						etiquetatgeAux.setEtiPosition(new Integer(i));
						etiquetatgesListOrdenado.add(etiquetatgeAux);
					}

					marca.setEtiquetatgesList(etiquetatgesListOrdenado);
					// Borramos el etiquetage del formulario
					marca.setObservacionsEtiquetatge(null);
					marca.setTipusEnvasIdEtiquetatge(null);
					marca.setEtiPosition(null);
					marca.setActiuEtiquetatge(null);
					marca.setArxiucImatgeFrontal(null);
					marca.setImatgeFrontal(null);
					marca.setArxiucImatgePosterior(null);
					marca.setImatgePosterior(null);
					// Pasamos al modelo el listado de etiquetages formulario
					myModel.put("llistatEtiquetatge", etiquetatgesListOrdenado);

					break;

				case 1:
					oliInfraestructuraEjb
							.setHibernateTemplate(getHibernateTemplate());
					myModel.put("tipusEnvas", oliInfraestructuraEjb
							.tipusEnvasCercaTotsActius());
					String etiPosit = request.getParameter("etiPosit");
					if (etiPosit != null && !etiPosit.equals("")) {
						etiquetatgesList = new ArrayList();
						etiquetatgesList = marca.getEtiquetatgesList();
						EtiquetatgeCommand etiquetatge = new EtiquetatgeCommand();
						etiquetatge = (EtiquetatgeCommand) etiquetatgesList.get(Integer.valueOf(etiPosit).intValue());
						marca.setActiuEtiquetatge(etiquetatge.getActiu());
						marca.setObservacionsEtiquetatge(etiquetatge.getObservacions());
						marca.setEtiPosition(etiquetatge.getEtiPosition());
						marca.setTipusEnvasIdEtiquetatge(etiquetatge.getTipusEnvas().getId());
						if (etiquetatge.getArxiucImatgeFrontal() != null) {
							marca.setArxiucImatgeFrontal(etiquetatge.getArxiucImatgeFrontal());
						} 
						if (etiquetatge.getImatgeFrontal() != null) {
							marca.setImatgeFrontal(etiquetatge.getImatgeFrontal());
						}
						if (etiquetatge.getArxiucImatgePosterior() != null) {
							marca.setArxiucImatgePosterior(etiquetatge.getArxiucImatgePosterior());
						}
						if (etiquetatge.getImatgePosterior() != null) {
							marca.setImatgePosterior(etiquetatge.getImatgePosterior());
						}
					}

				}

			}
		} catch (Exception ex) {
			logger.error("Error obtenint llistat de etiquetatges", ex);
			ControllerUtils.saveMessageError(request,
					missatge("etiquetatge.missatge.llistat.no"));
		}

		// if ((!isFormSubmission(request)) && (!isCreate(request))) {
		// myModel.put("path", "modificar_marca");
		// } else{
		// myModel.put("path", "crear_marca");
		// }

		// if (!isCreate(request)) {
		// myModel.put("path", "modificar_marca");
		// } else{
		// myModel.put("path", "crear_marca");
		// }

		if (marca.getId() != null) {
			myModel.put("path", "modificar_marca");
		} else {
			myModel.put("path", "crear_marca");
		}

		return myModel;
	}

	/**
	 * En caso de que sea una edición retorna el objeto rellenado con los datos
	 * actuales del registro. En caso de que sea una creación retorna el objeto
	 * vacío.
	 * 
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {

		MarcaCommand marca = null;
		try {
			marca = new MarcaCommand();
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				if (request.getParameter("id") != null
						&& !request.getParameter("id").equals("")) {
					Long codi = new Long(Long.parseLong(request.getParameter("id")));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Marca m = oliInfraestructuraEjb.marcaAmbId(codi);
					marca = new MarcaCommand();
					if (m != null) {
						marca.fromMarca(m);
					}
				}
			}

		} catch (RemoteException ex) {
			throw new ServletException("Error cridant l'EJB", ex);
		}

		return marca;
	}

	/**
	 * Configuración del <i>binder</i>. Si hay campos en el <i>command</i> con
	 * tipos que no sean <i>String</i> se ha de configurar su correspondiente
	 * binder aquí.
	 * 
	 * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("S",
				"N", true));
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	/*
	 * protected void validatePage(Object command, Errors errors, int page) {
	 * setAllowDirtyBack(true); setAllowDirtyForward(true); Marca m = (Marca)
	 * command; }
	 */
	protected void validatePage(HttpServletRequest request, Object command,
			Errors errors, int page, boolean finish) {

		setAllowDirtyBack(false);
		setAllowDirtyForward(false);

		if (request.getParameter("cancelar") != null) {
			setAllowDirtyBack(true);
		}

		MarcaValidator mi_validator = (MarcaValidator) this.getValidator();
		MarcaCommand marca = (MarcaCommand) command;

		String guardarEtiq = request.getParameter("guardarEtiq");

		if (finish) {
			mi_validator.validateFirstPage(marca, errors);
			// Si la marca esta activa Comprobamos que existe al menos algun
			// etiquetageActivo
			// sino comprobamos que no esta asociada a ningun establecimiento de
			// tipo envasadora o tafona/envasadora
			String activaParam = request.getParameter("actiu");
			if (activaParam != null || isCreate(request)) {
				mi_validator.validateEtiquetageActivo(marca, errors);
			} else {
				Long campanyaId = (Long) request.getSession().getAttribute(
						campanyaSessionKey);
				mi_validator.validateEstablecimientosEnvasadoresActivo(marca,
						errors, campanyaId, tipusEstablimentTafonaEnvasadora,
						tipusEstablimentEnvasadora);
			}

		} else {

			switch (page) {
			case 0:
				mi_validator.validateFirstPage(marca, errors);
				break;
			case 1:
				if (guardarEtiq != null && !guardarEtiq.equals("")) {
					mi_validator.validateSecondPage(marca, errors);
				}
				break;

			}
		}

	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors, int page) {
		boolean finish = false;
		if (request.getParameter("_finish") != null) {
			finish = true;
		}
		validatePage(request, command, errors, page, finish);

	}

	// protected boolean suppressValidation(HttpServletRequest request) {
	// int currentPage = getCurrentPage(request);
	// int targetPage = getTargetPage(request, currentPage);
	// return (request.getParameter("cancelar")!= null);
	// }

	/**
	 * Indica si la petición es de creación o no.
	 * 
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}

	/**
	 * Inyección de la dependencia informacioEjb
	 * 
	 * @param informacioEjb
	 *            La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(
			OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	private String missatge(String clave) {
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}

	/**
	 * Injecció de la dependència esDoGestorRequestKey
	 * 
	 * @param esDoGestorRequestKey
	 *            La classe a injectar.
	 */
	public void setEsDoGestorRequestKey(String esDoGestorRequestKey) {
		this.esDoGestorRequestKey = esDoGestorRequestKey;
	}

	/**
	 * Injecció de la dependència tipusEstablimentEnvasadora
	 * 
	 * @param tipusEstablimentEnvasadora
	 *            La classe a injectar.
	 */
	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}

	/**
	 * Injecció de la dependència tipusEstablimentTafonaEnvasadora
	 * 
	 * @param tipusEstablimentTafonaEnvasadora
	 *            La classe a injectar.
	 */
	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}

	/**
	 * Injecció de la dependència campanyaSessionKey
	 * 
	 * @param campanyaSessionKey
	 *            La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}

	/*
	 * Retorna els arxius associats a aquesta peticio o null si no n'hi ha
	 */
	private MultipartFile[] getFiles(HttpServletRequest request) {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
			Map mFiles = mpRequest.getFileMap();
			if (mFiles.size() > 0) {
				MultipartFile[] files = new MultipartFile[mFiles.size()];
				int i = 0;
				for (Iterator it = mFiles.keySet().iterator(); it.hasNext();) {
					files[i++] = mpRequest.getFile((String) it.next());
				}
				if (files[0].getSize() > 0 || files[1].getSize() > 0)
					return files;
				else
					return null;
			}
		}
		return null;
	}

	/**
	 * set the hibernate template.
	 * 
	 * @param hibernateTemplate
	 *            the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * get the hibernate template.
	 * 
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

}
