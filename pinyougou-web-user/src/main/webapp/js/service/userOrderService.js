app.service("userOrderService",function($http){

    // 查询用户中心订单列表
    this.findUserOrderList = function () {
        return $http.get("../userOrder/findUserOrderList.do");
    }

    //分页
    this.findPage=function(page,rows){
        return $http.get('../userOrder/findPage.do?page='+page+'&rows='+rows);
    }

    // 保存个人信息
    this.save = function (entity) {
        return $http.post("../userOrder/save.do",entity);
    }
});