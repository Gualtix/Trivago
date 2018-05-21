#-------------------------------------------------
#
# Project created by QtCreator 2018-05-10T11:54:49
#
#-------------------------------------------------

QT       += core gui network

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Estacion
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    TAD/tadrow.cpp

HEADERS  += mainwindow.h \
    DoubleList/doublenode.h \
    DoubleList/doublelist.h \
    TAD/tadrow.h

FORMS    += mainwindow.ui

DISTFILES += \
    .gitignore

RESOURCES += \
    res.qrc
