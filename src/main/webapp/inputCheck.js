
//проверка поля Y и подсветка правильности ввода. Наверное это не стоит делать по id в js а может быть делать классами и css, но и так работает хорошо
function checkY(event){
    var objY=document.getElementById("coordY");
        if(objY.value.match(new RegExp("^((-[1-3])|(-[0-2][\.,][0-9]{1,})|([0-3])|([0-2](\.)[0-9]{1,}))$"))){
            objY.style.backgroundColor="#9C9";
            return true;
        }
        objY.style.backgroundColor="#f33" ;
        return false; 
}
function checkR(event){
    var objY=document.getElementById("paramR");
        if(objY.value.match(new RegExp("^(([1-4])|([1-3](\.)[0-9]{1,}))$"))){
            objY.style.backgroundColor="#9C9";
            return true;
        }
        objY.style.backgroundColor="#f33" ;
        return false; 
}
//проверка правильности формы. X и R считается всегда определённым, так что проверка Y
function formcheck() {
        if(checkR() && checkY()){
            document.getElementById("wrong-input-message").hidden=true;
            return true;		
        }
        document.getElementById("wrong-input-message").hidden=false;
        return false;
}
//делаем из checkbox радио
function CheckboxXfunc(e) {
      for(var i = 0; i < inputs.length; i++){
        if(inputs[i]!=this)
            inputs[i].checked=false;    
    }        		
}
//назначаем чекбоксам для ввода X обработчики
var inputs = document.getElementsByName("coordX");
for(var i = 0; i < inputs.length; i++) 
    inputs[i].onclick = CheckboxXfunc;
document.getElementById("coordY").onkeyup = checkY;
document.getElementById("paramR").onkeyup = checkR;