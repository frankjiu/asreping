
/*
 注释： 调用时传入参数custScrollbar(father，child，speed),前两个必传。father:父级对象；child.子级对象。
 */

// addEventListener兼容IE
function addEvent(ele,eType,callback,capture) {
    // mouseWheel兼容火狐
    if(eType === 'mousewheel' && document.createElement('div').onmousewheel === undefined){
        eType = 'DOMMouseScroll';
    }
    if(ele.addEventListener){
        ele.addEventListener(eType,callback,capture);
    }else{
        // 处理IE的this
        var codeCall = function () {
            callback.call(ele);
        }
        ele.attachEvent("on"+ eType,codeCall);
    }
}
// removeEventListener兼容IE
function removeEvent(ele,eType,callback,capture) {
    ele.removeEventListener ? ele.removeEventListener(eType,callback,capture) :  ele.detachEvent("on" + eType,callback);
}

function custScrollbar(wSelector,cSelector,speed) {
    var owrapper = document.getElementById(wSelector),
        ocontent = document.getElementById(cSelector),
        ospeed = speed || 100;
    var fatHeight = owrapper.offsetHeight;
    var chiHeight = ocontent.offsetHeight;
    var scroll = document.createElement('div');
    var bar = document.createElement('span');
        scroll.id = 'scrollTools';
        bar.id = 'bar';
    scroll.appendChild(bar);
    owrapper.appendChild(scroll);
    if(chiHeight > fatHeight){
        scroll.style.display = 'block';
        // 鼠标滚轮事件监听
        addEvent(ocontent,'mousewheel',mouseWheel);
    }else{
        scroll.style.display = 'none';
    }
    bar.style.height = fatHeight * fatHeight / chiHeight + 'px';
    var barMax = fatHeight - bar.offsetHeight;
    var oconMax = chiHeight - fatHeight;
    var scrollStep = ospeed * barMax / oconMax;
    // 鼠标经过容器事件监听
    addEvent(owrapper,'mouseenter',mouseOver);
    // 鼠标离开容器事件监听
    addEvent(owrapper,'mouseleave',mouseLeave);
    // 鼠标按下事件监听
    addEvent(bar,'mousedown',mouseDown);
    // 鼠标抬起事件监听
    addEvent(document,'mouseup',mouseUp);

    function mouseOver() {
        scroll.style.opacity = 1;
        scroll.style.filter = 'alpha(opacity = 100)';
        bar.style.opacity = 1;
        bar.style.filter = 'alpha(opacity = 100)';
    }
    function mouseLeave() {
        scroll.style.opacity = .3;
        scroll.style.filter = 'alpha(opacity = 30)';
        bar.style.opacity = .3;
        bar.style.filter = 'alpha(opacity = 30)';
    }

    var downY,downTop;
    function mouseDown(ev) {
         ev = ev || window.event;
         downY = ev.clientY;
         downTop = bar.offsetTop;
         addEvent(document,'mousemove',mouseMove);
    }
    function mouseMove(ev) {
        ev = ev || window.event;
        var disY = ev.clientY - downY;
        var bTop = downTop + disY;
        bTop = Math.min(bTop,barMax);
        bTop = Math.max(bTop,0);
        bar.style.top = bTop + 'px';
        ocontent.style.top = -bTop * oconMax / barMax + 'px';
    }
    function mouseUp() {
        removeEvent(bar,'mousemove',mouseDown);
        removeEvent(document,'mousemove',mouseMove);
    }
    function mouseWheel(ev) {
        ev = ev || window.event;
        var dir;
        if(ev.wheelDelta){
            dir = ev.wheelDelta / 120;
        }else{
            dir = -ev.detail / 3;
        }
        changeTop(bar.offsetTop - dir * scrollStep);
    }
    function changeTop(bTop) {
        bTop = Math.min(bTop,barMax);
        bTop = Math.max(bTop,0);
        bar.style.top = bTop + 'px';
        ocontent.style.top = -bTop * oconMax / barMax + 'px';
    }

    // IE8及以下浏览器兼容
    if(!-[1,]){
        bar.style.opacity = .3;
        bar.style.filter = 'alpha(opacity = 30)';
    }
}


