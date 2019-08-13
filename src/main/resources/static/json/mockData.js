
// mockjs模拟生成数据
var Mock = require('mockjs');
var data = Mock.mock({
    "list|100":[{
        "id|+1" : /^[a-z0-9]{8,16}$/,
        "canme": "@cname()",
        "email": "@email()",
        "sex": /^(男|女)$/ ,
        "cadd": "@province" + "@city" + "@county",
        "ctitle": "@ctitle(60)",
        "IP": /^19\d{1}\.1[1-9]{2}\.1\.[1-9]{2}$/,
        "joinTime": "@date('yyyy-mm-dd')",
        "edit": "编辑",
        "del": "删除"
    }]
});

var mdata = Mock.mock(JSON.stringify(data, null,100));
console.log(mdata);