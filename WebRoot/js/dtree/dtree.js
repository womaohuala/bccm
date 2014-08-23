/*--------------------------------------------------|

| dTree 2.05 | www.destroydrop.com/javascript/tree/ |


|---------------------------------------------------|

| Copyright (c) 2002-2003 Geir Landr?              |

|                                                   |

| This script can be used freely as long as all     |

| copyright messages are intact.                    |

|                                                   |

| Updated: 17.04.2003                               |

|--------------------------------------------------*/



// Node object
var  returnData=new Array();

var idSt="";//选中的字符串集合

var strFullPath=window.document.location.href; 
var strPath=window.document.location.pathname; 
var pos=strFullPath.indexOf(strPath); 
var prePath=strFullPath.substring(0,pos); 
var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
var rootPath=prePath + postPath;
function Node(id, pid, name,disable,checked, url, title, target, icon, iconOpen, open,newValue,lastValue,nodeid) {
	this.id = id;

	this.pid = pid;

	this.name = name;
	
    this.newValue=newValue;
	
	this.lastValue=lastValue;
    
	this.url = url;

	this.title = title;

	this.target = target;

	this.icon = icon;

	this.iconOpen = iconOpen;

	this._io = false;

	this._is = false;

	this._ls = false;

	this._hc = false;

	this._ai = 0;

	this._p;
	
	this.disable=disable;
	
	this.checked=checked;
	
	this.nodeid=nodeid;
	
};



// Tree object

function dTree(objName) {
	this.config = {

		target					: null,

		folderLinks			: true,

		useSelection		: true,

		useCookies			: true,

		useLines				: true,

		useIcons				: true,

		useStatusText		: false,

		closeSameLevel	: false,

		inOrder					: false,
		
		useRadio       : false,           //yanal��ӣ������Ƿ�����ӵ�ѡ��ť
		lastRadio       : false,           //yanal��ӣ������Ƿ�����ӵ�ѡ��ť
		
		useCheckbox    : false           //yanal��ӣ������Ƿ�ʹ�ø�ѡ��
	}

	this.icon = {

		root				:  rootPath + '/images/dtree/base.gif',

		folder			:  rootPath + '/images/dtree/folder.gif',

		folderOpen	: rootPath + '/images/dtree/folderopen.gif',

		node				: rootPath + '/images/dtree/page.gif',

		empty				: rootPath + '/images/dtree/empty.gif',

		line				: rootPath + '/images/dtree/line.gif',

		join				: rootPath + '/images/dtree/join.gif',

		joinBottom	: rootPath + '/images/dtree/joinbottom.gif',

		plus				: rootPath + '/images/dtree/plus.gif',

		plusBottom	: rootPath + '/images/dtree/plusbottom.gif',

		minus				: rootPath + '/images/dtree/minus.gif',

		minusBottom	: rootPath + '/images/dtree/minusbottom.gif',

		nlPlus			: rootPath + '/images/dtree/nolines_plus.gif',

		nlMinus			: rootPath + '/images/dtree/nolines_minus.gif'

	};

	this.obj = objName;

	this.aNodes = [];

	this.aIndent = [];

	this.root = new Node(-1);

	this.selectedNode = null;

	this.selectedFound = false;

	this.completed = false;	
};



// Adds a new node to the node array

dTree.prototype.add = function(id, pid, name,disable,checked, url, title, target, icon, iconOpen, open,newValue,lastValue) {

	this.aNodes[this.aNodes.length] = new Node(id, pid, name,disable,checked, url, title, target, icon, iconOpen, open,newValue,lastValue,this.aNodes.length);

};



// Open/close all nodes

dTree.prototype.openAll = function() {

	this.oAll(true);

};

dTree.prototype.closeAll = function() {

	this.oAll(false);

};



// Outputs the tree to the page

dTree.prototype.toString = function() {

	var str = '<div class="dtree">\n';

	if (document.getElementById) {

		if (this.config.useCookies) this.selectedNode = this.getSelected();

		str += this.addNode(this.root);

	} else str += 'Browser not supported.';

	str += '</div>';

	if (!this.selectedFound) this.selectedNode = null;

	this.completed = true;

	return str;

};



// Creates the tree structure

dTree.prototype.addNode = function(pNode) {

	var str = '';

	var n=0;

	if (this.config.inOrder) n = pNode._ai;

	for (n; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == pNode.id) {

			var cn = this.aNodes[n];

			cn._p = pNode;

			cn._ai = n;

			this.setCS(cn);

			if (!cn.target && this.config.target) cn.target = this.config.target;

			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id);

			if (!this.config.folderLinks && cn._hc) cn.url = null;

			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) {

					cn._is = true;

					this.selectedNode = n;

					this.selectedFound = true;

			}

			str += this.node(cn, n);

			if (cn._ls) break;

		}

	}

	return str;

};



// Creates the node icon, url and text

dTree.prototype.node = function(node, nodeId) {

	var str = '<div class="dTreeNode">' + this.indent(node, nodeId);

	if (this.config.useIcons) {

		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);

		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;

		if (this.root.id == node.pid) {

			node.icon = this.icon.root;

			node.iconOpen = this.icon.root;

		}

		str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.icon) + '" alt="" />';
		
		//��ӵ�ѡ��ťradio ,�ڵ���ҳ��ѵ�һ��ڵ�id����Ϊ0���ýڵ㲻��ӵ�ѡ��ť
		// �˴���getRadioSelected��������ʵ���ڵ������ҳ��
		if(this.config.useRadio ){
			str +='<input type="radio"  name="'+this.obj+'_id"  id="r'+  this.obj + node.id + '" onclick="getRadioSelected('+node.id+')" ondblclick="getRadioSelected('+node.id+',1)" value="'+node.id+'"/>';
		    str+= '<input type="hidden"  name="'+this.obj+'_name"  id="c'+  this.obj + nodeId + '"  value="'+node.name+'"/>';
		    str+= '<input type="hidden"  name="'+this.obj+'_new"   value="'+node.newValue+'"/>';
		}
		
		//��Ӹ�ѡ���ڵ���ҳ��ѵ�һ��ڵ�id����Ϊ0���ýڵ㲻��Ӹ�ѡ�򡣴˴��ķ���selectCheckbox�ڱ�JS�ڲ�ʵ��
	    if(this.config.useCheckbox == true ){
	    	str+= '<input type="checkbox"  name="'+
	    	this.obj+'_id"  id="c'+  this.obj + node.id + 
	    	'" onclick="javascript:'+this.obj+'.selectCheckbox('+node.id+')" value="'+node.id+'"';
	    	if(node.checked){
	    		str+='checked=true';
	    	}
	    	if(node.disable){
	    		str+=' disabled';
	    	}
	    	str+='/>';
	    	str+= '<input type="hidden"  name="'+this.obj+'_name"  id="c'+  this.obj + node.id + '"  value="'+node.name+'"/>';
	    	str+= '<input type="hidden"  name="'+this.obj+'_new"   value="'+node.newValue+'"/>';
  		}
		if(node.lastValue){
			if(this.config.lastRadio &&node.lastValue=="0"){
				str +='<input type="radio"  name="'+this.obj+'_id"  id="r'+  this.obj + node.id + '" onclick="getRadioSelected('+node.id+')" ondblclick="getRadioSelected('+node.id+',1)" value="'+node.id+'"/>';
				str+= '<input type="hidden"  name="'+this.obj+'_name"  id="c'+  this.obj + nodeId + '"  value="'+node.name+'"/>';
				str+= '<input type="hidden"  name="'+this.obj+'_new"   value="'+node.newValue+'"/>';
			}
		}else{
			if(this.config.lastRadio &&node.newValue=="0"){
				str +='<input type="radio"  name="'+this.obj+'_id"  id="r'+  this.obj + node.id + '" onclick="getRadioSelected('+node.id+')" ondblclick="getRadioSelected('+node.id+',1)" value="'+node.id+'"/>';
				str+= '<input type="hidden"  name="'+this.obj+'_name"  id="c'+  this.obj + nodeId + '"  value="'+node.name+'"/>';
				str+= '<input type="hidden"  name="'+this.obj+'_new"   value="'+node.newValue+'"/>';
			}
		}
	}

	if (node.url) {

		str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"';

		if (node.title) str += ' title="' + node.title + '"';

		if (node.target) str += ' target="' + node.target + '"';

		if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';

		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))

			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';

		str += '>';

	}

	else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id)

		str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">';

	str += node.name;

	if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>';

	str += '</div>';

	if (node._hc) {

		str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';

		str += this.addNode(node);

		str += '</div>';

	}

	this.aIndent.pop();

	return str;

};



// Adds the empty and line icons

dTree.prototype.indent = function(node, nodeId) {

	var str = '';

	if (this.root.id != node.pid) {

		for (var n=0; n<this.aIndent.length; n++)

			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />';

		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);

		if (node._hc) {

			str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');"><img id="j' + this.obj + nodeId + '" src="';

			if (!this.config.useLines) str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;

			else str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );

			str += '" alt="" /></a>';

		} else str += '<img src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />';

	}

	return str;

};



// Checks if a node has any children and if it is the last sibling

dTree.prototype.setCS = function(node) {

	var lastId;

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id) node._hc = true;

		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id;

	}

	if (lastId==node.id) node._ls = true;

};



// Returns the selected node

dTree.prototype.getSelected = function() {

	var sn = this.getCookie('cs' + this.obj);

	return (sn) ? sn : null;

};



// Highlights the selected node

dTree.prototype.s = function(id) {

	if (!this.config.useSelection) return;

	var cn = this.aNodes[id];

	if (cn._hc && !this.config.folderLinks) return;

	if (this.selectedNode != id) {

		if (this.selectedNode || this.selectedNode==0) {

			eOld = document.getElementById("s" + this.obj + this.selectedNode);
            //update
			try{
				eOld.className = "node";
			}catch(e){}

		}

		eNew = document.getElementById("s" + this.obj + id);

		eNew.className = "nodeSel";

		this.selectedNode = id;

		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);
	}
};



// Toggle Open or close

dTree.prototype.o = function(id) {

	var cn = this.aNodes[id];

	this.nodeStatus(!cn._io, id, cn._ls);

	cn._io = !cn._io;

	if (this.config.closeSameLevel) this.closeLevel(cn);

	if (this.config.useCookies) this.updateCookie();

};



// Open or close all nodes

dTree.prototype.oAll = function(status) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {

			this.nodeStatus(status, n, this.aNodes[n]._ls)

			this.aNodes[n]._io = status;

		}

	}

	if (this.config.useCookies) this.updateCookie();

};



// Opens the tree to a specific node

dTree.prototype.openTo = function(nId, bSelect, bFirst) {

	if (!bFirst) {

		for (var n=0; n<this.aNodes.length; n++) {

			if (this.aNodes[n].id == nId) {

				nId=n;

				break;

			}

		}

	}

	var cn=this.aNodes[nId];

	if (cn.pid==this.root.id || !cn._p) return;

	cn._io = true;

	cn._is = bSelect;

	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);

	if (this.completed && bSelect) this.s(cn._ai);

	else if (bSelect) this._sn=cn._ai;

	this.openTo(cn._p._ai, false, true);

};



// Closes all nodes on the same level as certain node

dTree.prototype.closeLevel = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {

			this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);

		}

	}

}



// Closes all children of a node

dTree.prototype.closeAllChildren = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {

			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);		

		}

	}

}



// Change the status of a node(open or closed)

dTree.prototype.nodeStatus = function(status, id, bottom) {

	eDiv	= document.getElementById('d' + this.obj + id);

	eJoin	= document.getElementById('j' + this.obj + id);

	if (this.config.useIcons) {

		eIcon	= document.getElementById('i' + this.obj + id);

		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;

	}

	eJoin.src = (this.config.useLines)?

	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):

	((status)?this.icon.nlMinus:this.icon.nlPlus);

	eDiv.style.display = (status) ? 'block': 'none';

};





// [Cookie] Clears a cookie

dTree.prototype.clearCookie = function() {

	var now = new Date();

	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);

	this.setCookie('co'+this.obj, 'cookieValue', yesterday);

	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);

};



// [Cookie] Sets value in a cookie

dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {

	document.cookie =

		escape(cookieName) + '=' + escape(cookieValue)

		+ (expires ? '; expires=' + expires.toGMTString() : '')

		+ (path ? '; path=' + path : '')

		+ (domain ? '; domain=' + domain : '')

		+ (secure ? '; secure' : '');

};



// [Cookie] Gets a value from a cookie

dTree.prototype.getCookie = function(cookieName) {

	var cookieValue = '';

	var posName = document.cookie.indexOf(escape(cookieName) + '=');

	if (posName != -1) {

		var posValue = posName + (escape(cookieName) + '=').length;

		var endPos = document.cookie.indexOf(';', posValue);

		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));

		else cookieValue = unescape(document.cookie.substring(posValue));

	}

	return (cookieValue);

};



// [Cookie] Returns ids of open nodes as a string

dTree.prototype.updateCookie = function() {

	var str = '';

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {

			if (str) str += '.';

			str += this.aNodes[n].id;

		}

	}

	this.setCookie('co' + this.obj, str);

};



// [Cookie] Checks if a node id is in a cookie

dTree.prototype.isOpen = function(id) {

	var aOpen = this.getCookie('co' + this.obj).split('.');

	for (var n=0; n<aOpen.length; n++)

		if (aOpen[n] == id) return true;

	return false;

};
dTree.prototype.getAllIds=function(){
	var ids="";
	var names="";
	//var newValues="";
	for(i=0;i<document.getElementsByName(this.obj+"_id").length;i++){
			if(document.getElementsByName(this.obj+"_id")[i].checked==true&&document.getElementsByName(this.obj+"_id")[i].value!="0"){
					ids+=document.getElementsByName(this.obj+"_id")[i].value+",";
					names+= document.getElementsByName(this.obj+"_name")[i].value+",";
					//newValues=document.all[document.getElementsByName(this.obj+"_name")[i].sourceIndex+1].value+",";
			}
	}
	returnData[0]=ids.substring(0,ids.length-1);
	returnData[1]=names.substring(0,names.length-1);
	//returnData[2]=newValues.substring(0,newValues.length-1);
	return returnData;
};
/*  selected str id*/
dTree.prototype.setAllIds=function(str){
    if(!str) return;
	str=","+str+",";
	for(i=0;i<document.getElementsByName(this.obj+"_id").length;i++){
			if(str.indexOf(","+document.getElementsByName(this.obj+"_id")[i].value+",")!=-1){
				document.getElementsByName(this.obj+"_id")[i].checked=true;
			}
	}
};
/* un selected str id*/
dTree.prototype.setAllIdsNo=function(str){
    if(!str) return;
	str=","+str+",";
	for(i=0;i<document.getElementsByName(this.obj+"_id").length;i++){
			if(str.indexOf(","+document.getElementsByName(this.obj+"_id")[i].value+",")!=-1){
				document.getElementsByName(this.obj+"_id")[i].checked=false;
			}
	}
};
dTree.prototype.checkAllIds=function(){
	for(i=0;i<document.getElementsByName(this.obj+"_id").length;i++){
		document.getElementsByName(this.obj+"_id")[i].checked=true;
	}
};
dTree.prototype.clearAllIds=function(flag){
	for(i=0;i<document.getElementsByName(this.obj+"_id").length;i++){
		document.getElementsByName(this.obj+"_id")[i].checked=false;
	}
};
// If Push and pop is not implemented by the browser

if (!Array.prototype.push) {

	Array.prototype.push = function array_push() {

		for(var i=0;i<arguments.length;i++)

			this[this.length]=arguments[i];

		return this.length;

	}

};

if (!Array.prototype.pop) {

	Array.prototype.pop = function array_pop() {

		lastElement = this[this.length-1];

		this.length = Math.max(this.length-1,0);

		return lastElement;

	}

};

//点击选中自身即子节点
dTree.prototype.selectCheckboxMe=function(nodeId){

	document.getElementById("c"+this.obj+nodeId).checked=true;
}
var isClear=false;
dTree.prototype.selectCheckbox=function(nodeId){

	//将选中值写入idSt
	  var cs = document.getElementById("c"+this.obj+nodeId).checked;
	  var value = document.getElementById("c"+this.obj+nodeId).value;
	  if(cs&&nodeId!="0"){
	    	if(idSt==""){
	    		idSt+=",";
	    	}
	    	
	    	if(idSt.indexOf(","+value+",")<0){
	    		idSt+=value+",";
	    	}
	    }else{
	    	if(idSt.indexOf(","+value+",")>-1){
	    		idSt=idSt.replace(","+value+",", ",");
	    	}
	  }
	  
	  var len =this.aNodes.length;
	  for (var n=0; n<len; n++) {
	   if ((this.aNodes[n].pid == nodeId)) {
		    document.getElementById("c"+this.obj+this.aNodes[n].id).checked=cs;
		    this.selectCheckbox(this.aNodes[n].id); 
	    }
	  }
	  
	  var node;
	  for (var m=0; m<len; m++) {
		   if ((this.aNodes[m].id == nodeId)) {
			    node=this.aNodes[m];
		    }
		  }
	  
	  //判断子节点是否都有选中，控制父节点是否选中
	  if(node.pid!=-1){
		  var value = document.getElementById("c"+this.obj+node.pid).value;
		  if(cs){
			  document.getElementById("c"+this.obj+node.pid).checked=true;
			  if(idSt==""){
		    		idSt+=",";
		    	}
		    	
		    	if(idSt.indexOf(","+value+",")<0){
		    		idSt+=value+",";
		    	}
		  }else{
			  isClear=false;
			  if(!this.checkParentNodeCheck(node.pid)){
				  document.getElementById("c"+this.obj+node.pid).checked=false;
				  if(idSt.indexOf(","+value+",")>-1){
			    		idSt=idSt.replace(","+value+",", ",");
			    	}
			  }
		  }
	  }
}

//判断子节点是否都选中
dTree.prototype.checkParentNodeCheck=function(nodeId){
		  var len =this.aNodes.length;
		  for (var n=0; n<len; n++) {
		   if ( (this.aNodes[n].pid == nodeId)) {
			   isClear=document.getElementById("c"+this.obj+this.aNodes[n].id).checked;
			    if(isClear)return true;
			    this.checkParentNodeCheck(this.aNodes[n].id); 
		    }
		  }
	}

	 dTree.prototype.selectCheckbox2=function(nodeId){

	  var cs = document.getElementById("c"+this.obj+nodeId).checked;
	  var node = this.aNodes[nodeId];
	  var len2 =this.aNodes.length;
	  for (n2=0; n2<len2; n2++) {
	   if ((n2 != nodeId) && (this.aNodes[n2].pid == node.id)) {
	    document.getElementById("c"+this.obj+n2).checked=cs;

	    this.selectCheckbox3(n2);  
	   }
	  }
	 }
	 dTree.prototype.selectCheckbox3=function(nodeId){

	  var cs = document.getElementById("c"+this.obj+nodeId).checked;
	  var node = this.aNodes[nodeId];
	  var len3 =this.aNodes.length;
	  for (n3=0; n3<len3; n3++) {
	   if ((n3 != nodeId) && (this.aNodes[n3].pid == node.id)) {
	    document.getElementById("c"+this.obj+n3).checked=cs;

	    this.selectCheckbox4(n3);  
	   }
	  }
	 }
	 dTree.prototype.selectCheckbox4=function(nodeId){

	  var cs = document.getElementById("c"+this.obj+nodeId).checked;
	  var node = this.aNodes[nodeId];
	  var len4 =this.aNodes.length;
	  for (n4=0; n4<len4; n4++) {
	   if ((n4 != nodeId) && (this.aNodes[n4].pid == node.id)) {
	    document.getElementById("c"+this.obj+n4).checked=cs;

	    this.selectCheckbox5(n);  
	   }
	  }
	 }
	 dTree.prototype.selectCheckbox5=function(nodeId){

	  var cs = document.getElementById("c"+this.obj+nodeId).checked;
	  var node = this.aNodes[nodeId];
	  var len5 =this.aNodes.length;
	  for (n5=0; n5<len5; n5++) {
	   if ((n5 != nodeId) && (this.aNodes[n5].pid == node.id)) {
	    document.getElementById("c"+this.obj+n5).checked=cs;

	   // this.selectCheckbox5(n);  
	   }
	  }
	 }
