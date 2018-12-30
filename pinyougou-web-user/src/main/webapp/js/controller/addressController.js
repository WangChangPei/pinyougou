//地址控制器
app.controller('addressController',function($scope,userOrderService){
    // 初始化地址查询
    $scope.findUserAddressList=function () {
        userOrderService.findUserAddressList().success(
            function (response) {    // List<Address>
                $scope.userAddressList=response;
            }
        )
    }

    // 修改默认地址
    $scope.setDefaultAddress=function (id) {
        userOrderService.setDefaultAddress(id).success(
            function (response) {
                if (response.flag){
                    $scope.findUserAddressList();
                }else {
                    alert(response.message);
                }
            }
        )
    }

    // 删除地址
    $scope.deleteAddress=function (id) {
        userOrderService.deleteAddress(id).success(
            function (response) {
                if (response.flag){
                    $scope.findUserAddressList();
                }else {
                    alert(response.message);
                }
            }
        )
    }

    // 编辑地址之回显
    $scope.findAddressById=function (id) {
        userOrderService.findAddressById(id).success(
            function (response) {
                $scope.entity2=response;
            }
        )
    }

    // 编辑地址
    $scope.updateUserAddress=function () {
        userOrderService.updateUserAddress($scope.entity2).success(
            function (response) {
                if(response.flag){
                    $scope.findUserAddressList();
                }else {
                    alert(response.message);
                    // location.href="http://localhost:9104/home-setting-info.html";
                }
            }
        )
    }

    // 新增地址
    $scope.addUserAddress=function () {
        userOrderService.addUserAddress($scope.entity).success(
            function (response) {
                if(response.flag){
                    $scope.findUserAddressList();
                }else {
                    alert(response.message);
                    // location.href="http://localhost:9104/home-setting-info.html";
                }
            }
        )
    }


});