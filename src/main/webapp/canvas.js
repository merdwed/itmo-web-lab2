var canvas  = document.getElementById('canvas');
ctx = canvas.getContext('2d');
function CanvasToCenterCoord(x,y){
    var width=canvas.scrollWidth;
    var height=canvas.scrollHeight;
    x=x/width*2-1;
    y=-y/height*2+1;
    return {x:x,y:y};
}
function CenterToCanvasCoord(x, y){
    var width=canvas.scrollWidth;
    var height=canvas.scrollHeight;
    x=(x+1)*width/2;
    y=(y-1)*height/2;
    return {x:x,y:y};
}
function canvas_arrow(context, fromx, fromy, tox, toy) {
    var headlen = 10; // length of head in pixels
    var dx = tox - fromx;
    var dy = toy - fromy;
    var angle = Math.atan2(dy, dx);
    context.moveTo(fromx, fromy);
    context.lineTo(tox, toy);
    context.lineTo(tox - headlen * Math.cos(angle - Math.PI / 6), toy - headlen * Math.sin(angle - Math.PI / 6));
    context.moveTo(tox, toy);
    context.lineTo(tox - headlen * Math.cos(angle + Math.PI / 6), toy - headlen * Math.sin(angle + Math.PI / 6));
  }
  function draw_area(){
    var [w,h]=[canvas.scrollWidth,canvas.scrollHeight];
    ctx.fillStyle = "green";
    ctx.strokeStyle = "green";
    ctx.lineWidth = 1;
    ctx.beginPath();
    ctx.moveTo(w/2,h/2);
    ctx.lineTo(5*w/6,h/2);
    ctx.lineTo(5*w/6,5*h/6);
    ctx.lineTo(w/2,5*h/6);
    ctx.lineTo(w/2,h/2);
    ctx.stroke();
    ctx.fill();
    ctx.beginPath();
    ctx.moveTo(w/2,h/2);
    ctx.lineTo(w/6,h/2);
    ctx.lineTo(w/2,5*h/6);
    ctx.lineTo(w/2,h/2);
    ctx.stroke();
    ctx.fill();
    ctx.beginPath();
    ctx.arc(w/2,h/2,w/6,-Math.PI/2,-Math.PI,true);
    ctx.lineTo(w/2,h/2);
    ctx.lineTo(w/2,h/3);
    ctx.stroke();
    ctx.fill();
}
function draw_legend(){
    var [w,h]=[canvas.scrollWidth,canvas.scrollHeight];
    ctx.beginPath();
    ctx.font="20px serif";
    ctx.strokeStyle="black";
    ctx.lineWidth=2;
    ctx.strokeText('R', 5*w/6-4, h/2-5);//+x
    ctx.strokeText('-R', w/6-4, h/2-5);//-x
    ctx.strokeText('R', w/2+4, h/6+3);//+y
    ctx.strokeText('-R', w/2+4, 5*h/6+3);//-y
    ctx.fillStyle="black";
    ctx.fillRect(5*w/6-1,h/2-3,2,6);//+x
    ctx.fillRect(w/6-1,h/2-3,2,6);//-x
    ctx.fillRect(w/2-3,h/6-1,6,2);//+y
    ctx.fillRect(w/2-3,5*h/6-1,6,2);//-y
}
function draw_scene(event){
    ctx.clearRect(0,0,canvas.scrollWidth,canvas.scrollHeight);
    draw_area();
    ctx.beginPath();
    ctx.lineWidth = 2;
    ctx.strokeStyle='black';
    canvas_arrow(ctx, 0, canvas.scrollHeight/2, canvas.scrollWidth, canvas.scrollHeight/2);
    canvas_arrow(ctx, canvas.scrollWidth/2, canvas.scrollHeight, canvas.scrollWidth/2, 0);
    ctx.stroke();
    draw_legend();
    if(event == null)return;
    ctx.beginPath();
    ctx.lineWidth = 2;
    ctx.strokeStyle='blue';
    ctx.arc(event.offsetX,event.offsetY,3,0,2 * Math.PI);
    ctx.stroke();
    ctx.beginPath();
    ctx.lineWidth = 2;
    ctx.font="20px serif";
    ctx.strokeStyle='black';
    var {x,y}=CanvasToCenterCoord(event.offsetX,event.offsetY);
    var str;
    var R=document.querySelector('input[name=paramR]').value;
    if(R==null || R=="")
        str='вы не ввели R';
    else
    if(!checkR())
        str='вы ввели некорректный R'
    else
        str=`(${(x*R*1.5).toFixed(2).toString()}; ${(y*R*1.5).toFixed(2).toString()})`;
    var offsetX=canvas.scrollWidth * 3 / 4-event.offsetX;
    var offsetY=canvas.scrollHeight / 16-event.offsetY;
    if(offsetX>=0)offsetX=0;
    if(offsetY<=0)offsetY=0;
    ctx.strokeText(str, event.offsetX+5+offsetX, event.offsetY-8+offsetY);
}
canvas.onmousemove=function(event){
    canvas.onmousedown=function(event){
        if(!checkR())return;
        var xhr = new XMLHttpRequest();
        var {x,y}=CanvasToCenterCoord(event.offsetX,event.offsetY);
        var R=document.querySelector('input[name=paramR]').value;
        var body =`coordX=${(x*R*1.5).toFixed(2).toString()}&coordY=${(y*R*1.5).toFixed(2).toString()}&paramR=${R}`
        xhr.open("POST", window.location.pathname + '/ControllerServlet', false);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(body);
        document.body.innerHTML=xhr.responseText;
    }
    canvas.onmouseup=function(event){
        //canvas.onmousemove=null;
    }
    draw_scene(event);
}
draw_scene(null);
