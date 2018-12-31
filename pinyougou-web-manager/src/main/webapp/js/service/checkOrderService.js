// 定义服务层:
app.service("checkOrderService",function($http){
	this.findAll = function(){
		return $http.get("../checkOrder/findAll.do");
	}
	
	this.findPage = function(page,rows){
		return $http.get("../checkOrder/findPage.do?pageNum="+page+"&pageSize="+rows);
	}
	
	this.add = function(entity){
		return $http.post("../checkOrder/add.do",entity);
	}
	
	this.update=function(entity){
		return $http.post("../checkOrder/update.do",entity);
	}
	
	this.findOne=function(id){
		return $http.get("../checkOrder/findOne.do?id="+id);
	}
	
	this.dele = function(ids){
		return $http.get("../checkOrder/delete.do?ids="+ids);
	}
	
	this.search = function(page,rows,searchEntity){
		return $http.post("../checkOrder/search.do?pageNum="+page+"&pageSize="+rows,searchEntity);
	}
	
	this.selectOptionList = function(){
		return $http.get("../checkOrder/selectOptionList.do");
	}
});