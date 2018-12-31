//服务层
app.service('checkItemCatService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../checkItemCat/findAll.do');
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../checkItemCat/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../checkItemCat/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../checkItemCat/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../checkItemCat/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../checkItemCat/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../checkItemCat/search.do?page='+page+"&rows="+rows, searchEntity);
	}    	
	
	this.findByParentId = function(parentId){
		return $http.get("../checkItemCat/findByParentId.do?parentId="+parentId);
	}
    this.updateStatus = function(ids,status){
        return $http.get('../checkItemCat/updateStatus.do?ids='+ids+"&status="+status);
    }
});
