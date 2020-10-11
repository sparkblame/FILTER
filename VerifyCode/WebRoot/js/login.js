function changeCode(){
    var codeImg = document.getElementById("verifyCodeImage");
    codeImg.src="servlet/createImage?t="+Math.random();
}