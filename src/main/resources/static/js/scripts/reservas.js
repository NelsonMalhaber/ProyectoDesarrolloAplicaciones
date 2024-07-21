const url = "api/reservas";

function save(bandera) {
    $("#modal-update").modal("hide");

    let id = $("#guardar").data("id");
    let clienteId = $("#clienteId").val();
    let mesaId = $("#mesaId").val();
    let fecha = $("#fecha").val();
    let hora = $("#hora").val();
    let estado = $("#estado").val();
    let trabajadorId = $("#trabajadorId").val();

    let reserva = {
        id: id,
        clienteId: parseInt(clienteId), // Convertir a número
        mesaId: parseInt(mesaId), // Convertir a número
        fecha: fecha,
        hora: hora,
        estado: estado,
        trabajadorId: parseInt(trabajadorId) // Convertir a número
    };

    let metodo = (bandera === 1) ? "POST" : "PUT";

    $.ajax({
        type: metodo,
        url: url,
        data: JSON.stringify(reserva),
        dataType: "json",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if (data === 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'La reserva ya está registrada',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                let texto = (bandera === 1) ? "guardado" : "actualizado";
                getTabla();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha ' + texto + ' la reserva',
                    showConfirmButton: false,
                    timer: 1500
                });
                clear();
            }
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error al procesar la solicitud',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function deleteFila(id) {
    $.ajax({
        type: "DELETE",
        url: url + "/" + id,
        cache: false,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado la reserva',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error al eliminar la reserva',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function getTabla() {
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        cache: false,
        success: function (data) {
            let t = $("#tablaReservas").DataTable();
            t.clear().draw(false);

            $.each(data, function (index, reserva) {
                let botonera = '<button type="button" class="btn btn-warning btn-sm editar" data-id="' + reserva.id + '"><i class="fas fa-edit"></i> </button>' +
                    '<button type="button" class="btn btn-danger btn-sm eliminar" data-id="' + reserva.id + '"><i class="fas fa-trash"></i></button>';

                t.row.add([
                    reserva.id,
                    reserva.clienteId,
                    reserva.mesaId,
                    reserva.fecha,
                    reserva.hora,
                    reserva.estado,
                    reserva.trabajadorId,
                    botonera
                ]);

            });

            t.draw(false);

        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener los datos de las reservas',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function getFila(id) {
    $.ajax({
        type: "GET",
        url: url + "/" + id,
        cache: false,
        success: function (data) {
            console.log(data);
            $("#modal-title").text("Editar Reserva");
            $("#clienteId").val(data.clienteId);
            $("#mesaId").val(data.mesaId);
            $("#fecha").val(data.fecha);
            $("#hora").val(data.hora);
            $("#estado").val(data.estado);
            $("#trabajadorId").val(data.trabajadorId);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener la reserva',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}


function clear() {
    $("#modal-title").text("Nueva Reserva");
    $("#clienteId").val("");
    $("#mesaId").val("");
    $("#fecha").val("");
    $("#hora").val("");
    $("#estado").val("Reservado"); // Estado por defecto
    $("#trabajadorId").val("");
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

$(document).ready(function () {

    $("#tablaReservas").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_ reservas",
            infoEmpty: "No hay reservas disponibles",
            search: "Buscar:",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior"
            }
        },
        columnDefs: [
            { targets: 0, width: "5%" },
            { targets: 1, width: "10%" },
            { targets: 2, width: "10%" },
            { targets: 3, width: "10%" },
            { targets: 4, width: "10%" },
            { targets: 5, width: "10%" },
            { targets: 6, width: "10%" },
            { targets: 7, orderable: false, width: "15%" }
        ]
    });

    clear();

    $("#nuevo").click(function () {
        clear();
    });

    $("#guardar").click(function () {
        let bandera = $("#guardar").data("bandera");
        save(bandera);
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Reserva',
            text: "¿Está seguro de querer eliminar esta reserva?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $(this).data("id");
                deleteFila(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        let id = $(this).data("id");
        getFila(id);
    });

    getTabla();
});
