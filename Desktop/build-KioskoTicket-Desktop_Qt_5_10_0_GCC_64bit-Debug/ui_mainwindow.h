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
#include <QtWidgets/QVBoxLayout>
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
    QLabel *lblTicketValor;
    QLineEdit *edtTicketCodigo;
    QLineEdit *edtTicketFecha;
    QLabel *lblTicketCodigo;
    QLineEdit *edtTicketValor;
    QLineEdit *edtTicketSaldo;
    QLabel *lblTicketFecha;
    QLabel *lblTicketSaldo;
    QSpacerItem *verticalSpacer;
    QLabel *lblTicketMonto;
    QComboBox *cmbTicketMonto;
    QPushButton *btnTicketComprar;
    QWidget *tabDevolucion;
    QGridLayout *gridLayout_4;
    QLabel *lblDevolverCodigo;
    QVBoxLayout *verticalLayout;
    QLabel *lblDevolverMonto;
    QGroupBox *groupBox;
    QFormLayout *formLayout;
    QLabel *lblDevolverVerificacion;
    QLineEdit *edtDevolverVerificacion;
    QLabel *lblDevolverEmision;
    QLineEdit *edtDevolverEmision;
    QLabel *lblDevolverDevulucion;
    QLineEdit *edtDevolverDevolucion;
    QLabel *lblDevolverValor;
    QLineEdit *edtDevolverValor;
    QLabel *lblDevolverSaldo;
    QLineEdit *edtDevolverSaldo;
    QLineEdit *edtDevolverCodigo;
    QPushButton *btnDevolver;
    QSpacerItem *verticalSpacer_2;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(483, 385);
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
        lblTicketValor = new QLabel(grpTicketDetalle);
        lblTicketValor->setObjectName(QStringLiteral("lblTicketValor"));

        gridLayout_2->addWidget(lblTicketValor, 2, 0, 1, 1);

        edtTicketCodigo = new QLineEdit(grpTicketDetalle);
        edtTicketCodigo->setObjectName(QStringLiteral("edtTicketCodigo"));
        edtTicketCodigo->setReadOnly(true);

        gridLayout_2->addWidget(edtTicketCodigo, 0, 1, 1, 1);

        edtTicketFecha = new QLineEdit(grpTicketDetalle);
        edtTicketFecha->setObjectName(QStringLiteral("edtTicketFecha"));
        edtTicketFecha->setReadOnly(true);

        gridLayout_2->addWidget(edtTicketFecha, 1, 1, 1, 1);

        lblTicketCodigo = new QLabel(grpTicketDetalle);
        lblTicketCodigo->setObjectName(QStringLiteral("lblTicketCodigo"));

        gridLayout_2->addWidget(lblTicketCodigo, 0, 0, 1, 1);

        edtTicketValor = new QLineEdit(grpTicketDetalle);
        edtTicketValor->setObjectName(QStringLiteral("edtTicketValor"));
        edtTicketValor->setReadOnly(true);

        gridLayout_2->addWidget(edtTicketValor, 2, 1, 1, 1);

        edtTicketSaldo = new QLineEdit(grpTicketDetalle);
        edtTicketSaldo->setObjectName(QStringLiteral("edtTicketSaldo"));

        gridLayout_2->addWidget(edtTicketSaldo, 3, 1, 1, 1);

        lblTicketFecha = new QLabel(grpTicketDetalle);
        lblTicketFecha->setObjectName(QStringLiteral("lblTicketFecha"));

        gridLayout_2->addWidget(lblTicketFecha, 1, 0, 1, 1);

        lblTicketSaldo = new QLabel(grpTicketDetalle);
        lblTicketSaldo->setObjectName(QStringLiteral("lblTicketSaldo"));

        gridLayout_2->addWidget(lblTicketSaldo, 3, 0, 1, 1);


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
        gridLayout_4 = new QGridLayout(tabDevolucion);
        gridLayout_4->setSpacing(6);
        gridLayout_4->setContentsMargins(11, 11, 11, 11);
        gridLayout_4->setObjectName(QStringLiteral("gridLayout_4"));
        lblDevolverCodigo = new QLabel(tabDevolucion);
        lblDevolverCodigo->setObjectName(QStringLiteral("lblDevolverCodigo"));

        gridLayout_4->addWidget(lblDevolverCodigo, 0, 0, 1, 1);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        lblDevolverMonto = new QLabel(tabDevolucion);
        lblDevolverMonto->setObjectName(QStringLiteral("lblDevolverMonto"));

        verticalLayout->addWidget(lblDevolverMonto);

        groupBox = new QGroupBox(tabDevolucion);
        groupBox->setObjectName(QStringLiteral("groupBox"));
        formLayout = new QFormLayout(groupBox);
        formLayout->setSpacing(6);
        formLayout->setContentsMargins(11, 11, 11, 11);
        formLayout->setObjectName(QStringLiteral("formLayout"));
        lblDevolverVerificacion = new QLabel(groupBox);
        lblDevolverVerificacion->setObjectName(QStringLiteral("lblDevolverVerificacion"));

        formLayout->setWidget(0, QFormLayout::LabelRole, lblDevolverVerificacion);

        edtDevolverVerificacion = new QLineEdit(groupBox);
        edtDevolverVerificacion->setObjectName(QStringLiteral("edtDevolverVerificacion"));
        edtDevolverVerificacion->setReadOnly(true);

        formLayout->setWidget(0, QFormLayout::FieldRole, edtDevolverVerificacion);

        lblDevolverEmision = new QLabel(groupBox);
        lblDevolverEmision->setObjectName(QStringLiteral("lblDevolverEmision"));

        formLayout->setWidget(1, QFormLayout::LabelRole, lblDevolverEmision);

        edtDevolverEmision = new QLineEdit(groupBox);
        edtDevolverEmision->setObjectName(QStringLiteral("edtDevolverEmision"));
        edtDevolverEmision->setReadOnly(true);

        formLayout->setWidget(1, QFormLayout::FieldRole, edtDevolverEmision);

        lblDevolverDevulucion = new QLabel(groupBox);
        lblDevolverDevulucion->setObjectName(QStringLiteral("lblDevolverDevulucion"));

        formLayout->setWidget(2, QFormLayout::LabelRole, lblDevolverDevulucion);

        edtDevolverDevolucion = new QLineEdit(groupBox);
        edtDevolverDevolucion->setObjectName(QStringLiteral("edtDevolverDevolucion"));
        edtDevolverDevolucion->setReadOnly(true);

        formLayout->setWidget(2, QFormLayout::FieldRole, edtDevolverDevolucion);

        lblDevolverValor = new QLabel(groupBox);
        lblDevolverValor->setObjectName(QStringLiteral("lblDevolverValor"));

        formLayout->setWidget(3, QFormLayout::LabelRole, lblDevolverValor);

        edtDevolverValor = new QLineEdit(groupBox);
        edtDevolverValor->setObjectName(QStringLiteral("edtDevolverValor"));
        edtDevolverValor->setReadOnly(true);

        formLayout->setWidget(3, QFormLayout::FieldRole, edtDevolverValor);

        lblDevolverSaldo = new QLabel(groupBox);
        lblDevolverSaldo->setObjectName(QStringLiteral("lblDevolverSaldo"));

        formLayout->setWidget(4, QFormLayout::LabelRole, lblDevolverSaldo);

        edtDevolverSaldo = new QLineEdit(groupBox);
        edtDevolverSaldo->setObjectName(QStringLiteral("edtDevolverSaldo"));
        edtDevolverSaldo->setReadOnly(true);

        formLayout->setWidget(4, QFormLayout::FieldRole, edtDevolverSaldo);


        verticalLayout->addWidget(groupBox);


        gridLayout_4->addLayout(verticalLayout, 0, 2, 4, 1);

        edtDevolverCodigo = new QLineEdit(tabDevolucion);
        edtDevolverCodigo->setObjectName(QStringLiteral("edtDevolverCodigo"));

        gridLayout_4->addWidget(edtDevolverCodigo, 1, 0, 1, 2);

        btnDevolver = new QPushButton(tabDevolucion);
        btnDevolver->setObjectName(QStringLiteral("btnDevolver"));
        btnDevolver->setLayoutDirection(Qt::LeftToRight);

        gridLayout_4->addWidget(btnDevolver, 2, 0, 1, 2);

        verticalSpacer_2 = new QSpacerItem(20, 107, QSizePolicy::Minimum, QSizePolicy::Expanding);

        gridLayout_4->addItem(verticalSpacer_2, 3, 1, 1, 1);

        tabWidget->addTab(tabDevolucion, QString());

        gridLayout->addWidget(tabWidget, 0, 0, 1, 1);

        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 483, 22));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        tabWidget->setCurrentIndex(1);


        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", nullptr));
        grpTicketDetalle->setTitle(QApplication::translate("MainWindow", "Ticket generado", nullptr));
        lblTicketValor->setText(QApplication::translate("MainWindow", "Valor", nullptr));
        lblTicketCodigo->setText(QApplication::translate("MainWindow", "C\303\263digo", nullptr));
        lblTicketFecha->setText(QApplication::translate("MainWindow", "Fecha emisi\303\263n", nullptr));
        lblTicketSaldo->setText(QApplication::translate("MainWindow", "Saldo", nullptr));
        lblTicketMonto->setText(QApplication::translate("MainWindow", "Seleccoinar Monto", nullptr));
        cmbTicketMonto->setItemText(0, QApplication::translate("MainWindow", "Q. 3", nullptr));
        cmbTicketMonto->setItemText(1, QApplication::translate("MainWindow", "Q. 10", nullptr));
        cmbTicketMonto->setItemText(2, QApplication::translate("MainWindow", "Q. 50", nullptr));
        cmbTicketMonto->setItemText(3, QApplication::translate("MainWindow", "Q. 100", nullptr));

        btnTicketComprar->setText(QApplication::translate("MainWindow", "Comprar", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tabComprar), QApplication::translate("MainWindow", "Comprar", nullptr));
        lblDevolverCodigo->setText(QApplication::translate("MainWindow", "C\303\263digo", nullptr));
        lblDevolverMonto->setText(QApplication::translate("MainWindow", "Monto a devolver", nullptr));
        groupBox->setTitle(QApplication::translate("MainWindow", "GroupBox", nullptr));
        lblDevolverVerificacion->setText(QApplication::translate("MainWindow", "Verificacion", nullptr));
        lblDevolverEmision->setText(QApplication::translate("MainWindow", "Emision", nullptr));
        lblDevolverDevulucion->setText(QApplication::translate("MainWindow", "Devolucion", nullptr));
        lblDevolverValor->setText(QApplication::translate("MainWindow", "Valor", nullptr));
        lblDevolverSaldo->setText(QApplication::translate("MainWindow", "Saldo", nullptr));
        btnDevolver->setText(QApplication::translate("MainWindow", "Registrar devoluci\303\263n", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tabDevolucion), QApplication::translate("MainWindow", "Devoluci\303\263n", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
