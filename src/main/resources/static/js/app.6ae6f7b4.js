(function(e){function t(t){for(var a,r,u=t[0],i=t[1],l=t[2],s=0,f=[];s<u.length;s++)r=u[s],Object.prototype.hasOwnProperty.call(c,r)&&c[r]&&f.push(c[r][0]),c[r]=0;for(a in i)Object.prototype.hasOwnProperty.call(i,a)&&(e[a]=i[a]);d&&d(t);while(f.length)f.shift()();return o.push.apply(o,l||[]),n()}function n(){for(var e,t=0;t<o.length;t++){for(var n=o[t],a=!0,r=1;r<n.length;r++){var u=n[r];0!==c[u]&&(a=!1)}a&&(o.splice(t--,1),e=i(i.s=n[0]))}return e}var a={},r={app:0},c={app:0},o=[];function u(e){return i.p+"js/"+({}[e]||e)+"."+{"chunk-0e29d112":"4bd53da9","chunk-2a8640cc":"66136450","chunk-3057afbe":"e5f53407","chunk-32f10583":"39678309","chunk-b0d65fda":"3109d68c","chunk-bfa9120c":"f9f96701","chunk-dd58303e":"92b29308","chunk-33bd4fb0":"0c3c8318","chunk-485b3368":"6c6c5fa8","chunk-4c7aae74":"08fd5f85","chunk-4fc5eafc":"37ec406e","chunk-5f058181":"fd6025c2","chunk-2d0d7ea3":"4f446951","chunk-2d22cae3":"3211e76f","chunk-65f26b18":"f272e33d","chunk-4ab0a0aa":"42c42092","chunk-7638b319":"02706cd3","chunk-7eb08e2f":"86f1f01f","chunk-665f93ac":"18ebf2c5","chunk-72d682b3":"3f24e546","chunk-76d0b990":"e4f81db6","chunk-8a155ac6":"bd88ce3d","chunk-ac7c887a":"ab1297e3","chunk-ad254e52":"dfbd98cf"}[e]+".js"}function i(t){if(a[t])return a[t].exports;var n=a[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,i),n.l=!0,n.exports}i.e=function(e){var t=[],n={"chunk-0e29d112":1,"chunk-2a8640cc":1,"chunk-3057afbe":1,"chunk-32f10583":1,"chunk-b0d65fda":1,"chunk-bfa9120c":1,"chunk-dd58303e":1,"chunk-33bd4fb0":1,"chunk-485b3368":1,"chunk-4c7aae74":1,"chunk-4fc5eafc":1,"chunk-5f058181":1,"chunk-65f26b18":1,"chunk-4ab0a0aa":1,"chunk-7638b319":1,"chunk-7eb08e2f":1,"chunk-665f93ac":1,"chunk-72d682b3":1,"chunk-76d0b990":1,"chunk-8a155ac6":1,"chunk-ac7c887a":1,"chunk-ad254e52":1};r[e]?t.push(r[e]):0!==r[e]&&n[e]&&t.push(r[e]=new Promise((function(t,n){for(var a="css/"+({}[e]||e)+"."+{"chunk-0e29d112":"33ef5e34","chunk-2a8640cc":"8cc61e35","chunk-3057afbe":"330938c8","chunk-32f10583":"5db94d44","chunk-b0d65fda":"dbb99746","chunk-bfa9120c":"9a0eb9b8","chunk-dd58303e":"992f0be6","chunk-33bd4fb0":"f10eab93","chunk-485b3368":"68f6d2ae","chunk-4c7aae74":"0df6f010","chunk-4fc5eafc":"d1f5ffc4","chunk-5f058181":"fc323ce7","chunk-2d0d7ea3":"31d6cfe0","chunk-2d22cae3":"31d6cfe0","chunk-65f26b18":"f3f87cee","chunk-4ab0a0aa":"02e0da34","chunk-7638b319":"78ed007e","chunk-7eb08e2f":"b68eee82","chunk-665f93ac":"6c0361a6","chunk-72d682b3":"2985cedb","chunk-76d0b990":"bf7d4aa3","chunk-8a155ac6":"be0039b4","chunk-ac7c887a":"a1136aca","chunk-ad254e52":"8c024c95"}[e]+".css",c=i.p+a,o=document.getElementsByTagName("link"),u=0;u<o.length;u++){var l=o[u],s=l.getAttribute("data-href")||l.getAttribute("href");if("stylesheet"===l.rel&&(s===a||s===c))return t()}var f=document.getElementsByTagName("style");for(u=0;u<f.length;u++){l=f[u],s=l.getAttribute("data-href");if(s===a||s===c)return t()}var d=document.createElement("link");d.rel="stylesheet",d.type="text/css",d.onload=t,d.onerror=function(t){var a=t&&t.target&&t.target.src||c,o=new Error("Loading CSS chunk "+e+" failed.\n("+a+")");o.code="CSS_CHUNK_LOAD_FAILED",o.request=a,delete r[e],d.parentNode.removeChild(d),n(o)},d.href=c;var h=document.getElementsByTagName("head")[0];h.appendChild(d)})).then((function(){r[e]=0})));var a=c[e];if(0!==a)if(a)t.push(a[2]);else{var o=new Promise((function(t,n){a=c[e]=[t,n]}));t.push(a[2]=o);var l,s=document.createElement("script");s.charset="utf-8",s.timeout=120,i.nc&&s.setAttribute("nonce",i.nc),s.src=u(e);var f=new Error;l=function(t){s.onerror=s.onload=null,clearTimeout(d);var n=c[e];if(0!==n){if(n){var a=t&&("load"===t.type?"missing":t.type),r=t&&t.target&&t.target.src;f.message="Loading chunk "+e+" failed.\n("+a+": "+r+")",f.name="ChunkLoadError",f.type=a,f.request=r,n[1](f)}c[e]=void 0}};var d=setTimeout((function(){l({type:"timeout",target:s})}),12e4);s.onerror=s.onload=l,document.head.appendChild(s)}return Promise.all(t)},i.m=e,i.c=a,i.d=function(e,t,n){i.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(i.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var a in e)i.d(n,a,function(t){return e[t]}.bind(null,a));return n},i.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(t,"a",t),t},i.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},i.p="/",i.oe=function(e){throw console.error(e),e};var l=window["webpackJsonp"]=window["webpackJsonp"]||[],s=l.push.bind(l);l.push=t,l=l.slice();for(var f=0;f<l.length;f++)t(l[f]);var d=s;o.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var a=n("85ec"),r=n.n(a);r.a},"1bab":function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));var a=n("bc3a"),r=n.n(a),c=n("a18c");function o(e){var t=r.a.create({baseURL:"",timeout:1e4});return t.interceptors.request.use((function(e){var t=window.localStorage.getItem("token");return""!=t&&(e.headers.common["token"]=t),e}),(function(e){console.log(e)})),t.interceptors.response.use((function(e){return 900!==e.data.code?(void 0!==e.headers.header_token&&null!==e.headers.header_token&&""!==e.headers.header_token&&window.localStorage.setItem("token",e.headers.header_token),e.data):(c["a"].push("/user/login"),e.data)}),(function(e){console.log(e)})),t(e)}},"1c02":function(e,t,n){"use strict";var a=n("547c"),r=n.n(a);r.a},"547c":function(e,t,n){},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var a=n("2b0e"),r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[e.isRouterAlive?n("router-view"):e._e()],1)},c=[],o={name:"app",provide:function(){return{reload:this.reload}},data:function(){return{isRouterAlive:!0}},methods:{reload:function(){this.isRouterAlive=!1,this.$nextTick((function(){this.isRouterAlive=!0}))}}},u=o,i=(n("034f"),n("2877")),l=Object(i["a"])(u,r,c,!1,null,null,null),s=l.exports,f=n("a18c"),d=n("2f62"),h=n("ef1b"),m={namespaced:!0,state:{formData:""},mutations:{updateFormData:function(e,t){e.formData=t}},getters:{getFormData:function(e){return e.formData}},actions:{saveMyform:function(e){Object(h["f"])().then((function(t){e.commit("updateFormData",t.data)}))},saveFavorites:function(e){Object(h["d"])().then((function(t){e.commit("updateFormData",t.data)}))}}},p=m,b=n("c157"),k={namespaced:!0,state:{height:""},mutations:{setheight:function(e,t){e.height=t}},getters:{height:function(e){return e.height}},actions:{}},g=k,_={namespaced:!0,state:{userInfo:null},mutations:{updateUserInfo:function(e,t){var n={userId:t.userId,userImg:t.userImg,userName:t.userName};e.userInfo=n,localStorage.setItem("userInfo",JSON.stringify(n))}},getters:{getUserInfo:function(e,t,n){return null===e.userInfo&&(e.userInfo=JSON.parse(localStorage.getItem("userInfo")),void 0!=n.getURL&&null!=n.getURL&&""!=n.getURL&&(e.userInfo.userImg=n.getURL+e.userInfo.userImg)),e.userInfo}}},v=_;a["default"].use(d["a"]);var L=new d["a"].Store({state:{getURL:"http://qforest.fast2.fgnwctvip.com"},mutations:{},actions:{},modules:{form:p,tabledata:b["a"],windowheight:g,user:v}}),y=n("bc3a"),C=n.n(y),x=n("b76a"),O=n.n(x),P=n("313e"),w=n.n(P),I=(n("840a"),n("b2b6")),A=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("i",{staticClass:"icon-item iconfont",class:e.icon,style:{fontSize:e.size}})},j=[],S={name:"Icon",props:{icon:String,size:String}},E=S,T=(n("1c02"),Object(i["a"])(E,A,j,!1,null,null,null)),F=T.exports,D=n("5c96"),U=n.n(D);n("0fae");a["default"].prototype.$echarts=w.a,a["default"].component("icon-btn",I["a"]),a["default"].component("icon",F),a["default"].config.productionTip=!1,a["default"].use(U.a),a["default"].prototype.axios=C.a,a["default"].component("draggable",O.a),new a["default"]({router:f["a"],store:L,render:function(e){return e(s)}}).$mount("#app");t["default"]=a["default"]},"85ec":function(e,t,n){},"93bb":function(e,t,n){"use strict";var a=n("b466"),r=n.n(a);r.a},a18c:function(e,t,n){"use strict";n("d3b7");var a=n("2b0e"),r=n("8c4f"),c=function(){return Promise.all([n.e("chunk-32f10583"),n.e("chunk-bfa9120c")]).then(n.bind(null,"b3d7"))},o=function(){return Promise.all([n.e("chunk-5f058181"),n.e("chunk-2d22cae3")]).then(n.bind(null,"f3ee"))},u=function(){return Promise.all([n.e("chunk-5f058181"),n.e("chunk-2d0d7ea3")]).then(n.bind(null,"799d"))},i=function(){return n.e("chunk-485b3368").then(n.bind(null,"2bea"))},l=function(){return Promise.all([n.e("chunk-32f10583"),n.e("chunk-b0d65fda")]).then(n.bind(null,"b42b"))},s=function(){return n.e("chunk-4c7aae74").then(n.bind(null,"4567"))},f=function(){return n.e("chunk-2a8640cc").then(n.bind(null,"2554"))},d=function(){return n.e("chunk-72d682b3").then(n.bind(null,"a770"))},h=function(){return n.e("chunk-33bd4fb0").then(n.bind(null,"cbf4"))},m=function(){return n.e("chunk-ac7c887a").then(n.bind(null,"f3f9"))},p=function(){return Promise.all([n.e("chunk-32f10583"),n.e("chunk-dd58303e")]).then(n.bind(null,"7e7b"))},b=function(){return n.e("chunk-0e29d112").then(n.bind(null,"169e"))};a["default"].use(r["a"]);var k=[{path:"/",component:c,redirect:"/home",meta:{index:0},children:[{path:"home",component:o,meta:{index:1}},{path:"favorites",component:u,meta:{index:2}},{path:"participated_forms",component:i,meta:{index:3}}]},{path:"/templates",component:l,redirect:"/templates/list",children:[{path:"list",component:s},{path:"detail",component:f}]},{path:"/formFill",name:"EditPage",component:function(){return n.e("chunk-3057afbe").then(n.bind(null,"feec"))},redirect:"/formFill/maincontent",children:[{path:"maincontent/:id",name:"maincontent",component:function(){return Promise.all([n.e("chunk-65f26b18"),n.e("chunk-7eb08e2f")]).then(n.bind(null,"0c47"))}},{path:"summarypage/:id",name:"summarypage",component:function(){return n.e("chunk-665f93ac").then(n.bind(null,"95a5"))}},{path:"formdata/:id",name:"formdata",component:function(){return Promise.all([n.e("chunk-65f26b18"),n.e("chunk-7638b319")]).then(n.bind(null,"3eae"))}},{path:"chartsdata/:id",name:"chartsdata",component:function(){return n.e("chunk-76d0b990").then(n.bind(null,"7681"))}}]},{path:"/f/:id",component:function(){return Promise.all([n.e("chunk-65f26b18"),n.e("chunk-4ab0a0aa")]).then(n.bind(null,"9d36"))},meta:{requiresAuth:!1}},{path:"/profile",component:p,redirect:"/profile/setting",children:[{path:"setting",component:b}]},{path:"/user",component:d,redirect:"/user/login",children:[{path:"login",component:h,meta:{requiresAuth:!1}},{path:"register",component:m,meta:{requiresAuth:!1}}]},{path:"/finish",component:function(){return n.e("chunk-8a155ac6").then(n.bind(null,"2972"))},meta:{requiresAuth:!1}},{path:"/test",component:function(){return n.e("chunk-ad254e52").then(n.bind(null,"4505"))},meta:{requiresAuth:!1}},{path:"/excel",component:function(){return n.e("chunk-4fc5eafc").then(n.bind(null,"b92e"))},meta:{requiresAuth:!1}}],g=new r["a"]({mode:"history",base:"/",routes:k});g.beforeEach((function(e,t,n){if(!1===e.meta.requiresAuth)n();else{var a=window.localStorage.getItem("token");void 0===a||null===a||""===a?g.push("/user/login"):n()}}));var _=r["a"].prototype.push;r["a"].prototype.push=function(e){return _.call(this,e).catch((function(e){return e}))},t["a"]=g},b2b6:function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"icon",staticClass:"btn-item",style:e.activeStyle,on:{click:e.btnClick}},["left"==e.iconBtnType?n("i",{class:"iconfont "+e.iconClass},[""!=this.tips?n("span",{staticClass:"tooltiptext"},[e._v(e._s(e.tips))]):e._e()]):e._e(),e._t("btn-text",[e._v(e._s(e.btnText))]),"right"==e.iconBtnType?n("i",{class:"iconfont "+e.iconClass}):e._e(),""!=this.tips?n("span",{staticClass:"tooltiptext"},[e._v(e._s(e.tips))]):e._e()],2)},r=[],c=(n("c975"),{name:"IconBtn",props:{iconBtnType:{type:String,default:"left"},path:String,iconClass:String,btnText:String,tips:{type:String,default:""},activeColor:{type:String,default:"var(--color-text-change)"}},computed:{isActive:function(){return-1!=this.$route.path.indexOf(this.path)},activeStyle:function(){return this.isActive?{color:this.activeColor}:{}}},methods:{btnClick:function(){this.$router.push(this.path).catch((function(e){return e}))}},mounted:function(){""!=this.tips&&(this.$refs.icon.className+=" tooltip")}}),o=c,u=(n("93bb"),n("2877")),i=Object(u["a"])(o,a,r,!1,null,null,null);t["a"]=i.exports},b466:function(e,t,n){},c157:function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es_array_splice__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("a434"),core_js_modules_es_array_splice__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es_array_splice__WEBPACK_IMPORTED_MODULE_0__),tabledata={namespaced:!0,state:{form:{formTitle:"未命名",formDesc:"描述",templateList:[]},index:0,subjects:[],deletePicArr:[]},mutations:{update:function(e,t){e.form.templateList=t},insert:function insert(state,data){var index=0,arr=[];console.log(data);while(index<data.templateList.length){for(var i in data.templateList)data.templateList[i].templateCount===index&&arr.push(data.templateList[i]);index++}for(var r in data.templateList)data.templateList[r].selected=!1,null!==data.templateList[r].templateContent&&void 0!==data.templateList[r].templateContent&&""!==data.templateList[r].templateContent&&(data.templateList[r].templateContent=eval(data.templateList[r].templateContent)),null!==data.templateList[r].templateImgUrl&&void 0!==data.templateList[r].templateImgUrl&&""!==data.templateList[r].templateImgUrl&&(data.templateList[r].templateImgUrl=eval(data.templateList[r].templateImgUrl)),"checkbox"===data.templateList[r].templateName||"piccheckbox"===data.templateList[r].templateName?data.templateList[r].data=[]:data.templateList[r].data="";data.templateList=arr,state.form=data},increment:function(e,t){var n=!1;for(var a in 0===e.form.templateList.length&&(t.templateCount=e.form.templateList.length),e.form.templateList){for(var r in n=!1,e.form.templateList)if(parseInt(a)===e.form.templateList[r].templateCount){n=!0;break}if(!n){t.templateCount=parseInt(a);break}t.templateCount=e.form.templateList.length}"picradio"===t.templateName&&e.deletePicArr.push({templateCount:t.templateCount,option:[]}),console.log(e.deletePicArr),e.form.templateList.push(t)},moveup:function(e,t){e.form.templateList[t]=e.form.templateList.splice(t-1,1,e.form.templateList[t])[0]},movedown:function(e,t){e.form.templateList[t]=e.form.templateList.splice(t+1,1,e.form.templateList[t])[0]},delete:function(e,t){e.form.templateList.splice(t,1)},setindex:function(e,t){e.index=t},setsubjects:function(e,t){e.subjects=t},commitdata:function(e,t){e.form.templateList[e.index]=t},cleanNewOption:function(e){e.newOption=[]},addDeletePicArr:function(e,t){e.deletePicArr.push({templateCount:t,option:[]})},cleanPicArr:function(e,t){e.deletePicArr=t}},getters:{form:function(e){return e.form},data:function(e){return e.form.templateList[e.index]},subjects:function(e){return e.subjects},index:function(e){return e.index},deletePicArr:function(e){return e.deletePicArr}},actions:{}};__webpack_exports__["a"]=tabledata},ef1b:function(e,t,n){"use strict";n.d(t,"f",(function(){return r})),n.d(t,"d",(function(){return c})),n.d(t,"e",(function(){return o})),n.d(t,"h",(function(){return u})),n.d(t,"a",(function(){return i})),n.d(t,"g",(function(){return l})),n.d(t,"b",(function(){return s})),n.d(t,"c",(function(){return f}));var a=n("1bab");function r(){return Object(a["a"])({url:"/FormController/form"})}function c(){return Object(a["a"])({url:"/FormController/getFavourForms"})}function o(){return Object(a["a"])({url:"/FormController/getFillesForms"})}function u(e){return Object(a["a"])({method:"PATCH",url:"/FormController/updateFavour/"+e})}function i(e){return Object(a["a"])({method:"POST",url:"/FormController/copy/"+e})}function l(e,t){return Object(a["a"])({method:"PATCH",url:"/FormController/updateName/"+e+"/"+t})}function s(e){return Object(a["a"])({method:"DELETE",url:"/FormController/form/"+e})}function f(e){return Object(a["a"])({method:"PATCH",url:"/FormController/updateOpen/"+e})}}});
//# sourceMappingURL=app.6ae6f7b4.js.map