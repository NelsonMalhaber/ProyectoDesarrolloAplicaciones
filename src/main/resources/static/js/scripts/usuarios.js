const urlUsuarios = "/api/usuarios"; // Corregido el path de la URL



function saveUsuario(bandera) {
    $("#modal-update").modal("hide");
    let id = $("#guardar").data("id");
    let usuario = {
        id: id,
        usuario: $("#usuario").val(),
        password: $("#password").val(),
        tipoUsuario: $("#tipoUsuario").val(), // Convertir a mayúsculas
        trabajadorId: parseInt($("#trabajadorId").val())
    };
    let metodo = (bandera === 1) ? "POST" : "PUT";
    console.log(usuario);
    $.ajax({
        type: metodo,
        url: urlUsuarios,
        data: JSON.stringify(usuario),
        dataType: "json",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if (data === 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'El usuario ya está registrado',
                    showConfirmButton: false,
                    timer: 1500
                });				
            } else {
                let texto = bandera === 1 ? "guardado" : "actualizado";
                getTablaUsuarios();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha ' + texto + ' el usuario',
                    showConfirmButton: false,
                    timer: 1500
                });
                clearUsuario();
            }
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error al procesar la solicitud',
                text: xhr.responseText,
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function deleteFilaUsuario(id) {
    $.ajax({
        type: "DELETE",
        url: urlUsuarios + "/" + id,
        cache: false,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado el usuario',
                showConfirmButton: false,
                timer: 1500
            });
            getTablaUsuarios();
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error al eliminar el usuario',
                text: xhr.responseText,
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function getTablaUsuarios() {
    $.ajax({
        type: "GET",
        url: urlUsuarios,
        dataType: "json",
        cache: false,
        success: function (data) {
            let t = $("#tablaUsuarios").DataTable();
            t.clear().draw(false);
            $.each(data, function (index, usuario) {
                let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i></button>' +
                               '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';
                t.row.add([usuario.id, usuario.usuario, usuario.tipoUsuario, usuario.trabajadorId, botonera]);
            });
            t.draw(false);
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener los usuarios',
                text: xhr.responseText,
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function getFilaUsuario(id) {
    $.ajax({
        type: "GET",
        url: urlUsuarios + "/" + id,
        cache: false,
        success: function (data) {
		    $("#modal-title").text("Editar Usuario");
		    $("#usuario").val(data.usuario);
		    $("#password").val(data.password);
		    $("#tipoUsuario").val(data.tipoUsuario);
		    $("#trabajadorId").val(data.trabajadorId);
		    $("#guardar").data("id", data.id);
		    $("#guardar").data("bandera", 0);
		    $("#modal-update").modal("show");
		},
        error: function (xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener el usuario',
                text: xhr.responseText,
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function clearUsuario() {
    $("#modal-title").text("Nuevo Usuario");
    $("#usuario").val("");
    $("#password").val("");
    $("#tipoUsuario").val("administrador"); // Asegúrate que este valor coincida con tu enum en la entidad Usuario
    $("#trabajadorId").val("");
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

$(document).ready(function () {

    $("#tablaUsuarios").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_ usuarios",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, width: "10%" },
            { targets: 1, width: "30%" },
            { targets: 2, width: "20%" },
            { targets: 3, width: "20%" },
            { targets: 4, orderable: false, width: "20%" }
        ],
    });

    clearUsuario();

    $("#nuevo").click(function () {
        clearUsuario();
    });

    $("#guardar").click(function () {
        let bandera = $("#guardar").data("bandera");
        saveUsuario(bandera);
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Usuario',
            text: "¿Está seguro de querer eliminar este usuario?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteFilaUsuario(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        getFilaUsuario(id);
    });

    getTablaUsuarios();
});
