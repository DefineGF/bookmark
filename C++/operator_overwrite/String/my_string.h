#ifndef MY_STRING_H_
#define MY_STRING_H_
class String {
    public:
        String(const char *s=nullptr);
        ~String();
        bool operator!();
    private:
        char *str;
};
#endif
