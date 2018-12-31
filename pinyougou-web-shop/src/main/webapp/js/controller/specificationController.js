//控制层
app.controller('specificationController', function ($scope, $controller, specificationService) {

    $controller('baseController', {$scope: $scope});//继承
    $scope.status = ["未审核", "审核通过", "审核未通过", "关闭"];
    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        specificationService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        specificationService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        specificationService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象

        serviceObject = specificationService.add($scope.entity);//增加

        serviceObject.success(
            function (response) {
                if (response.flag) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        specificationService.dele($scope.selectIds).success(
            function (response) {
                if (response.flag) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }
    /*$scope.dele1 = function (id) {
        specificationService.dele1(id).success(
            function (response) {
                if ( response.flag) {
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }*/

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        specificationService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }
    $scope.searchApply = function (page, rows) {
        specificationService.searchApply(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }



    $scope.addTableRow = function () {
        $scope.entity.specificationOptionList.push({});
    }

    $scope.deleteTableRow = function (index) {
        $scope.entity.specificationOptionList.splice(index, 1);
    }

    //申请  直接发出申请请求
    $scope.apply = function () {
        var serviceObject;//服务层对象
        serviceObject = specificationService.apply($scope.entity);
        serviceObject.success(
            function (response) {
                if (response.flag) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    //请求发送失败
                    alert(response.message);
                }
            }
        );
    }
});	