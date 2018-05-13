<div style="display: none;padding: 3% 3%;" id="editorDialog">
<form class="layui-form" action="">
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
        <label class="layui-form-label">种类</label>
        <div class="layui-input-block">
            <select name="category" lay-verify="required">
                <option value=""></option>
                <option value="0">北京</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上映年代</label>
        <div class="layui-input-block">
            <input type="text" name="releaseYear" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</div>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
