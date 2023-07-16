#include <stdio.h>

typedef struct stack{
    struct stack *next;
    int data;
}stack;

stack *top = NULL;

int isEmpty()
{
    if(top == NULL){
        return 1;
    }
    return 0;
}

void stackPush(int data)
{
    stack *newnode = (stack*)malloc(sizeof(stack));
    newnode -> data = data;
    newnode -> next = top;
    top = newnode;
    return;
}

int stackPop()
{
    if(!isEmpty()){
        stack *temp = top;
        int data = temp->data;
        top = temp->next;
        free(temp);
        return data;
    }
    else  return -1; 
}

int stackSize()
{
    int size = 0;
    if(!isEmpty()){
        size++;
        stack *temp = top;
        while (! (temp->next == NULL))
        {
            temp = temp->next;
            size++;
        }
    }
   return size;
}

int stackTop()
{    
    if(!isEmpty()){       
        return top->data;
    }
    else    return -1;

}

int main(void)
{
    int num, data, ret;
    char command[32];
    scanf("%d",&num);
    for(int i=0; i < num; i++)
    {
        command[0]='\0';
        scanf("%s",command);
        if(!strcmp(command,"push"))
        {
            scanf("%d",&data);
            stackPush(data);
            continue;
        }
        else if(!strcmp(command,"pop"))
        {
            ret = stackPop();
        }
        else if(!strcmp(command,"top"))
        {
            ret = stackTop();
        }
        else if(!strcmp(command,"size"))
        {
            ret = stackSize();            
        }
        else if(!strcmp(command,"empty"))
        {
            ret = isEmpty();
        }
        printf("%d\n",ret);
    }
    return 0;
}
