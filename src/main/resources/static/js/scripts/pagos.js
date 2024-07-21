const url = "api/pagos";

function save(bandera) {
    $("#modal-update").modal("hide");

    let id = $("#guardar").data("id");
    let documentoInquilino = $("#documentoInquilino").val();
    let fecha = $("#fecha").val();
    let montoPago = $("#montoPago").val();
    let metodoPago = $("#metodoPago").val();
    let descripcion = $("#descripcion").val();

    let pago = {
        id: id,
        documentoInquilino: documentoInquilino,
        fecha: fecha,
        montoPago: parseFloat(montoPago), // Convertir a número
        metodoPago: metodoPago,
        descripcion: descripcion
    };

    let metodo = (bandera === 1) ? "POST" : "PUT";

    $.ajax({
        type: metodo,
        url: url,
        data: JSON.stringify(pago),
        dataType: "json",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if (data === 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'El pago ya está registrado',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                let texto = (bandera === 1) ? "guardado" : "actualizado";
                getTabla();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha ' + texto + ' el pago',
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
                title: 'Se ha eliminado el pago',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error al eliminar el pago',
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
            let t = $("#tablaPagos").DataTable();
            t.clear().draw(false);

            $.each(data, function (index, pago) {
                let botonera = '<button type="button" class="btn btn-warning btn-sm editar" data-id="' + pago.id + '"><i class="fas fa-edit"></i> </button>' +
                    '<button type="button" class="btn btn-danger btn-sm eliminar" data-id="' + pago.id + '"><i class="fas fa-trash"></i></button>';

                t.row.add([
                    pago.id,
                    pago.documentoInquilino,
                    pago.fecha,
                    pago.montoPago,
                    pago.metodoPago,
                    pago.descripcion,
                    botonera
                ]);

            });

            t.draw(false);

        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener los datos de los pagos',
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
            $("#modal-title").text("Editar Pago");
            $("#documentoInquilino").val(data.documentoInquilino);
            $("#fecha").val(data.fecha); // Asignar la fecha correctamente
            $("#montoPago").val(data.montoPago);
            $("#metodoPago").val(data.metodoPago);
            $("#descripcion").val(data.descripcion);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener el pago',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}


function clear() {
    $("#modal-title").text("Nuevo Pago");
    $("#documentoInquilino").val("");
    $("#fecha").val("");
    $("#montoPago").val("");
    $("#metodoPago").val("");
    $("#descripcion").val("");
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

$(document).ready(function () {

    $("#tablaPagos").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_ pagos",
            infoEmpty: "No hay pagos disponibles",
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
            { targets: 1, width: "15%" },
            { targets: 2, width: "10%" },
            { targets: 3, width: "10%" },
            { targets: 4, width: "15%" },
            { targets: 5, width: "35%" },
            { targets: 6, orderable: false, width: "10%" }
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
            title: 'Eliminar Pago',
            text: "¿Está seguro de querer eliminar este pago?",
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
