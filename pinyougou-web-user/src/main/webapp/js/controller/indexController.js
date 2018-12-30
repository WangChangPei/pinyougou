//首页控制器
app.controller('indexController',function($scope,loginService,userOrderService){
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
});