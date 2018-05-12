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
#include <QtWidgets/QComboBox>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTabWidget>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QGridLayout *gridLayout;
    QTabWidget *tabWidget;
    QWidget *tabComprar;
    QGridLayout *gridLayout_3;
    QGroupBox *grpTicketDetalle;
    QGridLayout *gridLayout_2;
    QLabel *lblTicketCodigo;
    QLineEdit *edtTicketCodigo;
    QLabel *lblTicketVerificacion;
    QLineEdit *edtTicketVerificacion;
    QLabel *lblTicketFecha;
    QLineEdit *edtFecha;
    QLabel *lblTicketValor;
    QLineEdit *edtValor;
    QSpacerItem *verticalSpacer;
    QLabel *lblTicketMonto;
    QComboBox *cmbTicketMonto;
    QPushButton *btnTicketComprar;
    QWidget *tabDevolucion;
    QFormLayout *formLayout;
    QLabel *lblDevolverCodigo;
    QLabel *lblDevolverMonto;
    QLineEdit *edtDevolverCodigo;
    QHBoxLayout *layoutDevolucion;
    QLabel *lblDevolverQ;
    QLabel *lblDevolverMontoQ;
    QPushButton *btnDevolver;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(482, 319);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        gridLayout = new QGridLayout(centralWidget);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        tabWidget = new QTabWidget(centralWidget);
        tabWidget->setObjectName(QStringLiteral("tabWidget"));
        tabComprar = new QWidget();
        tabComprar->setObjectName(QStringLiteral("tabComprar"));
        gridLayout_3 = new QGridLayout(tabComprar);
        gridLayout_3->setSpacing(6);
        gridLayout_3->setContentsMargins(11, 11, 11, 11);
        gridLayout_3->setObjectName(QStringLiteral("gridLayout_3"));
        grpTicketDetalle = new QGroupBox(tabComprar);
        grpTicketDetalle->setObjectName(QStringLiteral("grpTicketDetalle"));
        gridLayout_2 = new QGridLayout(grpTicketDetalle);
        gridLayout_2->setSpacing(6);
        gridLayout_2->setContentsMargins(11, 11, 11, 11);
        gridLayout_2->setObjectName(QStringLiteral("gridLayout_2"));
        lblTicketCodigo = new QLabel(grpTicketDetalle);
        lblTicketCodigo->setObjectName(QStringLiteral("lblTicketCodigo"));

        gridLayout_2->addWidget(lblTicketCodigo, 0, 0, 1, 1);

        edtTicketCodigo = new QLineEdit(grpTicketDetalle);
        edtTicketCodigo->setObjectName(QStringLiteral("edtTicketCodigo"));
        edtTicketCodigo->setReadOnly(true);

        gridLayout_2->addWidget(edtTicketCodigo, 0, 1, 1, 1);

        lblTicketVerificacion = new QLabel(grpTicketDetalle);
        lblTicketVerificacion->setObjectName(QStringLiteral("lblTicketVerificacion"));

        gridLayout_2->addWidget(lblTicketVerificacion, 1, 0, 1, 1);

        edtTicketVerificacion = new QLineEdit(grpTicketDetalle);
        edtTicketVerificacion->setObjectName(QStringLiteral("edtTicketVerificacion"));
        edtTicketVerificacion->setReadOnly(true);

        gridLayout_2->addWidget(edtTicketVerificacion, 1, 1, 1, 1);

        lblTicketFecha = new QLabel(grpTicketDetalle);
        lblTicketFecha->setObjectName(QStringLiteral("lblTicketFecha"));

        gridLayout_2->addWidget(lblTicketFecha, 2, 0, 1, 1);

        edtFecha = new QLineEdit(grpTicketDetalle);
        edtFecha->setObjectName(QStringLiteral("edtFecha"));
        edtFecha->setReadOnly(true);

        gridLayout_2->addWidget(edtFecha, 2, 1, 1, 1);

        lblTicketValor = new QLabel(grpTicketDetalle);
        lblTicketValor->setObjectName(QStringLiteral("lblTicketValor"));

        gridLayout_2->addWidget(lblTicketValor, 3, 0, 1, 1);

        edtValor = new QLineEdit(grpTicketDetalle);
        edtValor->setObjectName(QStringLiteral("edtValor"));
        edtValor->setReadOnly(true);

        gridLayout_2->addWidget(edtValor, 3, 1, 1, 1);


        gridLayout_3->addWidget(grpTicketDetalle, 0, 1, 4, 1);

        verticalSpacer = new QSpacerItem(20, 82, QSizePolicy::Minimum, QSizePolicy::Expanding);

        gridLayout_3->addItem(verticalSpacer, 3, 0, 1, 1);

        lblTicketMonto = new QLabel(tabComprar);
        lblTicketMonto->setObjectName(QStringLiteral("lblTicketMonto"));

        gridLayout_3->addWidget(lblTicketMonto, 0, 0, 1, 1);

        cmbTicketMonto = new QComboBox(tabComprar);
        cmbTicketMonto->addItem(QString());
        cmbTicketMonto->addItem(QString());
        cmbTicketMonto->addItem(QString());
        cmbTicketMonto->addItem(QString());
        cmbTicketMonto->setObjectName(QStringLiteral("cmbTicketMonto"));

        gridLayout_3->addWidget(cmbTicketMonto, 1, 0, 1, 1);

        btnTicketComprar = new QPushButton(tabComprar);
        btnTicketComprar->setObjectName(QStringLiteral("btnTicketComprar"));

        gridLayout_3->addWidget(btnTicketComprar, 2, 0, 1, 1);

        tabWidget->addTab(tabComprar, QString());
        tabDevolucion = new QWidget();
        tabDevolucion->setObjectName(QStringLiteral("tabDevolucion"));
        formLayout = new QFormLayout(tabDevolucion);
        formLayout->setSpacing(6);
        formLayout->setContentsMargins(11, 11, 11, 11);
        formLayout->setObjectName(QStringLiteral("formLayout"));
        lblDevolverCodigo = new QLabel(tabDevolucion);
        lblDevolverCodigo->setObjectName(QStringLiteral("lblDevolverCodigo"));

        formLayout->setWidget(0, QFormLayout::LabelRole, lblDevolverCodigo);

        lblDevolverMonto = new QLabel(tabDevolucion);
        lblDevolverMonto->setObjectName(QStringLiteral("lblDevolverMonto"));

        formLayout->setWidget(0, QFormLayout::FieldRole, lblDevolverMonto);

        edtDevolverCodigo = new QLineEdit(tabDevolucion);
        edtDevolverCodigo->setObjectName(QStringLiteral("edtDevolverCodigo"));

        formLayout->setWidget(1, QFormLayout::LabelRole, edtDevolverCodigo);

        layoutDevolucion = new QHBoxLayout();
        layoutDevolucion->setSpacing(6);
        layoutDevolucion->setObjectName(QStringLiteral("layoutDevolucion"));
        lblDevolverQ = new QLabel(tabDevolucion);
        lblDevolverQ->setObjectName(QStringLiteral("lblDevolverQ"));
        lblDevolverQ->setMaximumSize(QSize(16777215, 16777215));
        lblDevolverQ->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);

        layoutDevolucion->addWidget(lblDevolverQ);

        lblDevolverMontoQ = new QLabel(tabDevolucion);
        lblDevolverMontoQ->setObjectName(QStringLiteral("lblDevolverMontoQ"));
        lblDevolverMontoQ->setAlignment(Qt::AlignCenter);

        layoutDevolucion->addWidget(lblDevolverMontoQ);


        formLayout->setLayout(1, QFormLayout::FieldRole, layoutDevolucion);

        btnDevolver = new QPushButton(tabDevolucion);
        btnDevolver->setObjectName(QStringLiteral("btnDevolver"));
        btnDevolver->setLayoutDirection(Qt::LeftToRight);

        formLayout->setWidget(2, QFormLayout::LabelRole, btnDevolver);

        tabWidget->addTab(tabDevolucion, QString());

        gridLayout->addWidget(tabWidget, 0, 0, 1, 1);

        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 482, 26));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        tabWidget->setCurrentIndex(0);


        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", nullptr));
        grpTicketDetalle->setTitle(QApplication::translate("MainWindow", "Ticket generado", nullptr));
        lblTicketCodigo->setText(QApplication::translate("MainWindow", "C\303\263digo", nullptr));
        lblTicketVerificacion->setText(QApplication::translate("MainWindow", "Verificaci\303\263n", nullptr));
        lblTicketFecha->setText(QApplication::translate("MainWindow", "Fecha emisi\303\263n", nullptr));
        lblTicketValor->setText(QApplication::translate("MainWindow", "Valor", nullptr));
        lblTicketMonto->setText(QApplication::translate("MainWindow", "Seleccoinar Monto", nullptr));
        cmbTicketMonto->setItemText(0, QApplication::translate("MainWindow", "Q. 3", nullptr));
        cmbTicketMonto->setItemText(1, QApplication::translate("MainWindow", "Q. 10", nullptr));
        cmbTicketMonto->setItemText(2, QApplication::translate("MainWindow", "Q. 50", nullptr));
        cmbTicketMonto->setItemText(3, QApplication::translate("MainWindow", "Q. 100", nullptr));

        btnTicketComprar->setText(QApplication::translate("MainWindow", "Comprar", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tabComprar), QApplication::translate("MainWindow", "Comprar", nullptr));
        lblDevolverCodigo->setText(QApplication::translate("MainWindow", "C\303\263digo", nullptr));
        lblDevolverMonto->setText(QApplication::translate("MainWindow", "Monto a devolver", nullptr));
        lblDevolverQ->setText(QApplication::translate("MainWindow", "Q.", nullptr));
        lblDevolverMontoQ->setText(QApplication::translate("MainWindow", "0.00", nullptr));
        btnDevolver->setText(QApplication::translate("MainWindow", "Registrar devoluci\303\263n", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tabDevolucion), QApplication::translate("MainWindow", "Devoluci\303\263n", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
