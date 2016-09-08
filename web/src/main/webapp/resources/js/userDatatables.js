var ajaxUrl = '/library/rest/users/';
var dataTableApi;
var item = ' User';

$(function () {
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        jqXHR.setRequestHeader(
            $("meta[name='_csrf_header']").attr("content"),
            $("meta[name='_csrf']").attr("content"));
    });

    dataTableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "searching": false,
        "info": false,
        "rowId": 'id',
        "columns": [
            {
                "data": "name",
                "render": renderEditLink
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});
