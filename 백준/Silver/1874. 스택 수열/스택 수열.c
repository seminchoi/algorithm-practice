#include<stdio.h>

typedef struct stack{
    struct stack *link;
    int data;
}stack;

stack *top = NULL;

int isEmpty()
{
    if(top == NULL)
        return 1;
    return 0;
}

void stackPush(int data){
    stack *newNode = (stack*)malloc(sizeof(stack));
    newNode->link = top;
    newNode->data = data;
    top = newNode;
}

int stackPop(){
    if(!isEmpty()){
        int data;
        stack *temp;
        temp = top;
        data = temp -> data;
        top = temp->link;
        free(temp);
        return data; 
    }
    return -1;
}

int stackTop(){
    return top->data;
}

int main(void)
{
    int num;
    int seq[100001];
    char res[1000001];
    scanf("%d",&num);
    for(int i = 0; i<num; i++)
    {
        scanf("%d",&seq[i]);
    }


    int seqIdx = 0, resIdx = 0;
    for(int i = 1; i <= num; i++){
        stackPush(i);
        res[resIdx] = '+';
        resIdx++;
        while( (!isEmpty()) && stackTop() == seq[seqIdx] ){
            stackPop();
            res[resIdx] = '-';
            resIdx++; seqIdx++;
        }
    }
    if(isEmpty()){
    for(int i=0; i < resIdx ; i++)
        printf("%c\n",res[i]);
    }
    else
        printf("NO\n");

    return 0;    

}