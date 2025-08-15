#version 330
out vec3 f;
struct circle{
    vec2 m;
    float r;
};
float x = gl_FragCoord.x;
float y = gl_FragCoord.y;

bool IsInsideSquare(float SQxmin, float SQxmax, float SQymin, float SQymax, float xvalue, float yvalue){
    return (xvalue>SQxmin && xvalue<SQxmax && yvalue>SQymin && yvalue<SQymax);
}

bool IsInsideCircle(circle kreis){
    return (((x-kreis.m.x)*(x-kreis.m.x)+(y-kreis.m.y)*(y-kreis.m.y))<kreis.r*kreis.r);
}


void main(){
    /*task 1 square
    if(x<650  && x>200 && y<650 && y>200){
        f=vec3(1.0,0.0,2.0);
    } else {
        f=vec3(0.0,0.0,0.0);
    } */
    /*task 2 circle
    int xm = 350;
    int ym = 350;
    int r = 100;
    if(((x-xm)*(x-xm) + (y-ym)*(y-ym)) < r*r){
        f=vec3(1.0,0.0,2.0);
    } else{
        f=vec3(0.0,0.0,0.0);
    }*/
    /* task 2 circle mit struct
    circle teststruct = circle(vec2(600,600),200);
    if(IsInsideCircle(teststruct)){
        f=vec3(0.5,0.0,1.0);
    } else {
        f=vec3(0.0,0.0,0.0);
    } */
    // task 3
    f= vec3(0.0,0.0,0.0);
    if(IsInsideSquare(100,500,100,500,x,y)){
        f=vec3(1.0,0.0,0.0);
    }
    circle circle1= circle(vec2(350, 500), 50);
    circle circle2= circle(vec2(500, 350), 100);
    if(IsInsideCircle(circle1)){
        f=vec3(0.0,1.0,0.0);
    }else if(IsInsideCircle(circle2)){
        f=vec3(0.0,0.0,1.0);
    }
    // task4
    float winkel=.2;
    float xrotate=cos(winkel)*x -sin(winkel)*y;
    float yrotate=sin(winkel)*x +cos(winkel)*y;
    if(IsInsideSquare(600,1000,600,1000,xrotate,yrotate)){
        f=vec3(0.5,0.3,0.0);
    }
}
