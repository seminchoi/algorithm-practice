#include <stdio.h>

int main(void)
{
    char VpsStr[51];
    int num, VpsCnt = 0, idx = 0;
    scanf("%d",&num);
    getchar();
    for(int i = 0; i < num; i++)
    {
        scanf("%s",VpsStr);
        idx=0, VpsCnt = 0;
        while(VpsStr[idx] != '\0')
        {
            if(VpsStr[idx] == '(')
                VpsCnt++;
            else
                VpsCnt--;
            
            if(VpsCnt < 0)  break;
            idx++;
        }
        if(VpsCnt != 0 )     printf("NO\n");
        else    printf("YES\n");
    }
    return 0;
}