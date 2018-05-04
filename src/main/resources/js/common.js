function getQueryString() {
    var qs = location.search.substr(1), // 获取url中"?"符后的字串
        args = {}, // 保存参数数据的对象
        items = qs.length ? qs.split("&") : [], // 取得每一个参数项,
        item = null,
        len = items.length;

    for(var i = 0; i < len; i++) {
        item = items[i].split("=");
        var name = decodeURIComponent(item[0]),
            value = decodeURIComponent(item[1]);
        if(name) {
            args[name] = value;
        }
    }
    return args;
}
var id = getQueryString()["id"];

var listTable;
var apiRoot;
$(function () {
    listTable = $("#listTable");

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    $("#btn_add").click(function () {
        location.href = apiRoot + "/add.html";
    });
    $("#btn_delete").click(function () {
        var tables = listTable.bootstrapTable('getSelections');
        console.log(tables[0])
        var lines = new Array();
        for (var i = 0; i < tables.length; i++) {
            lines.push(tables[i].id);
        }
        if(confirm('确认删除所有选中数据?') == true){
            $.ajax({
                method:'POST',
                url: apiRoot + '/delete',
                data:{ids:lines},
                traditional:true,
                success : function(o) {
                    if(o.status == 1){
                        $("#btn_delete,#btn_edit").hide();
                        listTable.bootstrapTable('remove', {field: 'id', values: lines});
                    }else {
                        alert(o.msg == null ? "删除失败" : o.msg);
                    }
                }
            });
        }
    })
});

//得到查询的参数
var queryParams = function (params) {
    console.log(params);
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        page: params.offset / params.limit + 1,  //页码
    };
    return temp;
};
function deleteRole(id) {
    $.ajax({
        method:'POST',
        url: apiRoot + '/delete',
        data:{ids:[id]},
        traditional:true,
        success : function(o) {
            if(o.status == 1){
                listTable.bootstrapTable('remove', {field: 'id', values: [id]});
            }else {
                alert(o.msg == null ? "删除失败" : o.msg);
            }
        }
    });
}
function deleteModel(id) {
    $.ajax({
        method:'POST',
        url: apiRoot + '/delete',
        data:{ids:[id]},
        traditional:true,
        success : function(o) {
            if(o.status == 1){
                listTable.bootstrapTable('remove', {field: 'id', values: [id]});
            }else {
                alert(o.msg == null ? "删除失败" : o.msg);
            }
        }
    });
}