(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-63f297e7"],{"0811":function(t,e,a){},"19f9":function(t,e,a){},"353b":function(t,e,a){},"39c4":function(t,e,a){"use strict";a.d(e,"a",(function(){return s})),a.d(e,"c",(function(){return r})),a.d(e,"b",(function(){return n}));var o=a("1bab");function s(t){return Object(o["a"])({method:"PATCH",url:"/IssureFormController/updateFormIssure",data:t})}function r(){return Object(o["a"])({url:"/data/template.json"})}function n(){return Object(o["a"])({url:"/data/temlateList.json"})}},"3dcf":function(t,e,a){"use strict";var o=a("6158"),s=a.n(o);s.a},"438b":function(t,e,a){},"43c4f":function(t,e,a){"use strict";var o=a("bdee"),s=a.n(o);s.a},"4de4":function(t,e,a){"use strict";var o=a("23e7"),s=a("b727").filter,r=a("1dde"),n=a("ae40"),i=r("filter"),l=n("filter");o({target:"Array",proto:!0,forced:!i||!l},{filter:function(t){return s(this,t,arguments.length>1?arguments[1]:void 0)}})},6108:function(t,e,a){"use strict";var o=a("438b"),s=a.n(o);s.a},6158:function(t,e,a){},"691c":function(t,e,a){"use strict";var o=a("e9cd"),s=a.n(o);s.a},"7bfb":function(t,e,a){},"7ea0":function(t,e,a){"use strict";var o=a("7bfb"),s=a.n(o);s.a},a15b:function(t,e,a){"use strict";var o=a("23e7"),s=a("44ad"),r=a("fc6a"),n=a("a640"),i=[].join,l=s!=Object,c=n("join",",");o({target:"Array",proto:!0,forced:l||!c},{join:function(t){return i.call(r(this),void 0===t?",":t)}})},b2a7:function(t,e,a){"use strict";var o=a("19f9"),s=a.n(o);s.a},b2e2:function(t,e,a){"use strict";var o=a("0811"),s=a.n(o);s.a},b727:function(t,e,a){var o=a("0366"),s=a("44ad"),r=a("7b0b"),n=a("50c4"),i=a("65f0"),l=[].push,c=function(t){var e=1==t,a=2==t,c=3==t,m=4==t,u=6==t,f=5==t||u;return function(d,p,h,b){for(var v,g,w=r(d),y=s(w),_=o(p,h,3),C=n(y.length),I=0,x=b||i,k=e?x(d,C):a?x(d,0):void 0;C>I;I++)if((f||I in y)&&(v=y[I],g=_(v,I,w),t))if(e)k[I]=g;else if(g)switch(t){case 3:return!0;case 5:return v;case 6:return I;case 2:l.call(k,v)}else if(m)return!1;return u?-1:c||m?m:k}};t.exports={forEach:c(0),map:c(1),filter:c(2),some:c(3),every:c(4),find:c(5),findIndex:c(6)}},bdee:function(t,e,a){},d46c:function(t,e,a){"use strict";var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("main-bar",{attrs:{title:t.viewTitle},on:{compName:t.formShowChange}}),""==t.formData?a("div",{staticClass:"myform-content"},[t._v("暂无数据")]):a("div",{staticClass:"myform-content"},[a("layout-thumbnail",{directives:[{name:"show",rawName:"v-show",value:"LayoutThumbnail"===t.formDisplayType,expression:"formDisplayType === 'LayoutThumbnail'"}],staticClass:"myform-content",attrs:{formData:t.formData}}),a("layout-list",{directives:[{name:"show",rawName:"v-show",value:"LayoutList"===t.formDisplayType,expression:"formDisplayType === 'LayoutList'"}],attrs:{formData:t.formData}})],1)],1)},s=[],r=a("e59f"),n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"layout-thumbnail"},t._l(t.formData,(function(t,e){return a("thumbnail-item",{key:e,staticClass:"form-item",attrs:{formData:t}})})),1)},i=[],l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"thumbnail-item",on:{mousemove:function(e){return t.formHover(!0)},mouseleave:function(e){return t.formHover(!1)}}},[a("div",{staticClass:"ti-content"},[a("div",{staticClass:"ti-CollectionIcon"},[1===t.formData.formIsFavour?a("icon",{attrs:{icon:"gd-favorites"}}):t._e()],1),a("div",{staticClass:"ti-formHeader"},[a("div",{staticClass:"ti-title"},[t._v(t._s(t.formData.formTitle))]),t.formType?a("div",{staticClass:"ti-formType",style:t.formType.style},[t._v(" "+t._s(t.formType.name)+" ")]):t._e()]),a("div",{staticClass:"ti-formOpen",style:t.formOpen.style},[t._v(" "+t._s(t.formOpen.name)+" ")]),a("div",{staticClass:"ti-formIcon"},[a("icon",{attrs:{icon:"gd-form"}})],1)]),a("div",{staticClass:"ti-footer"},[a("div",{staticClass:"ti__count-data"},[a("span",{staticClass:"ti__count text-exceeds"},[t._v(t._s(t.formData.formDataCount))]),a("span",{staticClass:"ti__text"},[t._v("数据")])])]),a("div",{directives:[{name:"show",rawName:"v-show",value:t.isActive,expression:"isActive"}],staticClass:"ti-footer__focus"},[a("div",{staticClass:"ti-footer__operations"},t._l(t.operations,(function(e){return a("div",{key:e.iconClass,on:{click:function(a){return t.operationClick(e)}}},[a("icon",{attrs:{icon:e.iconClass}}),a("span",[t._v(t._s(e.text))])],1)})),0),a("div",{staticClass:"ti-more"},[a("form-operation",{attrs:{formItem:t.formData}})],1)])])},c=[],m=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{style:{fontSize:t.size},on:{click:function(e){t.drawer=!0}}},[t._t("content",[a("icon",{attrs:{icon:"gd-more"}})])],2),a("el-drawer",{ref:"eldrawer",attrs:{size:"20%","append-to-body":"","with-header":!1,visible:t.drawer},on:{"update:visible":function(e){t.drawer=e}}},[t._t("drawer-content",[a("operations",{attrs:{formItem:t.formItem,drawer:t.ObjDrawer},on:{"templates-dialog":t.templatesDialogShow}})])],2),a(t.comp,{tag:"component",attrs:{show:t.show,form:t.formItem}})],1)},u=[],f=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"operation-detail"},[a("div",{staticClass:"operation-detail__content"},[a("h3",{staticClass:"od__form-name"},[t._v(t._s(t.formItem.formName))]),t._l(t.showData,(function(e,o){return a("ul",{key:o,staticClass:"od-list"},t._l(e,(function(e){return a("li",{key:e.text,staticClass:"od-list__item",on:{click:function(a){return t.pathClick(e)}}},[a("icon",{attrs:{icon:e.iconClass}}),a("span",[t._v(t._s(e.text))])],1)})),0)}))],2)])},d=[],p=a("ef1b"),h={name:"Operations",data:function(){return{showData:[[{iconClass:"gd-bianji",text:"编辑",path:"/formFill/maincontent/"},{iconClass:"gd-shezhi",text:"设置",path:"/formFill/summarypage/"},{iconClass:"gd-fabu",text:"发布"},{iconClass:"gd-shuju",text:"数据查看",path:"formFill/formdata"},{iconClass:"gd-muban",text:"发表为模板"}],[{iconClass:"gd-fuzhi",text:"复制"},{iconClass:"gd-shoucang",text:"收藏"},{iconClass:"gd-bianji",text:"重命名"},{iconClass:"gd-shanchu",text:"删除"}],[{iconClass:"gd-biaoqian",text:"设置标签"},{iconClass:"gd-tubiao",text:"设置文件图标"}]]}},props:{drawer:Object,formItem:Object},watch:{formItem:function(t){0!=t.formIsFavour?this.showData[1][1].text="取消收藏":this.showData[1][1].text="收藏"}},methods:{pathClick:function(t){var e=this,a=t.iconClass;if(null!=t.path||void 0!=t.path)this.$router.push(t.path+"/"+this.formItem.formId);else switch(a){case"gd-muban":this.$emit("templates-dialog"),this.drawer.eldrawer.hide();break;case"gd-fuzhi":this.drawer.eldrawer.hide(),Object(p["a"])(this.formItem.formId).then((function(t){console.log("复制表单",t),e.viewType()})),this.message("复制表单成功");break;case"gd-shoucang":Object(p["h"])(this.formItem.formId).then((function(t){t.code,e.message(t.msg),e.drawer.eldrawer.hide(),e.viewType()}));break;case"gd-bianji":this.drawer.eldrawer.hide(),this.$prompt("<p class='msg'>表单重命名成功，系统内各处将显示重命名标题。此操作不影响公开表单名称</p><p class='content'>新表单名：</p>","重命名",{customClass:"message-box-rename",confirmButtonText:"确定",dangerouslyUseHTMLString:!0,inputValue:this.formItem.formTitle}).then((function(t){var a=t.value;""==a?e.message("表单名不能为空","error"):Object(p["g"])(e.formItem.formId,a).then((function(t){24===t.code?(e.message(t.msg),e.formItem.formTitle=a):e.message(t.msg,"warning")}))}));break;case"gd-shanchu":Object(p["b"])(this.formItem.formId).then((function(t){e.drawer.eldrawer.hide(),e.message(t.msg),e.viewType()}));break;case"gd-fabu":this.drawer.eldrawer.hide(),0==this.formItem.formOpen?Object(p["c"])(this.formItem.formId).then((function(){e.formItem.formOpen=1,e.message("发布表单成功！")})):this.message("表单已经发布","warning"),this.$router.push("/formFill/summarypage/"+this.formItem.formId);break}},message:function(t,e){this.$message({showClose:!0,type:e||"success",message:t,duration:2e3})},viewType:function(){var t=this.$route.meta.index;1===t?this.$store.dispatch("form/saveMyform"):this.$store.dispatch("form/saveFavorites")}},created:function(){0!=this.formItem.formIsFavour&&(this.showData[1][1].text="取消收藏")}},b=h,v=(a("b2e2"),a("2877")),g=Object(v["a"])(b,f,d,!1,null,null,null),w=g.exports,y=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{"custom-class":"tpublish-dialog",title:"发表为模板",visible:t.dialogVisible,width:"50%","append-to-body":!0},on:{"update:visible":function(e){t.dialogVisible=e}}},[a("div",{staticClass:"template-publish__body"},[a("el-form",{attrs:{"label-position":t.formType.labelPosition},model:{value:t.template,callback:function(e){t.template=e},expression:"template"}},[a("el-form-item",{attrs:{label:"模板标题"}},[a("el-input",{attrs:{minlength:"4",maxlength:"10","show-word-limit":""},model:{value:t.template.formIssureName,callback:function(e){t.$set(t.template,"formIssureName",e)},expression:"template.formIssureName"}})],1),a("el-form-item",[a("span",{attrs:{slot:"label"},slot:"label"},[a("strong",[t._v("模板标签")]),a("br"),a("i",{staticClass:"templateSort-tip"},[t._v("选择恰当的标签获得更多推荐哦")])]),a("div",{staticClass:"templateSort-selects"},t._l(t.templteSorts,(function(e,o){return a("el-select",{key:e.id,attrs:{placeholder:e.tips},model:{value:t.template.formIssureTag[o],callback:function(e){t.$set(t.template.formIssureTag,o,e)},expression:"template.formIssureTag[index]"}},t._l(e.values,(function(t){return a("el-option",{key:t,attrs:{value:t}})})),1)})),1)]),a("el-form-item",{attrs:{label:"模板介绍(选填)"}},[a("el-input",{attrs:{type:"textarea",placeholder:"简单介绍你的模板用途或特定,更容易通过审核和被用户关注哦~",maxlength:"500","show-word-limit":"",rows:7},model:{value:t.template.formIssureDesc,callback:function(e){t.$set(t.template,"formIssureDesc",e)},expression:"template.formIssureDesc"}})],1),a("el-form-item",[a("el-checkbox",{model:{value:t.agreementChecked,callback:function(e){t.agreementChecked=e},expression:"agreementChecked"}},[a("span",{staticClass:"user-agreement"},[t._v("选中即表示您同意我们的用户协议"),a("i",[t._v("《用户协议》")])])])],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",disabled:!t.agreementChecked},on:{click:t.templateSubmit}},[t._v("提交")]),a("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取消")])],1)],1)],1)])},_=[],C=(a("4de4"),a("a15b"),a("39c4")),I={name:"TemplatePublish",data:function(){return{dialogVisible:!0,formType:{labelPosition:"top"},agreementChecked:!0,template:{formId:this.form.formId,formIssureName:this.form.formTitle,formIssureTag:["","",""],formIssureDesc:null}}},props:["show","form"],watch:{show:function(t){this.dialogVisible=t.valueOf()}},computed:{templteSorts:function(){return this.$store.getters["templates/getTemplateSorts"]}},methods:{templateSubmit:function(){var t=JSON.parse(JSON.stringify(this.template)),e=t.formIssureTag.filter((function(t){return""!=t}));0!=e.length&&null!=e.length?t.formIssureTag=e.join(","):t.formIssureTag=null;var a=new FormData;a.append("formId",t.formId),a.append("formIssureName",t.formIssureName),a.append("formIssureTag",t.formIssureTag),a.append("formIssureDesc",t.formIssureDesc),Object(C["a"])(a).then((function(t){console.log(t)}))}}},x=I,k=(a("3dcf"),Object(v["a"])(x,y,_,!1,null,null,null)),T=k.exports,O={name:"FormOperation",data:function(){return{drawer:!1,ObjDrawer:this.$refs,comp:"",show:!1}},props:{formItem:Object,size:{type:String,default:"24px"}},components:{Operations:w,TemplatePublish:T},methods:{templatesDialogShow:function(){this.comp="TemplatePublish",this.show=new Boolean(!0)}}},j=O,D=(a("7ea0"),Object(v["a"])(j,m,u,!1,null,null,null)),S=D.exports,$=a("a18c"),L=a("2b0e");function F(t,e){var a=this,o=t.iconClass;if(null!=t.path||void 0!=t.path)$["a"].push(t.path+"/"+e.formId);else switch(o){case"gd-fuzhi":Object(p["a"])(e.formId).then((function(t){N(t.msg),z()}));break;case"gd-shoucang":Object(p["h"])(this.formItem.formId).then((function(t){a.message(t.msg),a.drawer.eldrawer.hide(),a.viewType()}));break;case"gd-bianji":this.drawer.eldrawer.hide(),this.$prompt("<p class='msg'>表单重命名成功，系统内各处将显示重命名标题。此操作不影响公开表单名称</p><p class='content'>新表单名：</p>","重命名",{customClass:"message-box-rename",confirmButtonText:"确定",dangerouslyUseHTMLString:!0,inputValue:this.formItem.formName}).then((function(t){var e=t.value;""==e?a.message("表单名不能为空","error"):Object(p["g"])(a.formItem.formId,e).then((function(t){24===t.code?(a.message(t.msg),a.formItem.formName=e):a.message(t.msg,"warning")}))}));break;case"gd-shanchu":Object(p["b"])(this.formItem.formId).then((function(t){a.drawer.eldrawer.hide(),a.message(t.msg),z()}));break;case"gd-fabu":0==e.formOpen?Object(p["c"])(e.formId).then((function(){e.formOpen=1,N("发布表单成功！")})):N("表单已经发布","warning"),$["a"].push("/formFill/summarypage/"+e.formId);break}}function N(t,e){(new L["default"]).$message({showClose:!0,type:e||"success",message:t,duration:2e3})}function z(){var t=this.$route.meta.index;1===t?this.$store.dispatch("form/saveMyform"):this.$store.dispatch("form/saveFavorites")}var A={name:"ThumbnailItem",data:function(){return{isActive:!1,operations:[{iconClass:"gd-bianji",text:"编辑",path:"/formFill/maincontent"},{iconClass:"gd-fabu",text:"发布"},{iconClass:"gd-shuju",text:"数据",path:"formFill/formdata"}]}},computed:{formType:function(){var t={};return t.name=this.formData.formType.substring(this.formData.formType.length-2),t.style={color:"",background:""},"问卷"===t.name?(t.style.color="#83adff",t.style.background="#eef4ff"):(t.style.color="#7dd1c2",t.style.background="#ebf8f6"),t},formOpen:function(){var t={name:"",style:{color:""}},e=this.formData.formOpen;return 0===e?(t.name="未发布",t.style.color="#ffbe3a"):(t.name="发布中",t.style.color="#00bf6f"),t}},components:{FormOperation:S},props:{formData:Object},methods:{formHover:function(t){this.isActive=t},operationClick:function(t){console.log(t),F(t,this.formData)}}},E=A,H=(a("691c"),Object(v["a"])(E,l,c,!1,null,null,null)),M=H.exports,V={name:"LayoutThumbnail",components:{ThumbnailItem:M},data:function(){return{formIndex:""}},props:{formData:Array}},B=V,P=(a("b2a7"),Object(v["a"])(B,n,i,!1,null,null,null)),J=P.exports,U=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"layout-list"},[a("el-table",{staticStyle:{width:"100%","font-size":"18px"},attrs:{data:t.formData,"max-height":"100%"}},[a("el-table-column",{attrs:{fixed:"",width:"22"},scopedSlots:t._u([{key:"default",fn:function(e){return[1===e.row.formIsFavour?a("icon",{staticClass:"li__form-favorites",attrs:{icon:"gd-shoucang_black"}}):t._e()]}}])}),a("el-table-column",{attrs:{fixed:"","show-overflow-tooltip":"",label:"名称"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("icon",{staticClass:"li__form-icon",attrs:{icon:"gd-form"}}),a("span",[t._v(t._s(e.row.formTitle))])]}}])}),a("el-table-column",{attrs:{label:"类型",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.formType))])]}}])}),a("el-table-column",{attrs:{label:"收集状态",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[0===e.row.formOpen?a("span",{staticStyle:{color:"#ffbe3a"}},[t._v("未发布")]):a("span",{staticStyle:{color:"#00bf6f"}},[t._v("发布中")])]}}])}),a("el-table-column",{attrs:{label:"数量",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.formDataCount))]),a("span",[t._v("数据")])]}}])}),a("el-table-column",{attrs:{prop:"formCreateTime",label:"创建时间",align:"center",width:"200"}}),a("el-table-column",{attrs:{label:"操作",fit:!1,width:"280",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("div",{staticClass:"layout-list__operations"},[t._l(t.operations,(function(o){return a("div",{key:o.iconClass,staticClass:"layout-list__operations-item",on:{click:function(a){return t.operationClick(o,e.row)}}},[a("icon",{attrs:{icon:o.iconClass}}),a("span",[t._v(t._s(o.text))])],1)})),a("form-operation",{staticClass:"li-operation",attrs:{formItem:e.row}})],2)]}}])})],1)],1)},q=[],G={name:"LayoutList",data:function(){return{operations:[{iconClass:"gd-bianji",text:"编辑",path:"/formFill/maincontent"},{iconClass:"gd-fabu",text:"发布"},{iconClass:"gd-shuju",text:"数据",path:"formFill/formdata"}]}},components:{FormOperation:S},props:{formData:Array},methods:{operationClick:function(t,e){console.log(t),F(t,e)}}},K=G,Q=(a("43c4f"),Object(v["a"])(K,U,q,!1,null,null,null)),R=Q.exports,W={name:"MyForm",data:function(){return{viewTitle:this.title,formDisplayType:"LayoutThumbnail"}},props:["title","formData"],computed:{},components:{MainBar:r["a"],LayoutThumbnail:J,LayoutList:R},methods:{formShowChange:function(t){this.formDisplayType=t}}},X=W,Y=(a("6108"),Object(v["a"])(X,o,s,!1,null,null,null));e["a"]=Y.exports},e566:function(t,e,a){"use strict";var o=a("353b"),s=a.n(o);s.a},e59f:function(t,e,a){"use strict";var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"main-bar"},[a("div",{staticClass:"main-title"},[t._v(t._s(t.title))]),t._t("right",[t.operations?a("ul",[a("li",[a("el-tooltip",{directives:[{name:"show",rawName:"v-show",value:"LayoutList"===t.index,expression:"index === 'LayoutList'"}],attrs:{content:"切换到列表视图","popper-class":"tooltiptext"},nativeOn:{click:function(e){return t.indexClick("LayoutThumbnail")}}},[a("icon",{attrs:{icon:"gd-liebiao"}})],1),a("el-tooltip",{directives:[{name:"show",rawName:"v-show",value:"LayoutThumbnail"===t.index,expression:"index === 'LayoutThumbnail'"}],attrs:{content:"切换到缩略图视图","popper-class":"tooltiptext"},nativeOn:{click:function(e){return t.indexClick("LayoutList")}}},[a("icon",{attrs:{icon:"gd-caidan"}})],1)],1)]):t._e()])],2)},s=[],r={name:"MainBar",data:function(){return{index:"LayoutThumbnail"}},props:{title:{type:String,default:""},operations:{type:Boolean,default:!0}},methods:{indexClick:function(t){this.index=t,this.$emit("compName",t)}}},n=r,i=(a("e566"),a("2877")),l=Object(i["a"])(n,o,s,!1,null,null,null);e["a"]=l.exports},e9cd:function(t,e,a){}}]);
//# sourceMappingURL=chunk-63f297e7.229d6d22.js.map