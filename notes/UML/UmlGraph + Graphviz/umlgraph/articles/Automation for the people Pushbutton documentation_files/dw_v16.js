jQuery(document).ready(function(E){E("#ibm-my-menu-dw .ibm-ribbon-pane-dw").scrollable({speed:0,keyboard:false,onSeek:function(I,H){E.each(dwsi.fi,function(L,K){dwsi.fi[L]={}});var J="#dw-mast-top-"+H+"-0";dwsi.fi[J]=dwsi.fi[J]||{};dwsi.fi[J].hScrollDone=true;(H==1)?F(J):focusIbmid()}}).navigator({navi:"#ibm-mast-options-dw",naviItem:"li",activeItem:"child",activeClass:"current"});E("#ibm-common-menu-dw .ibm-ribbon-pane-dw").scrollable({speed:0,keyboard:false,onSeek:function(I,H){E.each(dwsi.fi,function(L,K){dwsi.fi[L]={}
});var J="#dw-mast-nav-"+H+"-0";dwsi.fi[J]=dwsi.fi[J]||{};dwsi.fi[J].hScrollDone=true;F(J)}}).navigator({navi:"#ibm-menu-links-dw",naviItem:"li",activeClass:"current"});E("#dw-page-tools-menu .ibm-ribbon-pane-dw").scrollable({speed:0,keyboard:false,onSeek:function(I,H){E.each(dwsi.fi,function(L,K){dwsi.fi[L]={}});var J="#dw-foot-"+H+"-0";dwsi.fi[J]=dwsi.fi[J]||{};dwsi.fi[J].hScrollDone=true;F(J)}}).navigator({navi:"#ibm-foot-options-dw",naviItem:"li",activeClass:"current"});var F=function(H){if(dwsi.inuse==true){return 
}dwsi.inuse=true;if(dwsi.fi[H].hScrollDone&&dwsi.fi[H].vScrollDone&&dwsi.inuse){E(H).focus();dwsi.fi[H].hScrollDone=false;dwsi.fi[H].vScrollDone=false}dwsi.inuse=false};var D="",C="";mastCkHdr=function(J,I,K,H){C=E(J.currentTarget).parent().attr("id");if(E(H).is(":visible")){E(H).slideUp("600");E(K).removeClass("current")}if(D==C){E(".dw-mast-close").click();J.stopPropagation();J.preventDefault()}else{D=C;E(I).slideDown("600",function(){dwsi.fi["#"+C+"-0"]=dwsi.fi["#"+C+"-0"]||{};dwsi.fi["#"+C+"-0"].vScrollDone=true;
if(C=="dw-mast-top-0"){E("#dw-mast-top-0").addClass("popen");focusIbmid()}else{F("#"+C+"-0")}})}};E("#ibm-mast-options-dw a").click(function(H){mastCkHdr(H,"#ibm-my-menu-dw","#ibm-menu-links-dw li","#ibm-common-menu-dw")});E("#ibm-universal-nav-dw #ibm-menu-links-dw a").click(function(H){mastCkHdr(H,"#ibm-common-menu-dw","#ibm-mast-options-dw li a","#ibm-my-menu-dw")});var G="",B="";E("#ibm-foot-options-dw a").click(function(H){B=E(H.currentTarget).parent().attr("id");if(G==B){E(".dw-footer-close").click();
H.stopPropagation();H.preventDefault()}else{G=E(H.currentTarget).parent().attr("id");E("#dw-page-tools-menu").slideDown("600",function(){dwsi.fi["#"+B+"-0"]=dwsi.fi["#"+B+"-0"]||{};dwsi.fi["#"+B+"-0"].vScrollDone=true;F("#"+B+"-0")})}});E(".dw-mast-close").click(function(){E("#ibm-common-menu-dw,#ibm-my-menu-dw").slideUp("600");E("#dw-mast-top-0").removeClass("popen");E("#ibm-mast-options-dw li a,#ibm-menu-links-dw li").removeClass("current");D=C="";return false});E(".dw-footer-close").click(function(){E("#dw-page-tools-menu").slideUp("600");
E("#ibm-foot-options-dw li").removeClass("current");G=B="";return false});var A=E("#ibm-search-form #q").val();E("#ibm-search-form #q").focus(function(){if(E(this).val()==A){E(this).val("")}E(this).blur(function(){if(E(this).val().trim().length==0){E(this).val(A)}})});E(".ibm-print-link").click(function(){print();return false});E(".ibm-email-link").click(function(H){ibmDynNav.share.clickHandler.call(this,H);displayFormLayer(H,2);return false});E(".dw-footer-col-3-1 .ibm-facebook-link").attr("href","http://www.facebook.com/sharer.php?u="+encodeURIComponent(location.href)+"&t="+encodeURIComponent(document.title));
E(".dw-footer-col-3-1 .ibm-linkedin-link").attr("href","http://www.linkedin.com/shareArticle?mini=true&url="+encodeURIComponent(location.href)+"&title="+encodeURIComponent(document.title));E(".dw-footer-col-3-1 .ibm-twitter-link").attr("href","http://twitter.com/home?status="+encodeURIComponent(location.href)+"%20-%20"+encodeURIComponent(document.title));E(".dw-footer-col-3-2 .ibm-delicious-link").attr("href","http://delicious.com/post?url="+encodeURIComponent(location.href)+"&title="+encodeURIComponent(document.title));
E(".dw-footer-col-3-2 .ibm-digg-link").attr("href","http://digg.com/submit?phase=2&url="+encodeURIComponent(location.href)+"&title="+encodeURIComponent(document.title));E(".dw-footer-col-3-2 .ibm-stumbleupon").attr("href","http://www.stumbleupon.com/submit?url="+encodeURIComponent(location.href)+"&title="+encodeURIComponent(document.title))});