//控制层
app.controller('checkSpecificationController', function ($scope, $controller, checkSpecificationService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        checkSpecificationService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        checkSpecificationService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    // 显示状态
    $scope.status = ["未审核", "审核通过", "审核未通过", "关闭"];

    //查询实体
    $scope.findOne = function (id) {
        checkSpecificationService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.specification.id != null) {//如果有ID
            serviceObject = checkSpecificationService.update($scope.entity); //修改
        } else {
            serviceObject = checkSpecificationService.add($scope.entity);//增加
        }
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



    // 删除:
    $scope.dele = function () {
        checkSpecificationService.dele($scope.selectIds).success(
            function (response) {
                // 判断保存是否成功:
                if (response.flag == true) {
                    // 保存成功
                    // alert(response.message);
                    $scope.reloadList();
                    $scope.selectIds = [];
                } else {
                    // 保存失败
                    alert(response.message);
                }
            });
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        checkSpecificationService.search(page, rows, $scope.searchEntity).success(
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

    // 审核的方法:
    $scope.updateStatus = function (status) {
        checkSpecificationService.updateStatus($scope.selectIds, status).success(function (response) {
            if (response.flag) {
                $scope.reloadList();//刷新列表
                $scope.selectIds = [];
            } else {
                alert(response.message);
            }
        });
    }
});	
