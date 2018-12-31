//控制层 
app.controller('checkTypeTemplateController' ,function($scope,$controller,brandService ,specificationService  ,checkTypeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承

    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
        checkTypeTemplateService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		checkTypeTemplateService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		checkTypeTemplateService.findOne(id).success(
			function(response){
				$scope.entity= response;	
				// eval()   JSON.parse();   
				$scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
				
				$scope.entity.specIds = JSON.parse($scope.entity.specIds);
				
				$scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=checkTypeTemplateService.update( $scope.entity ); //修改
		}else{
			serviceObject=checkTypeTemplateService.add( $scope.entity  );//增加
		}				
		serviceObject.success(
			function(response){
				if(response.flag){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除
    $scope.dele = function () {
        checkTypeTemplateService.dele($scope.selectIds).success(
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
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		checkTypeTemplateService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	$scope.brandList={data:[]}
	// 查询关联的品牌信息:
	$scope.findBrandList = function(){
		brandService.selectOptionList().success(function(response){
			$scope.brandList = {data:response};//URL 入参:无 ,返回值: 后端 List<Map>
		});
	}
	
	$scope.specList={data:[]}
	// 查询关联的品牌信息:
	$scope.findSpecList = function(){
		specificationService.selectOptionList().success(function(response){
			$scope.specList = {data:response};
		});
	}
	
	//给扩展属性添加行
	$scope.entity={customAttributeItems:[]};
	$scope.addTableRow = function(){
		$scope.entity.customAttributeItems.push({});
	}
	
	$scope.deleteTableRow = function(index){
		$scope.entity.customAttributeItems.splice(index,1);
	}


    // 显示状态
    $scope.status = ["未审核", "审核通过", "审核未通过", "关闭"];

    // 审核的方法:
    $scope.updateStatus = function (status) {
        checkTypeTemplateService.updateStatus($scope.selectIds, status).success(function (response) {
            if (response.flag) {
                $scope.reloadList();//刷新列表
                $scope.selectIds = [];
            } else {
                alert(response.message);
            }
        });
    }

});	
