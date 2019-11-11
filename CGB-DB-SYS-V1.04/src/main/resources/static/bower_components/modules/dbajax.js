(function(){
	//定义一个函数，函数名为jsFramework
	var jsFramework=function(){}
	//在jsFramework中添加一个函数
	jsFramework.prototype.getJSON=function(url,params,callback){
  	  debugger
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		  if(xhr.readyState==4&&xhr.status==200){
			  var jsonObj=
			  JSON.parse(xhr.responseText);
			  console.log("execute callback")
			  callback(jsonObj);
	      } 
	  }
	  //建立连接
      xhr.open("GET",url+"?"+params,true);//true代表异步请求
      xhr.send();  
    }
	//var obj=new jsFramework();
	//window.$=obj;
	//将对象暴露给外界(window对象为浏览器中的一个全局的BOM对象)
	window.$=new jsFramework();
})()
