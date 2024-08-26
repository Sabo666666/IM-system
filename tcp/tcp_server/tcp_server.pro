# 项目名称
TEMPLATE = app
TARGET = tcp_example
CONFIG += console c++11
CONFIG -= app_bundle

# 指定使用的Qt模块
QT += core network

# 指定源文件
SOURCES += \
    tcp_lib.cpp \
    tcp_server.cpp

# 指定头文件
HEADERS += \
    tcp_lib.h
