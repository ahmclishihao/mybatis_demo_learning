<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>电影管理</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <script type="text/javascript" src="/static/layui/layui.all.js"></script>
    <style>
        .layui-layer-content {
            overflow: visible !important;
        }
    </style>
</head>
<body>
<div class="">
    <div class="layui-row">
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
            <table id="filmTable" lay-filter="filmTableFilter"></table>
        </div>
    </div>
</div>
<#include 'editor.ftl' >
<script type="text/html" id="filmTableTool">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$;
        //第一个实例
        table.render({
            elem: '#filmTable'
            ,width: '100%'
            ,height: 600
            ,url: '/page' //数据接口
            ,method: 'POST'
            ,page: true //开启分页
            ,limit: 15
            ,cols: [[ //表头
                {field: 'filmId', title: 'ID', width:60, sort: true, fixed: 'left'}
                ,{field: 'title', title: '电影名称', width:200}
                ,{field: 'description', title: '简介', width:200, sort: true}
                ,{field: 'releaseYear', title: '上映年代', width:80}
                ,{field: 'replacementCost', title: '票价', width: 80}
                ,{field: 'rating', title: '等级', width: 80, sort: true}
                ,{field: 'lastUpdate', title: '更新日期', width: 160, sort: true}
                ,{field: 'actor', title: '导演', width: 80,templet:'<div>{{d.actor.firstName}}</div>'}
                ,{field: 'category', title: '种类', width: 135, sort: true,templet:'<div>{{d.category.name}}</div>'}
                ,{title: '操作',fixed:'right',align:'center', width: 140,toolbar:'#filmTableTool'}
            ]]
        });

        table.on('tool(filmTableFilter)', function(obj){//注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'edit'){
                layer.open({
                    type:1
                    ,id:'editor'
                    ,area:['auto','auto']
                    ,shadeClose:true
                    ,content: $('#editorDialog')
                    ,success:function(){
                        var data = getFilm(obj.data.filmId);
                        $("[name='filmId']").val(data.filmId);
                        $("[name='title']").val(data.title);
                        $("[name='description']").val(data.description);
                        $("[name='replacementCost']").val(data.replacementCost);
                        $("[name='releaseYear']").val(data.releaseYear);
                        $("[name='lastUpdate']").val(data.lastUpdate);

                        // 评级
                        $("[name='rating'] option").each(function(i,data){
                           if(data.value === obj.data.rating){
                               $(this).prop("selected",true);
                           }
                        });
                        // 种类
                        $("[name='category'] option").each(function(i,data){
                            if(data.innerHTML === obj.data.category.name){
                                $(this).prop("selected",true);
                            }
                        });
                        var form = layui.form;
                        form.render();
                    }
                });
            }
            if(layEvent === 'del'){
                layer.confirm(`确认删除 ${r'${data.title}'}吗？`
                        ,{icon: 3, title:'提示'}
                        ,function(){
                            deleteFilm(data.filmId)
                        });
            }
        });

        function getFilm(id){
            var resultData ;
            $.ajax({
                url:'/info'
                ,type:'POST'
                ,dataType:'json'
                ,data:{id:id}
                ,async:false
                ,success:function(data){
                    resultData = data.data;
                }
            });
            return resultData;
        }

        function deleteFilm(id){
            $.post('/delete',{id:id},function(data){
                if(data.code === '0'){
                    if(data.data === true){
                        layer.msg('删除成功');
                    }else{
                        layer.msg('删除失败');
                    }
                    setTimeout(function () {
                        $(".layui-laypage-btn").click()
                    },1000);
                }
            })
        }

    });
</script>
</body>
</html>