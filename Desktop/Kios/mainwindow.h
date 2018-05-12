#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QtNetwork>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonValue>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_btnTicketComprar_clicked();

    void on_btnDevolver_clicked();

private:
    Ui::MainWindow *ui;

    QString SERVER;
    QString APPLICATION;
    QString PATH;

    enum
    {
        TRES, DIEZ, CINCUENTA, CIEN
    };
    
    QString enviarPeticion(QString string);
};

#endif // MAINWINDOW_H
