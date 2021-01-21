#include "my_string.h"
#include <string.h>

String::String(const char*s) {
    if (s == nullptr) {
        this -> str = nullptr;
    } else {
        str = new char[strlen(s) + 1];
        strcpy(str, s);
    }
}

String::~String() {
    if (str != nullptr) {
        delete []str;
    }
}

bool String::operator!() {
    if (str == nullptr || strlen(str) == 0) {
        return true;
    }
    return false;
}
