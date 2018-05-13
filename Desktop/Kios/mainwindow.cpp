#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    SERVER = "http://localhost:8080/UServer/api/urban";
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_btnTicketComprar_clicked()
{
    int precio = ui->cmbTicketMonto->currentIndex();

    switch (precio) {
    case TRES:
        precio = 3;
        break;
    case DIEZ:
        precio = 10;
        break;
    case CINCUENTA:
        precio = 50;
        break;
    case CIEN:
        precio = 100;
        break;
    default:
        return;
    }

    QString json = tr("{\"codigo\": 0,\"verificacion\":\"\",\"emision\":\"\",\"devolucion\":\"\",\"valor\":%1,\"saldo\":%1}").arg(precio);
    QString path = "/buyticket";

    QString response = enviarPeticion_post(path, json);

    QJsonDocument jsd = QJsonDocument::fromJson(response.toLatin1());
    if (!jsd.isEmpty())
    {
        QJsonObject jso = jsd.object();

        ui->edtTicketCodigo->setText(QString::number(jso["codigo"].toDouble()));
        ui->edtTicketFecha->setText(jso["emision"].toString());
        ui->edtTicketSaldo->setText(QString::number(jso["valor"].toDouble()));
        ui->edtTicketSaldo->setText(QString::number(jso["saldo"].toDouble()));
    }
    else
        limpiarCampo(1);
}

void MainWindow::on_btnDevolver_clicked()
{
    int codigo = ui->edtDevolverCodigo->text().toInt();
    QString json = tr("{\"codigo\": %1,\"verificacion\":\"\",\"emision\":\"\",\"devolucion\":\"\",\"valor\":0.0,\"saldo\":0.0}").arg(codigo);
    QString path = "/devolucion";

    QString response = enviarPeticion_put(path, json);

    QJsonDocument jsd = QJsonDocument::fromJson(response.toLatin1());
    if (!jsd.isEmpty())
    {
        QJsonObject jso = jsd.object();

        if (!jso["estado"].isNull())
        {
            QMessageBox message(this);
            message.setText("Codigo de ticket no valido");
            message.exec();
            return;
        }

        ui->edtDevolverVerificacion->setText(QString::number(jso["verificacion"].toDouble()));
        ui->edtDevolverEmision->setText(jso["emision"].toString());
        ui->edtDevolverDevolucion->setText(jso["devolucion"].toString());
        ui->edtDevolverValor->setText(QString::number(jso["valor"].toDouble()));
        ui->edtDevolverSaldo->setText(QString::number(jso["saldo"].toDouble()));
    }
    else
        limpiarCampo(2);
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

    if (reply->error() == QNetworkReply::NoError)
    {
        QByteArray response = reply->readAll();
        return QString(response);

        qDebug() << "Success";
        delete reply;
    }
    else
    {
        qDebug() << "Failure ";
        delete reply;
    }
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

    if (reply->error() == QNetworkReply::NoError)
    {
        QByteArray response = reply->readAll();
        return QString(response);

        qDebug() << "Success";
        delete reply;
    }
    else
    {
        qDebug() << "Failure ";
        delete reply;
    }
}

void MainWindow::limpiarCampo(int tab)
{
    if (tab == 1)
    {
        ui->edtTicketCodigo->clear();
        ui->edtTicketFecha->clear();
        ui->edtTicketSaldo->clear();
        ui->edtTicketSaldo->clear();
    }
    else
    {
        ui->edtDevolverVerificacion->clear();
        ui->edtDevolverEmision->clear();
        ui->edtDevolverDevolucion->clear();
        ui->edtDevolverValor->clear();
        ui->edtDevolverSaldo->clear();
    }
}
