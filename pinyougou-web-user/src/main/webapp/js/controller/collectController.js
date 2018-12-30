//收藏控制器
app.controller('collectController',function($scope,userOrderService) {
    // 收藏列表查询
    $scope.findUserCollectList = function () {
        userOrderService.findUserCollectList().success(
            function (response) {
                $scope.userCollectList = response;
            }
        )
    }
};