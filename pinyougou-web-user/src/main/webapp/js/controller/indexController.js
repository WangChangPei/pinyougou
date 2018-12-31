//首页控制器
app.controller('indexController',function($scope,userOrderService,loginService){
	$scope.showName=function(){
		loginService.showName().success(
			function(response){

				$scope.loginName=response.loginName;
			}
		);
	}

	// 初始化个人中心查询订单
	$scope.findUserOrderList=function () {
		userOrderService.findUserOrderList().success(
			function (response) {    // List<UserOrder>
				$scope.userOrderList=response;

            }
		)
    }

    // 分页
    // 分页查询
    $scope.findPage = function(page,rows){
        // 向后台发送请求获取数据:
        userOrderService.findPage(page,rows).success(function(response){
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        });
    }

    // 查询未支付订单
    $scope.findNotPayOrderList=function () {
        userOrderService.findNotPayOrderList().success(
            function (response) {    // List<UserOrder>
                $scope.userOrderList=response;

            }
        )
    }

    // 立即付款
	$scope.pay=function (orderId) {
		userOrderService.pay(orderId).success(
			function (response) {
				window.open("http://localhost:9103/pay.html#?");
            }
		)
    }
});