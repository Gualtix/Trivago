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
    codEstacion = text.toInt();

    if (ok && !text.isEmpty())
    {
        QString json = tr("{\"codigo\":%1,\"nombre\":null,\"latitud\":0.0,\"longitud\":0.0}").arg(codEstacion);
        QString path = "/askIfStationExists";
        estacion = enviarPeticion_post(path, json);
    }

    QJsonDocument jsd = QJsonDocument::fromJson(estacion.toLatin1());
    if (!jsd.isEmpty())
    {
        QString json = tr("{\"codigo\":%1,\"nombre\":null,\"latitud\":0.0,\"longitud\":0.0}").arg(codEstacion);
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
            QString cod = QString::number(jso["codigo"].toInt());
            QString nombre = jso["nombre"].toString();

            int y = ui->tblRoutes->rowCount();
            ui->tblRoutes->insertRow(y);
            ui->tblRoutes->setItem(y, 0, new QTableWidgetItem(cod));
            ui->tblRoutes->setItem(y, 1, new QTableWidgetItem(nombre));
        }
    }
}

void MainWindow::on_pushButton_clicked()
{
    int y = ui->tblRoutes->currentRow();
    QString ticket = ui->edtTicket->text();
    QString ruta = ui->tblRoutes->item(y, 0)->text();

    QString jsonCompra = tr("{\"ruta\":%1,\"ticket\":%2,\"estacion\":%3}").arg(ruta, ticket, QString::number(codEstacion));

    jsonCompra = enviarPeticion_post("/buy_urban_ride", jsonCompra);
    QJsonDocument jsd = QJsonDocument::fromJson(jsonCompra.toLatin1());
    if (!jsd.isEmpty())
    {
        QJsonObject jso = jsd.object();
        if (jso["le_alcanza"].toBool())
        {
            QPixmap pixmap(":/res/ok.png");
            ui->lblState->setPixmap(pixmap);
        }
    }
    else
    {
        QMessageBox msg(this);
        msg.setText("Ingrese un ticket válido");
        msg.exec();
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
    ui->tblRoutes->setRowCount(0);
    ui->tblRoutes->clear();
}
