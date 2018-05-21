#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    SERVER = "http://localhost:8080/UServer/api/urban";
    seterEstacion();
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::seterEstacion()
{
    bool ok;
    QString estacion;
    QString text = QInputDialog::getText(this, tr("Configuración"),
                                          tr("Codigo de estación:"), QLineEdit::Normal,
                                          QDir::home().dirName(), &ok);
    if (ok && !text.isEmpty())
        estacion = enviarPeticion_get("/askIfStationExists", tr("{\"codigo\":%d,\"nombre\":null,\"latitud\":0.0,\"longitud\":0.0}").arg(text.toInt()));

    QJsonDocument jsd = QJsonDocument::fromJson(estacion.toLatin1());
    if (!jsd.isEmpty())
    {
        QString rutas = enviarPeticion_get("/getList_of_Routes_that_pass_through_a_Station", tr("{\"codigo\":%d,\"nombre\":null,\"latitud\":0.0,\"longitud\":0.0}").arg(text.toInt()));
        setterRutas(ruta);
    }
}

void MainWindow::setterRutas(QString json)
{
    QJsonDocument jsd = QJsonDocument::fromJson(json.toLatin1());
    if (!jsd.isEmpty())
    {

    }
}

QString MainWindow::enviarPeticion_post(QString path, QString json)
{
    QEventLoop loop;

    QNetworkAccessManager accessManager;
    QObject::connect(&accessManager, SIGNAL(finished(QNetworkReply*)),
                     &loop,
                     SLOT(quit())
                     );

    QNetworkRequest request(QUrl(SERVER + path));
    request.setRawHeader("Content-Type", "application/json");

    QNetworkReply *reply = accessManager.post(request, json.toUtf8());
    loop.exec();

    QString result = "{}";
    if (reply->error() == QNetworkReply::NoError)
        result = QString(reply->readAll());

    delete reply;
    return result;
}

QString MainWindow::enviarPeticion_put(QString path, QString json)
{
    QEventLoop loop;

    QNetworkAccessManager accessManager;
    QObject::connect(&accessManager, SIGNAL(finished(QNetworkReply*)),
                     &loop,
                     SLOT(quit())
                     );

    QNetworkRequest request(QUrl(SERVER + path));
    request.setRawHeader("Content-Type", "application/json");

    QNetworkReply *reply = accessManager.put(request,json.toUtf8());
    loop.exec();

    QString result = "{}";
    if (reply->error() == QNetworkReply::NoError)
        result = QString(reply->readAll());

    delete reply;
    return result;
}

QString MainWindow::enviarPeticion_get(QString path, QString json)
{
    QEventLoop loop;

    QNetworkAccessManager accessManager;
    QObject::connect(&accessManager, SIGNAL(finished(QNetworkReply*)),
                     &loop,
                     SLOT(quit())
                     );

    QNetworkRequest request(QUrl(SERVER + path));
    request.setRawHeader("Content-Type", "application/json");

    QNetworkReply *reply = accessManager.get(request, json.toUtf8());
    loop.exec();

    QString result = "{}";
    if (reply->error() == QNetworkReply::NoError)
        result = QString(reply->readAll());

    delete reply;
    return result;
}

void MainWindow::limpiarCampo()
{
    ui->edtTicket->clear();
    ui->cmbRutas->clear();
}
