(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-45fbded4"],{2158:function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es_array_map__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("d81d"),core_js_modules_es_array_map__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es_array_map__WEBPACK_IMPORTED_MODULE_0__),D_project_jinshuju_jinshuju_v4_1_0_node_modules_babel_runtime_helpers_esm_toConsumableArray__WEBPACK_IMPORTED_MODULE_1__=__webpack_require__("2909"),_network_formdata_js__WEBPACK_IMPORTED_MODULE_2__=__webpack_require__("501c");__webpack_exports__["a"]={components:{},inject:["reload"],data:function(){return{dialogVisible:!1,tableColumn:[],tableData:[],multipleSelection:[],selectData:[],formId:""}},methods:{getSelect:function(a){this.selectData=[];for(var t=0;t<a.length;t++)this.selectData.push(a[t].dataId)},getSelectAll:function(a){this.getSelect(a)},getDetails:function(a){this.dialogVisible=!0,console.log(a)},refreshData:function(){this.reload()},addData:function(){},delData:function(){var a=this;Object(_network_formdata_js__WEBPACK_IMPORTED_MODULE_2__["a"])(this.selectData).then((function(t){console.log(t),1==t.code&&a.reload()}))},gettableDatas:function gettableDatas(){var _this2=this;this.axios.all([Object(_network_formdata_js__WEBPACK_IMPORTED_MODULE_2__["e"])(this.formId),Object(_network_formdata_js__WEBPACK_IMPORTED_MODULE_2__["d"])(this.formId)]).then(this.axios.spread((function(res1,res2){var allData=res1.data,allQuestion=res2.data,ans=[],arrData=[];for(var questionIndex in console.log(allData),console.log(allQuestion),allQuestion){var paramOption={};paramOption.prop=allQuestion[questionIndex].formTemplateId+"",paramOption.label=allQuestion[questionIndex].templateTitle,_this2.tableColumn.push(paramOption)}if(void 0===allData[0])alert("暂无数据哟~(#^.^#)");else for(var i in allData){for(var j in allQuestion){var paramId={},paramData={};for(var k in paramId["dataId"]=allData[i].dataId,allData[i].dataDetailsList)if(allQuestion[k].formTemplateId==allData[i].dataDetailsList[j].formTemplateId)if("radio"==allQuestion[k].templateName||"select"==allQuestion[k].templateName||"picradio"==allQuestion[k].templateName)"[]"!=allData[i].dataDetailsList[j].dataContent&&""!=allData[i].dataDetailsList[j].dataContent?(ans=JSON.parse(allData[i].dataDetailsList[j].dataContent),ans=ans.value):(ans=JSON.parse('{"key":0,"value":""}'),ans=ans.value);else if("checkbox"==allQuestion[k].templateName||"piccheckbox"==allQuestion[k].templateName)if("[]"!=allData[i].dataDetailsList[j].dataContent&&""!=allData[i].dataDetailsList[j].dataContent){var _result=eval(allData[i].dataDetailsList[j].dataContent);ans=_result.map((function(a){return a.value+" "}))}else ans=JSON.parse('{"key":0,"value":""}'),ans=ans.value;else ans=allData[i].dataDetailsList[j].dataContent;paramData[allData[i].dataDetailsList[j].formTemplateId]=ans,arrData.push(paramId),arrData.push(paramData)}var result=Object.assign.apply(Object,Object(D_project_jinshuju_jinshuju_v4_1_0_node_modules_babel_runtime_helpers_esm_toConsumableArray__WEBPACK_IMPORTED_MODULE_1__["a"])(arrData));arrData=[],_this2.tableData.push(result)}})))}},created:function(){sessionStorage.setItem("TagActiveName","third"),this.formId=this.$route.params.id,this.gettableDatas()}}},"3eae":function(a,t,e){"use strict";e.r(t);var l=function(){var a=this,t=a.$createElement,e=a._self._c||t;return e("div",{staticClass:"fd-all"},[e("el-dialog",{attrs:{title:"数据详情",visible:a.dialogVisible,width:"80%"},on:{"update:visible":function(t){a.dialogVisible=t}}},[e("div",{staticClass:"fd-main"},[e("el-table",{attrs:{data:a.tableData,stripe:""},on:{"select-all":a.getSelectAll,select:a.getSelect,"row-click":a.getDetails}},[e("el-table-column",{attrs:{type:"selection",width:"55"}}),a._l(a.tableColumn,(function(a,t){return e("el-table-column",{key:t,staticStyle:{width:"'150'"},attrs:{prop:a.prop,label:a.label,sortable:"",fixed:""}})}))],2)],1),e("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{on:{click:function(t){a.dialogVisible=!1}}},[a._v("取 消")]),e("el-button",{attrs:{type:"primary"},on:{click:function(t){a.dialogVisible=!1}}},[a._v("确 定")])],1)]),e("div",{staticClass:"fd-head"},[e("tbody",[e("tr",{staticClass:"fd-tr"},[e("td",[e("el-button",{staticClass:"iconfont gd-shuaxin",attrs:{type:"text"},on:{click:a.refreshData}},[a._v("刷新页面")])],1),e("td",[e("el-button",{staticClass:"iconfont gd-tianjia",attrs:{type:"text"},on:{click:function(t){a.dialogVisible=!0}}},[a._v("增加数据")])],1),e("td",[e("el-button",{staticClass:"iconfont gd-shanchu",attrs:{type:"text"},on:{click:a.delData}},[a._v("删除数据")])],1)])])]),e("div",{staticClass:"fd-main"},[e("el-table",{attrs:{data:a.tableData,stripe:""},on:{"select-all":a.getSelectAll,select:a.getSelect,"row-click":a.getDetails}},[e("el-table-column",{attrs:{type:"selection",width:"55"}}),e("el-table-column",{attrs:{type:"index",width:"55"}}),a._l(a.tableColumn,(function(a,t){return e("el-table-column",{key:t,staticStyle:{width:"'150'"},attrs:{prop:a.prop,label:a.label,sortable:""}})}))],2)],1)],1)},s=[],i=e("2158"),o=i["a"],n=(e("dada"),e("2877")),_=Object(n["a"])(o,l,s,!1,null,"da246d70",null);t["default"]=_.exports},"60f0":function(a,t,e){},dada:function(a,t,e){"use strict";var l=e("60f0"),s=e.n(l);s.a}}]);
//# sourceMappingURL=chunk-45fbded4.588414db.js.map