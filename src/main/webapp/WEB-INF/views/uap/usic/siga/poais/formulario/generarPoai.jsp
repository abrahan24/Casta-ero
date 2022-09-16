<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script type="text/javascript">
		window.onload = function () {
			window.print();
    	}
	</script>
	
		<table width="100%">
			<tr>
				<td>
					<table width="100%" class="table table-sm table-bordered border-dark">
						<tr>
	
							<td width="10%" height="100%" class="text-center"><img
								src="${pageContext.request.contextPath}/resource/images/logs/logouap.jpg" class="img-responsive" alt="UAP"
								width="40" height="55"></td>
	
							<td width="80%" height="100%" class="text-center">
								<h5>UNIVERSIDAD AMAZONICA DE PANDO</h5>
								<h4>PROGRAMACION OPERATIVA ANUAL INDIVIDUAL</h4>
							</td>
	
							<td width="10%" height="100%" class="text-center"><br>
								<h5>FORM 001</h5>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th colspan="2" ><h6>1 - IDENTIFICACION</h6></th>
			</tr>
			
			<tr>
				<th width="30%" >CARGO</th>
				<td>${identificacion.pnlCargos.cargo}</td>
			</tr>
			
			<tr>
				<th width="30%" >UNIDAD O AREA</th>
				<td>${administrativo.insUnidadesFuncionales.unidadFuncional}</td>
			</tr>
			
			<tr>
				<th width="30%">SUPERVISION</th>
				<td>${identificacion.poaisSupervisores.personas.nombres} ${identificacion.poaisSupervisores.personas.paterno} ${identificacion.poaisSupervisores.personas.materno}</td>
			</tr>
			
			<tr>
				<th width="30%">PUESTO DE INMEDIATO SUPERIOR</th>
				<td>${plnISuperior.pnlCargos.cargo}</td>
			</tr>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th colspan="2" >RELACIONES DEL CARGO</th>
			</tr>
			
			<tr class="text-center">
				<th width="50%" >RELACIONES INTERINSTITUCIONALES</th>
				<th>RELACIONES INTRAINSTITUCIONALES</th>
			</tr>
			
			<tr>
				<td width="50%" >
				  	${identificacion.relacionInterinstitucional}
				</td>
				<td>
					${identificacion.relacionIntrainstitucional}
				</td>
			</tr>		
			
		</table>
		
		<hr align="left" noshade="noshade" size="10" width="100%" />
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th><h6>2 - OBJETIVOS, RESULTADOS Y FUNCIONES DEL CARGO</h6></th>
			</tr>
			<tr>
				<th>OBJETIVO DE CARGO</th>
			</tr>
			
			<tr>
				<td style="text-align: justify;">${identificacion.pnlCargos.objetivoCargo}</td>
			</tr>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th colspan="5">RESULTADOS ESPECIFICOS (PROFESIONALES Y TECNICOS)</th>
			</tr>
			<tr>
				<th>#</th>
				<th>RESULTADOS ESPECIFICOS</th>
				<th>PONDERACION</th>
				<th>INDICADORES</th>
				<th class="text-center">ACTIVIDADES</th>
			</tr>
			
			<c:forEach items="${objetivo.poaisResultados}" var="pr">
			<c:if test="${pr.idEstado != 'X' && pr.tipo == 'A'}">
			<c:set var="cont" value="${cont+1}" />
			<tr>
				<td>${cont}</td>
				<td style="text-align: justify;" width="45%">${pr.resultado}</td>
				<td class="text-center">${pr.ponderacion} %</td>
				<td style="text-align: justify;" width="7%">${pr.indicador}</td>
				<td width="40%"><br>
					<table width="100%" class="table table-sm table-bordered border-dark">
						<tr>
							<th width="40%">ACTIVIDAD</th>
							<th>MEDIO DE VERIFICACION</th>
							<th>% DE EJECUCION</th>
						</tr>
						<c:forEach items="${pr.poaisActividades}" var="act">					
						<tr>
							<td style="text-align: justify;">${act.actividad}</td>
							<td>${act.medioVerificacion}</td>
							<td>${act.puntaje}</td>				
						</tr>
					</c:forEach>
					</table>
				</td>		
			</tr>				
			</c:if>
			</c:forEach>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th colspan="3" >RESULTADOS CONTINUOS O FUNCIONES</th>
			</tr>
			
			<tr>
				<th>#</th>
				<th>RESULTADO CONTINUO</th>
				<th>PONDERACION</th>
			</tr>
			
			<c:forEach items="${objetivo.poaisResultados}" var="pr">
			<c:if test="${pr.idEstado != 'X' && pr.tipo == 'C'}">
			<c:set var="cont1" value="${cont1+1}" />			
			<tr>
				<td width="2%">${cont1}</td>
				<td width="90%" >${pr.resultado}</td>
				<td class="text-center" width="10%">${pr.ponderacion} %</td>
			</tr>					
			</c:if>
			</c:forEach>
		</table>
		
		<hr align="left" noshade="noshade" size="10" width="100%" />
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th><h6>3 - REQUISITOS DEL PUESTO (EXTRAER INFORMACION DEL MANUAL DE CARGOS)</h6></th>
			</tr>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th>FORMACION</th>
			</tr>
			<c:forEach items="${formaciones}" var="f">
			<tr>
				<td style="text-align: justify;">${f.requisitoFormacion}</td>
			</tr>
			</c:forEach>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th>EXPERIENCIA</th>
			</tr>
			<c:forEach items="${experiencias}" var="ex">
			<tr>
				<td style="text-align: justify;">${ex.requisitoExperiencia}</td>
			</tr>
			</c:forEach>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th>CUALIDADES</th>
			</tr>
			<c:forEach items="${cualidades}" var="cu">
			<tr>
				<td style="text-align: justify;">${cu.requisitoCualidad}</td>
			</tr>
			</c:forEach>
		</table>
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th>CUMPLIMIENTO DE NORMAS Y RESPONSABILIDAD</th>
			</tr>
			<c:forEach items="${cumplimientos}" var="cum">
			<tr>
				<td style="text-align: justify;">${cum.requisitoCumplimiento}</td>
			</tr>
			</c:forEach>
		</table>
		
		<hr align="left" noshade="noshade" size="10" width="100%" />
		
		<table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th colspan="3"><h6>4 - COMPROMISO</h6></th>
			</tr>
			<tr class="text-center">
				<th>FECHA ELABORACION</th>
				<th>FECHA INICIO DE EJECUCION</th>
				<th>FECHA DE APROBACION	</th>
			</tr>
			<tr class="text-center" height="30">
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</table>
		
		<table class="table table-bordered border-dark table-sm">
        <tr height="70">
	        <th class="text-center" width="50%"></th>                 
	        <th class="text-center" width="50%"></th>
        </tr>
        
        <tr>
	        <th class="text-center" width="50%">JEFE INMEDIATO SUPERIOR</th>	                              
	        <th  class="text-center" width="50%">
	        SERVIDOR PUBLICO <br>
	        ${persona.nombres} ${persona.paterno} ${persona.materno}
	        </th>
        </tr>
        </table>
        
        <hr align="left" noshade="noshade" size="10" width="100%" />
        
        <table width="100%" class="table table-sm table-bordered border-dark">
			<tr>
				<th colspan="3"><h6>5 - RECURSO DE REVOCATORIA</h6></th>
			</tr>
			<tr class="text-center">
				<th width="33%">ACAPITE</th>
				<th width="33%">DESCRIPCION</th>
				<th width="33%">OBSERVACION</th>
			</tr>
			<tr class="text-center" height="90">
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</table>
        
        <table width="100%" class="table table-sm table-bordered border-dark">
			<tr class="text-center">
				<th width="50%">FECHA DE RECEPCION DE LA APLICACION</th>
				<th>FECHA DE APROBACION DEFINITIVA</th>
			</tr>
			<tr class="text-center" height="30">
				<th></th>
				<th></th>
			</tr>
		</table>
        
        <table class="table table-bordered border-dark table-sm">
        <tr height="70">
	        <th class="text-center" width="50%"></th>                 
	        <th class="text-center" width="50%"></th>
        </tr>
        
        <tr>
	        <th class="text-center" width="50%">JEFE INMEDIATO SUPERIOR</th>	                              
	        <th  class="text-center" width="50%">
	        SERVIDOR PUBLICO <br>
	        ${persona.nombres} ${persona.paterno} ${persona.materno}
	        </th>
        </tr>
        </table>
        
        
		