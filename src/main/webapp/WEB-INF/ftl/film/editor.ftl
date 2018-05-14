<div style="display: none;padding: 3% 3%;" id="editorDialog">
<form class="layui-form" action="">
    <input type="hidden" name="filmId"/>
    <div class="layui-form-item">
        <label class="layui-form-label">电影名</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">简介</label>
        <div class="layui-input-block">
            <textarea name="description" placeholder="请输入简介" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上映年代</label>
        <div class="layui-input-block">
            <input type="text" name="releaseYear" id="releaseYear" required readonly lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">票价</label>
        <div class="layui-input-block">
            <input type="text" name="replacementCost" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">评级</label>
        <div class="layui-input-block">
            <select name="rating" lay-verify="required">
                <#if (ratings?? && ratings?size>0 )>
                    <#list ratings as rating>
                        <option value="${rating}">${rating}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">种类</label>
        <div class="layui-input-block">
            <select name="category" lay-verify="required">
                <#if (categories?? && categories?size > 0)>
                    <#list categories as category >
                        <option value="${category.categoryId}">${category.name}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">更新日期</label>
        <div class="layui-input-block">
            <input type="text" name="lastUpdate" id="lastUpdate" required readonly lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="updateFilm">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</div>

<script>
    layui.use(['form','laydate'], function(){
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.$;
        //监听提交
        form.on('submit(updateFilm)', function(data){
            var data = data.field;
            $.ajax({
                url:'/modify'
                ,type:'POST'
                ,data:{
                    filmId: data.filmId
                    , title: data.title
                    , description: data.description
                    , releaseYear: data.releaseYear
                    , rating: data.rating
                    , 'category.categoryId': data.category
                    , replacementCost: data.replacementCost
                    , lastUpdate: data.lastUpdate
                }
                ,dataType:'json'
                ,success:function(data){
                    if(data.code === '0' ){
                        if(data.data === true){
                            layer.msg('更新成功');
                            layer.closeAll();
                            setTimeout(function(){
                                $('.layui-laypage-btn').click();
                            },1000);
                        }else{
                            layer.msg('更新失败');
                        }
                    }
                }
            });
            return false;
        });

        laydate.render({
            elem:'#releaseYear'
            ,type: 'year'
        });
        laydate.render({
            elem:'#lastUpdate'
            ,type: 'datetime'
        });
    });

</script>
