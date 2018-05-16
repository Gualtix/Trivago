#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
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
    QString rutas;
    QString text = QInputDialog::getText(this, tr("Configuración"),
                                          tr("Codigo de estación:"), QLineEdit::Normal,
                                          QDir::home().dirName(), &ok);
    if (ok && !text.isEmpty())
        rutas = enviarPeticion_get("/rutas", tr("{\"estacion\":%1}").arg(text));

    QJsonDocument jsd = QJsonDocument::fromJson(rutas.toLatin1());
    if (!jsd.isEmpty())
    {
        QJsonObject jso = jsd.object();
        /* SETER cmbRutas */
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
