#include <stdio.h>
#include <stdlib.h>
#include <string.h>


//void ir_malloc(int *&pointerReg, int mallocSize) {
//    pointerReg = (int *) malloc(mallocSize);
//}

int *string__add(int *str1, int *str2) {
    int *ret = (int *) malloc(strlen((char *) str1) + strlen((char *) str2) + 4);
    strcpy((char *) ret, (char *) str1);
    strcat((char *) ret, (char *) str2);
    return ret;
}

int string__eq(int *str1, int *str2) {
    return strcmp((char *) str1, (char *) str2) == 0;
}

int string__neq(int *str1, int *str2) {
    return strcmp((char *) str1, (char *) str2) != 0;
}

int string__l(int *str1, int *str2) {
    return strcmp((char *) str1, (char *) str2) < 0;
}

int string__leq(int *str1, int *str2) {
    return strcmp((char *) str1, (char *) str2) <= 0;
}

int string__g(int *str1, int *str2) {
    return strcmp((char *) str1, (char *) str2) > 0;
}

int string__geq(int *str1, int *str2) {
    return strcmp((char *) str1, (char *) str2) >= 0;
}

void print(int *str) {
    printf("%s", (char*)str);
}

void println(int *str) {
    printf("%s\n", (char*)str);
}

void printInt(int num) {
    printf("%d", num);
}

void printlnInt(int num) {
    printf("%d\n", num);
}

int *getString() {
    int *readBuf = (int *) malloc(1024);
    scanf("%s", (char*)readBuf);
    return readBuf;
}

int getInt() {
    int ret;
    scanf("%d", &ret);
    return ret;
}

int *toString(int num) {
    int *numBuf = (int *) malloc(20);
    sprintf((char *) numBuf, "%d", num);
    return numBuf;
}

int string__length(int *str) {
    return strlen((const char *) (str));
}

int *string__substring(int left, int right, int *str) {
    int *ret = (int *) malloc(sizeof(char) * (right - left + 1));
    memcpy(ret, (char *)str + left, right - left);
    ret[right - left] = '\0';
    return ret;
}

int string__parseInt(int *str) {
    int ret;
    sscanf((const char *) str, "%d", &ret);
    return ret;
}

int string__ord(int pos, char *str) {
    return str[pos];
}

int array__size(int *para1) {
    return *(para1 - 1);
}

#define lg 2
#define len 4

int ir_new_array(int p, int i) {//de出来了。是因为不能用带引用的函数。之前那个ir_malloc也得改。
    if (i > ((*(int*)p)<<lg)) return 0;
    int sz = *(int*)(p + i);
    int sz4 = sz << lg;
    int ret;
    (int *&)ret = (int*)malloc(sz4 + len);
    ret += len;
    *(int*)(ret - len) = sz;
    for (int j = 0; j < sz4; j += len) {
        *(int *) (ret + j) = ir_new_array(p, i + len);
    }
    return ret;
}