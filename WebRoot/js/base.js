
function newIframe(src,title){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: title,
    move: 'false',
    maxmin: true,
    iframe: {src : src},
    area: ['800px' , '240px']
  });
 }

 /*
 function  processJson(data){
	 parent.layer.msg(data.resultInfo);
	 parent.closeWindow();	    
 }
 */
 function  processJson(data){
	    //1修改成功
	    if(data.result){
	    	parent.layer.alert(data.resultInfo, 1, function(){
		    	 parent.location.reload(); //自动关闭后可做一些刷新页面等操作
			     parent.layer.closeAll()
		    })
		}else{
			parent.layer.msg(data.resultInfo,1);
		}
 }
 
 function processError(){
   parent.layer.msg('提交失败');
 }
 
 function del(src){
	 $.ajax({ //一个Ajax过程 
		 type: "post", //以post方式与后台沟通 
		 url : src, //与此php页面沟通 
		 dataType:'json',//从php返回的值以 JSON方式 解释 
		 success: function(data){//如果调用php成功 
		 	if(data.result){
		    	layer.alert(data.resultInfo, 1, function(){
			    	 location.reload(); //自动关闭后可做一些刷新页面等操作
				     layer.closeAll()
			    })
			}else{
				parent.layer.msg(data.resultInfo,1);
			}
			} 
		 }); 
 }
