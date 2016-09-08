var ajaxUrl = '/library/rest/books/';
var dataTableApi;
var selectedRow = 0;
var item = ' Book';

$(function () {
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        jqXHR.setRequestHeader(
            $("meta[name='_csrf_header']").attr("content"),
            $("meta[name='_csrf']").attr("content"));
    });

    dataTableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl + 'getMore',
            "type": 'POST',
            "data": {
                "position": 0
            },
            "dataSrc": ""
        },
        "paging": false,
        "searching": false,
        "info": false,
        "rowId": 'id',
        "columns": [
            {
                "data": "isbn",
                "orderable": false,
                "render": renderEditLink
            },
            {
                "data": "author"
            },
            {
                "data": "name"
            },
            {
                "data": "userName",
                "orderable": false,
                "render": renderRent
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                1,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});
