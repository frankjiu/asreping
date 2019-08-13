
/*
*  <table cellspacing="0" cellpadding="0" border="0">
        <tr>
            <td data-filed="0">
                <div class="table-cell table-cell-0">
                    <div class="lay-checkbox">
                        <i class="fa fa-square-o"></i>
                    </div>
                </div>
            </td>
            <td data-filed="id">
                <div class="table-cell table-cell-1">10001</div>
            </td>
            <td data-filed="username" data-edit="text">
                <div class="table-cell table-cell-2">杜鹃</div>
            </td>
            <td data-filed="email" data-edit="text">
                <div class="table-cell table-cell-4">xianxin@layui.com</div>
            </td>
            <td data-filed="sex" data-edit="text">
                <div class="table-cell table-cell-1">男</div>
            </td>
            <td data-filed="city">
                <div class="table-cell table-cell-2">湖北武汉</div>
            </td>
            <td data-filed="sign">
                <div class="table-cell table-cell-12">君不见，黄河之水天上来，奔流到海不复回。 君不见，高堂明镜悲白发，朝如青丝暮成雪。 人生得意须尽欢，莫使金樽空对月。 天生我材必有用，千金散尽还复来。 烹羊宰牛且为乐，会须一饮三百杯。 岑夫子，丹丘生，将进酒，杯莫停。 与君歌一曲，请君为我倾耳听。(倾耳听 一作：侧耳听) 钟鼓馔玉不足贵，但愿长醉不复醒。(不足贵 一作：何足贵；不复醒 一作：不愿醒/不用醒) 古来圣贤皆寂寞，惟有饮者留其名。(古来 一作：自古；惟 通：唯) 陈王昔时宴平乐，斗酒十千恣欢谑。 主人何为言少钱，径须沽取对君酌。 五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。</div>
            </td>
            <td data-filed="ip">
                <div class="table-cell table-cell-2">192.168.0.8</div>
            </td>
            <td data-filed="joinTime">
                <div class="table-cell table-cell-2">2016-10-14</div>
            </td>
            <td data-filed="operating">
                <div class="table-cell table-cell-3">
                    <a class="laybtn btn-smmash-default">编辑</a>
                    <a class="laybtn btn-smmash-danger">删除</a>
                </div>
            </td>
        </tr>
    </table>
*
* */

// 调用数据表格插件
dataTable({
    "ele": $("#table_container"),
    "dataUrl": $('#table_container').attr('data-url')
});

function dataTable(options) {
    var tableBox = options.ele,
        pageUrl = options.dataUrl,
        prevHeader = options.ele.prev();
    gettableData(tableBox,pageUrl,prevHeader);
}
// 请求接口数据
function gettableData(obj,Url,header) {
    $.ajax({
        type: 'get',
        url: Url,
        success: function (data) {
            var doc = data.list;
            dataRender(obj,doc,header);
        },error: function (data) {
            console.log("页面请求失败，错误信息为：" + data);
        }
    });
}
// 渲染数据
var otableStr = "";   // 存储html数据结构
function dataRender(obj,data,header) {
    var headWid = header.children('table').width();
    otableStr += '<table cellspacing="0" cellpadding="0" border="0">';
    for(var i in data){
        otableStr += '<tr>';
        otableStr += '<td data-filed="0"><div class="table-cell table-cell-0"><div class="lay-checkbox"> <i class="fa fa-square-o"></i></div></div></td>';
        otableStr += '<td data-filed="id"><div class="table-cell table-cell-2">'+data[i].id+'</div></td>';
        otableStr += '<td data-filed="username" data-edit="text"><div class="table-cell table-cell-2">'+data[i].canme+'</div></td>';
        otableStr += '<td data-filed="email" data-edit="text"><div class="table-cell table-cell-4">'+data[i].email+'</div></td>';
        otableStr += '<td data-filed="sex" data-edit="text"><div class="table-cell table-cell-1">'+data[i].sex+'</div></td>';
        otableStr += '<td data-filed="city"><div class="table-cell table-cell-2">'+data[i].cadd+'</div></td>';
        otableStr += '<td data-filed="sign" data-edit="text"><div class="table-cell table-cell-12">'+data[i].ctitle+'</div></td>';
        otableStr += '<td data-filed="ip" data-edit="text"><div class="table-cell table-cell-2">'+data[i].IP+'</div></td>';
        otableStr += '<td data-filed="joinTime" data-edit="text"><div class="table-cell table-cell-2">'+data[i].joinTime+'</div></td>';
        otableStr += '<td data-filed="operating" data-edit="text"><div class="table-cell table-cell-3"> <a class="laybtn btn-smmash-default">'+data[i].edit+'</a><a class="laybtn btn-smmash-danger">'+data[i].del+'</a></div></td>';
    }
    obj.html(otableStr);
    obj.css({'width': headWid + 'px'});
}
