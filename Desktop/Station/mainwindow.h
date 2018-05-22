#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QtNetwork/QtNetwork>
#include <QInputDialog>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonArray>
#include <QDebug>

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
    void on_btnComprar_clicked();

private:
    Ui::MainWindow *ui;
    QString SERVER;
    int codStation;

    void seterStation();
    void setterRoutes(QString jsonRoutes);
    QString enviarPeticion_post(QString path, QString json);
    QString enviarPeticion_put(QString path, QString json);
};

#endif // MAINWINDOW_H
