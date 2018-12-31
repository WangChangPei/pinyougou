// 定义服务层:
app.service("checkBrandService",function($http){
    this.findAll = function(){
        return $http.get("../checkBrand/findAll.do");
    }

    this.findPage = function(page,rows){
        return $http.get("../checkBrand/findPage.do?pageNum="+page+"&pageSize="+rows);
    }

    this.add = function(entity){
        return $http.post("../checkBrand/add.do",entity);
    }

    this.update=function(entity){
        return $http.post("../checkBrand/update.do",entity);
    }

    this.findOne=function(id){
        return $http.get("../checkBrand/findOne.do?id="+id);
    }

    this.dele = function(ids){
        return $http.get("../checkBrand/delete.do?ids="+ids);
    }

    this.search = function(page,rows,searchEntity){
        return $http.post("../checkBrand/search.do?pageNum="+page+"&pageSize="+rows,searchEntity);
    }

    this.selectOptionList = function(){
        return $http.get("../checkBrand/selectOptionList.do");
    }

    this.updateStatus = function(ids,status){
        return $http.get('../checkBrand/updateStatus.do?ids='+ids+"&status="+status);
    }
});