function makeEditable() {
    var form = $('#detailsForm');

    $(document).on('click', '#add', function () {
        selectedRow = 0;
        $('#modalTitle').text('Add' + item);
        form.find(":input").val("");
        $('#id').val(null);
        $('#editRow').modal();
    });

    $(document).on('click', '.editLink', function (event) {
        event.preventDefault();
        $('#modalTitle').text('Edit' + item);
        var id = $(this).attr('id');
        selectedRow = id;
        $.get(ajaxUrl + id, function (data) {
            $.each(data, function (key, value) {
                form.find("input[name='" + key + "']").val(value);
            });
            $('#editRow').modal();
        });
    });

    $(document).on('click', '.deleteBtn', function () {
        var id = $(this).attr('id');
        noty({
            text: 'Are you sure?',
            type: 'confirm',
            modal: true,
            layout: 'center',
            buttons: [
                {
                    addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
                    $noty.close();
                    $.ajax({
                        url: ajaxUrl + id,
                        type: 'DELETE',
                        success: function () {
                            dataTableApi.row('#' + id).remove().draw();
                            successNoty('Deleted');
                        }
                    });
                }
                },
                {
                    addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                    $noty.close();
                }
                }
            ]
        });
    });

    $(document).on('click', '.rentBtn', function () {
        var id = $(this).attr('id');
        var book = dataTableApi.row('#' + id).data();
        book.userName = $('#loggedUserName').text();
        $.ajax({
            url: ajaxUrl,
            type: 'POST',
            data: book,
            success: function (data) {
                dataTableApi.row('#' + id).data(data).draw();
                successNoty('Rented');
            }
        });
    });

    $(document).on('click', '.returnBtn', function () {
        var id = $(this).attr('id');
        var book = dataTableApi.row('#' + id).data();
        book.userName = null;
        $.ajax({
            url: ajaxUrl,
            type: 'POST',
            data: book,
            success: function (data) {
                dataTableApi.row('#' + id).data(data).draw();
                successNoty('Returned');
            }
        });
    });

    form.submit(function () {
        $.ajax({
            url: ajaxUrl,
            type: "POST",
            data: form.serialize(),
            success: function (data) {
                $('#editRow').modal('hide');
                if (selectedRow == 0) {
                    dataTableApi.row.add(data).draw();
                } else {
                    dataTableApi.row('#' + selectedRow).data(data).draw();
                }
                successNoty('Saved');
            }
        });
        return false;
    });

    $(document).on('click', '#more', function () {
        $.ajax({
            url: ajaxUrl + 'getMore',
            type: 'POST',
            data: {
                "position": dataTableApi.data().count()
            },
            success: function (data) {
                dataTableApi.clear().rows.add(data).draw();
            }
        });
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    // Hide DELETE Column
    if($('#hideColumn').length){
        var columnsCount = dataTableApi.columns().nodes().length;
        dataTableApi.column(columnsCount - 1).visible(false);
    }
}

function renderEditLink(data, type, row) {
    if (row.name == $('#loggedUserName').text()){
        return row.name;
    } else {
        return '<a href="" class="editLink" id="' + row.id + '">' + data + '</a>';
    }
}

function renderDeleteBtn(data, type, row) {
    if (row.name == $('#loggedUserName').text()) {
        return '<a class="btn btn-xs btn-danger deleteBtn disabled" id="' + row.id + '">Delete</a>';
    } else {
        return '<a class="btn btn-xs btn-danger deleteBtn" id="' + row.id + '">Delete</a>';
    }
}

function renderRent(data, type, row) {
    if (!data) {
        return '<a class="btn btn-xs btn-success rentBtn" id="' + row.id + '">Rent</a>';
    } else if (data == $('#loggedUserName').text()) {
        return '<a class="btn btn-xs btn-warning returnBtn" id="' + row.id + '">Return</a>';
    } else {
        return row.userName;
    }
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = jqXHR.responseJSON;
    $.each(errorInfo, function () {
        failedNote = noty({
            text: 'Failed: ' + jqXHR.statusText + "<br>" + this.field + "<br>" + this.message,
            type: 'error',
            layout: 'bottomRight'
        });
    });
}
