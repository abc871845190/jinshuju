(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-71da915f"],{7587:function(e,r,s){},d3a9:function(e,r,s){"use strict";var t=s("7587"),u=s.n(t);u.a},f3f9:function(e,r,s){"use strict";s.r(r);var t=function(){var e=this,r=e.$createElement,s=e._self._c||r;return s("div",[s("div",{staticClass:"register-title"},[s("h3",[e._v("账号注册")]),s("p",[e._v("已有账号？ "),s("router-link",{staticClass:"rt-login",attrs:{tag:"span",to:"login"}},[e._v("立即登录")])],1)]),s("div",{staticClass:"lr-content"},[s("el-form",{attrs:{model:e.user,rules:e.rules},nativeOn:{keyup:function(r){return!r.type.indexOf("key")&&e._k(r.keyCode,"enter",13,r.key,"Enter")?null:e.register(r)}}},[s("el-form-item",{attrs:{prop:"userName"}},[s("el-input",{attrs:{placeholder:"账号"},model:{value:e.user.userName,callback:function(r){e.$set(e.user,"userName",r)},expression:"user.userName"}})],1),s("el-form-item",{attrs:{prop:"userPassword"}},[s("el-input",{attrs:{type:"password",placeholder:"密码"},model:{value:e.user.userPassword,callback:function(r){e.$set(e.user,"userPassword",r)},expression:"user.userPassword"}})],1),s("el-form-item",{attrs:{prop:"userEmail"}},[s("el-input",{attrs:{placeholder:"邮箱"},model:{value:e.user.userEmail,callback:function(r){e.$set(e.user,"userEmail",r)},expression:"user.userEmail"}})],1),s("el-form-item",{attrs:{prop:"userTelephone"}},[s("el-input",{attrs:{placeholder:"手机号"},model:{value:e.user.userTelephone,callback:function(r){e.$set(e.user,"userTelephone",r)},expression:"user.userTelephone"}})],1),s("el-form-item",[s("el-button",{staticClass:"lr_btn"},[e._v("立即注册")])],1)],1)],1)])},u=[],a=s("8d85"),l={name:"register",data:function(){return{user:{userName:"",userPassword:"",userEmail:"",userTelephone:""},rules:{userName:[{required:!0,message:"账号不能为空",trigger:"blur"},{min:3,max:10,message:"账号长度在 3 到 10 个字符之间",trigger:"blur"}],userPassword:[{required:!0,message:"密码不能为空",trigger:"blur"},{min:3,max:10,message:"密码长度在 3 到 10 个字符之间",trigger:"blur"}],userEmail:[{required:!0,message:"邮箱地址不能为空",trigger:"blur"},{type:"email",message:"请输入正确的邮箱地址",trigger:["blur","change"]}],userTelephone:[{required:!0,message:"手机号码不能为空",trigger:"blur"},{pattern:/^1[3456789]\d{9}$/,message:"请输入正确的手机号码",trigger:"blur"}]}}},methods:{register:function(){var e=this;Object(a["h"])(this.user).then((function(r){e.$message({showClose:!0,message:r.msg,duration:1e3}),1===r.code&&e.$router.push("/user/login")}))}}},n=l,i=(s("d3a9"),s("2877")),o=Object(i["a"])(n,t,u,!1,null,null,null);r["default"]=o.exports}}]);
//# sourceMappingURL=chunk-71da915f.93f2c4d0.js.map