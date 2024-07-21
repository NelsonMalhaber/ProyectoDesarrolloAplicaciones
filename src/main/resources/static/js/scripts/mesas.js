const urlMesas = "api/mesas";

function saveMesa(bandera) {
    $("#modal-update").modal("hide");
    let id = $("#guardarMesaBtn").data("id");    
    let mesa = {
        id: id,
        numero: $("#numero").val(),
        capacidad: $("#capacidad").val(),
        descripcion: $("#descripcion").val(),
    };
    let metodo = (bandera == 1) ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: urlMesas,
        data: JSON.stringify(mesa),
        dataType: "text",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if(data == 0){
                Swal.fire({
                    icon: 'error',
                    title: 'La mesa ya está registrada',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                let texto = bandera == 1 ? "guardada" : "actualizada";
                getTablaMesas();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha ' + texto + ' la mesa',
                    showConfirmButton: false,
                    timer: 1500
                });
                clearMesaForm();
            }
        },
    }).fail(function () {
        Swal.fire({
            icon: 'error',
            title: 'Error al guardar la mesa',
            showConfirmButton: false,
            timer: 1500
        });
    });
}

function deleteMesa(id) {
    $.ajax({
        type: "DELETE",
        url: urlMesas + "/" + id,
        data: { id: id },
        cache: false,
        timeout: 600000,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado la mesa',
                showConfirmButton: false,
                timer: 1500
            });
            getTablaMesas();
        },
    }).fail(function () {
        Swal.fire({
            icon: 'error',
            title: 'Error al eliminar la mesa',
            showConfirmButton: false,
            timer: 1500
        });
    });
}

function getTablaMesas() {
    $.ajax({
        type: "GET",
        url: urlMesas,
        dataType: "text",
        cache: false,
        success: function (data) {
            let t = $("#tablaMesas").DataTable();
            t.clear().draw(false);
            let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i></button>' +
                           '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';
            let js = JSON.parse(data);
            $.each(js, function (a, b) {
                t.row.add([b.id, b.numero, b.capacidad, b.descripcion, botonera]);
            });
            t.draw(false);
        },
    }).fail(function () {
        Swal.fire({
            icon: 'error',
            title: 'Error al cargar las mesas',
            showConfirmButton: false,
            timer: 1500
        });
    });
}

function getMesa(id) {
    $.ajax({
        type: "GET",
        url: urlMesas + "/" + id,
        data: { id: id },
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Mesa");
            $("#numero").val(data.numero);
            $("#capacidad").val(data.capacidad);
            $("#descripcion").val(data.descripcion);
            $("#guardarMesaBtn").data("id", data.id);
            $("#guardarMesaBtn").data("bandera", 0);
            $("#modal-update").modal("show");
        },
    }).fail(function () {
        Swal.fire({
            icon: 'error',
            title: 'Error al cargar los datos de la mesa',
            showConfirmButton: false,
            timer: 1500
        });
    });
}

function clearMesaForm() {
    $("#modal-title").text("Nueva Mesa");
    $("#numero").val("");
    $("#capacidad").val("");
    $("#descripcion").val("");
    $("#guardarMesaBtn").data("id", 0);
    $("#guardarMesaBtn").data("bandera", 1);
}

$(document).ready(function () {
    $("#tablaMesas").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_",
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
            { targets: 1, width: "20%" },
            { targets: 2, width: "20%" },
            { targets: 3, width: "40%" },
            { targets: 4, orderable: false, width: "10%" }
        ],
    });

    clearMesaForm();

    $("#nuevaMesa").click(function () {
        clearMesaForm();
    });

    $("#guardarMesaBtn").click(function () {
        let bandera = $("#guardarMesaBtn").data("bandera");
        saveMesa(bandera);
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Mesa',
            text: "¿Está seguro de querer eliminar esta mesa?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, eliminar'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteMesa(id);
            }
        });
    });
    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        getMesa(id);
    });
    getTablaMesas();
}
)
;
