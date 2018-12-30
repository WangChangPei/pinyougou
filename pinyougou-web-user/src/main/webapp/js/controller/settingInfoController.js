// 控制层
app.controller('settingInfoController',function ($scope,userOrderService) {

    // $controller('baseController',{$scope:$scope});

    // $scope.entity = {username:"",nickName:"",sex:"",birthday:"",address:"",job:""};
    // 保存个人信息
    $scope.save=function () {
        userOrderService.save($scope.entity).success(
            function (response) {
                if(response.flag){
                    alert("添加成功!")
                }else {
                    alert("添加失败!");
                    // location.href="http://localhost:9104/home-setting-info.html";
                }
            }
        )
    }
});