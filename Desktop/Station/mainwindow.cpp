#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    SERVER = "http://localhost:8080/UServer/api/urban";
    seterStation();
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_btnComprar_clicked()
{
    int codTicket = ui->edtTicket->text().toInt();
    int codRoute = ui->cmbRutas->itemData(ui->cmbRutas->currentIndex()).toInt();
    QString route = ui->cmbRutas->currentText();

    QString jsonBuy = tr("{\"codigo_ruta\":%1,\"ruta\":\"%2\",\"ticket\":%3,\"estacion\":%4}").arg(QString::number(codRoute), route, QString::number(codTicket), QString::number(codStation));

    jsonBuy = enviarPeticion_put("/buy_urban_ride", jsonBuy);
    QJsonDocument jsd = QJsonDocument::fromJson(jsonBuy.toLatin1());

    QJsonObject jso = jsd.object();
    if (!jso.isEmpty())
    {
        if (jso["le_alcanza"].toBool())
            ui->lblMensaje->setText("Compra realizada");
        else
            ui->lblMensaje->setText("Saldo insuficiente");
    }
    else
        ui->lblMensaje->setText("Ticket inválido");
}

void MainWindow::seterStation()
{
    bool ok;
    QString text = QInputDialog::getText(
                this,
                "Configuración de estación",
                "Código de estación",
                QLineEdit::Normal,
                "0",
                &ok);

    QString jsonStation;

    if (ok && !text.isEmpty())
    {
        codStation = text.toInt();
        jsonStation = tr("{\"codigo\":%1,\"nombre\":null,\"latitud\":0.0,\"longitud\":0.0}").arg(codStation);
        QString path = "/askIfStationExists";

        jsonStation = enviarPeticion_post(path, jsonStation);
    }

    QJsonDocument jsd = QJsonDocument::fromJson(jsonStation.toLatin1());
    if (!jsd.object().isEmpty())
    {
        QString path = "/getList_of_Routes_that_pass_through_a_Station";
        QString jsonRoutes = enviarPeticion_post(path, jsonStation);
        setterRoutes(jsonRoutes);
    }
}

void MainWindow::setterRoutes(QString jsonRoutes)
{
    QJsonDocument jsd = QJsonDocument::fromJson(jsonRoutes.toLatin1());
    QJsonArray jsa = jsd.array();
    if (!jsa.isEmpty())
    {
        for (int i = 0; i < jsa.size(); i++)
        {
            QJsonObject jso = jsa.at(i).toObject();
            int codRoute = jso["codigo"].toInt();
            QString nombre = jso["nombre"].toString();

            ui->cmbRutas->addItem(nombre, codRoute);
        }
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
