tag = 0;
startList = function(rootObj) {
var navRoot,i=0;
if(rootObj == '' || rootObj == null)rootObj=nav;
try{
navRoot = rootObj;
if(navRoot==null){alert('null a wrong accoured');}
else
{
for (i=0; i<navRoot.childNodes.length; i++) {
node = navRoot.childNodes[i];
if (node.nodeName=="LI") {
node.onmouseover=function()
{
this.className ="over";
info.value =this.innerHTML + ' \n';
evalinfo.innerHTML = this.innerHTML;
}
node.onmouseout=function()
{
this.className="out";
info.value = this.innerHTML +' \n';
evalinfo.innerHTML = this.innerHTML;
        }
if(tag==1){
node.className = "out";
}
//info.value +='node['+i+']'+ node.nodeName +'\n';
startList(node);
}
else if (node.nodeName=="UL")
{  
tag =1;
startList(node);
}
}//for end
}
}//try end
catch(e){
alert("error by catch throw");
}//catch end
}//</startList>
window.onload = startList;