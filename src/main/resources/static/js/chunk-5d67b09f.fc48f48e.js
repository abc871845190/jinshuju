(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5d67b09f"],{"0811":function(t,e,a){},"19f9":function(t,e,a){},"353b":function(t,e,a){},"438b":function(t,e,a){},"43c4":function(t,e,a){"use strict";var o=a("bdee"),s=a.n(o);s.a},6108:function(t,e,a){"use strict";var o=a("438b"),s=a.n(o);s.a},"691c":function(t,e,a){"use strict";var o=a("e9cd"),s=a.n(o);s.a},"7bfb":function(t,e,a){},"7ea0":function(t,e,a){"use strict";var o=a("7bfb"),s=a.n(o);s.a},b2a7:function(t,e,a){"use strict";var o=a("19f9"),s=a.n(o);s.a},b2e2:function(t,e,a){"use strict";var o=a("0811"),s=a.n(o);s.a},bdee:function(t,e,a){},d46c:function(t,e,a){"use strict";var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("main-bar",{attrs:{title:t.viewTitle},on:{compName:t.formShowChange}}),""==t.formData?a("div",{staticClass:"myform-content"},[t._v("暂无数据")]):a("div",{staticClass:"myform-content"},[a("layout-thumbnail",{directives:[{name:"show",rawName:"v-show",value:"LayoutThumbnail"===t.formDisplayType,expression:"formDisplayType === 'LayoutThumbnail'"}],staticClass:"myform-content",attrs:{formData:t.formData}}),a("layout-list",{directives:[{name:"show",rawName:"v-show",value:"LayoutList"===t.formDisplayType,expression:"formDisplayType === 'LayoutList'"}],attrs:{formData:t.formData}})],1)],1)},s=[],n=a("e59f"),i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"layout-thumbnail"},t._l(t.formData,(function(t,e){return a("thumbnail-item",{key:e,staticClass:"form-item",attrs:{formData:t}})})),1)},r=[],l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"thumbnail-item",on:{mousemove:function(e){return t.formHover(!0)},mouseleave:function(e){return t.formHover(!1)}}},[a("div",{staticClass:"ti-content"},[a("div",{staticClass:"ti-CollectionIcon"},[1===t.formData.formIsFavour?a("icon",{attrs:{icon:"gd-favorites"}}):t._e()],1),a("div",{staticClass:"ti-formHeader"},[a("div",{staticClass:"ti-title"},[t._v(t._s(t.formData.formTitle))]),t.formType?a("div",{staticClass:"ti-formType",style:t.formType.style},[t._v(" "+t._s(t.formType.name)+" ")]):t._e()]),a("div",{staticClass:"ti-formOpen",style:t.formOpen.style},[t._v(" "+t._s(t.formOpen.name)+" ")]),a("div",{staticClass:"ti-formIcon"},[a("icon",{attrs:{icon:"gd-form"}})],1)]),a("div",{staticClass:"ti-footer"},[a("div",{staticClass:"ti__count-data"},[a("span",{staticClass:"ti__count text-exceeds"},[t._v(t._s(t.formData.formDataCount))]),a("span",{staticClass:"ti__text"},[t._v("数据")])])]),a("div",{directives:[{name:"show",rawName:"v-show",value:t.isActive,expression:"isActive"}],staticClass:"ti-footer__focus"},[a("div",{staticClass:"ti-footer__operations"},t._l(t.operations,(function(e){return a("div",{key:e.iconClass,on:{click:function(a){return t.operationClick(e)}}},[a("icon",{attrs:{icon:e.iconClass}}),a("span",[t._v(t._s(e.text))])],1)})),0),a("div",{staticClass:"ti-more"},[a("form-operation",{attrs:{formItem:t.formData}})],1)])])},c=[],m=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{style:{fontSize:t.size},on:{click:function(e){t.drawer=!0}}},[t._t("content",[a("icon",{attrs:{icon:"gd-more"}})])],2),a("el-drawer",{ref:"eldrawer",attrs:{size:"20%","append-to-body":"","with-header":!1,visible:t.drawer},on:{"update:visible":function(e){t.drawer=e}}},[t._t("drawer-content",[a("operations",{attrs:{formItem:t.formItem,drawer:t.ObjDrawer},on:{"templates-dialog":t.templatesDialogShow}})])],2)],1)},f=[],u=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"operation-detail"},[a("div",{staticClass:"operation-detail__content"},[a("h3",{staticClass:"od__form-name"},[t._v(t._s(t.formItem.formName))]),t._l(t.showData,(function(e,o){return a("ul",{key:o,staticClass:"od-list"},t._l(e,(function(e){return a("li",{key:e.text,staticClass:"od-list__item",on:{click:function(a){return t.pathClick(e)}}},[a("icon",{attrs:{icon:e.iconClass}}),a("span",[t._v(t._s(e.text))])],1)})),0)}))],2)])},d=[],h=a("ef1b"),p={name:"Operations",data:function(){return{showData:[[{iconClass:"gd-bianji",text:"编辑",path:"/formFill/maincontent/"+this.formItem.formId},{iconClass:"gd-shezhi",text:"设置",path:"/formFill/summarypage/"+this.formItem.formId},{iconClass:"gd-fabu",text:"发布"},{iconClass:"gd-shuju",text:"数据查看",path:"formFill/formdata"},{iconClass:"gd-muban",text:"发表为模板"}],[{iconClass:"gd-fuzhi",text:"复制"},{iconClass:"gd-shoucang",text:"收藏"},{iconClass:"gd-bianji",text:"重命名"},{iconClass:"gd-shanchu",text:"删除"}],[{iconClass:"gd-biaoqian",text:"设置标签",path:"/formFill/maincontent"},{iconClass:"gd-tubiao",text:"设置文件图标",path:"/formFill/maincontent"}]]}},props:{drawer:Object,formItem:Object},watch:{formItem:function(t){0!=t.formIsFavour?this.showData[1][1].text="取消收藏":this.showData[1][1].text="收藏"}},methods:{pathClick:function(t){var e=this,a=t.iconClass;if(null!=t.path||void 0!=t.path)this.$router.push(t.path);else switch(a){case"gd-muban":this.$emit("templates-dialog"),this.drawer.eldrawer.hide();break;case"gd-fuzhi":this.drawer.eldrawer.hide(),Object(h["a"])(this.formItem.formId).then((function(t){console.log("复制表单",t),e.viewType()})),this.message("复制表单成功");break;case"gd-shoucang":Object(h["h"])(this.formItem.formId).then((function(t){t.code,e.message(t.msg),e.drawer.eldrawer.hide(),e.viewType()}));break;case"gd-bianji":this.drawer.eldrawer.hide(),this.$prompt("<p class='msg'>表单重命名成功，系统内各处将显示重命名标题。此操作不影响公开表单名称</p><p class='content'>新表单名：</p>","重命名",{customClass:"message-box-rename",confirmButtonText:"确定",dangerouslyUseHTMLString:!0,inputValue:this.formItem.formTitle}).then((function(t){var a=t.value;""==a?e.message("表单名不能为空","error"):Object(h["g"])(e.formItem.formId,a).then((function(t){24===t.code?(e.message(t.msg),e.formItem.formTitle=a):e.message(t.msg,"warning")}))}));break;case"gd-shanchu":Object(h["b"])(this.formItem.formId).then((function(t){e.drawer.eldrawer.hide(),e.message(t.msg),e.viewType()}));break;case"gd-fabu":this.drawer.eldrawer.hide(),0==this.formItem.formOpen?Object(h["c"])(this.formItem.formId).then((function(){e.formItem.formOpen=1,e.message("发布表单成功！")})):this.message("表单已经发布","warning"),this.$router.push("/formFill/summarypage/"+this.formItem.formId);break}},message:function(t,e){this.$message({showClose:!0,type:e||"success",message:t,duration:2e3})},viewType:function(){var t=this.$route.meta.index;1===t?this.$store.dispatch("form/saveMyform"):this.$store.dispatch("form/saveFavorites")}},created:function(){0!=this.formItem.formIsFavour&&(this.showData[1][1].text="取消收藏")}},v=p,b=(a("b2e2"),a("2877")),g=Object(b["a"])(v,u,d,!1,null,null,null),w=g.exports,y={name:"FormOperation",data:function(){return{drawer:!1,ObjDrawer:this.$refs,comp:"",show:!1}},props:{formItem:Object,size:{type:String,default:"24px"}},components:{Operations:w},methods:{templatesDialogShow:function(){this.comp="TemplatePublish",this.show=new Boolean(!0)}}},_=y,C=(a("7ea0"),Object(b["a"])(_,m,f,!1,null,null,null)),x=C.exports,I=a("a18c"),k=a("2b0e");function T(t,e){var a=this,o=t.iconClass;if(null!=t.path||void 0!=t.path)I["a"].push(t.path+"/"+e.formId);else switch(o){case"gd-fuzhi":Object(h["a"])(e.formId).then((function(t){O(t.msg),D()}));break;case"gd-shoucang":Object(h["h"])(this.formItem.formId).then((function(t){a.message(t.msg),a.drawer.eldrawer.hide(),a.viewType()}));break;case"gd-bianji":this.drawer.eldrawer.hide(),this.$prompt("<p class='msg'>表单重命名成功，系统内各处将显示重命名标题。此操作不影响公开表单名称</p><p class='content'>新表单名：</p>","重命名",{customClass:"message-box-rename",confirmButtonText:"确定",dangerouslyUseHTMLString:!0,inputValue:this.formItem.formName}).then((function(t){var e=t.value;""==e?a.message("表单名不能为空","error"):Object(h["g"])(a.formItem.formId,e).then((function(t){24===t.code?(a.message(t.msg),a.formItem.formName=e):a.message(t.msg,"warning")}))}));break;case"gd-shanchu":Object(h["b"])(this.formItem.formId).then((function(t){a.drawer.eldrawer.hide(),a.message(t.msg),D()}));break;case"gd-fabu":0==e.formOpen?Object(h["c"])(e.formId).then((function(){e.formOpen=1,O("发布表单成功！")})):O("表单已经发布","warning"),I["a"].push("/formFill/summarypage/"+e.formId);break}}function O(t,e){(new k["default"]).$message({showClose:!0,type:e||"success",message:t,duration:2e3})}function D(){var t=this.$route.meta.index;1===t?this.$store.dispatch("form/saveMyform"):this.$store.dispatch("form/saveFavorites")}var j={name:"ThumbnailItem",data:function(){return{isActive:!1,operations:[{iconClass:"gd-bianji",text:"编辑",path:"/formFill/maincontent"},{iconClass:"gd-fabu",text:"发布"},{iconClass:"gd-shuju",text:"数据",path:"formFill/formdata"}]}},computed:{formType:function(){var t={};return t.name=this.formData.formType.substring(this.formData.formType.length-2),t.style={color:"",background:""},"问卷"===t.name?(t.style.color="#83adff",t.style.background="#eef4ff"):(t.name="表单",t.style.color="#7dd1c2",t.style.background="#ebf8f6"),t},formOpen:function(){var t={name:"",style:{color:""}},e=this.formData.formOpen;return 0===e?(t.name="未发布",t.style.color="#ffbe3a"):(t.name="发布中",t.style.color="#00bf6f"),t}},components:{FormOperation:x},props:{formData:Object},methods:{formHover:function(t){this.isActive=t},operationClick:function(t){console.log(t),T(t,this.formData)}}},L=j,$=(a("691c"),Object(b["a"])(L,l,c,!1,null,null,null)),F=$.exports,S={name:"LayoutThumbnail",components:{ThumbnailItem:F},data:function(){return{formIndex:""}},props:{formData:Array}},N=S,z=(a("b2a7"),Object(b["a"])(N,i,r,!1,null,null,null)),E=z.exports,M=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"layout-list"},[a("el-table",{staticStyle:{width:"100%","font-size":"18px"},attrs:{data:t.formData,"max-height":"100%"}},[a("el-table-column",{attrs:{fixed:"",width:"22"},scopedSlots:t._u([{key:"default",fn:function(e){return[1===e.row.formIsFavour?a("icon",{staticClass:"li__form-favorites",attrs:{icon:"gd-shoucang_black"}}):t._e()]}}])}),a("el-table-column",{attrs:{fixed:"","show-overflow-tooltip":"",label:"名称"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("icon",{staticClass:"li__form-icon",attrs:{icon:"gd-form"}}),a("span",[t._v(t._s(e.row.formTitle))])]}}])}),a("el-table-column",{attrs:{label:"类型",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.formType))])]}}])}),a("el-table-column",{attrs:{label:"收集状态",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[0===e.row.formOpen?a("span",{staticStyle:{color:"#ffbe3a"}},[t._v("未发布")]):a("span",{staticStyle:{color:"#00bf6f"}},[t._v("发布中")])]}}])}),a("el-table-column",{attrs:{label:"数量",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.formDataCount))]),a("span",[t._v("数据")])]}}])}),a("el-table-column",{attrs:{prop:"formCreateTime",label:"创建时间",align:"center",width:"200"}}),a("el-table-column",{attrs:{label:"操作",fit:!1,width:"280",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("div",{staticClass:"layout-list__operations"},[t._l(t.operations,(function(o){return a("div",{key:o.iconClass,staticClass:"layout-list__operations-item",on:{click:function(a){return t.operationClick(o,e.row)}}},[a("icon",{attrs:{icon:o.iconClass}}),a("span",[t._v(t._s(o.text))])],1)})),a("form-operation",{staticClass:"li-operation",attrs:{formItem:e.row}})],2)]}}])})],1)],1)},A=[],B={name:"LayoutList",data:function(){return{operations:[{iconClass:"gd-bianji",text:"编辑",path:"/formFill/maincontent"},{iconClass:"gd-fabu",text:"发布"},{iconClass:"gd-shuju",text:"数据",path:"formFill/formdata"}]}},components:{FormOperation:x},props:{formData:Array},methods:{operationClick:function(t,e){console.log(t),T(t,e)}}},H=B,J=(a("43c4"),Object(b["a"])(H,M,A,!1,null,null,null)),U=J.exports,V={name:"MyForm",data:function(){return{viewTitle:this.title,formDisplayType:"LayoutThumbnail"}},props:["title","formData"],computed:{},components:{MainBar:n["a"],LayoutThumbnail:E,LayoutList:U},methods:{formShowChange:function(t){this.formDisplayType=t}}},q=V,P=(a("6108"),Object(b["a"])(q,o,s,!1,null,null,null));e["a"]=P.exports},e566:function(t,e,a){"use strict";var o=a("353b"),s=a.n(o);s.a},e59f:function(t,e,a){"use strict";var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"main-bar"},[a("div",{staticClass:"main-title"},[t._v(t._s(t.title))]),t._t("right",[t.operations?a("ul",[a("li",[a("el-tooltip",{directives:[{name:"show",rawName:"v-show",value:"LayoutList"===t.index,expression:"index === 'LayoutList'"}],attrs:{content:"切换到列表视图","popper-class":"tooltiptext"},nativeOn:{click:function(e){return t.indexClick("LayoutThumbnail")}}},[a("icon",{attrs:{icon:"gd-liebiao"}})],1),a("el-tooltip",{directives:[{name:"show",rawName:"v-show",value:"LayoutThumbnail"===t.index,expression:"index === 'LayoutThumbnail'"}],attrs:{content:"切换到缩略图视图","popper-class":"tooltiptext"},nativeOn:{click:function(e){return t.indexClick("LayoutList")}}},[a("icon",{attrs:{icon:"gd-caidan"}})],1)],1)]):t._e()])],2)},s=[],n={name:"MainBar",data:function(){return{index:"LayoutThumbnail"}},props:{title:{type:String,default:""},operations:{type:Boolean,default:!0}},methods:{indexClick:function(t){this.index=t,this.$emit("compName",t)}}},i=n,r=(a("e566"),a("2877")),l=Object(r["a"])(i,o,s,!1,null,null,null);e["a"]=l.exports},e9cd:function(t,e,a){}}]);
//# sourceMappingURL=chunk-5d67b09f.fc48f48e.js.map