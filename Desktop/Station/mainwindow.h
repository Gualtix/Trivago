#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QtNetwork>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonValue>
#include <QMessageBox>
#include <QInputDialog>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private:
    QString SERVER;
    Ui::MainWindow *ui;

    void seterEstacion();
    QString enviarPeticion_post(QString path, QString json);
    QString enviarPeticion_put(QString path, QString json);
    QString enviarPeticion_get(QString path, QString json);
    void setterRutas(QString json);
    void limpiarCampo();
};

#endif // MAINWINDOW_H
