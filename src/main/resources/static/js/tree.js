
getTree();
function getTree() {
    $.ajax({
        type: "get",
        url: "tree.json",
        success: function (data) {
            filterTree(data);
            renderTree(0,filterTree(data));
        },error: function (data) {
            console.log("数据请求失败，错误信息为：" + data);
        }
    })
}
// 处理接口数据
var filterData = [];
function filterTree(data) {
    var temp = [];
    for(var i in data){
        var obj = data[i];
        if(obj.children){
            filterTree(obj.children);
            delete obj.children;
        }
        filterData.push(obj);
    }
    // 数组去重;
    for(var i = 0; i < filterData.length; i++) {
        for(var j = i + 1; j < filterData.length; j++){
            if (filterData[i] === filterData[j]){
                i++;
                j = i;
            }
        }
        temp.push(filterData[i]);
    }
    return temp;
}

var omenu = "";     // 存储数据结构
function renderTree(id,arry) {
    var childArry = getChildArry(id,arry);
    if(childArry.length > 0){
        omenu += '<ul>';
        for( var i in childArry ){
            omenu += '<li data-id="'+childArry[i].id+'">\n'+
                        '<div class="tree-entry">\n'+
                            '<div class="laytree-set">\n'+
                                 '<span class="fa fa-plus-square"></span>\n'+
                                 '<a href="javascript:void(0);">'+childArry[i].title+'</a>\n'+
                            '</div>\n'+
                            '<div class="tree-btngroup">\n'+
                                 '<i class="fa fa-plus"></i>\n'+
                                 '<i class="fa fa-pencil"></i>\n'+
                                 '<i class="fa fa-trash"></i>\n'+
                            '</div>\n'+
                        '</div>';
            renderTree(childArry[i].id,arry);
            omenu += '</li>'
        }
        omenu += '</ul>';
    }
    $('#tree').html(omenu);
    $('#tree ul:eq(0)').addClass('menuTree').prop('id','menuTree');
    $('.tree-entry').siblings('ul').hide();
    filterDom();
}
function getChildArry(id,arry) {
    var newArry = new Array();
    for ( var i in arry ) {
        if (arry[i].pid == id){
            newArry.push(arry[i]);
        }
    }
    return newArry;
}
function filterDom(){
    $('#menuTree li').each(function (idx,item) {
        var $item = $(item).find('ul');
        // 筛选根级目录替换图标
        if($item.length == 0){
            $(item).find('span').removeClass('fa-plus-square').addClass('fa-file-text');
        }
    })
};

$(document).on('click','.tree-btngroup',userAction);
$(document).on('mouseenter','.tree-entry',btnShow);
$(document).on('mouseleave','.tree-entry',btnHide);
$(document).on('click','.laytree-set',showMenu);

function showMenu(ev) {
    var nextUL =  $(this).parents('.tree-entry').siblings();
    nextUL.toggle();
    if(nextUL.css('display') == 'block'){
        $(ev.target).parent().find('span').removeClass( 'fa-plus-square').addClass('fa-minus-square');
    }else{
        $(ev.target).parent().find('span').removeClass( 'fa-minus-square').addClass('fa-plus-square');
    }
}
function btnShow() {
    $(this).find('.tree-btngroup').css({'visibility':'visible'});
}
function btnHide() {
    $(this).find('.tree-btngroup').css({'visibility':'hidden'});
}
function userAction(ev) {
    var oindex = $(ev.target).index();
    switch (oindex){
        case 0:
            inCrease(ev);
            break;
        case 1:
            reName(ev);
            break;
        case 2:
            $(ev.target).parent().parent().parent().remove();
            break;
    }
}
function inCrease(ev) {
    var nextUL =  $(ev.target).parents('.tree-entry');
    var onewLi = $('<li data-id="undefined">' +
                        '<div class="tree-entry">\n' +
                            '<div class="laytree-set">\n' +
                                '<span class="fa fa-file-text"></span>\n' +
                                '<a href="javascript:void(0);">未命名</a>\n' +
                            '</div>\n' +
                            '<div class="tree-btngroup" style="visibility: hidden;">\n' +
                                '<i class="fa fa-plus"></i>\n' +
                                '<i class="fa fa-pencil"></i>\n' +
                                '<i class="fa fa-trash"></i>\n' +
                            '</div>\n' +
                        '</div>' +
                 '</li>');
    nextUL.siblings().append(onewLi);
    if(nextUL.siblings().length == 0){
        var onewUL = $('<ul></ul>');
        onewUL.append(onewLi);
        nextUL.parent().append(onewUL);
        onewUL.hide();
    }
    if(nextUL.siblings().length > 0){
        nextUL.find('span').removeClass('fa-file-text').addClass('fa-plus-square');
    }
}
function reName(ev) {
    var prevDiv = $(ev.target).parent().prev();
    var oinput = $('<input type="text" class="editInput">');
    oinput.val(prevDiv.find('a').text());
    prevDiv.find('a').text('');
    prevDiv.append(oinput);
    prevDiv.find('input').focus();
    $('.editInput').on('blur',leaveInput);
}
// input输入框失去焦点事件监听
function leaveInput(){
    var otext = $(this).val();
    $(this).prev('a').text(otext);
    $(this).remove();
}
// enter键事件监听处理
$(document).on('keydown', function (ev) {
    var ev=ev||window.event;
    if(ev.keyCode == 13){
        $('.editInput').blur();
    }
});
