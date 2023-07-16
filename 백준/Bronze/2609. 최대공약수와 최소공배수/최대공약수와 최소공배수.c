#include<stdio.h>

void sort(int *a, int *b){
    if(a < b){
        int temp;
        temp = a;
        a = b;
        b= temp;
    }
}

int gcd(int a, int b)
{
    if(a%b == 0)
        return b;
    
    gcd(b,a%b);
}

int lcm(int a, int b, int gcd){
    return (a / gcd) * b;
}

int main(void){
    int a, b, c, d;
    scanf("%d %d",&a, &b);
    sort(&a,&b);

    c = gcd(a,b);
    d = lcm(a,b,c);

    printf("%d\n%d\n",c,d);
}