#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    SERVER = "http://localhost:8080/UServer/api/urban";
    APPLICATION = "";
    PATH = "/buyticket";
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_btnTicketComprar_clicked()
{
    int codigo = ui->cmbTicketMonto->currentIndex();

    switch (codigo) {
    case TRES:
        codigo = 3;
        break;
    case DIEZ:
        codigo = 10;
        break;
    case CINCUENTA:
        codigo = 50;
        break;
    case CIEN:
        codigo = 100;
        break;
    default:
        return;
    }

    QByteArray response = enviarPeticion(QString::number(codigo));

   QJsonDocument jsd = QJsonDocument::fromJson(response);
   QJsonObject jso = jsd.object();

   ui->edtTicketCodigo->setText(jso["codigo"].toString());
   ui->edtFecha->setText(jso["emision"].toString());
   ui->edtValor->setText(QString::number(jso["valor"].toDouble()));
}

void MainWindow::on_btnDevolver_clicked()
{
    int codigo = ui->edtDevolverCodigo->text().toInt();
}

QByteArray MainWindow::enviarPeticion(QString string)
{
    QString json = tr("{\"codigo\": 0,\"verificacion\":\"\",\"emision\":\"\",\"devolucion\":\"\",\"valor\":%1,\"saldo\":%1}").arg(string);

    QEventLoop loop;

    QNetworkAccessManager accessManager;
    QObject::connect(&accessManager, SIGNAL(finished(QNetworkReply*)),
                     &loop,
                     SLOT(quit())
                     );

    QNetworkRequest request(QUrl(SERVER + PATH));
    request.setRawHeader("Content-Type", "application/json");

    QNetworkReply *reply = accessManager.post(request, json.toUtf8());
    loop.exec();

    if (reply->error() == QNetworkReply::NoError)
    {
        QByteArray response = reply->readAll();
        return response;

        qDebug() << "Success";
        delete reply;
    }
    else
    {
        qDebug() << "Failure ";
        delete reply;
    }
}
