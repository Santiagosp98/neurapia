//calendario fecha
$(document).ready(function() {
    
    $('#fecha').datepicker( {
        dateFormat: "yy-mm-dd"
    });
    
});


function olvidasteContraseña(){
	var usuario2 = $('#emailRe').val();

	console.log(usuario2);

	if (usuario2 == "babuitrago655@misena.edu.co") 
	{
		swal({
			title: "Muy bien",
			text: "Hemos enviado una nueva clave a tu correo!",
			type: "success",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok !",
			closeOnConfirm: false,
			closeOnCancel: false
		},
		function(isConfirm){
			if (isConfirm) {
				window.location.href = 'Inicio.html';
			}
		});

	}
	else
	{
		$('#emailRe').val("");
		swal({
			title: "Error",
			text: "El correo ingresado no es valido",
			type: "error",
		})

	}
}

function ingresar(){
	var usuario = $('#email').val();
	var password = $('#password').val();

	console.log(usuario);
	console.log(password);

	if (usuario == "babuitrago655@misena.edu.co" && password == "neurapia") 
	{
		swal({
			title: "Bienvenido",
			text: "Neurapia te da la bienvenida Administrador!",
			type: "success",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok !",
			closeOnConfirm: false,
			closeOnCancel: false
		},
		function(isConfirm){
			if (isConfirm) {
				window.location.href = 'Administrador/Pagina Usuario Administrador.html';
			}
		});

	}else if (usuario == "j645@misena.edu.co" && password == "software") {
		swal({
			title: "Bienvenido",
			text: "Neurapia te da la bienvenida Paciente!",
			type: "success",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok !",
			closeOnConfirm: false,
			closeOnCancel: false
		},
		function(isConfirm){
			if (isConfirm) {
				window.location.href = 'Paciente/Pagina inicio Paciente.html';
			}
		});
	}else{
		swal({
			title: "Error",
			text: "Has digitado mal el usuario o la contraseña",
			type: "error",
		});
	}
}
function cambioUsuarioFisio(){
	swal({
		title: "Cambio usuario!",
		text: "Quieres cambiar de usuario a fisioterapeuta!",
		type: "info",
		showCancelButton: true,
		confirmButtonColor: "#3DCCC2",
		confirmButtonText: "si, Cambiar!",
		cancelButtonText: "No, cancela porfavor!",
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
		if (isConfirm) {
			window.location.href = 'Pagina inicio AdminFisioterapeuta.html';
		} else {
			swal("Cancelado", "No hemos cambiado el usuario :)", "success");
		}
	});
}
function cambioUsuarioFisiCita(){
	swal({
		title: "Cambio usuario!",
		text: "Quieres cambiar de usuario a fisioterapeuta!",
		type: "info",
		showCancelButton: true,
		confirmButtonColor: "#3DCCC2",
		confirmButtonText: "si, Cambiar!",
		cancelButtonText: "No, cancela porfavor!",
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
		if (isConfirm) {
			window.location.href = '../Pagina inicio AdminFisioterapeuta.html';
		} else {
			swal("Cancelado", "No hemos cambiado el usuario :)", "success");
		}
	});
}
function cambioUsuarioAdmin(){
	swal({
		title: "Cambio usuario!",
		text: "Quieres cambiar de usuario a Administrador!",
		type: "info",
		showCancelButton: true,
		confirmButtonColor: "#3DCCC2",
		confirmButtonText: "si, Cambiar!",
		cancelButtonText: "No, cancela porfavor!",
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
		if (isConfirm) {
			window.location.href = 'Pagina Usuario Administrador.html';
		} else {
			swal("Cancelado", "No hemos cambiado el usuario :)", "success");
		}
	});
}
function cambioUsuarioAdminCita(){
	swal({
		title: "Cambio usuario!",
		text: "Quieres cambiar de usuario a fisioterapeuta!",
		type: "info",
		showCancelButton: true,
		confirmButtonColor: "#3DCCC2",
		confirmButtonText: "si, Cambiar!",
		cancelButtonText: "No, cancela porfavor!",
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
		if (isConfirm) {
			window.location.href = '../Pagina Usuario Administrador.html';
		} else {
			swal("Cancelado", "No hemos cambiado el usuario :)", "success");
		}
	});
}
function cerrar(){
	swal({
		title: "estas seguro?",
		text: "Quieres salir del sistema!",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "si, salir!",
		cancelButtonText: "No, cancela porfavor!",
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
		if (isConfirm) {
			window.location.href = 'inicio.html';
		} else {
			swal("Cancelado", "No hemos salido de la sesion :)", "success");
		}
	});
}
function cerrarAdminUsuario(){
	swal({
		title: "estas seguro?",
		text: "Quieres salir del sistema!",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "si, salir!",
		cancelButtonText: "No, cancela porfavor!",
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
		if (isConfirm) {
			window.location.href = '../inicio.html';
		} else {
			swal("Cancelado", "No hemos salido de la sesion :)", "success");
		}
	});
}
function cerrarAdminCitas(){
	swal({
		title: "estas seguro?",
		text: "Quieres salir del sistema!",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "si, salir!",
		cancelButtonText: "No, cancela porfavor!",
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
		if (isConfirm) {
			window.location.href = '../../inicio.html';
		} else {
			swal("Cancelado", "No hemos salido de la sesion :)", "success");
		}
	});
}

function permite(elEvento, permitidos) {
  // Variables que definen los caracteres permitidos
  var numeros = "0123456789";
  var caracteres = " abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
  var numeros_caracteres = numeros + caracteres;
  var teclas_especiales = [8, 37, 39, 46];
  // 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha


  // Seleccionar los caracteres a partir del parámetro de la función
  switch(permitidos) {
  	case 'num':
  	permitidos = numeros;
  	break;
  	case 'car':
  	permitidos = caracteres;
  	break;
  	case 'num_car':
  	permitidos = numeros_caracteres;
  	break;
  }

  // Obtener la tecla pulsada 
  var evento = elEvento || window.event;
  var codigoCaracter = evento.charCode || evento.keyCode;
  var caracter = String.fromCharCode(codigoCaracter);

  // Comprobar si la tecla pulsada es alguna de las teclas especiales
  // (teclas de borrado y flechas horizontales)
  var tecla_especial = false;
  for(var i in teclas_especiales) {
  	if(codigoCaracter == teclas_especiales[i]) {
  		tecla_especial = true;
  		break;
  	}
  }

  // Comprobar si la tecla pulsada se encuentra en los caracteres permitidos
  // o si es una tecla especial
  return permitidos.indexOf(caracter) != -1 || tecla_especial;
}

function nombreCompleto(){
	var nombre = $ ('#primerNombre').val();
	var nombre2 = $ ('#segundoNombre').val();
	var apellido = $ ('#primerApellido').val();
	var apellido2 = $ ('#segundoApellido').val();
	if (nombre2==null || nombre2 == "") {
		var nombreCompleto = $('#nombreCompleto').val(nombre+" "+apellido+" "+apellido2);
	} else if(apellido2==null || apellido2 == ""){
		var nombreCompleto = $('#nombreCompleto').val(nombre+" "+nombre2+" "+apellido);
	} else if((nombre2==null || nombre2 == "") && (apellido2==null || apellido2 == "")){
		var nombreCompleto = $('#nombreCompleto').val(nombre+apellido);
	} else{
		$('#nombreCompleto').val(nombre+" "+nombre2+" "+apellido+" "+apellido2);
	}
	
}	

function validacion(){
	selectorCivil=document.getElementById('EstadoCivil').selectedIndex;
	if( selectorCivil == null || selectorCivil == 0 ) {
		document.getElementById('EstadoCivil').focus();
		$('#EstadoCivil').css('border-color', '#FB1F1F')
		swal({
			title: "Error!",
			text: "En selector de Estado civil, eliga una opcion correcta",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok",
			closeOnConfirm: false,
			closeOnCancel: false,
		});
		
		return false;
	}else{
		$('#EstadoCivil').css('border-color', '#21E23C')
	}
	selSexo=document.getElementById('selSexo').selectedIndex;
	if(selSexo == null || selSexo == 0){
		document.getElementById('selSexo').focus();
		$('#selSexo').css('border-color', '#FB1F1F')
		swal({
			title: "Error!",
			text: "En selector de Sexo, eliga una opcion correcta",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok",
			closeOnConfirm: false,
			closeOnCancel: false,
		});
		return false;
	}else{
		$('#selSexo').css('border-color', '#21E23C')
	}
	selRH=document.getElementById('selRH').selectedIndex;
	if(selRH == null || selRH == 0){
		document.getElementById('selRH').focus();
		$('#selRH').css('border-color', '#FB1F1F')
		swal({
			title: "Error!",
			text: "En selector de RH, eliga una opcion correcta",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok",
			closeOnConfirm: false,
			closeOnCancel: false,
		});
		return false;
	}else{
		$('#selRH').css('border-color', '#21E23C')
	}
	selEscolaridad=document.getElementById('selEscolaridad').selectedIndex;
	if(selEscolaridad == null || selEscolaridad == 0){
		document.getElementById('selEscolaridad').focus();
		$('#selEscolaridad').css('border-color', '#FB1F1F')
		swal({
			title: "Error!",
			text: "En selector de escolaridad, eliga una opcion correcta",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok",
			closeOnConfirm: false,
			closeOnCancel: false,
		});
		return false;
	}else{
		$('#selEscolaridad').css('border-color', '#21E23C')
	}
	selZona=document.getElementById('selZona').selectedIndex;
	if(selZona == null || selZona == 0){
		document.getElementById('selZona').focus();
		$('#selZona').css('border-color', '#FB1F1F')
		swal({
			title: "Error!",
			text: "En selector de zona, eliga una opcion correcta",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok",
			closeOnConfirm: false,
			closeOnCancel: false,
		});
		return false;
	}else{
		$('#selZona').css('border-color', '#21E23C')
	}
	clave=document.getElementById('clave').value;
	confirmarClave=document.getElementById('confirmarClave').value;
	if(confirmarClave!=clave){
		document.getElementById('clave').focus();
		$('#confirmarClave').val('');
		$('#clave').val('');
		swal({
			title: "Error!",
			text: "En Confirmar contrasseña, No coinciden",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok",
			closeOnConfirm: false,
			closeOnCancel: false
		});
		
		return false;
	}
	alert('Muy bien, hemos guardado tus datos personales');
}


function miFuncion(data){
//    alert(data.status);
    if(data.status === 'success'){
        swal({
			title: "Muy bien",
			text: "Hemos enviado una nueva clave a tu correo!",
			type: "success",
			showCancelButton: false,
			confirmButtonColor: "#3DCCC2",
			confirmButtonText: "ok !",
			closeOnConfirm: false,
			closeOnCancel: false
		},
		function(isConfirm){
			if (isConfirm) {
				window.location.href = 'index.xhtml';
			}
		});
    }else if(data.status === 'begin' ){
        swal({
			title: "Comprobando datos",
			text: "Estamos comprobando el correo ingresado",
			imageUrl: "resources/imgs/load1.gif",
                        showConfirmButton: false
	});
    }
}