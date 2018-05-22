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
    {
        QString json = tr("{\"codigo\":%1,\"nombre\":null,\"latitud\":0.0,\"longitud\":0.0}").arg(text.toInt());
        QString path = "/askIfStationExists";
        estacion = enviarPeticion_post(path, json);
    }

    QJsonDocument jsd = QJsonDocument::fromJson(estacion.toLatin1());
    if (!jsd.isEmpty())
    {
        QString json = tr("{\"codigo\":%1,\"nombre\":null,\"latitud\":0.0,\"longitud\":0.0}").arg(text.toInt());
        QString path = "/getList_of_Routes_that_pass_through_a_Station";
        QString rutas = enviarPeticion_post(path, json);
        setterRutas(rutas);
    }
}

void MainWindow::setterRutas(QString json)
{
    QJsonDocument jsd = QJsonDocument::fromJson(json.toLatin1());
    if (!jsd.isEmpty())
    {
        QJsonArray jsa = jsd.array();
        for (int i = 0; i < jsa.size(); i++)
        {
            QJsonObject jso = jsa.at(i).toObject();
            QString item = QString::number(jso["codigo"].toInt());
            ui->comboBox->addItem(item);
        }
    }
}

void MainWindow::on_pushButton_clicked()
{
    QString ticket = ui->edtTicket->text();
    QString ruta = ui->comboBox->currentText();

    QString jsonTicket = tr("{\"codigo\":%d,\"verificacion\":null,\"emision\":null,\"devolucion\":null,\"valor\":0,\"saldo\":0}").arg(ticket);
    QString jsonRuta = tr("{\"ruta\":%d,\"ticket\":%d}").arg(ruta, ticket);

    jsonTicket = enviarPeticion_post("/verificar", ticket);
    if (!QJsonDocument::fromJson(jsonTicket.toLatin1()).isEmpty())
    {
        jsonRuta = enviarPeticion_put("/comprar", ruta);
        if (!QJsonDocument::fromJson(jsonRuta.toLatin1()).isEmpty())
            qDebug() << "Compra de existoso";
        else
            qDebug() << "Compra en conflicto";
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

    QNetworkReply *reply = accessManager.post(request, json.toUtf8());
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
    ui->comboBox->clear();
}
