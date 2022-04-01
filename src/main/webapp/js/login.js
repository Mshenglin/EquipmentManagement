$(function () {

   //登陆表单css间距设置
   function  editcss() {
       var input1 = document.getElementsByTagName("input")[0];
       var input2 = document.getElementsByTagName("input")[1];
       var a=document.getElementById("sign");
       a.style.fontSize=14;
       a.style.paddingLeft=40;
       a.style.display="inline-block";
       input1.style.paddingLeft=40;
       input2.style.paddingLeft=40;
   }
    editcss();

});