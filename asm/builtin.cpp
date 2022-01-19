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

int string__ord(int pos, int *str) {
    return str[pos];
}

int array__size(int *para1) {
    return *(para1 - 1);
}

void ifake(int *p, int &ret, int i) {
    if (i > 4 * (*p)) return;
    int sz = *(p + (i >> 2));
    int sz4 = 4 * sz;
    ir_malloc((int *&) (ret), sz4 + 4);
    ret += 4;
    *((int *) ret - 1) = sz;
    for (int j = 0; j < sz4; j += 4)
        ifake(p, *((int *) ret + (j >> 2)), i + 4);
}

void ir_new_array(int *para1, int &para2, int para3) {
    ifake(para1, para2, para3);
}
