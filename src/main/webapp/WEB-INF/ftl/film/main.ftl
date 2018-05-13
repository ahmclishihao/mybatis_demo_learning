<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>电影管理</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
</head>
<body>
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
            <table id="demo" lay-filter="test"></table>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/layui/layui.all.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            ,width: '100%'
            ,height: 600
            ,url: '/page' //数据接口
            ,method: 'POST'
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'filmId', title: 'ID', width:60, sort: true, fixed: 'left'}
                ,{field: 'title', title: '电影名称', width:200}
                ,{field: 'description', title: '简介', width:200, sort: true}
                ,{field: 'releaseYear', title: '上映年代', width:80}
                ,{field: 'replacementCost', title: '替换费用', width: 80}
                ,{field: 'rating', title: '等级', width: 80, sort: true}
                ,{field: 'lastUpdate', title: '更新日期', width: 160, sort: true}
                ,{field: 'actor', title: '导演', width: 80,templet:'<div>{{d.actor.firstName}}</div>'}
                ,{field: 'category', title: '种类', width: 135, sort: true,templet:'<div>{{d.category.name}}</div>'}
            ]]
            ,response:{
                statusName: 'code' //数据状态的字段名称，默认：code
                ,statusCode: '000' //成功的状态码，默认：0
                ,msgName: 'message' //状态信息的字段名称，默认：msg
                ,countName: 'total' //数据总数的字段名称，默认：count
                ,dataName: 'data' //数据列表的字段名称，默认：data
            }
        });

    });
</script>
</body>
</html>