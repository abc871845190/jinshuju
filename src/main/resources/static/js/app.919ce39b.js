(function(e){function t(t){for(var r,a,u=t[0],i=t[1],l=t[2],s=0,f=[];s<u.length;s++)a=u[s],Object.prototype.hasOwnProperty.call(c,a)&&c[a]&&f.push(c[a][0]),c[a]=0;for(r in i)Object.prototype.hasOwnProperty.call(i,r)&&(e[r]=i[r]);d&&d(t);while(f.length)f.shift()();return o.push.apply(o,l||[]),n()}function n(){for(var e,t=0;t<o.length;t++){for(var n=o[t],r=!0,a=1;a<n.length;a++){var u=n[a];0!==c[u]&&(r=!1)}r&&(o.splice(t--,1),e=i(i.s=n[0]))}return e}var r={},a={app:0},c={app:0},o=[];function u(e){return i.p+"js/"+({}[e]||e)+"."+{"chunk-0b2ec4bc":"31888eaa","chunk-2a8640cc":"66136450","chunk-2d0c113c":"0504ca0d","chunk-3f662c19":"da1c5a8b","chunk-239a381e":"7cb8023e","chunk-308aaece":"a9a5508c","chunk-3e673a42":"0695475f","chunk-440cc5e9":"975997bb","chunk-485b3368":"8dccbc2d","chunk-4c5ebc4c":"fb989d2d","chunk-4fc5eafc":"37ec406e","chunk-665f93ac":"197d468d","chunk-6b115e5e":"fdba99a2","chunk-71da915f":"93f2c4d0","chunk-72d682b3":"dd6bc6b7","chunk-75c1f874":"a06f1293","chunk-76d0b990":"e4f81db6","chunk-b117a140":"9ed2bbd3","chunk-c1f5cec6":"0a412789","chunk-2d0d7ea3":"4f446951","chunk-2d22cae3":"3211e76f","chunk-f9a9f014":"eb55b0db","chunk-1654e36c":"06ba227f","chunk-6200fed3":"ff8f103a","chunk-dd58303e":"bb434018"}[e]+".js"}function i(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,i),n.l=!0,n.exports}i.e=function(e){var t=[],n={"chunk-0b2ec4bc":1,"chunk-2a8640cc":1,"chunk-3f662c19":1,"chunk-239a381e":1,"chunk-308aaece":1,"chunk-3e673a42":1,"chunk-440cc5e9":1,"chunk-485b3368":1,"chunk-4c5ebc4c":1,"chunk-4fc5eafc":1,"chunk-665f93ac":1,"chunk-6b115e5e":1,"chunk-71da915f":1,"chunk-72d682b3":1,"chunk-75c1f874":1,"chunk-76d0b990":1,"chunk-b117a140":1,"chunk-c1f5cec6":1,"chunk-f9a9f014":1,"chunk-1654e36c":1,"chunk-6200fed3":1,"chunk-dd58303e":1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=new Promise((function(t,n){for(var r="css/"+({}[e]||e)+"."+{"chunk-0b2ec4bc":"0df6f010","chunk-2a8640cc":"8cc61e35","chunk-2d0c113c":"31d6cfe0","chunk-3f662c19":"b8fbebfc","chunk-239a381e":"84312712","chunk-308aaece":"5f1a540c","chunk-3e673a42":"f5ceb12d","chunk-440cc5e9":"a4c685c5","chunk-485b3368":"68f6d2ae","chunk-4c5ebc4c":"def29f51","chunk-4fc5eafc":"d1f5ffc4","chunk-665f93ac":"6c0361a6","chunk-6b115e5e":"aad9348f","chunk-71da915f":"a1136aca","chunk-72d682b3":"97b8e626","chunk-75c1f874":"848c0d5a","chunk-76d0b990":"bf7d4aa3","chunk-b117a140":"f3d40ce5","chunk-c1f5cec6":"b9825c51","chunk-2d0d7ea3":"31d6cfe0","chunk-2d22cae3":"31d6cfe0","chunk-f9a9f014":"8f88bbe9","chunk-1654e36c":"a3e33265","chunk-6200fed3":"512012f3","chunk-dd58303e":"caaec020"}[e]+".css",c=i.p+r,o=document.getElementsByTagName("link"),u=0;u<o.length;u++){var l=o[u],s=l.getAttribute("data-href")||l.getAttribute("href");if("stylesheet"===l.rel&&(s===r||s===c))return t()}var f=document.getElementsByTagName("style");for(u=0;u<f.length;u++){l=f[u],s=l.getAttribute("data-href");if(s===r||s===c)return t()}var d=document.createElement("link");d.rel="stylesheet",d.type="text/css",d.onload=t,d.onerror=function(t){var r=t&&t.target&&t.target.src||c,o=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");o.code="CSS_CHUNK_LOAD_FAILED",o.request=r,delete a[e],d.parentNode.removeChild(d),n(o)},d.href=c;var m=document.getElementsByTagName("head")[0];m.appendChild(d)})).then((function(){a[e]=0})));var r=c[e];if(0!==r)if(r)t.push(r[2]);else{var o=new Promise((function(t,n){r=c[e]=[t,n]}));t.push(r[2]=o);var l,s=document.createElement("script");s.charset="utf-8",s.timeout=120,i.nc&&s.setAttribute("nonce",i.nc),s.src=u(e);var f=new Error;l=function(t){s.onerror=s.onload=null,clearTimeout(d);var n=c[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),a=t&&t.target&&t.target.src;f.message="Loading chunk "+e+" failed.\n("+r+": "+a+")",f.name="ChunkLoadError",f.type=r,f.request=a,n[1](f)}c[e]=void 0}};var d=setTimeout((function(){l({type:"timeout",target:s})}),12e4);s.onerror=s.onload=l,document.head.appendChild(s)}return Promise.all(t)},i.m=e,i.c=r,i.d=function(e,t,n){i.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(i.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)i.d(n,r,function(t){return e[t]}.bind(null,r));return n},i.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(t,"a",t),t},i.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},i.p="/",i.oe=function(e){throw console.error(e),e};var l=window["webpackJsonp"]=window["webpackJsonp"]||[],s=l.push.bind(l);l.push=t,l=l.slice();for(var f=0;f<l.length;f++)t(l[f]);var d=s;o.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var r=n("85ec"),a=n.n(r);a.a},"1bab":function(e,t,n){"use strict";n.d(t,"a",(function(){return u}));var r=n("bc3a"),a=n.n(r),c=n("a18c"),o=n("4360");function u(e){var t=a.a.create({baseURL:"",timeout:1e4});return t.interceptors.request.use((function(e){o["a"].commit("setFullscreenLoading",!0);var t=window.localStorage.getItem("token");return""!=t&&(e.headers.common["token"]=t),e}),(function(e){console.log(e)})),t.interceptors.response.use((function(e){return o["a"].commit("setFullscreenLoading",!1),900!==e.data.code?(void 0!==e.headers.header_token&&null!==e.headers.header_token&&""!==e.headers.header_token&&window.localStorage.setItem("token",e.headers.header_token),e.data):(c["a"].push("/user/login"),e.data)}),(function(e){console.log(e)})),t(e)}},"1c02":function(e,t,n){"use strict";var r=n("547c"),a=n.n(r);a.a},4360:function(e,t,n){"use strict";var r=n("2b0e"),a=n("2f62"),c=n("ef1b"),o={namespaced:!0,state:{formData:""},mutations:{updateFormData:function(e,t){e.formData=t}},getters:{getFormData:function(e){return e.formData}},actions:{saveMyform:function(e){Object(c["f"])().then((function(t){e.commit("updateFormData",t.data)}))},saveFavorites:function(e){Object(c["d"])().then((function(t){e.commit("updateFormData",t.data)}))}}},u=o,i={namespaced:!0,state:{templateSorts:[{id:"1",name:"场景",tips:"适用于什么场景",values:["问卷调查","活动报名","信息登记","在线收款","满意度调查","考试评分","专业预约","疫情防控","在线预约","招聘","在线抽奖"]},{id:"2",name:"行业",tips:"适用于什么行业",values:["教育培训","摄影","零售","电商","旅游","行业通用","互联网软件","金融服务","政府单位","生活服务","广告传媒","建造业"]},{id:"3",name:"功能",tips:"包含了什么功能",values:["抽奖","评分","短信验证","日历预约","对外查询","行业通用","电子签名","计算得分","获取位置","上传附件","选项排序"]}]},getters:{getTemplateSorts:function(e){return e.templateSorts}}},l=i,s=n("c157"),f={namespaced:!0,state:{height:""},mutations:{setheight:function(e,t){e.height=t}},getters:{height:function(e){return e.height}},actions:{}},d=f,m=(n("d3b7"),n("8d85")),h={namespaced:!0,state:{userInfo:null},mutations:{updateUserInfo:function(e,t){e.userInfo=t,localStorage.setItem("userInfo",JSON.stringify(t))}},getters:{getuserInfo:function(e){return null===e.userInfo&&(e.userInfo=JSON.parse(localStorage.getItem("userInfo"))),e.userInfo}},actions:{saveUserInfo:function(e){var t=e.rootState,n=e.commit;return new Promise((function(e){Object(m["a"])().then((function(r){var a=r.data;void 0!=t.getURL&&null!=t.getURL&&""!=t.getURL&&(a.userImg=t.getURL+a.userImg),n("updateUserInfo",a),e()}))}))}}},p=h;r["default"].use(a["a"]);t["a"]=new a["a"].Store({state:{getURL:"http://qforest.fast2.fgnwctvip.com",fullscreenLoading:!1},mutations:{setFullscreenLoading:function(e,t){e.fullscreenLoading=t}},actions:{},modules:{form:u,tabledata:s["a"],windowheight:d,user:p,templates:l}})},"547c":function(e,t,n){},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("2b0e"),a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{id:"app"}},[e.isRouterAlive?n("router-view"):e._e()],1)},c=[],o={name:"app",provide:function(){return{reload:this.reload}},data:function(){return{isRouterAlive:!0}},methods:{reload:function(){this.isRouterAlive=!1,this.$nextTick((function(){this.isRouterAlive=!0}))}},computed:{fullscreenLoading:function(){return this.$store.state.fullscreenLoading}}},u=o,i=(n("034f"),n("2877")),l=Object(i["a"])(u,a,c,!1,null,null,null),s=l.exports,f=n("a18c"),d=n("4360"),m=n("bc3a"),h=n.n(m),p=n("b76a"),b=n.n(p),k=n("313e"),g=n.n(k),v=(n("840a"),n("b2b6")),_=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("i",{staticClass:"icon-item iconfont",class:e.icon,style:{fontSize:e.size}})},L=[],C={name:"Icon",props:{icon:String,size:String}},O=C,y=(n("1c02"),Object(i["a"])(O,_,L,!1,null,null,null)),P=y.exports,w=n("5c96"),x=n.n(w);n("0fae"),n("e05f");r["default"].prototype.$echarts=g.a,r["default"].component("icon-btn",v["a"]),r["default"].component("icon",P),r["default"].config.productionTip=!1,r["default"].use(x.a),x.a.Dialog.props.closeOnClickModal.default=!1,x.a.MessageBox.setDefaults({closeOnClickModal:!1}),r["default"].prototype.axios=h.a,r["default"].component("draggable",b.a),new r["default"]({router:f["a"],store:d["a"],render:function(e){return e(s)}}).$mount("#app");t["default"]=r["default"]},"85ec":function(e,t,n){},"8d85":function(e,t,n){"use strict";n.d(t,"g",(function(){return a})),n.d(t,"h",(function(){return c})),n.d(t,"a",(function(){return o})),n.d(t,"e",(function(){return u})),n.d(t,"f",(function(){return i})),n.d(t,"b",(function(){return l})),n.d(t,"c",(function(){return s})),n.d(t,"d",(function(){return f}));var r=n("1bab");function a(e,t){return Object(r["a"])({method:"POST",url:"/UserController/loginUser",data:{userName:e,userPassword:t}})}function c(e){return Object(r["a"])({method:"POST",url:"/UserController/User",data:{userName:e.userName,userPassword:e.userPassword,userEmail:e.userEmail,userTelephone:e.userTelephone}})}function o(){return Object(r["a"])({url:"/UserController/User"})}function u(e){return Object(r["a"])({method:"POST",url:"/UserController/uploadHeadImg",data:e})}function i(e){return Object(r["a"])({method:"patch",url:"/UserController/updateUserName",data:e})}function l(e){return Object(r["a"])({method:"patch",url:"/UserController/updateEmail",data:{userEmail:e}})}function s(e){return Object(r["a"])({method:"patch",url:"/UserController/updatePsw",data:{newPsw:e.newPsw,oriPsw:e.oriPsw}})}function f(e){return Object(r["a"])({method:"patch",url:"/UserController/updateTelephone",data:{userTelephone:e}})}},"93bb":function(e,t,n){"use strict";var r=n("b466"),a=n.n(r);a.a},a18c:function(e,t,n){"use strict";n("d3b7");var r=n("2b0e"),a=n("8c4f"),c=function(){return Promise.all([n.e("chunk-f9a9f014"),n.e("chunk-1654e36c")]).then(n.bind(null,"b3d7"))},o=function(){return Promise.all([n.e("chunk-c1f5cec6"),n.e("chunk-2d22cae3")]).then(n.bind(null,"f3ee"))},u=function(){return Promise.all([n.e("chunk-c1f5cec6"),n.e("chunk-2d0d7ea3")]).then(n.bind(null,"799d"))},i=function(){return n.e("chunk-485b3368").then(n.bind(null,"2bea"))},l=function(){return Promise.all([n.e("chunk-f9a9f014"),n.e("chunk-6200fed3")]).then(n.bind(null,"b42b"))},s=function(){return n.e("chunk-0b2ec4bc").then(n.bind(null,"4567"))},f=function(){return n.e("chunk-2a8640cc").then(n.bind(null,"2554"))},d=function(){return n.e("chunk-72d682b3").then(n.bind(null,"a770"))},m=function(){return n.e("chunk-75c1f874").then(n.bind(null,"cbf4"))},h=function(){return n.e("chunk-71da915f").then(n.bind(null,"f3f9"))},p=function(){return Promise.all([n.e("chunk-f9a9f014"),n.e("chunk-dd58303e")]).then(n.bind(null,"7e7b"))},b=function(){return n.e("chunk-440cc5e9").then(n.bind(null,"169e"))};r["default"].use(a["a"]);var k=[{path:"/",component:c,redirect:"/home",meta:{index:0},children:[{path:"home",component:o,meta:{index:1}},{path:"favorites",component:u,meta:{index:2}},{path:"participated_forms",component:i,meta:{index:3}}]},{path:"/templates",component:l,redirect:"/templates/list",children:[{path:"list",component:s},{path:"detail",component:f}]},{path:"/formFill",name:"EditPage",component:function(){return n.e("chunk-6b115e5e").then(n.bind(null,"feec"))},redirect:"/formFill/maincontent",children:[{path:"maincontent/:id",name:"maincontent",component:function(){return Promise.all([n.e("chunk-3f662c19"),n.e("chunk-239a381e")]).then(n.bind(null,"0c47"))}},{path:"summarypage/:id",name:"summarypage",component:function(){return n.e("chunk-665f93ac").then(n.bind(null,"95a5"))}},{path:"formdata/:id",name:"formdata",component:function(){return Promise.all([n.e("chunk-3f662c19"),n.e("chunk-308aaece")]).then(n.bind(null,"3eae"))}},{path:"chartsdata/:id",name:"chartsdata",component:function(){return n.e("chunk-76d0b990").then(n.bind(null,"7681"))}}]},{path:"/f/:id",component:function(){return Promise.all([n.e("chunk-3f662c19"),n.e("chunk-3e673a42")]).then(n.bind(null,"9d36"))},meta:{requiresAuth:!1}},{path:"/profile",component:p,redirect:"/profile/setting",children:[{path:"setting",component:b}]},{path:"/user",component:d,redirect:"/user/login",children:[{path:"login",component:m,meta:{requiresAuth:!1}},{path:"register",component:h,meta:{requiresAuth:!1}}]},{path:"/finish",component:function(){return n.e("chunk-b117a140").then(n.bind(null,"2972"))},meta:{requiresAuth:!1}},{path:"/stop",component:function(){return n.e("chunk-4c5ebc4c").then(n.bind(null,"4db6"))},meta:{requiresAuth:!1}},{path:"/test",component:function(){return n.e("chunk-2d0c113c").then(n.bind(null,"4505"))},meta:{requiresAuth:!1}},{path:"/excel",component:function(){return n.e("chunk-4fc5eafc").then(n.bind(null,"b92e"))},meta:{requiresAuth:!1}}],g=new a["a"]({mode:"history",base:"/",routes:k});g.beforeEach((function(e,t,n){if(!1===e.meta.requiresAuth)n();else{var r=window.localStorage.getItem("token");void 0===r||null===r||""===r?g.push("/user/login"):n()}}));var v=a["a"].prototype.push;a["a"].prototype.push=function(e){return v.call(this,e).catch((function(e){return e}))},t["a"]=g},b2b6:function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"icon",staticClass:"btn-item",style:e.activeStyle,on:{click:e.btnClick}},["left"==e.iconBtnType?n("i",{class:"iconfont "+e.iconClass},[""!=this.tips?n("span",{staticClass:"tooltiptext"},[e._v(e._s(e.tips))]):e._e()]):e._e(),e._t("btn-text",[e._v(e._s(e.btnText))]),"right"==e.iconBtnType?n("i",{class:"iconfont "+e.iconClass}):e._e(),""!=this.tips?n("span",{staticClass:"tooltiptext"},[e._v(e._s(e.tips))]):e._e()],2)},a=[],c=(n("c975"),{name:"IconBtn",props:{iconBtnType:{type:String,default:"left"},path:String,iconClass:String,btnText:String,tips:{type:String,default:""},activeColor:{type:String,default:"var(--color-text-change)"}},computed:{isActive:function(){return-1!=this.$route.path.indexOf(this.path)},activeStyle:function(){return this.isActive?{color:this.activeColor}:{}}},methods:{btnClick:function(){this.$router.push(this.path).catch((function(e){return e}))}},mounted:function(){""!=this.tips&&(this.$refs.icon.className+=" tooltip")}}),o=c,u=(n("93bb"),n("2877")),i=Object(u["a"])(o,r,a,!1,null,null,null);t["a"]=i.exports},b466:function(e,t,n){},c157:function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es_array_splice__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("a434"),core_js_modules_es_array_splice__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es_array_splice__WEBPACK_IMPORTED_MODULE_0__),tabledata={namespaced:!0,state:{form:{formTitle:"未命名",formDesc:"描述",templateList:[]},index:0,subjects:[],deletePicArr:[]},mutations:{update:function(e,t){e.form.templateList=t},insert:function insert(state,data){var index=0,arr=[];console.log(data);while(index<data.templateList.length){for(var i in data.templateList)data.templateList[i].templateCount===index&&arr.push(data.templateList[i]);index++}for(var r in data.templateList)data.templateList[r].selected=!1,null!==data.templateList[r].templateContent&&void 0!==data.templateList[r].templateContent&&""!==data.templateList[r].templateContent&&(data.templateList[r].templateContent=eval(data.templateList[r].templateContent)),null!==data.templateList[r].templateImgUrl&&void 0!==data.templateList[r].templateImgUrl&&""!==data.templateList[r].templateImgUrl&&(data.templateList[r].templateImgUrl=eval(data.templateList[r].templateImgUrl)),"checkbox"===data.templateList[r].templateName||"piccheckbox"===data.templateList[r].templateName?data.templateList[r].data=[]:data.templateList[r].data="";data.templateList=arr,state.form=data},increment:function(e,t){var n=!1;for(var r in 0===e.form.templateList.length&&(t.templateCount=e.form.templateList.length),e.form.templateList){for(var a in n=!1,e.form.templateList)if(parseInt(r)===e.form.templateList[a].templateCount){n=!0;break}if(!n){t.templateCount=parseInt(r);break}t.templateCount=e.form.templateList.length}"picradio"===t.templateName&&e.deletePicArr.push({templateCount:t.templateCount,option:[]}),console.log(e.deletePicArr),e.form.templateList.push(t)},moveup:function(e,t){e.form.templateList[t]=e.form.templateList.splice(t-1,1,e.form.templateList[t])[0]},movedown:function(e,t){e.form.templateList[t]=e.form.templateList.splice(t+1,1,e.form.templateList[t])[0]},delete:function(e,t){e.form.templateList.splice(t,1)},setindex:function(e,t){e.index=t},setsubjects:function(e,t){e.subjects=t},commitdata:function(e,t){e.form.templateList[e.index]=t},cleanNewOption:function(e){e.newOption=[]},addDeletePicArr:function(e,t){e.deletePicArr.push({templateCount:t,option:[]})},cleanPicArr:function(e,t){e.deletePicArr=t},setRequire:function(e,t){e.form.templateList[e.index].templateRequire=t}},getters:{form:function(e){return e.form},data:function(e){return e.form.templateList[e.index]},subjects:function(e){return e.subjects},index:function(e){return e.index},deletePicArr:function(e){return e.deletePicArr}},actions:{}};__webpack_exports__["a"]=tabledata},ef1b:function(e,t,n){"use strict";n.d(t,"f",(function(){return a})),n.d(t,"d",(function(){return c})),n.d(t,"e",(function(){return o})),n.d(t,"h",(function(){return u})),n.d(t,"a",(function(){return i})),n.d(t,"g",(function(){return l})),n.d(t,"b",(function(){return s})),n.d(t,"c",(function(){return f}));var r=n("1bab");function a(){return Object(r["a"])({url:"/FormController/form"})}function c(){return Object(r["a"])({url:"/FormController/getFavourForms"})}function o(){return Object(r["a"])({url:"/FormController/getFillesForms"})}function u(e){return Object(r["a"])({method:"PATCH",url:"/FormController/updateFavour/"+e})}function i(e){return Object(r["a"])({method:"POST",url:"/FormController/copy/"+e})}function l(e,t){return Object(r["a"])({method:"PATCH",url:"/FormController/updateName/"+e+"/"+t})}function s(e){return Object(r["a"])({method:"DELETE",url:"/FormController/form/"+e})}function f(e){return Object(r["a"])({method:"PATCH",url:"/FormController/updateOpen/"+e})}}});
//# sourceMappingURL=app.919ce39b.js.map