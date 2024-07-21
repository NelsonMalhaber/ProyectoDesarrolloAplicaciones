$(document).ready(function () {
    var tablaTrabajadores = $('#tablaTrabajadores').DataTable({
        ajax: {
            url: '/api/trabajadores',
            dataSrc: ''
        },
        columns: [
            { data: 'id' },
            { data: 'nombre' },
            { data: 'documento' },
            { data: 'telefono' },
            { data: 'direccion' },
            { data: 'email' },
            {
                data: null,
                render: function (data, type, row) {
                    return `<button class="btn btn-primary btn-sm editar" data-id="${row.id}"><i class="fas fa-edit"></i></button>
                            <button class="btn btn-danger btn-sm eliminar" data-id="${row.id}"><i class="fas fa-trash"></i></button>`;
                }
            }
        ]
    });

    $('#guardarTrabajador').click(function () {
        var trabajador = {
            nombre: $('#nombre').val(),
            documento: $('#documento').val(),
            telefono: $('#telefono').val(),
            direccion: $('#direccion').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/trabajadores',
            data: JSON.stringify(trabajador),
            contentType: 'application/json',
            success: function () {
                tablaTrabajadores.ajax.reload();
                $('#modal-update').modal('hide');
                Swal.fire('¡Guardado!', 'El trabajador ha sido guardado.', 'success');
            }
        });
    });

    $('#tablaTrabajadores tbody').on('click', '.editar', function () {
        var id = $(this).data('id');

        $.get('/api/trabajadores/' + id, function (trabajador) {
            $('#nombre').val(trabajador.nombre);
            $('#documento').val(trabajador.documento);
            $('#telefono').val(trabajador.telefono);
            $('#direccion').val(trabajador.direccion);
            $('#email').val(trabajador.email);
            $('#guardarTrabajador').data('id', id);
            $('#modal-title').text('Editar Trabajador');
            $('#modal-update').modal('show');
        });
    });

    $('#tablaTrabajadores tbody').on('click', '.eliminar', function () {
        var id = $(this).data('id');

        Swal.fire({
            title: '¿Estás seguro?',
            text: "¡No podrás revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, eliminarlo!'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'DELETE',
                    url: '/api/trabajadores/' + id,
                    success: function () {
                        tablaTrabajadores.ajax.reload();
                        Swal.fire('¡Eliminado!', 'El trabajador ha sido eliminado.', 'success');
                    }
                });
            }
        });
    });

    $('#nuevo').click(function () {
        $('#modal-title').text('Nuevo Trabajador');
        $('#nombre').val('');
        $('#documento').val('');
        $('#telefono').val('');
        $('#direccion').val('');
        $('#email').val('');
        $('#guardarTrabajador').data('id', null);
        $('#modal-update').modal('show');
    });

    $('#modal-update').on('hidden.bs.modal', function () {
        $('#nombre').val('');
        $('#documento').val('');
        $('#telefono').val('');
        $('#direccion').val('');
        $('#email').val('');
        $('#guardarTrabajador').data('id', null);
        $('#modal-title').text('Nuevo');
    });
});
