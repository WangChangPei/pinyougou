//服务层
app.service('checkSpecificationService',function($http){

    //读取列表数据绑定到表单中
    this.findAll=function(){
        return $http.get('../checkSpecification/findAll.do');
    }
    //分页
    this.findPage=function(page,rows){
        return $http.get('../checkSpecification/findPage.do?page='+page+'&rows='+rows);
    }
    //查询实体
    this.findOne=function(id){
        return $http.get('../checkSpecification/findOne.do?id='+id);
    }
    //增加
    this.add=function(entity){
        return  $http.post('../checkSpecification/add.do',entity );
    }
    //修改
    this.update=function(entity){
        return  $http.post('../checkSpecification/update.do',entity );
    }
    //删除
    this.dele = function(ids){
        return $http.get("../checkSpecification/delete.do?ids="+ids);
    }
    //搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('../checkSpecification/search.do?page='+page+"&rows="+rows, searchEntity);
    }

    this.selectOptionList=function(){
        return $http.get("../checkSpecification/selectOptionList.do");
    }

    this.updateStatus = function(ids,status){
        return $http.get('../checkSpecification/updateStatus.do?ids='+ids+"&status="+status);
    }
});
