$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();



    //删除
    $("#btn_delete").click(function (){
        var temp= $("#supplierTable").bootstrapTable('getSelections');
        if(temp.length<=0) {
            alert("请至少选中一行")
        } else {
            var putTemp = new Array();
            var bb = "";
            for(var i=0;i<temp.length;i++){
               /* putTemp[i]=temp[i].id;
                var aa = temp[i].id;*/
                bb =bb+ "ids="+temp[i].id+"&";

            }
            console.log(bb);
            $.ajax({
                type: "get",
                url: "/api/materialMerchants/deleteByIds.do"+"?"+ bb,
               /* data: {"ids": bb},*/
                dataType: "json",
                success: function(data) {
                    if(data.status == "1"){
                        alert("删除成功");
                        window.location.href="supplierList.admin";
                    }
                    else{
                        alert("删除失败");
                        window.location.href="supplierList.admin";
                    }
                },
                error: function() {
                    alert("删除失败");
                    window.location.href="supplierList.admin";
                }
            });
        }
    });
    //编辑弹框出现
    $("#btn_edit").click(function(){
       var temp= $("#supplierTable").bootstrapTable('getSelections');
       if(temp.length<=0){
           alert("请至少选中一行");
       }else if(temp.length==1){


           $("#supplierEdit").modal({show: true});
           $("#btn_submit_Edit").click(function () {
               var id = temp[0].id;
               console.log(id);
               var nickName = $("#nickNameEdit").val();
               var address = $("#addressEdit").val();
               var inventory = $("#inventoryEdit").val();
               var percentOfPass = $("#percentOfPassEdit").val();
               var goodsName = $("#goodsNameEdit").val();
               var unitPrice = $("#unitPriceEdit").val();

               $.ajax({
                   type: "get",
                   url: '/api/materialMerchants/update.do',
                   data: {
                       "nickName": nickName,
                       /*"address": address,*/
                       "inventory": inventory,
                       "percentOfPass": percentOfPass,
                       "goodsName": goodsName,
                       "unitPrice": unitPrice
                   },
                   dataType: "json",
                   success: function (data) {
                       if (data.status == 1) {
                           alert("修改成功");
                           $("#supplierEdit").modal({show: false});
                       }
                       else {
                           alert("修改失败");
                           $("#supplierEdit").modal({show: false});
                       }
                   },
                   error: function () {
                       alert("修改失败");
                       $("#supplierEdit").modal({show: false});
                   }
               });

           });
       }else{
           alert('最多只能选择一行');
       }
    });


    //新增弹框出现
    $("#btn_add").click(function (){
       $("#supplierAdd").modal({show:true});
    });
    $("#btn_submit").click(function (){
        var nickName = $("#nickName").val();
        var address = $("#address").val();
        var inventory = $("#inventory").val();
        var percentOfPass = $("#percentOfPass").val();
        var goodsName = $("#goodsName").val();
        var unitPrice = $("#unitPrice").val();

        $.ajax({
            type: "get",
            url: '/api/materialMerchants/add.do',
            data: {
                "nickName": nickName,
                /*"address": address,*/
                "inventory": inventory,
                "percentOfPass": percentOfPass,
                "goodsName": goodsName,
                "unitPrice": unitPrice
            },
            dataType: "json",
            success: function (data) {
                if(data.status==1) {
                    alert("新增成功");
                    $("#supplierAdd").modal({show:false});
                }
                else{
                    alert("新增失败");
                    $("#supplierAdd").modal({show:false});
                }
            },
            error: function () {
                alert("新增失败");
                $("#supplierAdd").modal({show:false});
            }
        });

    });

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#supplierTable').bootstrapTable({
            url: '/api/materialMerchants/get.do',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            onLoadSuccess: function(backDate) {
                $('#supplierTable').bootstrapTable('removeAll');
                console.log(backDate.list);
                this.data=backDate.list;
                $('#supplierTable').bootstrapTable('load',backDate.list);
            },
            data:[],
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '编号',
                visible:false
            }, {
                field: 'nickName',
                title: '名称'
            },{
                field: 'goodsName',
                title: '商品名称',
                visible: true
            }, {
                field: 'unitPrice',
                title: '单价'
            }, {
                field: 'inventory',
                title: '库存量'
            }, {
                field: 'address',
                title: '地址'
            }, {
                field: 'percentOfPass',
                title: '原料合格率'
            } ]
        });
    };
    //查询
    $("#btn_query").click(function (){
        console.log($("#Name").val());
        $.ajax({
            type: 'get',
            url: "/api/materialMerchants/get.do",
            dataType: 'json',
            data: {
                nickName: $("#Name").val()
            },
            success: function(data){

                console.log("serachResult:"+data.list);
               // $('#supplierTable').bootstrapTable('removeAll');

                this.data=data.list;
                $('#supplierTable').bootstrapTable('load',data.list);
            },
        })
    });

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            nickName: $("#Name").val(),  //名称
           /* departmentname: $("#txt_search_departmentname").val(),
            statu: $("#txt_search_statu").val()*/
        };
        console.log("here:"+temp.nickName);
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};