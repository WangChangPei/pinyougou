app.service("userOrderService",function($http){

    // 查询用户中心订单列表
    this.findUserOrderList = function () {
        return $http.get("../userOrder/findUserOrderList.do");
    }

    // 分页
    this.findPage = function(page,rows){
        return $http.get("../userOrder/findPage.do?pageNum="+page+"&pageSize="+rows);
    }

    // 查询待付款/待发货/待收货/待评价订单
    this.findAloneOrderList = function (status) {
        return $http.get("../userOrder/findAloneOrderList.do?status=" + status);
    }

    // 保存个人信息
    this.save = function (entity) {
        return $http.post("../userOrder/save.do",entity);
    }

    // 查询收货地址列表
    this.findUserAddressList = function () {
        return $http.get("../userOrder/findUserAddressList.do");
    }

    // 修改默认地址
    this.setDefaultAddress = function (id) {
        return $http.get("../userOrder/setDefaultAddress.do?id="+ id);
    }


    // 删除地址
    this.deleteAddress = function (id) {
        return $http.get("../userOrder/deleteAddress.do?id="+ id);
    }

    // 编辑地址之回显
    this.findAddressById=function (id) {
        return $http.get("../userOrder/findAddressById.do?id="+ id);
    }


    // 编辑收货地址
    this.updateUserAddress = function (entity2) {
        return $http.post("../userOrder/updateUserAddress.do",entity2);
    }

    // 新增收货地址
    this.addUserAddress = function (entity) {
        return $http.post("../userOrder/addUserAddress.do",entity);
    }

    // 查询用户收藏列表
    this.findUserCollectList = function () {
        return $http.get("../userOrder/findUserCollectList.do");
    }
});