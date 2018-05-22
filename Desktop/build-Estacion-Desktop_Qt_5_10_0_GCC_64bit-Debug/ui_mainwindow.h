/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.10.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTableWidget>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QGridLayout *gridLayout_2;
    QGridLayout *gridLayout;
    QPushButton *pushButton;
    QLineEdit *edtTicket;
    QLabel *lblRutas;
    QLabel *lblTicket;
    QTableWidget *tblRoutes;
    QLabel *lblState;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(519, 228);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        gridLayout_2 = new QGridLayout(centralWidget);
        gridLayout_2->setSpacing(6);
        gridLayout_2->setContentsMargins(11, 11, 11, 11);
        gridLayout_2->setObjectName(QStringLiteral("gridLayout_2"));
        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        pushButton = new QPushButton(centralWidget);
        pushButton->setObjectName(QStringLiteral("pushButton"));

        gridLayout->addWidget(pushButton, 1, 0, 1, 2);

        edtTicket = new QLineEdit(centralWidget);
        edtTicket->setObjectName(QStringLiteral("edtTicket"));
        edtTicket->setMinimumSize(QSize(100, 0));
        edtTicket->setMaximumSize(QSize(100, 16777215));

        gridLayout->addWidget(edtTicket, 0, 1, 1, 1);

        lblRutas = new QLabel(centralWidget);
        lblRutas->setObjectName(QStringLiteral("lblRutas"));
        lblRutas->setMinimumSize(QSize(100, 0));
        lblRutas->setMaximumSize(QSize(100, 16777215));
        lblRutas->setAlignment(Qt::AlignCenter);
        lblRutas->setWordWrap(true);

        gridLayout->addWidget(lblRutas, 0, 2, 1, 1);

        lblTicket = new QLabel(centralWidget);
        lblTicket->setObjectName(QStringLiteral("lblTicket"));
        lblTicket->setMinimumSize(QSize(75, 0));
        lblTicket->setMaximumSize(QSize(100, 16777215));

        gridLayout->addWidget(lblTicket, 0, 0, 1, 1);

        tblRoutes = new QTableWidget(centralWidget);
        if (tblRoutes->columnCount() < 2)
            tblRoutes->setColumnCount(2);
        QTableWidgetItem *__qtablewidgetitem = new QTableWidgetItem();
        tblRoutes->setHorizontalHeaderItem(0, __qtablewidgetitem);
        QTableWidgetItem *__qtablewidgetitem1 = new QTableWidgetItem();
        tblRoutes->setHorizontalHeaderItem(1, __qtablewidgetitem1);
        tblRoutes->setObjectName(QStringLiteral("tblRoutes"));
        tblRoutes->horizontalHeader()->setDefaultSectionSize(100);

        gridLayout->addWidget(tblRoutes, 0, 3, 2, 1);

        lblState = new QLabel(centralWidget);
        lblState->setObjectName(QStringLiteral("lblState"));
        lblState->setPixmap(QPixmap(QString::fromUtf8(":/res/noOK.png")));
        lblState->setAlignment(Qt::AlignCenter);

        gridLayout->addWidget(lblState, 1, 2, 1, 1);


        gridLayout_2->addLayout(gridLayout, 0, 0, 1, 1);

        MainWindow->setCentralWidget(centralWidget);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", nullptr));
        pushButton->setText(QApplication::translate("MainWindow", "Inicar Ruta", nullptr));
        lblRutas->setText(QApplication::translate("MainWindow", "<html><head/><body><p>Rutas disponibles</p></body></html>", nullptr));
        lblTicket->setText(QApplication::translate("MainWindow", "<html><head/><body><p align=\"center\">Ticket</p></body></html>", nullptr));
        QTableWidgetItem *___qtablewidgetitem = tblRoutes->horizontalHeaderItem(0);
        ___qtablewidgetitem->setText(QApplication::translate("MainWindow", "Cod", nullptr));
        QTableWidgetItem *___qtablewidgetitem1 = tblRoutes->horizontalHeaderItem(1);
        ___qtablewidgetitem1->setText(QApplication::translate("MainWindow", "Nombre", nullptr));
        lblState->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
